package com.afeng.web.module.app.controller;

import com.afeng.rpc.service.TestRPCService;
import com.afeng.web.common.cache.JedisUtil;
import com.afeng.web.common.log.ApiLogUtils;
import com.afeng.web.common.mq.config.MqEventModel;
import com.afeng.web.common.mq.config.MqEventType;
import com.afeng.web.common.rpc.RpcUtils;
import com.afeng.web.common.token.ApiSessionUtil;
import com.afeng.web.common.token.JwtUtil;
import com.afeng.web.common.util.QRCodeUtil;
import com.afeng.web.common.util.RedisMQUtil;
import com.afeng.web.common.util.SignUtil;
import com.afeng.web.common.util.SpringUtils;
import com.afeng.web.framework.annotation.ApiAuth;
import com.afeng.web.framework.core.BaseApiController;
import com.afeng.web.framework.core.constant.ApiResult;
import com.afeng.web.module.app.model.AppUser;
import com.afeng.web.module.app.service.AppUserService;
import com.afeng.web.module.common.dao.BaseDao;
import com.afeng.web.module.common.dao.CommonDao;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.redisson.api.RLock;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;
import org.redisson.api.RemoteInvocationOptions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.awt.image.BufferedImage;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ApiTestController
 * Description:
 * date: 2020/4/13 22:45
 *
 * @author afeng
 * @since JDK 1.8
 */
@Slf4j
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
    @Autowired
    private BaseDao baseDao;

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

    /**
     * 数据库操作测试
     *
     * @author AFeng
     * @createDate 2020/11/30 11:59
     **/
    @ApiAuth(isAuthSign = false)
    @GetMapping("/bd")
    public ApiResult bd(String uid) throws Exception {
        Map<String, Object> where = new HashMap<>();
        where.put("oper_id", 1);
        Map<String, Object> Param = new HashMap<>();
        Param.put("oper_location", 1);
        Param.put("title", "title as logTitle");
        Param.put("dept_name", "isnull(dept_name,'测试') as deptName");
        Map<String, Object> sysOperLog = commonDao.selectOneByParam("sys_oper_log", Param, where);
        Object object = sqlSessionTemplate.selectOne("findOperLogById", 1L);
        where = new HashMap<>();
        where.put("operId", 1);
        Map<String, Object> logData = baseDao.selectOne("findOperLog", where);

        //在sqlserver下不稳定，时行时不行，知道原因的同学请告诉我
//        Param = new HashMap<>();
//        Param.put("role_id", 2);
//        Param.put("menu_id", 1);
//        commonDao.insertIgnore("sys_role_menu", Param);

        Map<String, Object> reMap = new HashMap<>();
        reMap.put("sqlSessionTemplate", object);
        reMap.put("sysOperLog", sysOperLog);
        reMap.put("logData", logData);

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


    /**
     * 返回二维码图片流
     *
     * @author AFeng
     * @createDate 2020/12/28 17:54
     **/
    @RequestMapping("/appGetQrCode")
    public void appGetQrCode(HttpServletResponse response) {
        try {
            String data = getParameter("data");//转码链接
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(data)) {
                data = URLDecoder.decode(data, "UTF-8");
                BufferedImage image = QRCodeUtil.createImage(data);
                ImageIO.write(image, "png", response.getOutputStream());

                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setContentType("image/png");
                response.flushBuffer();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    @Autowired
    private RedissonClient redissonClient;

    /**
     * 分布式锁
     *
     * @author AFeng
     * @createDate 2020/12/29 16:45
     **/
    @GetMapping("/testRedisLock")
    public ApiResult testRedisLock() {
        String uid = getParameter("uid");
        RLock lock = null;
        try {
            String lockKey = "RedisLock:" + "testRedisLock:" + uid;
            lock = redissonClient.getFairLock(lockKey);//公平锁
            if (lock != null) {
                // 尝试加锁，最多等待3000毫秒，上锁以后6000毫秒自动解锁
                Boolean status = lock.tryLock(3000, 6000, TimeUnit.MILLISECONDS);
                if (status) {
                    log.info("==============获得分布式锁成功===============");
                    System.err.println("开始执行业务逻辑");
                    TimeUnit.SECONDS.sleep(20);
                    System.err.println("业务逻辑执行完毕");
                } else {
                    log.info("==============获得分布式锁失败===============");
                }
            } else {
                throw new IllegalArgumentException("==============分布式锁配置参数错误===============");
            }
        } catch (InterruptedException e) {
            log.error("==============处理分布式锁异常===============" + e.getMessage());
        } finally {
            if (lock != null && lock.isLocked()) {// 是否还是锁定状态
                if (lock.isHeldByCurrentThread()) {
                    log.info("==============释放分布式锁成功===============");
                    lock.unlock();
                } else {
                    log.info("==============系统已回收分布式锁=============");
                }
            } else {
                log.info("==============系统已回收分布式锁=============");
            }
        }
        log.debug("调用testRedisLock……");
        return success();
    }


    /**
     * RPC远程服务调用测试
     *
     * @author AFeng
     * @createDate 2020/12/30 16:18
     **/
    @GetMapping("/testRedissonRpc")
    public ApiResult testRedissonRpc() throws Exception {
//        //获取Redisson远程服务
//        RRemoteService remoteService = redissonClient.getRemoteService();
//        //应答回执超时1秒钟，远程执行超时30秒钟
//        RemoteInvocationOptions options = RemoteInvocationOptions.defaults();
//        //拿到生产端的接口
//        TestRPCService testRPCService = remoteService.get(TestRPCService.class, options);
//        //调用
//        String msg = testRPCService.testStr("哈喽");
//        return success(msg);
        return success(RpcUtils.getInstance().invoke(TestRPCService.class, "testStr", "你好RPC"));
    }


    @GetMapping("/testRpc")
    public ApiResult testRpc() {
        String text = getParameter("text");
        String methodName = getParameter("method");
        String serviceName = "TestRPC";
        Object invoke = null;
        try {
            invoke = RpcUtils.getInstance().invoke(serviceName, methodName, text);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success(invoke);
    }

}
