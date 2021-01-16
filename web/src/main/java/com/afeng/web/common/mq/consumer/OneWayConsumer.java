//package com.afeng.web.common.mq.consumer;
//
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.stereotype.Service;
//
////TODO 如有需要使用rocketmq则打开(包括pom里的依赖)，否则注释掉简化部署
///**
// * 单向消息
// * 单向发送，见名知意，就是一种单方向通信方式，也就是说 producer 只负责发送消息，不等待 broker 发回响应结果，而且也没有回调函数触发，这也就意味着 producer 只发送请求不等待响应结果。
// * 由于单向发送只是简单地发送消息，不需要等待响应，也没有回调接口触发，故发送消息所耗费的时间非常短，同时也意味着消息不可靠。所以这种单向发送比较适用于那些耗时要求非常短，但对可靠性要求并不高的场景，比如说日志收集。
// */
//@Service
//@RocketMQMessageListener(consumerGroup = "my-consumer_oneWay-topic", topic = "oneWay-topic")
//@ConditionalOnProperty(name = "rocketmq.enabled", havingValue = "true")
//public class OneWayConsumer implements RocketMQListener<String> {
//
//    private Logger log = LoggerFactory.getLogger(getClass());
//
//    public void onMessage(String message) {
//        log.debug("单向消息: {}", message);
//    }
//
//}
