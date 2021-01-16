package com.afeng.web.common.mq.config;

/**
 * 定义处理各类事件接口
 *
 * @author AFeng
 * @createDate 2020/8/13 10:24
 **/
public interface MqEventHandler {
    // 处理异步
    boolean onMessage(MqEventModel eventModel);

    //处理同步
    boolean onSyncMessage(MqEventModel eventModel);

}
