package com.afeng.web.module.quartz.controller;

import com.afeng.web.module.admin.aspectj.lang.annotation.Log;
import com.afeng.web.module.admin.aspectj.lang.enums.BusinessType;
import com.afeng.web.module.admin.controller.BaseController;
import com.afeng.web.module.admin.model.AjaxResult;
import com.afeng.web.module.admin.page.TableDataInfo;
import com.afeng.web.module.common.utils.StringUtils;
import com.afeng.web.module.common.utils.poi.ExcelUtil;
import com.afeng.web.module.quartz.model.Job;
import com.afeng.web.module.quartz.model.JobLog;
import com.afeng.web.module.quartz.service.IJobLogService;
import com.afeng.web.module.quartz.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度日志操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/admin/monitor/jobLog")
public class JobLogController extends BaseController {
    private String prefix = "admin/monitor/job";

    @Autowired
    private IJobService jobService;

    @Autowired
    private IJobLogService jobLogService;

    @GetMapping()
    public String jobLog(@RequestParam(value = "jobId", required = false) Long jobId, ModelMap mmap) {
        if (StringUtils.isNotNull(jobId)) {
            Job job = jobService.selectJobById(jobId);
            mmap.put("job", job);
        }
        return prefix + "/jobLog";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JobLog jobLog) {
        startPage();
        List<JobLog> list = jobLogService.selectJobLogList(jobLog);
        return getDataTable(list);
    }

    @Log(title = "调度日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JobLog jobLog) {
        List<JobLog> list = jobLogService.selectJobLogList(jobLog);
        ExcelUtil<JobLog> util = new ExcelUtil<JobLog>(JobLog.class);
        return util.exportExcel(list, "调度日志");
    }

    @Log(title = "调度日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(jobLogService.deleteJobLogByIds(ids));
    }

    @GetMapping("/detail/{jobLogId}")
    public String detail(@PathVariable("jobLogId") Long jobLogId, ModelMap mmap) {
        mmap.put("name", "jobLog");
        mmap.put("jobLog", jobLogService.selectJobLogById(jobLogId));
        return prefix + "/detail";
    }

    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        jobLogService.cleanJobLog();
        return success();
    }
}
