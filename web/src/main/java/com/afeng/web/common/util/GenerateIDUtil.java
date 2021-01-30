package com.afeng.web.common.util;

import com.afeng.web.common.cache.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class GenerateIDUtil {

    private static GenerateIDUtil instance;

    @PostConstruct
    private void init() {
        instance = this;
    }

    public static GenerateIDUtil getInstance() {
        return instance;
    }

    /**
     * 生成6位数字唯一码(如果异常失败则会临时发放8位)
     *
     * @author AFeng
     * @createDate 2021/1/30 14:23
     **/
    public String getPrimaryId() {
        String code = null;
        try (Jedis jedis = JedisUtil.getInstance().getJedis()) {
            final String codeSetKey = "primaryId:codeSet";
            code = jedis.spop(codeSetKey);
            if (StringUtils.isBlank(code)) {
                code = RedissonLockUtils.getInstance().locked(() -> {
                    try {
                        if (!jedis.exists(codeSetKey)) {
                            final String stepKey = "primaryId:step";
                            int add = 100000;//每批预生成编码数量
                            int step = 100000;//码数起点
                            if (jedis.exists(stepKey)) {
                                step = Integer.parseInt(jedis.get(stepKey)) + add;
                            }
                            int max = step + add;
                            List<String> list = new ArrayList<>();
                            for (int i = step; i < max; i++) {
                                list.add(String.valueOf(i));
                            }
                            Collections.shuffle(list);
                            jedis.del(codeSetKey);
                            jedis.sadd(codeSetKey, list.toArray(new String[0]));
                            jedis.set(stepKey, String.valueOf(step));
                            return jedis.spop(codeSetKey);
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                    return null;
                }, "RedisLock:getPrimaryId");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (StringUtils.isBlank(code)) {
                int first = RandomUtils.nextInt(1, 9);
                int randNo = Math.abs(UUID.randomUUID().toString().hashCode());
                code = first + String.valueOf(randNo);
                if (code.length() < 8) code = String.valueOf(RandomUtils.nextInt(10000000, 99999999));
                if (code.length() > 8) code = code.substring(0, 8);
            }
        }
        return code;
    }


}
