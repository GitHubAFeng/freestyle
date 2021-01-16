package com.afeng.web.framework.core;

import org.springframework.context.ApplicationEvent;

public abstract class BaseEvent<T> extends ApplicationEvent {

    private static final long serialVersionUID = -1588824276312662024L;

    protected T eventData;

    public BaseEvent(Object source, T eventData) {
        super(source);
        this.eventData = eventData;
    }

    public BaseEvent(T eventData) {
        super(eventData);
    }

    public T getEventData() {
        return eventData;
    }

    public void setEventData(T eventData) {
        this.eventData = eventData;
    }
}