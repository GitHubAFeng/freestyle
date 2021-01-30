package com.afeng.web.common.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * redisson 分布式锁
 */
@Slf4j
@Component
public class RedissonLockUtils {

    private static RedissonLockUtils instance;

    @PostConstruct
    private void init() {
        instance = this;
        instance.redissonClient = this.redissonClient;
    }

    public static RedissonLockUtils getInstance() {
        return instance;
    }


    @Autowired
    private RedissonClient redissonClient;

    /**
     * 锁等待时间
     */
    private int defaultLockWaitTime = 3;

    /**
     * 锁默认失效时间
     */
    private int defaultLockLeaseTime = 5;


    /**
     * Description: 加锁方法
     *
     * @param supplier      方法
     * @param key           锁Key
     * @param lockWaitTime  等待时间
     * @param lockLeaseTime 默认失效时间
     */
    public <T> T locked(Supplier<T> supplier, String key, int lockWaitTime, int lockLeaseTime) throws Exception {
        lockWaitTime = lockWaitTime <= 0 ? defaultLockWaitTime : lockWaitTime;
        lockLeaseTime = lockLeaseTime <= 0 ? defaultLockLeaseTime : lockLeaseTime;

        RLock lock = redissonClient.getFairLock(key);
        try {
            boolean locked = lock.tryLock(lockWaitTime, lockLeaseTime, TimeUnit.SECONDS);
            if (!locked) {
                // 尝试加锁，最多等待s秒，上锁以后x秒自动解锁
                throw new RuntimeException("获取分布式锁失败");
            }
            return supplier.get();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            // 是否还是锁定状态
            if (lock != null && lock.isLocked()) {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * Description: 加锁方法
     *
     * @param supplier 方法
     * @param key      锁Key
     */
    public <T> T locked(Supplier<T> supplier, String key) throws Exception {
        return locked(supplier, key, defaultLockWaitTime, defaultLockLeaseTime);
    }
}

