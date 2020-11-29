//package com.afeng.common.mq.consumer;
//
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.stereotype.Service;
//
////如有需要使用rocketmq则打开(包括pom里的依赖)，否则注释掉简化部署
///**
// * 同步消费
// * 简单来说，同步发送就是指 producer 发送消息后，会在接收到 broker 响应后才继续发下一条消息的通信方式。
// * 由于这种同步发送的方式确保了消息的可靠性，同时也能及时得到消息发送的结果，故而适合一些发送比较重要的消息场景，比如说重要的通知邮件、营销短信等等。在实际应用中，这种同步发送的方式还是用得比较多的。
// */
//@Service
//@RocketMQMessageListener(consumerGroup = "my-consumer_sync-topic", topic = "sync-topic")
//@ConditionalOnProperty(name = "rocketmq.enabled", havingValue = "true")
//public class SyncConsumer implements RocketMQListener<String> {
//
//    private Logger log = LoggerFactory.getLogger(getClass());
//
//    public void onMessage(String message) {
//        log.debug("同步消费: {}", message);
//    }
//
//}
