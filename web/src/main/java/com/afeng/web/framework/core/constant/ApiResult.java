package com.afeng.web.framework.core.constant;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;

/**
 * API回复
 */
public class ApiResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(1),
        /**
         * 失败
         */
        FAIL(0),
        /**
         * 警告
         */
        WARN(301),
        /**
         * 错误
         */
        ERROR(0);


        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 状态类型
     */
    private Type type;

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回内容
     */
    private String msg;

    /**
     * 数据对象
     */
    private Object data;

    /**
     * 初始化一个新创建的 ApiResult 对象，使其表示一个空消息。
     */
    public ApiResult() {
    }

    /**
     * 初始化一个新创建的 ApiResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     */
    public ApiResult(Type type, String msg) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    public ApiResult(String code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 ApiResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ApiResult(Type type, String msg, Object data) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ApiResult success() {
        return ApiResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ApiResult success(Object data) {
        return ApiResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ApiResult success(String msg) {
        return ApiResult.success(msg, "");
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ApiResult success(String msg, Object data) {
        return new ApiResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回失败消息
     *
     * @return 失败消息
     */
    public static ApiResult fail() {
        return ApiResult.fail("操作失败");
    }

    /**
     * 返回失败数据
     *
     * @return 失败消息
     */
    public static ApiResult fail(Object data) {
        return ApiResult.fail("操作失败", data);
    }

    /**
     * 返回失败消息
     *
     * @param msg 返回内容
     * @return 失败消息
     */
    public static ApiResult fail(String msg) {
        return ApiResult.fail(msg, "");
    }


    /**
     * 返回失败消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 失败消息
     */
    public static ApiResult fail(String msg, Object data) {
        return new ApiResult(Type.FAIL, msg, data);
    }


    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ApiResult warn(String msg) {
        return ApiResult.warn(msg, "");
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ApiResult warn(String msg, Object data) {
        return new ApiResult(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ApiResult error() {
        return ApiResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ApiResult error(String msg) {
        return ApiResult.error(msg, "");
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ApiResult error(String msg, Object data) {
        return new ApiResult(Type.ERROR, msg, data);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("code", getCode())
                .append("msg", getMsg())
                .append("data", getData())
                .toString();
    }
}
