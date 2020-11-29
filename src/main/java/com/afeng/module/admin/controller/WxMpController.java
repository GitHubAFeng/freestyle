package com.afeng.module.admin.controller;

import com.afeng.module.admin.dao.WxMapper;
import com.afeng.module.admin.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/wx")
public class WxMpController extends BaseController {
    private String prefix = "admin/wx/";

    @Autowired
    private WxMapper wxMapper;

    @GetMapping("/index")
    public String index(ModelAndView modelAndView) {
        return prefix + "wxMpIndex";
    }


    @RequestMapping("renChart")
    @ResponseBody
    public AjaxResult renChart() {
        List<Map<String, Object>> fissionLevelList = wxMapper.findFissionLevelList();

        return AjaxResult.success(fissionLevelList);
    }


}
