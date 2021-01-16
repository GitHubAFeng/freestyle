package com.afeng.web.module.common.controller;

import com.afeng.web.module.admin.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * swagger 接口
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/admin/tool/swagger")
public class SwaggerController extends BaseController {

    @GetMapping()
    public String index() {
        return redirect("/swagger-ui/index.html");
    }

}
