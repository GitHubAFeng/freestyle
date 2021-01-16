package com.afeng.web.framework.annotation;

import com.afeng.web.framework.core.constant.Constants;

import java.lang.annotation.*;

/**
 * api接口验证
 *
 * @author afeng
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiAuth {
    //验证签名
    boolean isAuthSign() default true;

    //是否使用简单签名
    boolean isUseSimpleSign() default true;

    //验证登录
    boolean isAuthLogin() default true;

    //是否使用简单验证登录
    boolean isUseSimpleLogin() default true;

    //是否防御重放攻击
    boolean isAntiReplayAttack() default false;

    //是否使用全局限流器针对某个接口
    boolean isUseGlobalRateLimiter() default false;

    //是否使用用户限流器针对某个用户ID
    boolean isUseUserRateLimiter() default false;

    //限流放行数量,1个
    int limitCount() default 1;

    //限流时间间隔，默认1秒
    int limitTimeout() default 1;

    //限流器，默认限流时间单位秒(SECONDS,MINUTES,HOURS,DAYS)
    String limitTimeUnit() default Constants.RateLimiterTimeUnit.SECONDS;

    //限流器，自定义key，例如限制某个用户如使用uid定义
    String limitRateKey() default "";

}
