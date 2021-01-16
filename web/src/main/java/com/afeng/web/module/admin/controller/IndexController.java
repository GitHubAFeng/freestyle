package com.afeng.web.module.admin.controller;

import com.afeng.web.framework.config.AFengConfig;
import com.afeng.web.module.admin.model.Menu;
import com.afeng.web.module.admin.model.User;
import com.afeng.web.module.admin.service.IConfigService;
import com.afeng.web.module.admin.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 首页 业务处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController {
    @Autowired
    private IMenuService menuService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private AFengConfig ruoYiConfig;

    private String prefix = "admin/";


    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap) {
        // 取身份信息
        User user = getSysUser();
        if (user == null) {
            return redirect("/admin/login");
        }
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        mmap.put("demoEnabled", ruoYiConfig.isDemoEnabled());
        return prefix + "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap) {
        return prefix + "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        mmap.put("version", ruoYiConfig.getVersion());
        return prefix + "main";
    }

    //退出登录
    @GetMapping("/logout")
    public String logout(ModelMap mmap) {
        setSysUser(null);
        return redirect("index");
    }
}
