package com.afeng.web.framework.config;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisConfig {

    /**
     * 根据XML上的databaseId="sqlserver" 识别数据库SQL，允许相关id不同databaseId，会自动适配
     * databaseId 如果配置了 databaseIdProvider，MyBatis 会加载所有的不带 databaseId 或匹配当前 databaseId 的语句；
     * 即无databaseId时默认为通用SQL
     * https://www.cnblogs.com/valten/p/12083128.html
     **/
    @Bean
    public DatabaseIdProvider getDatabaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.setProperty("SQL Server", "sqlserver");
        properties.setProperty("MySQL", "mysql");
        databaseIdProvider.setProperties(properties);
        return databaseIdProvider;
    }

    /**
     * 注册拦截器
     */
    @Bean
    public MyBatisInterceptor mybatisInterceptor() {
        MyBatisInterceptor interceptor = new MyBatisInterceptor();
        Properties properties = new Properties();
        // 可以调用properties.setProperty方法来给拦截器设置一些自定义参数
        interceptor.setProperties(properties);
        return interceptor;
    }


}
