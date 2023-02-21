package com.cat.miaosha.service.impl;

import com.cat.miaosha.common.contants.ResultStatus;
import com.cat.miaosha.common.vo.OrderVO;
import com.cat.miaosha.dao.OrderDOMapper;
import com.cat.miaosha.dao.SequenceDOMapper;
import com.cat.miaosha.entity.*;
import com.cat.miaosha.excption.BusinessException;
import com.cat.miaosha.service.ItemService;
import com.cat.miaosha.service.OrderService;
import com.cat.miaosha.service.PromoService;
import com.cat.miaosha.service.UserService;
import com.cat.miaosha.service.provider.TransactionProducer;
import com.cat.miaosha.utils.BeanUtils;
import com.cat.miaosha.utils.JsonUtils;
import com.cat.miaosha.utils.RedisUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * @author Mr.xin
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private static String ITEM_PREFIX = "item_";
    private static String STOCK_PREFIX = "stock_";
    private static Cache<String, Boolean> cache;
    private static String TX_ORDER_TOPIC = "tx_order";
    private static String TX_ORDER_TAG = "order_tag";

    @PostConstruct
    private void init() {
        cache = CacheBuilder.newBuilder()
                // 设置初始容量为50
                .initialCapacity(1000)
                // 50byte * 10000 = 500,000 -> 50KB
                .maximumSize(10000)
                .expireAfterAccess(30L, TimeUnit.MINUTES)
                .build();
    }

    @Resource
    private SequenceDOMapper sequenceDOMapper;

    @Resource
    private ItemService itemService;

    @Resource
    private UserService userService;

    @Resource
    private OrderDOMapper orderDOMapper;

    @Resource
    private PromoService promoService;
    @Resource
    private TransactionProducer producer;

    @Override
    public String createOrder(Long userId, Long itemId, Long promoId, Integer amount) throws BusinessException, ExecutionException {
        //1.校验下单状态,下单的商品是否存在，用户是否合法，购买数量是否正确
        ItemDO itemDo = null;
        itemDo = RedisUtils.get(ITEM_PREFIX + itemId);
        if (itemDo == null) {
            itemDo = itemService.queryGoods(itemId);
            RedisUtils.set(ITEM_PREFIX + itemId, itemDo, 1, TimeUnit.HOURS);
        }

        if (itemDo == null) {
            throw new BusinessException(ResultStatus.GOODS_NOT_EXIST);
        }

        UserDO userDO = userService.getUserById(userId);
        if (userDO == null) {
            throw new BusinessException(ResultStatus.USER_NOT_FOUND);
        }
        if (amount <= 0 || amount > 99) {
            throw new BusinessException(ResultStatus.QUANTITY_ERROR);
        }

        //校验活动信息
        Date now = new Date(System.currentTimeMillis());
        PromoDO promoActivity = null;
        if (promoId != null) {
            // TODO: 2023/1/13 直接将活动缓存在内存中,秒杀活动常驻内存 
            promoActivity = promoService.getPromoByItemId(itemId);
            if (promoActivity == null) {
                throw new BusinessException(ResultStatus.ACTIVITY_NOT_EXIST);
            } else if (now.before(promoActivity.getStartDate())) {
                throw new BusinessException(ResultStatus.ACTIVITY_NOT_STARTED);
            } else if (now.after(promoActivity.getEndDate())) {
                throw new BusinessException(ResultStatus.ACTIVITY_HAS_ENDED);
            }
        }

        // 先查本地缓存商品是否有库存
        // 穿透型缓存
        if (!cache.get(STOCK_PREFIX + itemId, () -> {
            Integer count = RedisUtils.get(STOCK_PREFIX + itemId);
            System.out.println(count);
            if (count == null) {
                return false;
            }
            return count > 0;
        })) {
            throw new BusinessException(ResultStatus.STOCK_NOT_ENOUGH);
        }
        // 预扣减
        Long stock = RedisUtils.decr(STOCK_PREFIX + itemId, amount);
        if (stock < 0) {
            // 反向补偿
            RedisUtils.incr(STOCK_PREFIX + itemId, amount);
            Integer count = RedisUtils.get(STOCK_PREFIX + itemId);
            // 判断库存是否为0
            if (count == 0) {
                cache.put(STOCK_PREFIX + itemId, false);
            }
            throw new BusinessException(ResultStatus.STOCK_NOT_ENOUGH);
        }

        // 创建订单
        OrderDO orderDO = new OrderDO();
        orderDO.setUserId(userId);
        orderDO.setItemId(itemId);
        orderDO.setAmount(amount);
        if (promoId != null) {
            // 促销价格
            orderDO.setItemPrice(promoActivity.getPromoItemPrice());
        } else {
            // 日常价格
            orderDO.setItemPrice(itemDo.getPrice());
        }
        orderDO.setPromoId(promoId);
        // fixme 抽象工具类
        orderDO.setOrderPrice((new BigDecimal(orderDO.getItemPrice()).multiply(new BigDecimal(amount))).doubleValue());
        //生成交易流水号,订单号
        orderDO.setId(generateOrderNo());

        // 记录库存流水
        String stockLogId = itemService.initStockLog(itemId,amount);

        // 发送消息
        try {
            Map<String, Object> msg = new HashMap<>(4);
            msg.put("itemId",itemId);
            msg.put("amount",amount);
            msg.put("stockLogId",stockLogId);
            msg.put("orderId",orderDO.getId());
            Message message = new Message();
            message.setTopic(TX_ORDER_TOPIC);
            message.setTags(TX_ORDER_TAG);
            message.setBody(JsonUtils.objectToJson(msg).getBytes(StandardCharsets.UTF_8));

            HashMap<String, Object> args = new HashMap<>(2);
            args.put("order",orderDO);
            args.put("stockLogId",stockLogId);
            producer.sendMessage(message,args);
        } catch (MQClientException e) {
            log.error("errorMessage:{}    method:{}",e.getErrorMessage(),Thread.currentThread().getStackTrace()[1].getMethodName());
            throw new BusinessException(ResultStatus.CREATE_ORDER_FAIL);
        }
        return orderDO.getId();
//        boolean result = itemService.decreaseStock(itemId, amount);
//        if (!result) {
//            // 可能是因为库存不足，也可能是乐观锁扣除失败
//
//        }


//        //3.订单入库
//
//        orderDOMapper.insertSelective(orderDO);
//
//        //加上商品的销量
//        itemService.increaseSales(itemId, amount);
//        //4.返回前端
//        OrderVO orderVO = new OrderVO();
//        BeanUtils.copyProperties(orderDO, orderVO);
//        return orderVO;
    }


    // FIXME: 2023/1/12 抽象成工具类
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateOrderNo() {
        //订单号有16位
        StringBuilder stringBuilder = new StringBuilder();
        //前8位为时间信息，年月日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowDate);

        //中间6位为自增序列
        //获取当前sequence
        int sequence = 0;
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6 - sequenceStr.length(); i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);

        //最后2位为分库分表位,暂时写死
        stringBuilder.append("00");

        return stringBuilder.toString();
    }

    @Override
    public int getOrderStatus(String orderId) {
        return orderDOMapper.selectOrderStatusById(orderId);
    }
}
