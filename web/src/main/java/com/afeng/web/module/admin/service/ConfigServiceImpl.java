package com.afeng.web.module.admin.service;

import com.afeng.web.common.cache.JedisUtil;
import com.afeng.web.module.admin.dao.ConfigMapper;
import com.afeng.web.module.admin.model.Config;
import com.afeng.web.module.common.constant.Constants;
import com.afeng.web.module.common.constant.UserConstants;
import com.afeng.web.module.common.utils.AdminUtils;
import com.afeng.web.module.common.utils.StringUtils;
import com.afeng.web.module.common.utils.text.Convert;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数配置 服务层实现
 *
 * @author ruoyi
 */
@Service
public class ConfigServiceImpl implements IConfigService {
    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private JedisUtil cacheUtil;

    /**
     * 项目启动时，初始化参数到缓存
     */
    @Override
    public void init() {
        if (cacheUtil.exists(getCacheName())) return;
        List<Config> configsList = configMapper.selectConfigList(new Config());
        Map<String, Object> data = null;
        for (Config config : configsList) {
            data = new HashMap<>();
            data.put(getCacheKey(config.getConfigKey()), config.getConfigValue());
            cacheUtil.mapObjectPut(getCacheName(), data);
        }
    }

    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    public Config selectConfigById(Long configId) {
        Config config = new Config();
        config.setConfigId(configId);
        return configMapper.selectConfig(config);
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数名称
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        Map<String, Object> data = cacheUtil.getObjectMap(getCacheName());
        String configValue = MapUtils.getString(data, getCacheKey(configKey));
        if (StringUtils.isNotEmpty(configValue)) {
            return configValue;
        }
        Config config = new Config();
        config.setConfigKey(configKey);
        Config retConfig = configMapper.selectConfig(config);
        if (StringUtils.isNotNull(retConfig)) {
            Map<String, Object> cdata = new HashMap<>();
            cdata.put(getCacheKey(configKey), retConfig.getConfigValue());
            cacheUtil.mapObjectPut(getCacheName(), cdata);
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public List<Config> selectConfigList(Config config) {
        return configMapper.selectConfigList(config);
    }

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int insertConfig(Config config) {
        config.setCreateBy(AdminUtils.getSysUser().getLoginName());
        int row = configMapper.insertConfig(config);
        if (row > 0) {
            Map<String, Object> cdata = new HashMap<>();
            cdata.put(getCacheKey(config.getConfigKey()), config.getConfigValue());
            cacheUtil.mapObjectPut(getCacheName(), cdata);
        }
        return row;
    }

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int updateConfig(Config config) {
        config.setUpdateBy(AdminUtils.getSysUser().getLoginName());
        int row = configMapper.updateConfig(config);
        if (row > 0) {
            Map<String, Object> cdata = new HashMap<>();
            cdata.put(getCacheKey(config.getConfigKey()), config.getConfigValue());
            cacheUtil.mapObjectPut(getCacheName(), cdata);
        }
        return row;
    }

    /**
     * 批量删除参数配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigByIds(String ids) {
        int count = configMapper.deleteConfigByIds(Convert.toStrArray(ids));
        if (count > 0) {
            cacheUtil.del(getCacheName());
        }
        return count;
    }

    /**
     * 清空缓存数据
     */
    @Override
    public void clearCache() {
        cacheUtil.del(getCacheName());
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(Config config) {
        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        Config info = configMapper.checkConfigKeyUnique(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue()) {
            return UserConstants.CONFIG_KEY_NOT_UNIQUE;
        }
        return UserConstants.CONFIG_KEY_UNIQUE;
    }

    /**
     * 获取cache name
     *
     * @return 缓存名
     */
    private String getCacheName() {
        return Constants.SYS_CONFIG_CACHE;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return Constants.SYS_CONFIG_KEY + configKey;
    }
}
