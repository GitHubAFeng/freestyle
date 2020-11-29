package com.afeng.module.oss.service;

import com.afeng.common.log.ApiLogUtils;
import com.afeng.module.admin.service.IConfigService;
import com.afeng.module.common.service.SysConfigType;
import com.afeng.module.common.utils.file.FileUploadUtils;
import com.afeng.module.oss.cloud.*;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author afeng
 * @date: 2020/5/2 9:21
 */
@Service
public class OssService {

    @Autowired
    private IConfigService configService;


    private CloudStorageService buildOss() {
        //获取云存储配置信息
        String oss_json = configService.selectConfigByKey(SysConfigType.OSS.name());
        ApiLogUtils.debugPrint(oss_json);
        CloudStorageConfig config = JSON.parseObject(oss_json, CloudStorageConfig.class);

        CloudStorageService build = null;
        if (config.getType() == CloudService.QINIU.getValue()) {
            build = new QiniuCloudStorageService(config);
        } else if (config.getType() == CloudService.ALIYUN.getValue()) {
            build = new AliyunCloudStorageService(config);
        } else if (config.getType() == CloudService.QCLOUD.getValue()) {
            build = new QcloudCloudStorageService(config);
        }
        return build;
    }

    /**
     * 上传文件到OSS
     *
     * @param configType OSS类型
     * @return URL路径
     * @author afeng
     * @date 2020/5/2 9:29
     */
    public String onUpload(MultipartFile file) throws Exception {
        //上传文件
        if (file == null) return null;
        //后缀
        String suffix = "png";
        if (file.getOriginalFilename() != null) {
            suffix = FileUploadUtils.getExtension(file);
        }
        return buildOss().uploadSuffix(file.getBytes(), suffix);
    }


}
