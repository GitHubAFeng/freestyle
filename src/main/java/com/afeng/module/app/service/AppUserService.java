package com.afeng.module.app.service;

import com.afeng.common.log.ApiLogUtils;
import com.afeng.common.util.SpringUtils;
import com.afeng.module.app.event.AppUserLoginEvent;
import com.afeng.module.app.model.AppUser;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

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
            e.printStackTrace();
        }
    }

}
