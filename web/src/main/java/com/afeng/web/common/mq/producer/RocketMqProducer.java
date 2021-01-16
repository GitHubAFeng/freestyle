//package com.afeng.web.common.mq.producer;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.rocketmq.client.producer.SendCallback;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
///**
// * rocket 发送消息封装类
// * 如有需要使用rocketmq则打开(包括pom里的依赖)，否则注释掉简化部署
// */
//@Component
//public class RocketMqProducer {
//
//    private Logger log = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;
//
//
//    /**
//     * 同步消息，先消费后回调
//     */
//    public SendResult syncNews(String msg) {
//        // 同步消息发送成功会有一个返回值，我们可以用这个返回值进行判断和获取一些信息
//        SendResult sendResult = rocketMQTemplate.syncSend("sync-topic", MessageBuilder.withPayload(msg).build());
//        log.debug("同步消息回调: {}", JSON.toJSONString(sendResult));
//        return sendResult;
//    }
//
//    /**
//     * 异步消息
//     */
//    public void asynNews(String msg) {
//        rocketMQTemplate.asyncSend("asyn-topic", MessageBuilder.withPayload(msg).build(), new SendCallback() {
//            public void onSuccess(SendResult sendResult) {
//                // 成功回调
//                log.debug("异步消息成功回调: {}", JSON.toJSONString(sendResult));
//            }
//
//            public void onException(Throwable throwable) {
//                // 失败回调
//                log.debug("异步消息失败回调: {}", throwable.getMessage());
//            }
//        });
//    }
//
//    /**
//     * 单向消息
//     */
//    public void sendOneWay(String msg) {
//        rocketMQTemplate.sendOneWay("oneWay-topic", MessageBuilder.withPayload(msg).build());
//    }
//}
