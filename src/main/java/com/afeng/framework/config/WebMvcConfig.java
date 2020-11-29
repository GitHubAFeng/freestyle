
package com.afeng.framework.config;

import com.afeng.framework.filter.AdminInterceptor;
import com.afeng.framework.filter.ApiInterceptor;
import com.afeng.framework.filter.AuthHandlerMethodArgumentResolver;
import com.afeng.framework.filter.rate.RateLimiterInterceptor;
import com.afeng.module.common.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * Web配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ApiInterceptor apiInterceptor;

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Autowired
    private RateLimiterInterceptor rateLimiterInterceptor;

    @Autowired
    private AuthHandlerMethodArgumentResolver authHandlerMethodArgumentResolver;

    @Value("${swagger.open:true}")
    private Boolean isOpen;

    /**
     * 系统欢迎页面
     * 类似于web.xml里面的welecome-file
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/admin/login");//首页路由设置
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * 配置资源放行
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /*本地文件上传路径 */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**").addResourceLocations("file:" + AFengConfig.getProfile() + "/");

        /*
         * 配置swagger映射路径
         */
        if (isOpen) {
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    /**
     * 配置CORS跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").maxAge(3600);
    }

    /**
     * 添加interceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor).addPathPatterns("/" + ApiInterceptor.API_PATH + "/**");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/" + AdminInterceptor.API_PATH + "/**").excludePathPatterns("/login");
        /*registry.addInterceptor(rateLimiterInterceptor).addPathPatterns("/**").excludePathPatterns("/page/static/**","swagger-ui.html","/webjars/**","/404,/500,/404,/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,*.mp3,*.html,*.htm,*.woff,/druid,/druid/*,/monitoring,/monitoring/*");*/
    }

    /**
     * 参数切面
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authHandlerMethodArgumentResolver);
    }


}
