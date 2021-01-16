package com.afeng.web.common.util;

import com.afeng.web.framework.filter.ApiInterceptor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 签名工具
 *
 * @author AFeng
 * @createDate 2020/11/25 15:26
 **/
public class SignUtil {

    /**
     * 生成拼接签名字字符串
     *
     * @author AFeng
     * @createDate 2020/11/25 15:13
     **/
    public static String createMethodArgumentToString(HttpServletRequest request) throws Exception {
        Map<String, Object> paramsMap = MapDataUtil.convertDataMap(request);
        Set<String> keySet = paramsMap.keySet();
        //所有请求参数排序后键值对拼接
        List<String> paramNames = new ArrayList<>(keySet);
        Collections.sort(paramNames);
        StringBuilder paramNameValue = new StringBuilder();
        for (String paramName : paramNames) {
            //不包含签名参数
            if (StringUtils.equals(ApiInterceptor.signKey, paramName)) continue;
            paramNameValue.append(paramName).append(paramsMap.get(paramName));
        }
        return paramNameValue.toString();
    }

    /**
     * 创建简单签名
     *
     * @param signSecret 密钥
     * @author AFeng
     * @createDate 2020/11/25 15:20
     **/
    public static String createSimpleSign(HttpServletRequest request, String signSecret) throws Exception {
        String source = SignUtil.createMethodArgumentToString(request) + signSecret;
        return DigestUtils.md5Hex(source);
    }

}
