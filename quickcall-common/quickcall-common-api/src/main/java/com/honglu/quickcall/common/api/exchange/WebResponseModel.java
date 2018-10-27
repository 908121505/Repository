package com.honglu.quickcall.common.api.exchange;

/**
 * Created by len.song on 2017-12-08.
 */
public class WebResponseModel extends AbstractModel{
    private String code;                //返回编码
    private String msg;                 //描述
    private String data;              //传输数据

    public WebResponseModel(){

    }

    public WebResponseModel(CommonResponse response, String data){
        this.code = response.getCode().code();
        this.msg = response.getCode().desc();
        this.data = data;
    }

    public WebResponseModel(String code,String msg,String data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
