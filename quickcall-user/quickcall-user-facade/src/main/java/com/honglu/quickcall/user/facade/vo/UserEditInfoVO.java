package com.honglu.quickcall.user.facade.vo;

import java.util.Date;
import java.util.List;

/**
 * Description: 用户编辑资料页面 数据展示
 *
 * @author chenpeng
 * @date 2018/10/23 18:38
 */
public class UserEditInfoVO {
    private Long customerId;

    private String nickName;

    private Integer gender;

    private String signName;

    private String starSign;

    private Date birthday;

    private Integer identityStatus;

    private Integer age;

    private List<InterestVO> interestList;

    private String defaultHeadPortrait;

    /** 头像*/
    private AppearanceVO headPortrait;
    /** 形象照*/
    private List<AppearanceVO> appearanceList;
    /** 声鉴卡*/
    private AppearanceVO viceCard;

    public String getDefaultHeadPortrait() {
        return defaultHeadPortrait;
    }

    public void setDefaultHeadPortrait(String defaultHeadPortrait) {
        this.defaultHeadPortrait = defaultHeadPortrait;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getIdentityStatus() {
        return identityStatus;
    }

    public void setIdentityStatus(Integer identityStatus) {
        this.identityStatus = identityStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getStarSign() {
        return starSign;
    }

    public void setStarSign(String starSign) {
        this.starSign = starSign;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<InterestVO> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<InterestVO> interestList) {
        this.interestList = interestList;
    }

    public AppearanceVO getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(AppearanceVO headPortrait) {
        this.headPortrait = headPortrait;
    }

    public List<AppearanceVO> getAppearanceList() {
        return appearanceList;
    }

    public void setAppearanceList(List<AppearanceVO> appearanceList) {
        this.appearanceList = appearanceList;
    }

    public AppearanceVO getViceCard() {
        return viceCard;
    }

    public void setViceCard(AppearanceVO viceCard) {
        this.viceCard = viceCard;
    }
}
