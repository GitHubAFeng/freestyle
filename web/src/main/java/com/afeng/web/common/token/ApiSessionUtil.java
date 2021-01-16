package com.afeng.web.common.token;

import com.afeng.web.common.cache.JedisUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * API会话管理
 *
 * @author afeng
 * @date: 2020/5/2 11:17
 */
public class ApiSessionUtil {

    private static final String session_PrefixKey = "session_";//缓存KEY前缀
    public static final int session_EXPIRE_TIME = 60 * 60;//session过期时间，单位秒


    private JedisUtil jedisUtil;

    public static ApiSessionUtil build(JedisUtil jedisUtil) {
        return new ApiSessionUtil(jedisUtil);
    }

    private ApiSessionUtil(JedisUtil jedisUtil) {
        this.jedisUtil = jedisUtil;
    }

    /**
     * 创建
     */
    public String createSession(String uid) {
        String cacheKey = session_PrefixKey + uid;
        String session = UUID.randomUUID().toString().replaceAll("-", "");
        if (jedisUtil.exists(cacheKey)) {
            jedisUtil.setSetAdd(cacheKey, session);
        } else {
            Set<String> stringSet = new HashSet<>();
            stringSet.add(session);
            jedisUtil.setSet(cacheKey, stringSet, ApiSessionUtil.session_EXPIRE_TIME);
        }
        return session;
    }

    /**
     * 刷新
     */
    public String refreshSession(String uid) {
        String cacheKey = session_PrefixKey + uid;
        if (jedisUtil.exists(cacheKey)) {
            jedisUtil.del(cacheKey);
        }
        return createSession(uid);
    }

    /**
     * 获取
     */
    public String getSession(String uid) {
        String cacheKey = session_PrefixKey + uid;
        Set<String> sessionSet = jedisUtil.getSet(cacheKey);
        if (sessionSet != null && sessionSet.size() > 0) {
            return sessionSet.iterator().next();
        }
        return createSession(uid);
    }

    /**
     * 检查是否存在
     */
    public boolean checkSession(String uid) {
        String cacheKey = session_PrefixKey + uid;
        return jedisUtil.exists(cacheKey);
    }

    /**
     * 验证是否匹配
     */
    public boolean authSession(String uid, String uidSession) {
        String cacheKey = session_PrefixKey + uid;
        Set<String> sessionSet = jedisUtil.getSet(cacheKey);
        if (sessionSet != null && sessionSet.size() > 0) {
            return sessionSet.contains(uidSession);
        }
        return false;
    }

}
