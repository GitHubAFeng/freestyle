package com.afeng.framework.filter.rate;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RMapCache;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;

/**
 * 接口限流器
 */
public class RedisRateLimiter {

    /*初始化配置redisson，限流key*/
    public final static String REDISSON_RATE_LIMITER_KEY = "RATE_LIMITER:";//redisson默认限流key
    public final static int REDISSON_RATE_LIMITER_PERMITS = 1000;//redisson默认限流次数
    public final static long REDISSON_RATE_LIMITER_TIMEOUT = 1L;//redisson默认限流超时数
    public final static String REDISSON_RATE_LIMITER_TIMEUNIT = "SECONDS";//redisson默认有效期单位

    /**
     * 采用redisson方式，
     * 自定义限流器
     * 该方法可以配合 mq，结果是 true 的话就 ack，false 的话就 reject
     *
     * @param redisClient
     * @param rateKey：自定义key
     * @param permits:允许最大限流数
     * @param timeout:时间间隔单位秒
     * @param timeUnit:时间间隔类型，默认秒
     */
    public static boolean acquire(RedissonClient redisClient, String rateKey, long permits, long timeout, String unit) {
        RMapCache<String, Integer> msgRateLimit = redisClient.getMapCache(rateKey, IntegerCodec.INSTANCE);
        TimeUnit timeUnit = TimeUnit.SECONDS;//时间间隔，默认秒
        permits = permits > 0 ? permits : 1000;//初始化默认1000个限流
        switch (unit.toUpperCase()) {
            case "SECONDS":
                timeUnit = TimeUnit.SECONDS;
                break;
            case "MINUTES":
                timeUnit = TimeUnit.MINUTES;
                break;
            case "HOURS":
                timeUnit = TimeUnit.HOURS;
                break;
            case "DAYS":
                timeUnit = TimeUnit.DAYS;
                break;
            default:
                timeUnit = TimeUnit.SECONDS;
                break;
        }
        msgRateLimit.putIfAbsent(rateKey, 0, timeout, timeUnit);
        Integer tokenCount = msgRateLimit.addAndGet(rateKey, 1);//目前总占用额度数
        return tokenCount > permits ? false : true;
    }

    /**
     * 直接使用redisson限流器，令牌桶算法
     * 参考:https://github.com/redisson/redisson/wiki 与 https://my.oschina.net/hanchao/blog/1833612?from=groupmessage
     * 限流器redisson3.7.1版本
     * 本处默认配置都是秒，可以手动修改
     * 重启服务，可以手动清除一下redis库
     * tryAcquire(int permits, long timeout, TimeUnit unit)
     * 从RateLimiter 获取指定许可数如果该许可数可以在不超过timeout的时间内获取得到的话，或者如果无法在timeout 过期之前获取得到许可数的话，那么立即返回false （无需等待）
     *
     * @param redisClient
     * @param rateKey：自定义key
     * @param permits:允许最大限流数
     * @param timeout:时间间隔单位秒
     * @param timeUnit:时间间隔类型，默认秒(SECONDS,MINUTES,HOURS,DAYS)
     */
    public static boolean tryAcquire(RedissonClient redisClient, String rateKey, long permits, long timeout, String timeUnit) {
        RRateLimiter rateLimiter = redisClient.getRateLimiter(rateKey);
        //初始化,最大流速 = 每1秒钟产生10个令牌
        /*rateLimiter.trySetRate(RateType.OVERALL, 1000, 1, RateIntervalUnit.SECONDS);//1秒1000个token*/
        switch (timeUnit.toUpperCase()) {
            case "SECONDS":
                rateLimiter.trySetRate(RateType.OVERALL, permits, timeout, RateIntervalUnit.SECONDS);//1秒permits个token
                return rateLimiter.tryAcquire(1, 0, TimeUnit.SECONDS);//间隔一秒，尝试一次，失败就不重试，直接拒绝访问，0换成正数，就会间隔重试
            case "MINUTES":
                rateLimiter.trySetRate(RateType.OVERALL, permits, timeout, RateIntervalUnit.MINUTES);//1分钟permits个token
                return rateLimiter.tryAcquire(1, 0, TimeUnit.MINUTES);//间隔一分钟，尝试一次，失败就不重试，直接拒绝访问，0换成正数，就会间隔重试
            case "HOURS":
                rateLimiter.trySetRate(RateType.OVERALL, permits, timeout, RateIntervalUnit.HOURS);//1小时permits个token
                return rateLimiter.tryAcquire(1, 0, TimeUnit.HOURS);//间隔一小时，尝试一次，失败就不重试，直接拒绝访问，0换成正数，就会间隔重试
            case "DAYS":
                rateLimiter.trySetRate(RateType.OVERALL, permits, timeout, RateIntervalUnit.DAYS);//1天permits个token
                return rateLimiter.tryAcquire(1, 0, TimeUnit.DAYS);//间隔一天，尝试一次，失败就不重试，直接拒绝访问，0换成正数，就会间隔重试
            default:
                rateLimiter.trySetRate(RateType.OVERALL, permits, timeout, RateIntervalUnit.SECONDS);//1秒permits个token
                return rateLimiter.tryAcquire(1, 0, TimeUnit.SECONDS);//间隔一秒，尝试一次，失败就不重试，直接拒绝访问，0换成正数，就会间隔重试
        }
    }
}
