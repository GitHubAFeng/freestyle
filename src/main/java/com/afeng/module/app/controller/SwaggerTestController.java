package com.afeng.module.app.controller;

import com.afeng.framework.annotation.AdminAuth;
import com.afeng.framework.core.constant.Constants;
import com.afeng.framework.filter.rate.RateLimiter;
import com.afeng.module.admin.controller.BaseController;
import com.afeng.module.admin.model.AjaxResult;
import com.afeng.module.common.utils.StringUtils;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 高阶功能演示
 */
@Api(tags = "高阶功能演示", description = "高阶功能演示")
@RestController
@RequestMapping("/advance")
public class SwaggerTestController extends BaseController {


    /**
     * 测试redisLock
     *
     * @throws InterruptedException DistRedisLock:lockKey1lockKey
     */
//    @RequestMapping(value = "/testRedisLock")
//    @ResponseBody
//    @RedisLock(lockKey = "lockKey")
//    @ApiOperation(value = "Redis分布式锁,简单使用", httpMethod = "GET", notes = "Redis分布式锁,简单使用", response = Boolean.class)
//    public Boolean testRedisLock(
//            @ApiParam(required = true, value = "key1", name = "key1", defaultValue = "1") @RequestParam(value = "key1") @RedisLockKey(order = 0) int key1,
//            @ApiParam(required = true, value = "key2", name = "key2", defaultValue = "2") @RequestParam(value = "key2") @RedisLockKey(order = 1) int key2)
//            throws InterruptedException {
//        System.err.println("开始执行业务逻辑");
//        TimeUnit.SECONDS.sleep(20);
//        System.err.println("业务逻辑执行完毕");
//        return true;
//    }

    /**
     * 限流器使用
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "redisson分布式，限流器使用", notes = "redisson分布式，限流器使用", httpMethod = "GET")
    @RequestMapping(value = "/rateLimiter", method = RequestMethod.GET)
    @RateLimiter(limit = 1, timeUnit = Constants.RateLimiterTimeUnit.SECONDS, timeout = 5, rateKey = "formatCode")
    public Boolean rateLimiter(HttpServletRequest req) {
        System.err.println("rateLimiter");
        return true;
    }


    private final static Map<Integer, UserEntity> users = new LinkedHashMap<Integer, UserEntity>();

    {
        users.put(1, new UserEntity(1, "admin", "admin123", "15888888888"));
        users.put(2, new UserEntity(2, "ry", "admin123", "15666666666"));
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public AjaxResult userList() {
        List<UserEntity> userList = new ArrayList<UserEntity>(users.values());
        return AjaxResult.success(userList);
    }

    @ApiOperation("获取用户详细")
    @GetMapping("/{userId}")
    public AjaxResult getUser(@PathVariable Integer userId) {
        if (!users.isEmpty() && users.containsKey(userId)) {
            return AjaxResult.success(users.get(userId));
        } else {
            return error("用户不存在");
        }
    }

    @ApiOperation("新增用户")
    @PostMapping("/save")
    public AjaxResult save(UserEntity user) {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId())) {
            return error("用户ID不能为空");
        }
        return AjaxResult.success(users.put(user.getUserId(), user));
    }

    @ApiOperation("更新用户")
    @PutMapping("/update")
    public AjaxResult update(UserEntity user) {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId())) {
            return error("用户ID不能为空");
        }
        if (users.isEmpty() || !users.containsKey(user.getUserId())) {
            return error("用户不存在");
        }
        users.remove(user.getUserId());
        return AjaxResult.success(users.put(user.getUserId(), user));
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("/{userId}")
    public AjaxResult delete(@PathVariable Integer userId) {
        if (!users.isEmpty() && users.containsKey(userId)) {
            users.remove(userId);
            return success();
        } else {
            return error("用户不存在");
        }
    }

    @ApiOperation("管理后台权限控制示例，需要在admin路径下")
    @AdminAuth(hasPermission = "system:role:editd")
    @GetMapping("/hasPermission")
    public AjaxResult hasPermission() {
        return success();
    }


}

@ApiModel("用户实体")
class UserEntity {
    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户手机")
    private String mobile;

    public UserEntity() {

    }

    public UserEntity(Integer userId, String username, String password, String mobile) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
