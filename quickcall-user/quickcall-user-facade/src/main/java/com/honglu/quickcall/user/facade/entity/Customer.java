package com.honglu.quickcall.user.facade.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Customer {
	private Long customerId;

	private String phone;

	private String appId;

	private String realName;

	private Integer sex;

	private String nickName;

	private Integer type;

	private Integer credentialsType;

	private String credentialsNum;

	private String wechatOpenId;

	private String qqOpenId;

	private String microblogOpenId;

	private Integer occupationId;

	private String frontPortraitUrl;

	private String backPortraitUrl;

	private String headPortraitUrl;

	private Integer source;

	private Integer custStatus;

	private String custPassword;

	private String signName;

	private String starSign;

	private Date birthday;

	private String voiceUrl;

	private Integer vStatus;

	private Integer identityStatus;

	private String tokenCode;

	private String otherRelation;

	private Date createTime;

	private Date modifyTime;

	private String createMan;

	private String modifyMan;

	private String remark;

	private String appChannelName;

	private Integer cumulateExperience;

	private Integer customerLevel;

	private String deviceId;

	private Date loginTime;
	/** 大V认证声音时长 */
	private BigDecimal voiceTime;
	/** 大V自己声音时长 */
	private BigDecimal vVoiceTime;
	/** 大V自己声音URL */
	private String vVoiceUrl;
	/** 大V自己声音时长（未审核或者审核拒绝） */
	private BigDecimal vVoiceTimeTmp;
	/** 大V自己声音URL（未审核或者审核拒绝） */
	private String vVoiceUrlTmp;
	/** 大V声音审核状态1：待审核（审核中） 2：审核拒绝 3.审核通过 4.修改之后待审核 5.修改之后审核拒绝 6.修改之后审核通过 */
	private Integer voiceStatus;
	private String gtClientId;// 个推用户端Id
	private Date blockStartTime;
	private Date blockEndTime;

	public Date getBlockStartTime() {
		return blockStartTime;
	}

	public void setBlockStartTime(Date blockStartTime) {
		this.blockStartTime = blockStartTime;
	}

	public Date getBlockEndTime() {
		return blockEndTime;
	}

	public void setBlockEndTime(Date blockEndTime) {
		this.blockEndTime = blockEndTime;
	}

	public String getGtClientId() {
		return gtClientId;
	}

	public void setGtClientId(String gtClientId) {
		this.gtClientId = gtClientId;
	}

	public BigDecimal getvVoiceTimeTmp() {
		return vVoiceTimeTmp;
	}

	public void setvVoiceTimeTmp(BigDecimal vVoiceTimeTmp) {
		this.vVoiceTimeTmp = vVoiceTimeTmp;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getvVoiceUrlTmp() {
		return vVoiceUrlTmp;
	}

	public void setvVoiceUrlTmp(String vVoiceUrlTmp) {
		this.vVoiceUrlTmp = vVoiceUrlTmp;
	}

	public Integer getVoiceStatus() {
		return voiceStatus;
	}

	public void setVoiceStatus(Integer voiceStatus) {
		this.voiceStatus = voiceStatus;
	}

	public BigDecimal getvVoiceTime() {
		return vVoiceTime;
	}

	public void setvVoiceTime(BigDecimal vVoiceTime) {
		this.vVoiceTime = vVoiceTime;
	}

	public String getvVoiceUrl() {
		return vVoiceUrl;
	}

	public void setvVoiceUrl(String vVoiceUrl) {
		this.vVoiceUrl = vVoiceUrl;
	}

	public BigDecimal getVoiceTime() {
		return voiceTime;
	}

	public void setVoiceTime(BigDecimal voiceTime) {
		this.voiceTime = voiceTime;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType(Integer credentialsType) {
		this.credentialsType = credentialsType;
	}

	public String getCredentialsNum() {
		return credentialsNum;
	}

	public void setCredentialsNum(String credentialsNum) {
		this.credentialsNum = credentialsNum == null ? null : credentialsNum.trim();
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

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getCustStatus() {
		return custStatus;
	}

	public void setCustStatus(Integer custStatus) {
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

	public String getStarSign() {
		return starSign;
	}

	public void setStarSign(String starSign) {
		this.starSign = starSign == null ? null : starSign.trim();
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

	public Integer getvStatus() {
		return vStatus;
	}

	public void setvStatus(Integer vStatus) {
		this.vStatus = vStatus;
	}

	public Integer getIdentityStatus() {
		return identityStatus;
	}

	public void setIdentityStatus(Integer identityStatus) {
		this.identityStatus = identityStatus;
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

	public String getAppChannelName() {
		return appChannelName;
	}

	public void setAppChannelName(String appChannelName) {
		this.appChannelName = appChannelName == null ? null : appChannelName.trim();
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId == null ? null : deviceId.trim();
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getCumulateExperience() {
		return cumulateExperience;
	}

	public void setCumulateExperience(Integer cumulateExperience) {
		this.cumulateExperience = cumulateExperience;
	}

	public Integer getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(Integer customerLevel) {
		this.customerLevel = customerLevel;
	}
}