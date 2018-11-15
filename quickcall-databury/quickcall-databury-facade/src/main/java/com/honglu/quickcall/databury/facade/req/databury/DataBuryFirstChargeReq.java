package com.honglu.quickcall.databury.facade.req.databury;

import com.honglu.quickcall.databury.facade.req.abs.AbstractBaseReq;

/**
 * 是否首次充值
 */
public class DataBuryFirstChargeReq extends AbstractBaseReq {
    private boolean isFirstTime;
    private String vcUserId;
    private String vcUserPhoneNum;

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public void setFirstTime(boolean firstTime) {
        isFirstTime = firstTime;
    }

    public String getVcUserId() {
        return vcUserId;
    }

    public void setVcUserId(String vcUserId) {
        this.vcUserId = vcUserId;
    }

    public String getVcUserPhoneNum() {
        return vcUserPhoneNum;
    }

    public void setVcUserPhoneNum(String vcUserPhoneNum) {
        this.vcUserPhoneNum = vcUserPhoneNum;
    }

    @Override
    public String toString() {
        return "BuryFirstChargeReq{" +
                "isFirstTime=" + isFirstTime +
                ", vcUserId='" + vcUserId + '\'' +
                ", vcUserPhoneNum='" + vcUserPhoneNum + '\'' +
                '}';
    }
}
