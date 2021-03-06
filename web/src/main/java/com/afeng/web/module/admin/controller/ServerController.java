package com.afeng.web.module.admin.controller;

import com.afeng.web.module.admin.model.Server;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务器监控
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/admin/monitor/server")
public class ServerController extends BaseController {
    private String prefix = "admin/monitor/server";

    @GetMapping()
    public String server(ModelMap mmap) throws Exception {
        Server server = new Server();
        server.copyTo();
        mmap.put("server", server);
        return prefix + "/server";
    }
}
