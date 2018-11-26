package com.honglu.quickcall.common.api.exception;


/**
 * 异常
 *
 * @author xiangping
 * @date 2018-10-29 13:35
 */
public class DataBuriedPointException extends Exception {
	private static final long serialVersionUID = 1L;


	public DataBuriedPointException() {
		super();
	}

	public DataBuriedPointException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 把异常信息存放到构造函数里
	 *
	 */
	public DataBuriedPointException(String message) {
		super(message);
	}

	public DataBuriedPointException(Throwable cause) {
		super(cause);
	}
}
