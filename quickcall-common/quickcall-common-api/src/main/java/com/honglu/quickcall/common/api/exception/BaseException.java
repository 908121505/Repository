package com.honglu.quickcall.common.api.exception;

import com.honglu.quickcall.common.api.code.BizCode;

import java.io.Serializable;

/**
 * Created by len.song on 2017-12-07.
 */
public abstract class BaseException extends RuntimeException implements Serializable {
    private BizCode code;

    public BaseException() {
        this.code = BizCode.Unknown;
    }

    public BaseException(BizCode code) {
        this.code = BizCode.Unknown;
        this.code = code;
    }

    public BaseException(BizCode code, String message) {
        super(message);
        this.code = BizCode.Unknown;
        this.code = code;
    }

    public BaseException(BizCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = BizCode.Unknown;
        this.code = code;
    }

    public BaseException(BizCode code, Throwable cause) {
        super(cause);
        this.code = BizCode.Unknown;
        this.code = code;
    }

    public BizCode getCode() {
        return this.code;
    }

    public void setCode(BizCode code) {
        this.code = code;
    }
}
