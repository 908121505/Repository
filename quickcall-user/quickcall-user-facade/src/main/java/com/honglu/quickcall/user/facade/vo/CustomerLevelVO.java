package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;

/**
 * 我的等级 -- 页面对象
 *
 * @author duanjun
 * @date 2018-10-20 11:37
 */
public class CustomerLevelVO implements Serializable {

    private static final long serialVersionUID = -5688398953637286224L;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 用户ID
     */
    private String customerAppId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String headPortraitUrl;
    /**
     * 大V审核状态
     */
    private Integer vStatus;
    /**
     * 用户等级
     */
    private Integer customerLevel;
    /**
     * 客户经验值
     */
    private Integer customerExperience;
    /**
     * 目前距离下一级所需经验
     */
    private Integer needExperienceNum;
    /**
     * 当前等级累计经验值
     */
    private Integer currentLevelExperience;
    /**
     * 下一等级累计经验值
     */
    private Integer nextLevelExperience;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAppId() {
        return customerAppId;
    }

    public void setCustomerAppId(String customerAppId) {
        this.customerAppId = customerAppId;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    public Integer getCustomerExperience() {
        return customerExperience;
    }

    public void setCustomerExperience(Integer customerExperience) {
        this.customerExperience = customerExperience;
    }

    public Integer getNeedExperienceNum() {
        return needExperienceNum;
    }

    public void setNeedExperienceNum(Integer needExperienceNum) {
        this.needExperienceNum = needExperienceNum;
    }

    public Integer getCurrentLevelExperience() {
        return currentLevelExperience;
    }

    public void setCurrentLevelExperience(Integer currentLevelExperience) {
        this.currentLevelExperience = currentLevelExperience;
    }

    public Integer getNextLevelExperience() {
        return nextLevelExperience;
    }

    public void setNextLevelExperience(Integer nextLevelExperience) {
        this.nextLevelExperience = nextLevelExperience;
    }

    public Integer getvStatus() {
        return vStatus;
    }

    public void setvStatus(Integer vStatus) {
        this.vStatus = vStatus;
    }

    @Override
    public String toString() {
        return "CustomerLevelVO{" +
                "customerId=" + customerId +
                ", customerAppId='" + customerAppId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headPortraitUrl='" + headPortraitUrl + '\'' +
                ", vStatus=" + vStatus +
                ", customerLevel=" + customerLevel +
                ", customerExperience=" + customerExperience +
                ", needExperienceNum=" + needExperienceNum +
                ", currentLevelExperience=" + currentLevelExperience +
                ", nextLevelExperience=" + nextLevelExperience +
                '}';
    }
}
