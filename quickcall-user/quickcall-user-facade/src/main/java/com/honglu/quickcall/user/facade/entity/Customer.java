package com.honglu.quickcall.user.facade.entity;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {
    private Long customerId;

    private Integer phone;

    private String realName;

    private Byte sex;

    private String nickName;

    private Byte type;

    private Byte credentialsType;

    private String credentialsNum;

    private Integer interestId;

    private String wechatOpenId;

    private String qqOpenId;

    private String microblogOpenId;

    private Integer occupationId;

    private String frontPortraitUrl;

    private String backPortraitUrl;

    private String headPortraitUrl;

    private Byte source;

    private Byte custStatus;

    private String custPassword;

    private String signName;

    private Date birthday;

    private String voiceUrl;

    private Byte isIdentityConfirm;

    private String tokenCode;

    private String otherRelation;

    private Date createTime;

    private Date modifyTime;

    private String createMan;

    private String modifyMan;

    private String remark;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Byte credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum == null ? null : credentialsNum.trim();
    }

    public Integer getInterestId() {
        return interestId;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId == null ? null : wechatOpenId.trim();
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId == null ? null : qqOpenId.trim();
    }

    public String getMicroblogOpenId() {
        return microblogOpenId;
    }

    public void setMicroblogOpenId(String microblogOpenId) {
        this.microblogOpenId = microblogOpenId == null ? null : microblogOpenId.trim();
    }

    public Integer getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(Integer occupationId) {
        this.occupationId = occupationId;
    }

    public String getFrontPortraitUrl() {
        return frontPortraitUrl;
    }

    public void setFrontPortraitUrl(String frontPortraitUrl) {
        this.frontPortraitUrl = frontPortraitUrl == null ? null : frontPortraitUrl.trim();
    }

    public String getBackPortraitUrl() {
        return backPortraitUrl;
    }

    public void setBackPortraitUrl(String backPortraitUrl) {
        this.backPortraitUrl = backPortraitUrl == null ? null : backPortraitUrl.trim();
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl == null ? null : headPortraitUrl.trim();
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }

    public Byte getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(Byte custStatus) {
        this.custStatus = custStatus;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword == null ? null : custPassword.trim();
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName == null ? null : signName.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl == null ? null : voiceUrl.trim();
    }

    public Byte getIsIdentityConfirm() {
        return isIdentityConfirm;
    }

    public void setIsIdentityConfirm(Byte isIdentityConfirm) {
        this.isIdentityConfirm = isIdentityConfirm;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode == null ? null : tokenCode.trim();
    }

    public String getOtherRelation() {
        return otherRelation;
    }

    public void setOtherRelation(String otherRelation) {
        this.otherRelation = otherRelation == null ? null : otherRelation.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    public String getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}