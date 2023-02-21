package com.cat.miaosha.service.provider;

import com.cat.miaosha.dao.OrderDOMapper;
import com.cat.miaosha.dao.StockLogDOMapper;
import com.cat.miaosha.entity.OrderDO;
import com.cat.miaosha.entity.StockLogDO;
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

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        Map<String, Object> args = (Map) o;
        OrderDO order = (OrderDO) args.get("order");
        String stockLogId = (String) args.get("stockLogId");
        // 订单落库
        int insertCount = orderDOMapper.insertSelective(order);
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

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        return null;
    }
}
