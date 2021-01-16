package com.afeng.web.framework.filter;


import com.afeng.web.common.cache.JedisUtil;
import com.afeng.web.common.token.AntiReplayAttackUtil;
import com.afeng.web.common.token.ApiSessionUtil;
import com.afeng.web.common.token.JwtUtil;
import com.afeng.web.common.util.SignUtil;
import com.afeng.web.framework.annotation.ApiAuth;
import com.afeng.web.framework.exception.ApiException;
import com.afeng.web.framework.filter.rate.RedisRateLimiter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * API权限验证
 */
@Component
public class ApiInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final String API_PATH = "api";//API路径名
    private static final String signSecret = "free2020";//签名盐值
    public static final String signKey = "sign";//签名请求头或URL请求参数key
    private static final String sessionHeaderKey = "session";//给客户端响应头或请求头存入session的键
    private static final String timeKey = "ts";//给客户端响应头或请求头或URL请求参数存入时间戳的键，毫秒
    public static final String tokenKey = "token";//给客户端响应头或URL请求参数存入token的键

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private RedissonClient redisClient;

    /**
     * 权限验证，默认需要验证简单签名sign与简单登录token
     * 简单检验登录
     *
     * @author AFeng
     * @createDate 2020/11/26 14:55
     * @see com.afeng.web.module.app.controller.ApiTestController#authSimpleLogin(String)
     * @see com.afeng.web.framework.filter.AuthHandlerMethodArgumentResolver#resolveArgument
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod;
        if (handler instanceof HandlerMethod) {
            handlerMethod = (HandlerMethod) handler;
        } else {
            return true;
        }
        // 获取方法上的注解
        ApiAuth apiAuth = handlerMethod.getMethodAnnotation(ApiAuth.class);
        if (apiAuth == null) {
            // 如果方法上的注解为空 则获取类的注解
            apiAuth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(ApiAuth.class);
        }
        if (apiAuth != null) {
            //判断全局限流处理
            if (apiAuth.isUseGlobalRateLimiter() || apiAuth.isUseUserRateLimiter()) {
                rateLimiter(apiAuth, request, response);
            }
            //验证登录token
            if (apiAuth.isAuthLogin()) {
                if (!apiAuth.isUseSimpleLogin()) {
                    verifyLogin(apiAuth, request, response);
                }
            }
            //验证签名
            if (apiAuth.isAuthSign()) {
                if (apiAuth.isUseSimpleSign()) {
                    verifySimpleSign(apiAuth, request, response);
                } else {
                    verifySign(apiAuth, request, response);
                }
            }
        }

        return true;
    }


    /**
     * 简单检验签名，需要业务自行处理防重放，签名算法 = MD5( data(请求参数) + signSecret )
     *
     * @author AFeng
     * @createDate 2020/11/19 17:30
     **/
    private void verifySimpleSign(ApiAuth apiAuth, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取sign
        String sign = request.getParameter(signKey);
        if (StringUtils.isEmpty(sign)) {
            throw ApiException.toAccessDenied("sign不能为空");
        }

        //签名
        String signAuth = SignUtil.createSimpleSign(request, signSecret);
        logger.debug("signAuth = " + signAuth);
        if (!StringUtils.equals(signAuth, sign)) {
            throw ApiException.toAccessDenied("签名验证失败");
        }

    }


    /*
     * 权限流程：
     * 1、登录时检查是否有token，如无则走登录接口重新获取，如有则检验是否有效，如无效则重新登录。
     * 2、token有效则下发会话Session，存入redis有效时间为60分钟，客户端请求需要在头部携带此Session，并参与md5签名计算
     * 3、客户端请求时检查Session是否存在，如无则重新登录，如有则刷新过期时间，并把md5签名存入redis同样30分钟
     * 4、防重放攻击(没必要每次请求都防重放，某些重要接口防御即可)，客户端请求时判断redis是否已经存在md5签名，如存在则请求无效
     * 客户端操作：
     * 1、请求时需要在请求头带上登录时（响应头）获取的token与session，并且带上ts时间戳毫秒与sign签名
     * 2、各请求头参数key对应：
     * Authorization = token_value
     * session = session_value
     * ts = ts_value
     * sign = sign_value
     * 3、每次请求完成后需要在响应头中判断是否存在session，如果有则需要使用此session替换本地的session缓存，否则会话失效
     * 4、或者定期例如每小时主动请求 http://localhost:9090/refreshToken 获取有效session
     */


    /**
     * Description: 验证参数签名
     * 签名算法 = MD5( session + ts(时间戳) + data(请求参数) + signSecret ) 大写
     *
     * @author afeng
     * @date 2020/1/19 22:39
     */
    private void verifySign(ApiAuth apiAuth, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取session
        String session = request.getHeader(sessionHeaderKey);
        if (StringUtils.isEmpty(session)) {
            throw ApiException.toAccessDenied("会话session丢失");
        }
        //获取ts(时间戳)
        String ts = request.getHeader(timeKey);
        if (StringUtils.isEmpty(ts)) {
            throw ApiException.toAccessDenied("时间ts丢失");
        }

        //签名
        String source = session + ts + SignUtil.createMethodArgumentToString(request) + signSecret;
        String signAuth = DigestUtils.md5Hex(source).toUpperCase();
        logger.debug("signAuth = " + signAuth);
        if (!StringUtils.equals(signAuth, request.getHeader(signKey))) {
            throw ApiException.toAccessDenied("签名验证失败");
        }

        //防重放攻击
        if (apiAuth.isAntiReplayAttack()) {
            String userId = JwtUtil.getUserId();
            AntiReplayAttackUtil attackUtil = AntiReplayAttackUtil.build(jedisUtil);

            //检查Session
            ApiSessionUtil apiSessionUtil = ApiSessionUtil.build(jedisUtil);
            if (apiSessionUtil.checkSession(userId)) {
                if (!apiSessionUtil.authSession(userId, session)) {
                    throw ApiException.toAccessDenied("身份验证失败");
                }
            } else {
                String sessionAuth = apiSessionUtil.createSession(userId);
                response.setHeader(sessionHeaderKey, sessionAuth);
                //清除旧的防重放缓存
                attackUtil.cleanSignCacheLog(userId);
            }

            //判断重放攻击
            if (attackUtil.checkSignCacheLog(userId, signAuth)) {
                throw ApiException.toAccessDenied("非法请求");
            } else {
                //存入缓存
                attackUtil.addSignCacheLog(userId, signAuth);
            }
        }

    }

    /**
     * 登录验证
     *
     * @author afeng
     */
    private void verifyLogin(ApiAuth apiAuth, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //从header中获取token
        String token = JwtUtil.getToken();
        //token为空
        if (StringUtils.isBlank(token)) {
            throw ApiException.toAccessDenied("token不能为空");
        }
        //查询token信息
        String encrypt = JwtUtil.queryByToken(token);
        if (StringUtils.isBlank(encrypt)) {
            throw ApiException.toAccessDenied("token信息丢失");
        }
        String userId = JwtUtil.getUserId();
        int re = JwtUtil.verifyToken(token, encrypt);
        if (re < 0) {
            throw ApiException.toAccessDenied("token失效，请重新登录");
        } else if (re == 2) {
            //刷新token
            String refreshToken = JwtUtil.createToken(userId);
            //设置token到响应头由前端获取
            response.setHeader(tokenKey, refreshToken);
        }
        //检查Session
        ApiSessionUtil apiSessionUtil = ApiSessionUtil.build(jedisUtil);
        //设置token到响应头由前端获取
        if (apiSessionUtil.checkSession(userId)) {
            response.setHeader(sessionHeaderKey, apiSessionUtil.getSession(userId));
        } else {
            //生成新的Session
            response.setHeader(sessionHeaderKey, apiSessionUtil.createSession(userId));
            //清除旧的防重放缓存
            AntiReplayAttackUtil.build(jedisUtil).cleanSignCacheLog(userId);
        }

    }

    /**
     * 限流器
     */
    private void rateLimiter(ApiAuth apiAuth, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int limit = ObjectUtils.isEmpty(apiAuth.limitCount()) ? RedisRateLimiter.REDISSON_RATE_LIMITER_PERMITS : apiAuth.limitCount();
        long timeout = ObjectUtils.isEmpty(apiAuth.limitTimeout()) ? RedisRateLimiter.REDISSON_RATE_LIMITER_TIMEOUT : apiAuth.limitTimeout();
        String rateKey = StringUtils.isEmpty(apiAuth.limitRateKey()) ? request.getRequestURI() : apiAuth.limitRateKey();//key为空判断，默认为uri
        //判断用户限流处理
        if (apiAuth.isUseUserRateLimiter()) {
            rateKey = rateKey + "_UID_" + JwtUtil.getUserId();
        }
        rateKey = RedisRateLimiter.REDISSON_RATE_LIMITER_KEY + rateKey;
        String timeUnit = ObjectUtils.isEmpty(apiAuth.limitTimeUnit()) ? RedisRateLimiter.REDISSON_RATE_LIMITER_TIMEUNIT : apiAuth.limitTimeUnit();
        boolean isAllow = RedisRateLimiter.tryAcquire(redisClient, rateKey, limit, timeout, timeUnit);
        if (!isAllow) {
            logger.debug("很抱歉，服务器繁忙，请稍后重试!");
            throw new ApiException("服务器繁忙，请稍后重试!", HttpStatus.SERVICE_UNAVAILABLE.value());
        }
    }


}
