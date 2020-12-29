package com.afeng.common.log;

import com.afeng.common.util.MapDataUtil;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 输出请求信息
 *
 * @author afeng
 * @date 2020/5/10 13:29
 */
@Aspect
@Component
public class LogRecordAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);

    @Value("${auto.log.enabled:true}")
    private Boolean isEnabled;


    // 定义切点Pointcut
    @Around("execution(* com.afeng.module.*.controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        if (!isEnabled) {
            return pjp.proceed();
        }
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if (sra == null) return pjp.proceed();
        HttpServletRequest request = sra.getRequest();
        String methodName = pjp.getSignature().getName();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        Object[] args = pjp.getArgs();
        String params = "";
        //获取请求参数集合并进行遍历拼接
        if (args.length > 0) {
            if ("POST".equals(method)) {
                Object object = args[0];
                Map map = MapDataUtil.getKeyAndValue(object);
                params = JSON.toJSONString(map);
            } else if ("GET".equals(method)) {
                params = queryString;
            }
        }


        logger.debug(methodName + "[" + method + "] -> url:" + url);
        logger.debug(methodName + "[" + method + "] -> params:" + params);

        long startTimeMillis = System.currentTimeMillis();
        //调用 proceed() 方法才会真正的执行实际被代理的方法
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        long execTimeMillis = System.currentTimeMillis() - startTimeMillis;
        logger.debug(methodName + "[" + method + "] -> execTime:" + execTimeMillis + "ms");
        logger.debug(methodName + "[" + method + "] -> result:" + JSON.toJSONString(result));
        return result;
    }

}
