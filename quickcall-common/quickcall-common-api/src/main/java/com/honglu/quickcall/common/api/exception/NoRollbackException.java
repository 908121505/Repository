package com.honglu.quickcall.common.api.exception;

import com.honglu.quickcall.common.api.code.BizCode;

/**
 * Created by len.song on 2017-12-07.
 */
public class NoRollbackException extends BaseException {
    public NoRollbackException() {
    }

    public NoRollbackException(BizCode code) {
        super(code);
    }

    public NoRollbackException(BizCode code, String message) {
        super(code, message);
    }

    public NoRollbackException(BizCode code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public NoRollbackException(BizCode code, Throwable cause) {
        super(code, cause);
    }
}