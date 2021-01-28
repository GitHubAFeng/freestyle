package com.afeng.web.framework.annotation;


import com.afeng.web.framework.config.dynamicData.DataSourceType;

import java.lang.annotation.*;

/**
 * 备注：自定义数据源选择注解
 * 优先级：先方法，后类，如果方法覆盖了类上的数据源类型，以方法的为准，否则以类上的为准
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TargetDataSource {
    DataSourceType value() default DataSourceType.defaultDataSource;

}
