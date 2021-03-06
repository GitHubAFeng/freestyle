package com.afeng.web.module.common.controller;

import com.afeng.web.common.cache.JedisUtil;
import com.afeng.web.common.token.AntiReplayAttackUtil;
import com.afeng.web.common.token.ApiSessionUtil;
import com.afeng.web.common.token.JwtUtil;
import com.afeng.web.framework.core.BaseApiController;
import com.afeng.web.framework.core.constant.ApiResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

/**
 * swagger 接口
 *
 * @author ruoyi
 */
@Validated
@RequestMapping("/")
@Controller
public class HomeController extends BaseApiController {

    @Autowired
    private JedisUtil jedisUtil;

    @ApiOperation("处理跨域，主要是swagger会默认调用这接口")
    @GetMapping("/csrf")
    @ResponseBody
    public ApiResult csrf() {
        return success();
    }


    @ApiOperation("刷新API接口的权限session")
    @GetMapping("/refreshToken")
    @ResponseBody
    public ApiResult refreshToken() {
        String userId = JwtUtil.getUserId();
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("session", ApiSessionUtil.build(jedisUtil).refreshSession(userId));
        //清除旧的防重放缓存
        AntiReplayAttackUtil.build(jedisUtil).cleanSignCacheLog(userId);
        return success(reMap);
    }

    @ApiOperation("清除某个缓存")
    @PostMapping("/redis/cleanCacheByKey")
    @ResponseBody
    public ApiResult cleanCacheByKey(@NotEmpty(message = "key不能为空") String key) {
        return success(jedisUtil.del(key));
    }

    @ApiOperation("清除某类缓存")
    @PostMapping("/redis/cleanCacheByLikeKey")
    @ResponseBody
    public ApiResult cleanCacheByLikeKey(@NotEmpty(message = "key不能为空") String key) {
        return success(jedisUtil.delByScan(key));
    }

}
