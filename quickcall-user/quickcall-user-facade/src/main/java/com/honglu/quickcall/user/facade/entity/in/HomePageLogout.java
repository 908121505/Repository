package com.honglu.quickcall.user.facade.entity.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.honglu.quickcall.user.facade.entity.Interest;
import com.honglu.quickcall.user.facade.entity.Occupation;

public class HomePageLogout implements Serializable{
	


	private static final long serialVersionUID = -1511252745865490428L;

	private Long customerId;

    private String phone;

    private String realName;

    private Integer sex;

    private String nickName;

    private Integer type;

    private Integer credentialsType;

    private String credentialsNum;

    private Integer interestId;

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

    private Date birthday;

    private String voiceUrl;

    private Integer identityStatus;

    private String tokenCode;

    private String otherRelation;

//    private Date createTime;
//
//    private Date modifyTime;
//
//    private String createMan;
//
//    private String modifyMan;

    private String remark;
    
    private String appChannelName;

    private String deviceId;

    private Date loginTime;
    
    private List<Interest> interestName;//兴趣名字
    
    private List<Occupation> occupationName;//工作名字
    
    private int vStatus;//大V认证，(0=未认证,1=已通过)
    
    private Long fansNum;//粉丝数量
    
    private List<VProductTag> vProductTags;//主播商品标签（擅长项目）
    
    private int age;//年纪
    
    private int attentionNum;//关注数量
    
    private String starSign;//星座
    
    /**是否关注该用户 0：未关注  1：已关注*/
    private Integer  attentionStatus;
    /**大V认证声音时长*/
    private BigDecimal  voiceTime;
    /**大V声音审核状态1：待审核（审核中） 2：审核拒绝   3.审核通过   4.修改之后待审核   5.修改之后审核拒绝    6.修改之后审核通过*/
    private  Integer  voiceStatus; 
    
    /**大V自己声音时长*/
    private BigDecimal  vVoiceTime;
    /**大V自己声音URL*/
    private String vVoiceUrl;
    
    /**大V自己声音时长*/
    public BigDecimal getvVoiceTime() {
		return vVoiceTime;
	}

    /**大V自己声音时长*/
	public void setvVoiceTime(BigDecimal vVoiceTime) {
		this.vVoiceTime = vVoiceTime;
	}

	/**大V自己声音URL*/
	public String getvVoiceUrl() {
		return vVoiceUrl;
	}

	/**大V自己声音URL*/
	public void setvVoiceUrl(String vVoiceUrl) {
		this.vVoiceUrl = vVoiceUrl;
	}

	/**大V声音审核状态1：待审核（审核中） 2：审核拒绝   3.审核通过   4.修改之后待审核   5.修改之后审核拒绝    6.修改之后审核通过*/
	public Integer getVoiceStatus() {
		return voiceStatus;
	}

	/**大V声音审核状态1：待审核（审核中） 2：审核拒绝   3.审核通过   4.修改之后待审核   5.修改之后审核拒绝    6.修改之后审核通过*/
	public void setVoiceStatus(Integer voiceStatus) {
		this.voiceStatus = voiceStatus;
	}

	/**大V认证声音时长*/
    public BigDecimal getVoiceTime() {
		return voiceTime;
	}

    /**大V认证声音时长*/
	public void setVoiceTime(BigDecimal voiceTime) {
		this.voiceTime = voiceTime;
	}

	/**是否关注该用户 0：未关注  1：已关注*/
    public Integer getAttentionStatus() {
		return attentionStatus;
	}

    /**是否关注该用户 0：未关注  1：已关注*/
	public void setAttentionStatus(Integer attentionStatus) {
		this.attentionStatus = attentionStatus;
	}

	public String getStarSign() {
		return starSign;
	}

	public void setStarSign(String starSign) {
		this.starSign = starSign;
	}

	public int getAttentionNum() {
		return attentionNum;
	}

	public void setAttentionNum(int attentionNum) {
		this.attentionNum = attentionNum;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<VProductTag> getvProductTags() {
		return vProductTags;
	}

	public void setvProductTags(List<VProductTag> vProductTags) {
		this.vProductTags = vProductTags;
	}

	public Long getFansNum() {
		return fansNum;
	}

	public void setFansNum(Long fansNum) {
		this.fansNum = fansNum;
	}

	public int getvStatus() {
		return vStatus;
	}

	public void setvStatus(int vStatus) {
		this.vStatus = vStatus;
	}

	public List<Interest> getInterestName() {
		return interestName;
	}

	public void setInterestName(List<Interest> interestName) {
		this.interestName = interestName;
	}

	public List<Occupation> getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(List<Occupation> occupationName) {
		this.occupationName = occupationName;
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
		this.realName = realName;
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
		this.nickName = nickName;
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
		this.credentialsNum = credentialsNum;
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
		this.wechatOpenId = wechatOpenId;
	}

	public String getQqOpenId() {
		return qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}

	public String getMicroblogOpenId() {
		return microblogOpenId;
	}

	public void setMicroblogOpenId(String microblogOpenId) {
		this.microblogOpenId = microblogOpenId;
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
		this.frontPortraitUrl = frontPortraitUrl;
	}

	public String getBackPortraitUrl() {
		return backPortraitUrl;
	}

	public void setBackPortraitUrl(String backPortraitUrl) {
		this.backPortraitUrl = backPortraitUrl;
	}

	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}

	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
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
		this.custPassword = custPassword;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
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
		this.voiceUrl = voiceUrl;
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
		this.tokenCode = tokenCode;
	}

	public String getOtherRelation() {
		return otherRelation;
	}

	public void setOtherRelation(String otherRelation) {
		this.otherRelation = otherRelation;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAppChannelName() {
		return appChannelName;
	}

	public void setAppChannelName(String appChannelName) {
		this.appChannelName = appChannelName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
