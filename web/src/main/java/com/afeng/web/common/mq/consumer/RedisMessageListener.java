package com.afeng.web.common.mq.consumer;


import com.afeng.web.module.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisMessageListener implements MessageListener {


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        String data = new String(message.getBody());

        log.debug("接收数据:" + message.toString());
        log.debug("订阅频道:" + channel);
        log.debug("订阅数据:" + data);
        log.debug("订阅频道pattern:" + new String(pattern));

        if (StringUtils.equals(channel, "test01")) {
            log.debug("eventModel:" + data);
        }


    }
}

