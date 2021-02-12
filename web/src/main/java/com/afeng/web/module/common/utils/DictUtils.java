package com.afeng.web.module.common.utils;

import com.afeng.web.common.cache.JedisUtil;
import com.afeng.web.module.admin.model.DictData;
import com.afeng.web.module.common.constant.Constants;
import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 *
 * @author ruoyi
 */
public class DictUtils {

    /**
     * 设置字典缓存
     *
     * @param key       参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(JedisUtil cacheUtil, String key, List<DictData> dictDatas) {
        Map<String, Object> cdata = new HashMap<>();
        cdata.put(getCacheKey(key), dictDatas);
        cacheUtil.mapObjectPut(getCacheName(), cdata);
    }

    /**
     * 获取字典缓存
     *
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<DictData> getDictCache(JedisUtil cacheUtil, String key) {
        Map<String, Object> data = cacheUtil.getObjectMap(getCacheName());
        Object cacheObj = MapUtils.getObject(data, getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj)) {
            return StringUtils.cast(cacheObj);
        }
        return null;
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache(JedisUtil cacheUtil) {
        cacheUtil.del(getCacheName());
    }

    /**
     * 获取cache name
     *
     * @return 缓存名
     */
    public static String getCacheName() {
        return Constants.SYS_DICT_CACHE;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return Constants.SYS_DICT_KEY + configKey;
    }
}
