package com.bokecc.supports;

import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * 状态码封装
 * </p>
 *
 * @description: 状态码封装
 */
@Getter
@ToString
public enum ResultCode implements IResultCode<Integer> {
	/**
	 * 操作成功
	 */
	OK(200, "操作成功"),
	FAIL(201, "操作失败"),
	PARAM_INVALID(1001, "参数错误"),
	/**
	 * 未知异常
	 */
	UNKNOWN_ERROR(500, "服务器出错啦");



	/**
	 * 状态码
	 */
	private Integer code;
	/**
	 * 内容
	 */
	private String message;

	ResultCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}



}
