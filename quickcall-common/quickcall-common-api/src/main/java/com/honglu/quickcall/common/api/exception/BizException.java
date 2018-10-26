package com.honglu.quickcall.common.api.exception;

import com.honglu.quickcall.common.api.code.BizCode;

/**
 * Created by len.song on 2017-12-07.
 */
public class BizException extends BaseException {
    public BizException() {

    }

    public BizException(BizCode code) {
        super(code);
    }

    public BizException(BizCode code, String message) {
        super(code, message);
    }

    public BizException(BizCode code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BizException(BizCode code, Throwable cause) {
        super(code, cause);
    }
}