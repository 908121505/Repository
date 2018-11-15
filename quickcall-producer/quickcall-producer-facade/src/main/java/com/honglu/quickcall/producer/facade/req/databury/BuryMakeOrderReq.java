package com.honglu.quickcall.producer.facade.req.databury;

import com.honglu.quickcall.producer.facade.req.abs.AbstractBaseReq;

/**
 * 下单
 */
public class BuryMakeOrderReq extends AbstractBaseReq {
    private String vcUserId;
    private String vcUserPhoneNum;
    private String vcOwnerUserId;
    private boolean doesSucceed;
    private String skillId;
    private String skillName;

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

    public String getVcOwnerUserId() {
        return vcOwnerUserId;
    }

    public void setVcOwnerUserId(String vcOwnerUserId) {
        this.vcOwnerUserId = vcOwnerUserId;
    }

    public boolean getDoesSucceed() {
        return doesSucceed;
    }

    public void setDoesSucceed(boolean doesSucceed) {
        this.doesSucceed = doesSucceed;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "BuryMakeOrderReq{" +
                "vcUserId='" + vcUserId + '\'' +
                ", vcUserPhoneNum='" + vcUserPhoneNum + '\'' +
                ", vcOwnerUserId='" + vcOwnerUserId + '\'' +
                ", doesSucceed=" + doesSucceed +
                ", skillId='" + skillId + '\'' +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
