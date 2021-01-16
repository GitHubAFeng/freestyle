package com.afeng.web.common.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedisMQUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static RedisMQUtil instance;

    @PostConstruct
    private void init() {
        instance = this;
        instance.stringRedisTemplate = this.stringRedisTemplate;
    }

    public static RedisMQUtil getInstance() {
        return instance;
    }

    /**
     * 发送MQ事件
     *
     * @param key   key值
     * @param value 数据
     * @author AFeng
     * @createDate 2020/11/18 9:19
     **/
    public void seedEvent(String key, Object value) {
        if (value instanceof String) {
            stringRedisTemplate.convertAndSend(key, value);
        } else {
            stringRedisTemplate.convertAndSend(key, JSON.toJSONString(value));
        }
    }

}
