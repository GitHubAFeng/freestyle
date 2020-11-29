package com.afeng.module.common.service;

import com.afeng.framework.config.AFengConfig;
import com.afeng.module.common.utils.file.FileUploadUtils;
import com.afeng.module.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author afeng
 * @date: 2020/5/10 11:25
 */
@Service
public class UploadService {

    @Autowired
    private OssService ossService;

    /**
     * 上传文件
     *
     * @param baseDir 指定路径
     * @param file    文件
     * @return
     * @author afeng
     * @date 2020/5/10 11:30
     */
    public String upload(String baseDir, MultipartFile file) throws Exception {
        String url = null;
        if (AFengConfig.isOssEnabled()) {
            url = ossService.onUpload(file);
        } else {
            url = FileUploadUtils.upload(baseDir, file);
        }
        return url;
    }

    /**
     * 默认上传到OSS
     *
     * @param file 文件
     * @return
     * @author afeng
     * @date 2020/5/10 11:31
     */
    public String upload(MultipartFile file) throws Exception {
        return ossService.onUpload(file);
    }

}
