package com.afeng.web.module.admin.service;

import com.afeng.web.module.admin.aspectj.AsyncFactory;
import com.afeng.web.module.admin.aspectj.AsyncManager;
import com.afeng.web.module.admin.model.Role;
import com.afeng.web.module.admin.model.User;
import com.afeng.web.module.admin.model.UserStatus;
import com.afeng.web.module.common.constant.Constants;
import com.afeng.web.module.common.constant.UserConstants;
import com.afeng.web.module.common.exception.user.UserBlockedException;
import com.afeng.web.module.common.exception.user.UserDeleteException;
import com.afeng.web.module.common.exception.user.UserNotExistsException;
import com.afeng.web.module.common.exception.user.UserPasswordNotMatchException;
import com.afeng.web.module.common.utils.DateUtils;
import com.afeng.web.module.common.utils.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class LoginService {

    @Autowired
    private IUserService userService;

    /**
     * 登录
     */
    public User login(String username, String password, String ip) {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, ip, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, ip, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, ip, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        User user = userService.selectUserByLoginName(username);

        if (user == null && maybeMobilePhoneNumber(username)) {
            user = userService.selectUserByPhoneNumber(username);
        }

        if (user == null && maybeEmail(username)) {
            user = userService.selectUserByEmail(username);
        }

        if (user == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, ip, MessageUtils.message("user.not.exists")));
            throw new UserNotExistsException();
        }
        //已被软删除
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, ip, MessageUtils.message("user.password.delete")));
            throw new UserDeleteException();
        }
        //已被封号
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, ip, MessageUtils.message("user.blocked", user.getRemark())));
            throw new UserBlockedException();
        }

        if (StringUtils.equals(user.getPassword(), password)) {
            //设置为管理员，用户拥有的角色中其中有主键为1的角色则是管理员
            if (user.getRoles() != null && user.getRoles().size() > 0) {
                for (Role role : user.getRoles()) {
                    if (Role.isAdmin(role.getRoleId())) {
                        user.setAdmin(true);
                        break;
                    }
                }
            }
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, ip, MessageUtils.message("user.login.success")));
            user.setLoginIp(ip);
            recordLoginInfo(user);
            return user;
        } else {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, ip, MessageUtils.message("user.password.not.match")));
        }

        return null;
    }

    private boolean maybeEmail(String username) {
        if (!username.matches(UserConstants.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }

    private boolean maybeMobilePhoneNumber(String username) {
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN)) {
            return false;
        }
        return true;
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(User user) {
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}
