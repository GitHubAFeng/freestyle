package com.afeng.web.framework.filter;

import com.afeng.web.common.token.JwtUtil;
import com.afeng.web.framework.annotation.ApiAuth;
import com.afeng.web.framework.exception.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器方法参数注解过滤器
 */
@Component
public class AuthHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    /**
     * 过滤出需要处理的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(String.class) && parameter.hasParameterAnnotation(ApiAuth.class);
    }


    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory factory) throws Exception {

        /**
         * 简单检验登录
         *
         * @see  com.afeng.web.module.app.controller.ApiTestController#authSimpleLogin(String)
         */
        if (parameter.hasParameterAnnotation(ApiAuth.class)) {
            ApiAuth apiAuth = parameter.getParameterAnnotation(ApiAuth.class);
            if (apiAuth != null && apiAuth.isAuthLogin() && apiAuth.isUseSimpleLogin()) {
                HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
                if (request != null) {
                    //获取token
                    String token = request.getParameter(ApiInterceptor.tokenKey);
                    if (StringUtils.isEmpty(token)) {
                        throw ApiException.toAccessDenied("token不能为空");
                    }
                    String uid = JwtUtil.authTokenByAES(token);
                    if (StringUtils.isEmpty(uid)) {
                        throw ApiException.toAccessDenied("token验证失败");
                    }
                    return uid;
                }
            }
        }

        return null;
    }
}

