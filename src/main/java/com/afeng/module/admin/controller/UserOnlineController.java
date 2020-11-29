package com.afeng.module.admin.controller;

import com.afeng.module.admin.aspectj.lang.annotation.Log;
import com.afeng.module.admin.aspectj.lang.enums.BusinessType;
import com.afeng.module.admin.model.AjaxResult;
import com.afeng.module.admin.model.OnlineSession;
import com.afeng.module.admin.model.UserOnline;
import com.afeng.module.admin.page.TableDataInfo;
import com.afeng.module.admin.service.IUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 在线用户监控
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/admin/monitor/online")
public class UserOnlineController extends BaseController {
    private String prefix = "admin/monitor/online";

    @Autowired
    private IUserOnlineService userOnlineService;

    @GetMapping()
    public String online() {
        return prefix + "/online";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserOnline userOnline) {
        startPage();
        List<UserOnline> list = userOnlineService.selectUserOnlineList(userOnline);
        return getDataTable(list);
    }

    @Log(title = "在线用户", businessType = BusinessType.FORCE)
    @PostMapping("/batchForceLogout")
    @ResponseBody
    public AjaxResult batchForceLogout(@RequestParam("ids[]") String[] ids) {
        for (String sessionId : ids) {
            UserOnline online = userOnlineService.selectOnlineById(sessionId);
            if (online == null) {
                return error("用户已下线");
            }
            online.setStatus(OnlineSession.OnlineStatus.off_line);
            userOnlineService.saveOnline(online);
        }
        return success();
    }

    @Log(title = "在线用户", businessType = BusinessType.FORCE)
    @PostMapping("/forceLogout")
    @ResponseBody
    public AjaxResult forceLogout(String sessionId) {
        UserOnline online = userOnlineService.selectOnlineById(sessionId);
        if (online == null) {
            return error("用户已下线");
        }
        online.setStatus(OnlineSession.OnlineStatus.off_line);
        userOnlineService.saveOnline(online);
        return success();
    }
}
