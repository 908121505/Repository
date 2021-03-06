package com.honglu.quickcall.common.api.exception;

import com.honglu.quickcall.common.api.code.BizCode;

/**
 * Created by len.song on 2017-12-07.
 */
public class RemoteException extends BaseException {
    public RemoteException() {
    }

    public RemoteException(BizCode code) {
        super(code);
    }

    public RemoteException(BizCode code, String message) {
        super(code, message);
    }

    public RemoteException(BizCode code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public RemoteException(BizCode code, Throwable cause) {
        super(code, cause);
    }
}
