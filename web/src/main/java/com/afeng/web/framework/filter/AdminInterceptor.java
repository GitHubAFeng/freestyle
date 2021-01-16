package com.afeng.web.framework.filter;

import com.afeng.web.common.cache.JedisUtil;
import com.afeng.web.framework.annotation.AdminAuth;
import com.afeng.web.framework.config.AFengConfig;
import com.afeng.web.framework.core.constant.Constants;
import com.afeng.web.framework.exception.AdminException;
import com.afeng.web.module.admin.model.User;
import com.afeng.web.module.common.utils.AdminUtils;
import com.afeng.web.module.common.utils.IpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 管理后台拦截器
 *
 * @author afeng
 * @date: 2020/5/4 9:16
 */
@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final String API_PATH = "admin";//路径名

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!AFengConfig.isAdminManageEnabled()) {
            response.sendRedirect("/404");
            logger.warn("管理后台未开放!访问IP = " + IpUtils.getIpAddr(request));
            return false;
        }
        logger.debug("后台访问URL = " + request.getRequestURI());
        if ("/admin/login".equals(request.getRequestURI())) return true;
        if (StringUtils.contains(request.getRequestURI(), "/admin/captcha/")) return true;

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/admin/login");
            logger.debug("后台用户权柄不存在，将重定向到登录页面");
            return false;
        }
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            response.sendRedirect("/admin/login");
            logger.debug("后台用户不存在，将重定向到登录页面");
            return false;
        }

        HandlerMethod handlerMethod;
        if (handler instanceof HandlerMethod) {
            handlerMethod = (HandlerMethod) handler;
        } else {
            return true;
        }
        // 获取方法上的注解
        AdminAuth adminAuth = handlerMethod.getMethodAnnotation(AdminAuth.class);
        if (adminAuth == null) {
            // 如果方法上的注解为空 则获取类的注解
            adminAuth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(AdminAuth.class);
        }
        if (adminAuth != null) {
            if (StringUtils.isNotEmpty(adminAuth.hasPermission())) {
                if (!AdminUtils.isPermitted(jedisUtil, adminAuth.hasPermission())) {
                    throw AdminException.toAccessDenied("用户未拥有此权限");
                }
            }
        }
        return true;
    }

}
