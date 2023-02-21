package com.cat.miaosha.service.provider;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Mr.xin
 */
@Component
@DependsOn("springContextUtils")
public class TransactionProducer {
    private TransactionMQProducer producer;

    @PostConstruct
    private void init() throws MQClientException {
        this.producer = new TransactionMQProducer("pg");
        // FIXME: 2023/1/14 namesrv 应该写在配置文件中
        producer.setNamesrvAddr("192.168.62.102:9876");
        producer.setRetryTimesWhenSendFailed(3);
        producer.setTransactionListener(new TransactionListenerImpl());
        start();
    }

    private void start() throws MQClientException {
        this.producer.start();
    }

    public TransactionSendResult sendMessage(Message message,Object args) throws MQClientException {
        return producer.sendMessageInTransaction(message, args);
    }


}
