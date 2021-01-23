package com.afeng.web.module.admin.controller;

import com.afeng.web.common.util.ServletUtils;
import com.afeng.web.module.admin.aspectj.AsyncFactory;
import com.afeng.web.module.admin.aspectj.AsyncManager;
import com.afeng.web.module.admin.model.AjaxResult;
import com.afeng.web.module.admin.model.User;
import com.afeng.web.module.admin.service.IConfigService;
import com.afeng.web.module.admin.service.IDictTypeService;
import com.afeng.web.module.admin.service.LoginService;
import com.afeng.web.module.common.constant.Constants;
import com.afeng.web.module.common.utils.IpUtils;
import com.afeng.web.module.common.utils.MessageUtils;
import com.afeng.web.module.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private IConfigService configService;
    @Autowired
    private IDictTypeService dictTypeService;

    private static final boolean captchaEnabled = true; //是否启用验证码

    private String prefix = "admin/";

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model
            , @CookieValue(value = "username", required = false) String username
            , @CookieValue(value = "password", required = false) String password) {

        //初始化管理后台数据
        configService.init();
        dictTypeService.init();

        //启用验证码
        model.addAttribute("captchaEnabled", captchaEnabled);
        //验证码类型 math 数组计算 char 字符
        model.addAttribute("captchaType", "math");

        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            try {
                username = URLDecoder.decode(username, "UTF-8");//进行解码
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
            String ip = IpUtils.getIpAddr(request);
            User user = loginService.login(username, password, ip);
            if (user != null) {
                setSysUser(user);
                return "redirect:/admin/index";
            }
        }

        return prefix + "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe, String validateCode
            , HttpServletRequest request, HttpServletResponse response) {

        String ip = IpUtils.getIpAddr(request);
        //验证码
        if (captchaEnabled) {
            HttpSession session = request.getSession();
            String captchaId = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            if (!StringUtils.equals(captchaId, validateCode)) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, ip, MessageUtils.message("user.jcaptcha.error")));
                return error("验证码错误");
            }
        }
        User user = loginService.login(username, password, ip);
        if (user != null) {
            setSysUser(user);
            try {
                username = URLEncoder.encode(username, "UTF-8"); //对输入的中文进行编码，防止乱码出现
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
            Cookie nameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password", password);
            nameCookie.setPath(request.getContextPath() + "/");    //设置Cookie的有效路径
            passwordCookie.setPath(request.getContextPath() + "/");//设置Cookie的有效路径
            if (rememberMe != null && rememberMe) {
                //有记住我，就设置cookie的保存时间
                nameCookie.setMaxAge(7 * 24 * 60 * 60);
                passwordCookie.setMaxAge(7 * 24 * 60 * 60);
            } else {
                //没有记住我，设置cookie的时间为0
                nameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
            }
            response.addCookie(nameCookie);
            response.addCookie(passwordCookie);
            return success();
        }
        return error("登录失败：用户不存在或密码错误");

    }

    //权限认证失败地址
    @GetMapping("/unauth")
    public String unauth() {
        return "error/403";
    }
}
