package com.afeng.web.module.admin.service;

import com.afeng.web.common.util.ServletUtils;
import com.afeng.web.module.admin.aspectj.AsyncFactory;
import com.afeng.web.module.admin.aspectj.AsyncManager;
import com.afeng.web.module.admin.model.User;
import com.afeng.web.module.common.constant.Constants;
import com.afeng.web.module.common.constant.UserConstants;
import com.afeng.web.module.common.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注册校验方法
 *
 * @author ruoyi
 */
@Component
public class RegisterService {
    @Autowired
    private IUserService userService;

    /**
     * 注册
     */
    public String register(User user, final String ip) {
        String msg = "", username = user.getLoginName(), password = user.getPassword();

        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute("captcha"))) {
            msg = "验证码错误";
        } else if (StringUtils.isEmpty(username)) {
            msg = "用户名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            msg = "用户密码不能为空";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在5到20个字符之间";
        } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        } else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(username))) {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        } else {
            boolean regFlag = userService.registerUser(user);
            if (!regFlag) {
                msg = "注册失败,请联系系统管理人员";
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, ip, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }
}
