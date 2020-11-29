package com.afeng.common.util;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModelProperty;

/**
 * 响应数据
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 3997124446365032582L;
	/**
	 * 系统响应码
	 */
	@ApiModelProperty(value = "系统响应码", required = true)
	@JSONField(name="sysCode")
	private Integer code = 200;
	@ApiModelProperty(value = "系统响应消息")
	@JSONField(name="sysMsg")
	private String msg="请求成功";
	/**
	 * 系统业务编码
	 */
	@ApiModelProperty(value = "业务编码")
	@JSONField(name="bizCode")
	private Integer bizCode=200;
	@ApiModelProperty(value = "业务消息")
	@JSONField(name="bizMsg")
	private String bizMsg="处理成功";
	/**
	 * 响应数据
	 */
	@ApiModelProperty(value = "响应数据", required = true)
	@JSONField(name="respData")
	private T data;
	
	public Result() {
		super();
	}

	public Result(Integer code, Integer bizCode, String msg) {
		super();
		this.code = code;
		this.bizCode = bizCode;
		this.msg = msg;
	}

	public Result(Integer code, Integer bizCode, String msg, T data) {
		super();
		this.code = code;
		this.bizCode = bizCode;
		this.msg = msg;
		this.data = data;
	}
	
	public Result(Integer code, String msg, Integer bizCode, String bizMsg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.bizCode = bizCode;
		this.bizMsg = bizMsg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getBizCode() {
		return bizCode;
	}

	public void setBizCode(Integer bizCode) {
		this.bizCode = bizCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	
	public String getBizMsg() {
		return bizMsg;
	}

	public void setBizMsg(String bizMsg) {
		this.bizMsg = bizMsg;
	}

	/**
     * 服务器接到请求
	 * 业务处理时错误
     * @param object 响应体
     * @return
     */
    public static Result<Object> error(Object object){
    	Result<Object> res=new Result<Object>();
    	res.setCode(200);//请求成功
    	res.setMsg("服务请求成功");
    	res.setBizCode(500);//业务处理失败
    	res.setBizMsg("业务处理失败");
		res.setData(object);
    	return res;
	}
    
    /**
     * 服务器接到请求
	 * 业务处理时错误
	 * 自定义错误信息
     * @param object 响应体
     * @param bizCode 业务编码
     * @param bizMsg  业务消息
     * @return
     */
    public static Result<Object> error(Object object,int bizCode,String bizMsg){
    	Result<Object> res=new Result<Object>();
    	res.setCode(200);//请求成功
    	res.setMsg("服务请求成功");
    	res.setBizCode(bizCode);//业务处理失败
    	res.setBizMsg(bizMsg);
		res.setData(object);
    	return res;
	}
 
    
    /**
     * 服务器接到请求
	 * 服务器处理成功
     * @param object 响应体
     * @return
     */
    public static Result<Object> success(Object object){
    	Result<Object> res=new Result<Object>();
    	res.setCode(200);//请求成功
    	res.setMsg("服务请求成功");
    	res.setBizCode(200);//业务处理失败
    	res.setBizMsg("业务处理成功");
		res.setData(object);
    	return res;
	}

}
