package com.honglu.quickcall.common.api.exception;

import com.honglu.quickcall.common.api.code.BizCode;

/**
 * Created by len.song on 2017-12-07.
 */
public class BizCheckException extends BaseException {
    public BizCheckException() {
    }

    public BizCheckException(BizCode code) {
        super(code);
    }

    public BizCheckException(BizCode code, String message) {
        super(code, message);
    }

    public BizCheckException(BizCode code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BizCheckException(BizCode code, Throwable cause) {
        super(code, cause);
    }
}
