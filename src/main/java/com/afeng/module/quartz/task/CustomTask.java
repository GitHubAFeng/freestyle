package com.afeng.module.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 定时任务调度测试
 * SpringBoot内置的定时任务，需要在启动类上添加注解@EnableScheduling开启，如果没有开启注解也能运行，则可能是在项目其它依赖库中开启了
 */
@Slf4j
@Component
public class CustomTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 设置每6秒执行一次
     **/
//    @Scheduled(fixedRate = 6000)
    private void process() {
        log.debug("now time is " + dateFormat.format(new Date()));
    }

    /**
     * 设置每6秒执行一次
     **/
    @Scheduled(cron = "*/6 * * * * ?")
    private void process2() {
        log.debug("now time is " + dateFormat.format(new Date()));
    }

}
