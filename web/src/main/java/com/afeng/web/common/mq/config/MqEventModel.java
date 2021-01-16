package com.afeng.web.common.mq.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 事件模型
 *
 * @author AFeng
 * @createDate 2020/8/13 10:22
 **/
public class MqEventModel implements Serializable {
    private static final long serialVersionUID = 5389651545505911199L;

    /*
     * 事件类型，不同的事件类型会触发调用不同的Handler，
     * 一种事件类型可以注册多个Handler，对应于消息队列中
     * 每个事件可能触发多个Handler异步去做其他事情。
     **/
    private MqEventType eventType;

    // 额外信息
    private Map<String, Object> extraInfo = new HashMap<>();

    public MqEventModel(MqEventType eventType, Map<String, Object> extraInfo) {
        this.eventType = eventType;
        this.extraInfo = extraInfo;
    }

    public MqEventModel(MqEventType eventType) {
        this.eventType = eventType;
    }

    public MqEventModel() {
    }

    public MqEventType getEventType() {
        return eventType;
    }

    // 所有set方法，均返回当前调用此方法的对象，用于链式设置属性
    public MqEventModel setEventType(MqEventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public Map<String, Object> putExtraInfo(String key, Object value) {
        this.extraInfo.put(key, value);
        return this.extraInfo;
    }

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    public MqEventModel setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
        return this;
    }
}
