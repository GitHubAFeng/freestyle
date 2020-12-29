package com.afeng.module.quartz.task;

import com.afeng.module.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 * @Description 此定时任务由管理后台动态执行(数据库持久化)
 */
@Slf4j
@Component("ryTask")
public class RyTask {
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        log.debug(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params) {
        log.debug("执行有参方法：" + params);
    }

    public void ryNoParams() {
        log.debug("执行无参方法");
    }
}
