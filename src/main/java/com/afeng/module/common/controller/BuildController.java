package com.afeng.module.common.controller;

import com.afeng.module.admin.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * build 表单构建
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/admin/tool/build")
public class BuildController extends BaseController
{
    private String prefix = "admin/tool/build";

    @GetMapping()
    public String build()
    {
        return prefix + "/build";
    }
}
