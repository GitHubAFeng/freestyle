package com.afeng.web.framework.core;


import com.afeng.web.framework.core.constant.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: ApiBaseController
 * Description:
 * date: 2020/1/24 9:23
 *
 * @author afeng
 * @since JDK 1.8
 */
@Slf4j
public class BaseApiController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    /**
     * 获取当前项目系统目录路径
     *
     * @author afeng
     * @date 2020/5/1 14:39
     */
    protected String getRealPath() {
        return request.getServletContext().getRealPath("upload/");
    }

    /**
     * 获取键值对参数
     */
    protected String getParameter(String key) {
        return request.getParameter(key);
    }

    /**
     * 获取流参数，如json、xml
     */
    protected String getBufferParam() {
        String param = null;
        try (BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            if (sb.length() < 1) {
                return "";
            }
            param = sb.toString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return param;
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {

    }


    /**
     * 返回成功消息
     */
    protected ApiResult success(Object data) {
        return ApiResult.success(data);
    }

    protected ApiResult success(String msg, Object data) {
        return ApiResult.success(msg, data);
    }

    protected ApiResult success() {
        return ApiResult.success();
    }


    /**
     * 返回失败消息
     */
    protected ApiResult error() {
        return ApiResult.error();
    }

    protected ApiResult error(String message) {
        return ApiResult.error(message);
    }

    protected ApiResult error(ApiResult.Type type, String message) {
        return new ApiResult(type, message);
    }

    /**
     * 返回失败消息
     */
    protected ApiResult fail() {
        return ApiResult.fail();
    }

    protected ApiResult fail(String message) {
        return ApiResult.fail(message);
    }

    protected ApiResult fail(ApiResult.Type type, String message) {
        return new ApiResult(type, message);
    }

    protected ApiResult fail(Object data) {
        return ApiResult.fail(data);
    }


}
