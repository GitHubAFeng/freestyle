package com.afeng.web.framework.filter.rate;

import com.afeng.web.framework.core.constant.Constants;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    int limit() default 5;//放行数量,5个

    int timeout() default 1;//限流时间间隔，默认1秒

    String rateKey() default "";//限流器，自定义key

    //限流器，默认限流时间单位(SECONDS,MINUTES,HOURS,DAYS)
    String timeUnit() default Constants.RateLimiterTimeUnit.SECONDS;
}