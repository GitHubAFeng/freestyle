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
// * 异步消息
// * 接着就是异步发送，异步发送是指 producer 发出一条消息后，不需要等待 broker 响应，就接着发送下一条消息的通信方式。需要注意的是，不等待 broker 响应，并不意味着 broker 不响应，而是通过回调接口来接收 broker 的响应。所以要记住一点，异步发送同样可以对消息的响应结果进行处理。
// * 由于异步发送不需要等待 broker 的响应，故在一些比较注重 RT（响应时间）的场景就会比较适用。比如，在一些视频上传的场景，我们知道视频上传之后需要进行转码，如果使用同步发送的方式来通知启动转码服务，那么就需要等待转码完成才能发回转码结果的响应，由于转码时间往往较长，很容易造成响应超时。此时，如果使用的是异步发送通知转码服务，那么就可以等转码完成后，再通过回调接口来接收转码结果的响应了。
// */
//@Service
//@RocketMQMessageListener(consumerGroup = "my-consumer_asyn-topic", topic = "asyn-topic")
//@ConditionalOnProperty(name = "rocketmq.enabled", havingValue = "true")
//public class AsynConsumer implements RocketMQListener<String> {
//
//    private Logger log = LoggerFactory.getLogger(getClass());
//
//    public void onMessage(String message) {
//        log.debug("异步消费: {}", message);
//    }
//
//}
