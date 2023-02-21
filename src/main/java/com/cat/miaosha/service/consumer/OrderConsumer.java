package com.cat.miaosha.service.consumer;

import com.cat.miaosha.dao.StockLogDOMapper;
import com.cat.miaosha.utils.JsonUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.xin
 */
@Component
@DependsOn("springContextUtils")
public class OrderConsumer {

    private static String TX_ORDER_TOPIC = "tx_order";
    private DefaultMQPushConsumer consumer;


    @PostConstruct
    private void init() throws MQClientException {
        this.consumer = new DefaultMQPushConsumer("cg");
        consumer.setNamesrvAddr("192.168.62.102:9876");
        consumer.setConsumeThreadMax(30);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(TX_ORDER_TOPIC, "*");
        consumer.setMessageListener(new MessageListenerImpl());
        start();
    }
    private void start() throws MQClientException {
        this.consumer.start();
    }
}
