package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

import java.util.List;

public class DelateInsertRequest extends UserCenterRequest {
    private List<Integer> delateIds; //投诉的Id
    private Long customerId;         //被举报人Id
    private Long delateCustId;       //举报人Id
    private String  otherReason;    //其他原因
    private Integer isBlack;        //是否加入黑名单（0：不加入，1：加入）

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getDelateCustId() {
        return delateCustId;
    }

    public void setDelateCustId(Long delateCustId) {
        this.delateCustId = delateCustId;
    }

    public Integer getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(Integer isBlack) {
        this.isBlack = isBlack;
    }

    public List<Integer> getDelateIds() {
        return delateIds;
    }

    public void setDelateIds(List<Integer> delateIds) {
        this.delateIds = delateIds;
    }

    public String getOtherReason() {
        return otherReason;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason;
    }

    @Override
    public String getBizCode() {
        return UserFunctionType.insertDelate;
    }
}
