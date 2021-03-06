package com.afeng.web.module.oss.controller;

import com.afeng.web.framework.core.BaseApiController;
import com.afeng.web.framework.core.constant.ApiResult;
import com.afeng.web.framework.exception.ApiException;
import com.afeng.web.module.common.service.SysConfigType;
import com.afeng.web.module.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController extends BaseApiController {


    @Autowired
    private OssService ossService;


    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public ApiResult upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new ApiException("上传文件不能为空");
        }
        String url = ossService.onUpload(file);
        return success(url);
    }


}
