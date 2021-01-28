package com.afeng.web.module.app.service;

import com.afeng.web.framework.annotation.TargetDataSource;
import com.afeng.web.framework.config.dynamicData.DataSourceType;
import com.afeng.web.module.common.dao.CommonDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 测试
 */
@Service
public class DynamicDataSourceService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CommonDao commonDao;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    @TargetDataSource(DataSourceType.freedomDataSource)
    public Integer selectFreedomDataSource() {
        return jdbcTemplate.queryForObject("select count(*) from app_user", Integer.class);
    }

    @TargetDataSource(DataSourceType.afengbootDataSource)
    public Integer selectAfengbootDataSource() {
        return jdbcTemplate.queryForObject("select count(*) from app_user", Integer.class);
    }

    @TargetDataSource(DataSourceType.freedomDataSource)
    public Integer selectFreedomMapper() {
        return sqlSessionTemplate.selectOne("testDynamicData", 1);
    }

    @TargetDataSource(DataSourceType.afengbootDataSource)
    public Integer selectAfengbootMapper() {
        return sqlSessionTemplate.selectOne("testDynamicData", 1);
    }

    @TargetDataSource(DataSourceType.freedomDataSource)
    public Integer selectFreedomDao() {
        return commonDao.count("app_user", null);
    }

    @TargetDataSource(DataSourceType.afengbootDataSource)
    public Integer selectAfengbootDao() {
        return commonDao.count("app_user", null);
    }

}
