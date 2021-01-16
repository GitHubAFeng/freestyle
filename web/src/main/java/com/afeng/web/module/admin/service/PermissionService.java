package com.afeng.web.module.admin.service;

import com.afeng.web.common.cache.JedisUtil;
import com.afeng.web.module.admin.model.User;
import com.afeng.web.module.common.utils.AdminUtils;
import com.afeng.web.module.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RuoYi首创 js调用 thymeleaf 实现按钮权限可见性
 *
 * @author ruoyi
 */
@Service("permission")
public class PermissionService {
    private static final Logger log = LoggerFactory.getLogger(PermissionService.class);

    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 没有权限，hidden用于前端隐藏按钮
     */
    public static final String NOACCESS = "hidden";

    private static final String ROLE_DELIMETER = ",";

    private static final String PERMISSION_DELIMETER = ",";

    /**
     * 验证用户是否具备某权限，无权限返回hidden用于前端隐藏（如需返回Boolean使用isPermitted）
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public String hasPermi(String permission) {
        return isPermitted(permission) ? StringUtils.EMPTY : NOACCESS;
    }

    /**
     * 验证用户是否不具备某权限，与 hasPermi逻辑相反。无权限返回hidden用于前端隐藏（如需返回Boolean使用isLacksPermitted）
     *
     * @param permission 权限字符串
     * @return 用户是否不具备某权限
     */
    public String lacksPermi(String permission) {
        return isLacksPermitted(permission) ? StringUtils.EMPTY : NOACCESS;
    }

    /**
     * 验证用户是否具有以下任意一个权限，无权限返回hidden用于隐藏（如需返回Boolean使用hasAnyPermissions）
     *
     * @param permissions 以 PERMISSION_NAMES_DELIMETER 为分隔符的权限列表
     * @return 用户是否具有以下任意一个权限
     */
    public String hasAnyPermi(String permissions) {
        return hasAnyPermissions(permissions, PERMISSION_DELIMETER) ? StringUtils.EMPTY : NOACCESS;
    }

    /**
     * 验证用户是否具备某角色，无权限返回hidden用于隐藏（如需返回Boolean使用isRole）
     *
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    public String hasRole(String role) {
        return isRole(role) ? StringUtils.EMPTY : NOACCESS;
    }

    /**
     * 验证用户是否不具备某角色，与hasRole逻辑相反。无权限返回hidden用于隐藏（如需返回Boolean使用isLacksRole）
     *
     * @param role 角色字符串
     * @return 用户是否不具备某角色
     */
    public String lacksRole(String role) {
        return isLacksRole(role) ? StringUtils.EMPTY : NOACCESS;
    }

    /**
     * 验证用户是否具有以下任意一个角色，无权限返回hidden用于隐藏（如需返回Boolean使用isAnyRoles）
     *
     * @param roles 以 ROLE_NAMES_DELIMETER 为分隔符的角色列表
     * @return 用户是否具有以下任意一个角色
     */
    public String hasAnyRoles(String roles) {
        return isAnyRoles(roles, ROLE_DELIMETER) ? StringUtils.EMPTY : NOACCESS;
    }

    /**
     * 验证用户是否认证通过或已记住的用户。
     *
     * @return 用户是否认证通过或已记住的用户
     */
    public boolean isUser() {
        return AdminUtils.getSysUser() != null;
    }

    /**
     * 判断用户是否拥有某个权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean isPermitted(String permission) {
        return AdminUtils.isPermitted(jedisUtil, permission);
    }

    /**
     * 判断用户是否不具备某权限，与 isPermitted逻辑相反。
     *
     * @param permission 权限名称
     * @return 用户是否不具备某权限
     */
    public boolean isLacksPermitted(String permission) {
        return isPermitted(permission) != true;
    }

    /**
     * 验证用户是否具有以下任意一个权限。
     *
     * @param permissions 以 PERMISSION_NAMES_DELIMETER 为分隔符的权限列表
     * @return 用户是否具有以下任意一个权限
     */
    public boolean hasAnyPermissions(String permissions) {
        return hasAnyPermissions(permissions, PERMISSION_DELIMETER);
    }

    /**
     * 验证用户是否具有以下任意一个权限。
     *
     * @param permissions 以 delimeter 为分隔符的权限列表
     * @param delimeter   权限列表分隔符
     * @return 用户是否具有以下任意一个权限
     */
    public boolean hasAnyPermissions(String permissions, String delimeter) {
        User subject = AdminUtils.getSysUser();

        if (subject != null) {
            if (delimeter == null || delimeter.length() == 0) {
                delimeter = PERMISSION_DELIMETER;
            }

            for (String permission : permissions.split(delimeter)) {
                if (permission != null && AdminUtils.isPermitted(jedisUtil, permission.trim())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 判断用户是否拥有某个角色
     *
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    public boolean isRole(String role) {
        return AdminUtils.hasRole(role);
    }

    /**
     * 验证用户是否不具备某角色，与 isRole逻辑相反。
     *
     * @param role 角色名称
     * @return 用户是否不具备某角色
     */
    public boolean isLacksRole(String role) {
        return isRole(role) != true;
    }

    /**
     * 验证用户是否具有以下任意一个角色。
     *
     * @param roles 以 ROLE_NAMES_DELIMETER 为分隔符的角色列表
     * @return 用户是否具有以下任意一个角色
     */
    public boolean isAnyRoles(String roles) {
        return isAnyRoles(roles, ROLE_DELIMETER);
    }

    /**
     * 验证用户是否具有以下任意一个角色。
     *
     * @param roles     以 delimeter 为分隔符的角色列表
     * @param delimeter 角色列表分隔符
     * @return 用户是否具有以下任意一个角色
     */
    public boolean isAnyRoles(String roles, String delimeter) {
        User subject = AdminUtils.getSysUser();
        if (subject != null) {
            if (delimeter == null || delimeter.length() == 0) {
                delimeter = ROLE_DELIMETER;
            }

            for (String role : roles.split(delimeter)) {
                if (AdminUtils.hasRole(role.trim())) {
                    return true;
                }
            }
        }

        return false;
    }


}
