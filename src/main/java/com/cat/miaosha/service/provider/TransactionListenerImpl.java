package com.cat.miaosha.service.provider;

import com.cat.miaosha.dao.OrderDOMapper;
import com.cat.miaosha.dao.StockLogDOMapper;
import com.cat.miaosha.entity.OrderDO;
import com.cat.miaosha.entity.StockLogDO;
import com.cat.miaosha.utils.JsonUtils;
import com.cat.miaosha.utils.SpringContextUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Map;

/**
 * @author Mr.xin
 */
public class TransactionListenerImpl implements TransactionListener {


    private final OrderDOMapper orderDOMapper = SpringContextUtils.getBean("orderDOMapper", OrderDOMapper.class);
    private final StockLogDOMapper logDOMapper = SpringContextUtils.getBean("stockLogDOMapper", StockLogDOMapper.class);

    // 执行本地事务
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        String body = new String(messageExt.getBody());
        Map<String, Object> msg = JsonUtils.jsonToObject(body, Map.class);
        String id = (String)msg.get("orderId");
        OrderDO orderDO = orderDOMapper.selectByPrimaryKey(id);
        if(orderDO == null){
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        // 消息回查
        String stockLogId = null;
        int insertCount = 0;
        try {
            Map<String, Object> args = (Map) o;
            OrderDO order = (OrderDO) args.get("order");
            stockLogId = (String) args.get("stockLogId");
            // 订单落库
            insertCount = orderDOMapper.insertSelective(order);
        } catch (Exception e) {
            e.printStackTrace();
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        // 订单创建成功
        if (insertCount > 0) {
            StockLogDO stockLogDO = new StockLogDO();
            stockLogDO.setStockLogId(stockLogId);
            stockLogDO.setStatus(2);
            logDOMapper.updateByPrimaryKeySelective(stockLogDO);
            return LocalTransactionState.COMMIT_MESSAGE;
        } else {
            StockLogDO stockLogDO = new StockLogDO();
            stockLogDO.setStockLogId(stockLogId);
            stockLogDO.setStatus(3);
            logDOMapper.updateByPrimaryKeySelective(stockLogDO);
            // 订单创建失败
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }
}
