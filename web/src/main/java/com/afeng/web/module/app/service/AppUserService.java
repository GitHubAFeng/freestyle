package com.afeng.web.module.app.service;

import com.afeng.web.common.log.ApiLogUtils;
import com.afeng.web.common.util.SpringUtils;
import com.afeng.web.module.app.event.AppUserLoginEvent;
import com.afeng.web.module.app.model.AppUser;
import com.afeng.web.module.common.dao.CommonDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AppUserService {

    @Autowired
    private CommonDao commonDao;

    public Map<String, Object> test() {
        Map<String, Object> where = new HashMap<>();
        where.put("oper_id", 1);
        Map<String, Object> Param = new HashMap<>();
        Param.put("oper_location", 1);
        Param.put("title", "title as logTitle");
        Param.put("dept_name", "isnull(dept_name,'测试') as deptName");
        return commonDao.selectOneByParam("sys_oper_log", Param, where);
    }

    //    @Transactional //使用数据库事务
    public void AppUserLogin(AppUser appUser) {

        //发布用户登录事件
        SpringUtils.getApplicationContext().publishEvent(new AppUserLoginEvent(this, appUser));
    }

    /*
     * 1、监听器方法中一定要try-catch异常，否则会造成发布事件(有事务的)的方法进行回滚
     * 2、可以使用@Order注解来控制多个监听器的执行顺序，@Order传入的值越小，执行顺序越高
     * 3、对于需要进行s事务监听或不想try-catch runtime异常，可以使用@TransactionalEventListener注解
     * 4、使用@Async实现异步监听
     * https://blog.csdn.net/lzx_longyou/article/details/56853570
     */

    //监听登录事件
    @EventListener
    @Order(1)
    @Async
    public void onAppUserLoginEvent(AppUserLoginEvent event) {
        try {
            AppUser user = event.getEventData();
            ApiLogUtils.debugPrint("===> 收到事件: " + user.getId() + "，线程名为： " + Thread.currentThread().getName());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
