package com.honglu.quickcall.common.api.exception;

import com.honglu.quickcall.common.api.code.BizCode;

/**
 * Created by len.song on 2017-12-07.
 */
public class RemoteValidateException extends ValidateException {
    public RemoteValidateException() {
    }

    public RemoteValidateException(BizCode code) {
        super(code);
    }

    public RemoteValidateException(BizCode code, String message) {
        super(code, message);
    }

    public RemoteValidateException(BizCode code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public RemoteValidateException(BizCode code, Throwable cause) {
        super(code, cause);
    }
}
