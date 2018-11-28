package com.honglu.quickcall.user.service.util;

/**
 * <p>Title: 异常处理</p>
 * <p>Description: 第三方调用异常</p>
 * <p>Company: eurlion</p>
 *
 * @author conly.xiangping
 */
public class WebException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public WebException() {
        super();
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 把异常信息存放到构造函数里
     */
    public WebException(String message) {
        super(message);
    }

    public WebException(Throwable cause) {
        super(cause);
    }
}
