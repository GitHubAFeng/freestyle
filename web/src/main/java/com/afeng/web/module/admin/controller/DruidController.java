package com.afeng.web.module.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * druid 监控
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/admin/monitor/data")
public class DruidController extends BaseController {

    /**
    * 打开SQL监控页面 登录afeng 密码free2020
    * @author afeng
    * @date 2020/11/28 13:58
    * @param
    * @return
    */
    @GetMapping()
    public String index() {
        return redirect("/admin/druid/index");
    }
}
