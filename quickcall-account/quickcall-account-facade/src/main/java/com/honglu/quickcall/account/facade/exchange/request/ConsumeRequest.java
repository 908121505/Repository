package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2017-12-19.
 */
public class ConsumeRequest extends AbstractRequest {
    private Integer womanId;            //女生id
    private Integer manId;              //男生id
    private Integer type;               //消费类型
    private Integer nums;               //份数
    private String clientOrderId;       //客户端标识(防刷)
    private String  chargesId;          //前端批次号

    private Integer startSex;           //开始性别 未传时默认男生发起

    @Override
    public String getBizCode() {
        return AccountFunctionType.PersonConsume;
    }

    public Integer getWomanId() {
        return womanId;
    }

    public void setWomanId(Integer womanId) {
        this.womanId = womanId;
    }

    public Integer getManId() {
        return manId;
    }

    public void setManId(Integer manId) {
        this.manId = manId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public String getChargesId() {
        return chargesId;
    }

    public void setChargesId(String chargesId) {
        this.chargesId = chargesId;
    }

    public Integer getStartSex() {
        return startSex;
    }

    public void setStartSex(Integer startSex) {
        this.startSex = startSex;
    }
}
