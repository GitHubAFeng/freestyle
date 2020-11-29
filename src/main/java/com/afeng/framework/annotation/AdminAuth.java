package com.afeng.framework.annotation;

import java.lang.annotation.*;

/**
 * 后台管理员验证
 *
 * @author afeng
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminAuth {

    //验证当前用户是否拥有指定权限，例如权限标识 system:user:add
    String hasPermission() default "";

}
