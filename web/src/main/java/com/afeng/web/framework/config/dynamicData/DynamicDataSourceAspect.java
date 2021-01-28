package com.afeng.web.framework.config.dynamicData;

import com.afeng.web.framework.annotation.TargetDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源处理
 */
@Order(1)   //设置AOP执行顺序(需要在事务之前，否则事务只发生在默认库中)
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {


    @Pointcut("@annotation(com.afeng.web.framework.annotation.TargetDataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        TargetDataSource dataSource = method.getAnnotation(TargetDataSource.class);
        if (dataSource != null) {
            log.debug("当前数据源 -- {} ", dataSource.value());
            DynamicDataSource.setDataSource(dataSource.value().name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }

}
