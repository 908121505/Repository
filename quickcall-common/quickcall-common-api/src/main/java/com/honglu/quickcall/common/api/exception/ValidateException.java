package com.honglu.quickcall.common.api.exception;

import com.honglu.quickcall.common.api.code.BizCode;

/**
 * Created by len.song on 2017-12-07.
 */
public class ValidateException extends BaseException {
    public ValidateException() {
    }

    public ValidateException(BizCode code) {
        super(code);
    }

    public ValidateException(BizCode code, String message) {
        super(code, message);
    }

    public ValidateException(BizCode code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public ValidateException(BizCode code, Throwable cause) {
        super(code, cause);
    }
}
