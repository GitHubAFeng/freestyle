package com.afeng.common.token;

import com.afeng.common.cache.JedisUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 防重放攻击
 *
 * @author afeng
 * @date: 2020/5/2 13:33
 */
public class AntiReplayAttackUtil {

    private static final String anti_PrefixKey = "anti_replay_";//缓存KEY前缀

    private JedisUtil jedisUtil;

    public static AntiReplayAttackUtil build(JedisUtil jedisUtil) {
        return new AntiReplayAttackUtil(jedisUtil);
    }

    private AntiReplayAttackUtil(JedisUtil jedisUtil) {
        this.jedisUtil = jedisUtil;
    }


    public void addSignCacheLog(String uid, String sign) {
        String cacheKey = anti_PrefixKey + uid;
        if (jedisUtil.exists(cacheKey)) {
            jedisUtil.setSetAdd(cacheKey, sign);
        } else {
            Set<String> stringSet = new HashSet<>();
            stringSet.add(sign);
            jedisUtil.setSet(cacheKey, stringSet, ApiSessionUtil.session_EXPIRE_TIME);
        }
    }

    public void cleanSignCacheLog(String uid) {
        String cacheKey = anti_PrefixKey + uid;
        jedisUtil.del(cacheKey);
    }


    public boolean checkSignCacheLog(String uid, String sign) {
        String cacheKey = anti_PrefixKey + uid;
        Set<String> stringSet = jedisUtil.getSet(cacheKey);
        if (stringSet != null) {
            return stringSet.contains(sign);
        }
        return false;
    }

}
