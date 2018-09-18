package com.honglu.quickcall.common.api.exchange;

import com.honglu.quickcall.common.api.code.BizCode;

import java.util.HashMap;

/**
 * Created by len.song on 2017-12-07.
 */
public  class CommonResponse extends AbstractModel {
    private BizCode code;
    private String message;
    private Object data;
    

    public CommonResponse() {
    }

    public boolean isSuccess() {
        return BizCode.Success.code().equals(this.code==null?null:this.code.code());
    }

    public BizCode getCode() {
        return this.code;
    }

    public void setCode(BizCode code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
        
}
