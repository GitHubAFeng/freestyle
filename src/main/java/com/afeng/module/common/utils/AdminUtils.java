package com.afeng.module.common.utils;

import com.afeng.common.cache.JedisUtil;
import com.afeng.common.util.SessionUtil;
import com.afeng.framework.core.constant.Constants;
import com.afeng.framework.exception.AdminException;
import com.afeng.module.admin.model.Menu;
import com.afeng.module.admin.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * 数据库角色主键为1是管理员
 *
 * @author afeng
 * @date: 2020/5/9 10:06
 */
public class AdminUtils {

    private static final String SysUserPermissionCacheKey = "sys-perm:admin:";//后台用户权限缓存KEY前缀

    public static User getSysUser() {
        User user = (User) SessionUtil.getSessionObject(Constants.CURRENT_USER);
        if (user == null) {
            throw AdminException.toAccessDenied("系统用户不存在");
        }
        return user;
    }

    //判断用户是否拥有某个权限
    public static boolean isPermitted(JedisUtil jedisUtil, String permission) {
        if (getSysUser().isAdmin()) return true;
        Set<String> permSet = getSysUserPermissionCache(jedisUtil);
        if (permSet != null && permSet.size() > 0) {
            return permSet.contains(permission);
        }
        return false;
    }

    //判断用户是否拥有某个角色
    public static boolean hasRole(String role) {
        //TODO 未实现
        return true;
    }

    /**
     * 保存用户权限缓存
     *
     * @param jedisUtil 缓存
     * @param menus     菜单列表
     */
    public static void setSysUserPermissionCache(JedisUtil jedisUtil, List<Menu> menus) {
        if (menus != null && menus.size() > 0) {
            String cacheKey = SysUserPermissionCacheKey + getSysUser().getUserId();
            if (!jedisUtil.exists(cacheKey)) {
                for (Menu item : menus) {
                    if (StringUtils.isNotEmpty(item.getPerms())) {
                        jedisUtil.setSetAdd(cacheKey, item.getPerms());
                    }
                }
            }
        }
    }

    /**
     * 获取用户权限缓存
     *
     * @param jedisUtil 缓存
     */
    public static Set<String> getSysUserPermissionCache(JedisUtil jedisUtil) {
        String cacheKey = SysUserPermissionCacheKey + getSysUser().getUserId();
        return jedisUtil.getSet(cacheKey);
    }

    //清除权限缓存
    public static long cleanSysUserPermissionCache(JedisUtil jedisUtil) {
        String cacheKey = SysUserPermissionCacheKey + getSysUser().getUserId();
        return jedisUtil.del(cacheKey);
    }

}
