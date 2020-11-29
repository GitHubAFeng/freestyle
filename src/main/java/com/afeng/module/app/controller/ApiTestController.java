package com.afeng.module.app.controller;

import com.afeng.common.cache.JedisUtil;
import com.afeng.common.log.ApiLogUtils;
import com.afeng.common.mq.config.MqEventModel;
import com.afeng.common.mq.config.MqEventType;
import com.afeng.common.token.ApiSessionUtil;
import com.afeng.common.token.JwtUtil;
import com.afeng.common.util.RedisMQUtil;
import com.afeng.common.util.SignUtil;
import com.afeng.framework.annotation.ApiAuth;
import com.afeng.framework.core.BaseApiController;
import com.afeng.framework.core.constant.ApiResult;
import com.afeng.module.app.model.AppUser;
import com.afeng.module.app.service.AppUserService;
import com.afeng.module.common.dao.CommonDao;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * ClassName: ApiTestController
 * Description:
 * date: 2020/4/13 22:45
 *
 * @author afeng
 * @since JDK 1.8
 */
@Validated
@RestController
@RequestMapping("/api/test")
public class ApiTestController extends BaseApiController {


    @Autowired
    private AppUserService appUserService;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private CommonDao commonDao;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    //测试redis消息队列操作
    @ApiAuth(isAuthSign = false)
    @GetMapping("/redisMqProducerSend")
    public ApiResult redisMqProducerSend() {
        // 后续生成的私信内容需要这些额外信息
        Map<String, Object> articleInfo = new HashMap<>();
        articleInfo.put("uid", getParameter("uid"));
        articleInfo.put("articleName", "测试");
        articleInfo.put("commentContent", "它支持添加元素");

        redisTemplate.convertAndSend("test", JSON.toJSONString(new MqEventModel()
                .setEventType(MqEventType.COMMENT)
                .setExtraInfo(articleInfo)));


        stringRedisTemplate.convertAndSend("test01", "aaaaaa");

        RedisMQUtil.getInstance().seedEvent("test01", "aaaaaa");

        return success();
    }

    @ApiAuth(isAuthSign = false)
    @GetMapping("/redis")
    public ApiResult other() {
        jedisUtil.set("test", "aaaaaaaaaaaaaa", 60 * 5);
        return success(jedisUtil.get("test"));
    }

    @ApiAuth(isAuthSign = false)
    @GetMapping("/token")
    public ApiResult token() {
        String uid = getParameter("uid");
        String refreshToken = JwtUtil.createToken(uid);
        return success(refreshToken);
    }

    @ApiAuth(isAuthSign = false)
    @GetMapping("/authToken")
    public ApiResult authToken() {
        return success(JwtUtil.getUserId());
    }

    @ApiAuth(isAuthSign = false)
    @GetMapping("/bd")
    public ApiResult bd(String uid) {
        Map<String, Object> where = new HashMap<>();
        where.put("oper_id", 1);
        Map<String, Object> Param = new HashMap<>();
        Param.put("oper_location", 1);
        Param.put("title", "title as logTitle");
        Param.put("dept_name", "isnull(dept_name,'测试') as deptName");
        Map<String, Object> sysOperLog = commonDao.selectOneByParam("sys_oper_log", Param, where);
        Object object = sqlSessionTemplate.selectOne("findOperLogById", 1L);

        Map<String, Object> reMap = new HashMap<>();
        reMap.put("sqlSessionTemplate", object);
        reMap.put("sysOperLog", sysOperLog);

        return success(reMap);
    }

    /**
     * 测试签名
     *
     * @author AFeng
     * @createDate 2020/11/25 15:04
     **/
    @ApiAuth(isUseSimpleSign = false)
    @GetMapping("/authSign")
    public ApiResult authSign() {
        return success();
    }

    /**
     * 测试签名并防重放
     *
     * @author AFeng
     * @createDate 2020/11/25 15:04
     **/
    @ApiAuth(isUseSimpleSign = false, isAntiReplayAttack = true)
    @GetMapping("/antiReplayAttack")
    public ApiResult antiReplayAttack() {
        return success();
    }

    /**
     * 测试简单签名验证
     * http://localhost:9090/api/test/authSimpleSign?sign=51cbb4eb55b538e3898716bfdf170b38&uid=11&a=22&b=33
     *
     * @author AFeng
     * @createDate 2020/11/25 15:04
     **/
    @GetMapping("/authSimpleSign")
    public ApiResult authSimpleSign() {
        return success();
    }

    /**
     * 创建简单签名
     * http://localhost:9090/api/test/simpleSign?uid=11&a=22&b=33
     *
     * @author AFeng
     * @createDate 2020/11/25 15:04
     **/
    @ApiAuth(isAuthSign = false)
    @GetMapping("/simpleSign")
    public ApiResult simpleSign() throws Exception {
        return success(SignUtil.createSimpleSign(request, "free2020"));
    }

    /**
     * 创建简单登录token
     * http://localhost:9090/api/test/simpleLoginToken?uid=11
     *
     * @author AFeng
     * @createDate 2020/11/25 14:40
     **/
    @ApiAuth(isAuthSign = false)
    @GetMapping("/simpleLoginToken")
    public ApiResult simpleLoginToken(String uid) {
        if (StringUtils.isEmpty(uid)) return fail();
        return success(JwtUtil.createTokenByAES(uid));
    }

    /**
     * 简单登录验证
     * http://localhost:9090/api/test/authSimpleLogin?token=8c7d16193d0425645abf1777fe038f66e7809d2e88dcc9bdd4a4c5889b29d944
     *
     * @author AFeng
     * @createDate 2020/11/25 14:26
     **/
    @ApiAuth(isAuthSign = false)
    @GetMapping("/authSimpleLogin")
    public ApiResult authSimpleLogin(@ApiAuth String tokenToUid) {
        return success(tokenToUid == null ? "token失效" : tokenToUid);
    }

    /**
     * 测试登录流程（比较复杂一些）
     *
     * @author AFeng
     * @createDate 2020/11/25 14:40
     **/
    @ApiAuth(isAuthSign = false)
    @GetMapping("/login")
    public ApiResult login(String uid) {
        if (StringUtils.isEmpty(uid)) return fail();
        String token = JwtUtil.createToken(uid);
        String session = ApiSessionUtil.build(jedisUtil).getSession(uid);
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("token", token);
        reMap.put("session", session);

        //测试登录事件
        AppUser appUser = new AppUser();
        appUser.setId(2);
        appUserService.AppUserLogin(appUser);

        return success(reMap);

    }

    /**
     * 登录验证
     *
     * @author AFeng
     * @createDate 2020/11/25 14:26
     **/
    @ApiAuth(isUseSimpleLogin = false)
    @GetMapping("/authLogin")
    public ApiResult authLogin() {
        return success();
    }

    /**
     * 测试参数验证
     *
     * @author AFeng
     * @createDate 2020/11/25 14:27
     **/
    @ApiAuth(isAuthSign = false)
    @GetMapping("testData")
    public ApiResult testData(@NotEmpty(message = "用户UID不能为空") String uid, @NotEmpty(message = "test不能为空") String test) {
        return success(uid);
    }

    /**
     * 测试全局流量控制
     *
     * @author AFeng
     * @createDate 2020/11/25 14:27
     **/
    @ApiAuth(isAuthSign = false, isUseGlobalRateLimiter = true)
    @GetMapping("/rateLimiterUrl")
    public ApiResult rateLimiterUrl() {
        return success();
    }

    /**
     * 测试单用户流量控制
     *
     * @author AFeng
     * @createDate 2020/11/25 14:27
     **/
    @ApiAuth(isAuthSign = false, isUseUserRateLimiter = true)
    @GetMapping("/rateLimiterUser")
    public ApiResult rateLimiterUser() {
        return success();
    }

    /**
     * 测试多线程
     *
     * @author AFeng
     * @createDate 2020/11/25 14:27
     **/
    @ApiAuth(isAuthSign = false)
    @GetMapping("testTaskExecutor")
    public ApiResult testTaskExecutor() {
        FutureTask<Object> task = new FutureTask<Object>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    ApiLogUtils.debugPrint("打印");
                } catch (Throwable e) {
                }
                return "ok";
            }
        });
        taskExecutor.execute(task);    //为提升访问速率, 日志记录采用异步的方式进行.

        return success();
    }


}
