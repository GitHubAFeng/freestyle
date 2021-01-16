package com.afeng.web.module.admin.aspectj;

import com.afeng.web.common.util.ServletUtils;
import com.afeng.web.common.util.SpringUtils;
import com.afeng.web.module.admin.model.Logininfor;
import com.afeng.web.module.admin.model.OnlineSession;
import com.afeng.web.module.admin.model.OperLog;
import com.afeng.web.module.admin.model.UserOnline;
import com.afeng.web.module.admin.service.IOperLogService;
import com.afeng.web.module.admin.service.IUserOnlineService;
import com.afeng.web.module.admin.service.LogininforServiceImpl;
import com.afeng.web.module.common.constant.Constants;
import com.afeng.web.module.common.utils.*;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author liuhulu
 */
public class AsyncFactory {
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 同步session到数据库
     *
     * @param session 在线用户会话
     * @return 任务task
     */
    public static FutureTask<Object> syncSessionToDb(final OnlineSession session) {
        FutureTask<Object> task = new FutureTask<Object>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    UserOnline online = new UserOnline();
                    online.setSessionId(String.valueOf(session.getId()));
                    online.setDeptName(session.getDeptName());
                    online.setLoginName(session.getLoginName());
                    online.setStartTimestamp(session.getStartTimestamp());
                    online.setLastAccessTime(session.getLastAccessTime());
                    online.setExpireTime(session.getTimeout());
                    online.setIpaddr(session.getHost());
                    online.setLoginLocation(AddressUtils.getRealAddressByIP(session.getHost()));
                    online.setBrowser(session.getBrowser());
                    online.setOs(session.getOs());
                    online.setStatus(session.getStatus());
                    online.setSession(session);
                    SpringUtils.getBean(IUserOnlineService.class).saveOnline(online);

                } catch (Throwable e) {
                    sys_user_logger.error("线程池异步 记录日志失败", e);
                }
                return "ok";
            }
        });
        return task;
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static FutureTask<Object> recordOper(final OperLog operLog) {
        FutureTask<Object> task = new FutureTask<Object>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    // 远程查询操作地点
                    if (StringUtils.isNotEmpty(operLog.getOperIp())) {
                        operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                    } else {
                        operLog.setOperLocation("内网IP");
                    }
                    SpringUtils.getBean(IOperLogService.class).insertOperlog(operLog);
                } catch (Throwable e) {
                    sys_user_logger.error("线程池异步 记录日志失败", e);
                }
                return "ok";
            }
        });
        return task;
    }

    /**
     * 记录登陆信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static FutureTask<Object> recordLogininfor(final String username, final String status, final String ip, final String message, final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        FutureTask<Object> task = new FutureTask<Object>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    String address = AddressUtils.getRealAddressByIP(ip);
                    StringBuilder s = new StringBuilder();
                    s.append(LogUtils.getBlock(ip));
                    s.append(address);
                    s.append(LogUtils.getBlock(username));
                    s.append(LogUtils.getBlock(status));
                    s.append(LogUtils.getBlock(message));
                    // 打印信息到日志
                    sys_user_logger.info(s.toString(), args);
                    // 获取客户端操作系统
                    String os = userAgent.getOperatingSystem().getName();
                    // 获取客户端浏览器
                    String browser = userAgent.getBrowser().getName();
                    // 封装对象
                    Logininfor logininfor = new Logininfor();
                    logininfor.setLoginName(username);
                    logininfor.setIpaddr(ip);
                    logininfor.setLoginLocation(address);
                    logininfor.setBrowser(browser);
                    logininfor.setOs(os);
                    logininfor.setMsg(message);
                    // 日志状态
                    if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
                        logininfor.setStatus(Constants.SUCCESS);
                    } else if (Constants.LOGIN_FAIL.equals(status)) {
                        logininfor.setStatus(Constants.FAIL);
                    }
                    // 插入数据
                    SpringUtils.getBean(LogininforServiceImpl.class).insertLogininfor(logininfor);
                } catch (Throwable e) {
                    sys_user_logger.error("线程池异步 记录日志失败", e);
                }
                return "ok";
            }
        });
        return task;
    }
}
