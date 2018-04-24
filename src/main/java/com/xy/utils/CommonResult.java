package com.xy.utils;

import java.io.Serializable;

/**
 * 手机端统一返回的状态码
 * @author Administrator
 * @param
 *
 */

/*
	1001	成功返回
	1002	参数错误

 */
public class CommonResult implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String OK = "1001";	//请求成功
	public static final String FAIL = "1002";	//参数非法或者没有数据

	public static final String NOSKILL = "2";	//参数非法或者没有数据
	public static final String ERROR = "500";	//服务器错误
	public static final String WRONGTOKEN = "403";//无效的token
	public static final String EMPTY = "";//字符串初始化

	private  Object data;

	private String status;	// 1.正常    2.错误    500.服务器错误  403:无效的token
	private String tips;	//提示信息
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	@Override
	public String toString() {
		return "{\"data\":" + data + ", \"status\":" + status + ",\"tips\":"
				+ tips + "}";
	}


}
