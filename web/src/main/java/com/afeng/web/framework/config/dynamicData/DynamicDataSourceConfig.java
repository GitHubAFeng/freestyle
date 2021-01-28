package com.afeng.web.framework.config.dynamicData;

import com.afeng.web.common.util.SpringUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置
 * 事务设置参考
 * https://doc.ruoyi.vip/ruoyi/other/faq.html#%E5%A6%82%E4%BD%95%E8%A7%A3%E5%86%B3%E5%A4%9A%E6%95%B0%E6%8D%AE%E6%BA%90%E4%BA%8B%E5%8A%A1%E7%9A%84%E4%B8%80%E8%87%B4%E6%80%A7
 *
 * @author AFeng
 * @createDate 2021/1/28 16:41
 **/
@Slf4j
@Configuration
public class DynamicDataSourceConfig {

    @Value("${spring.datasource.druid.freedom.enabled}")
    private boolean isFreedomEnabled;

    @Value("${spring.datasource.druid.afengboot.enabled}")
    private boolean isAfengbootEnabled;


    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.freedom")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.freedom", name = "enabled", havingValue = "true")
    public DataSource freedomDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.afengboot")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.afengboot", name = "enabled", havingValue = "true")
    public DataSource afengbootDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.defaultDataSource.name(), masterDataSource);
        //从库的bean
        if (isFreedomEnabled) {
            setDataSource(targetDataSources, DataSourceType.freedomDataSource.name(), "freedomDataSource");
        }
        if (isAfengbootEnabled) {
            setDataSource(targetDataSources, DataSourceType.afengbootDataSource.name(), "afengbootDataSource");
        }
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

    /**
     * 设置数据源
     *
     * @param targetDataSources 备选数据源集合
     * @param sourceName        数据源名称
     * @param beanName          bean名称
     */
    private void setDataSource(Map<Object, Object> targetDataSources, String sourceName, String beanName) {
        try {
            DataSource dataSource = SpringUtils.getBean(beanName);
            targetDataSources.put(sourceName, dataSource);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}

