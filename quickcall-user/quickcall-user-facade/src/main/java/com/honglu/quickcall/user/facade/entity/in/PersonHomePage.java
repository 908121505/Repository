package com.honglu.quickcall.user.facade.entity.in;

import java.util.Date;
import java.util.List;

import com.honglu.quickcall.user.facade.entity.Interest;

public class PersonHomePage {
	 	private Long customerId;

	    private Integer phone;

	    private String realName;

	    private Byte sex;

	    private String nickName;

	    private Byte type;//用户类型(0=普通用户,1=大V)

	    private Byte credentialsType;//证件类型

	    private String credentialsNum;

	    private List<Interest> interest;//兴趣

	    private Byte custStatus;//用户状态(状态:1=正常 2=锁定 3=冻结,4=禁用 5=注销；6=限制社区发帖；7=限制社区评论；8=限制社区发帖和评论,9=限制发送消息)

	    private String signName;//签名

	    private Date birthday;

	    private String voiceUrl;
	    
	    private String occupation;//职业
	    
	    private String headPortraitUrl;//头像路径
	    

		public String getHeadPortraitUrl() {
			return headPortraitUrl;
		}

		public void setHeadPortraitUrl(String headPortraitUrl) {
			this.headPortraitUrl = headPortraitUrl;
		}

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
			this.realName = realName;
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
			this.nickName = nickName;
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
			this.credentialsNum = credentialsNum;
		}

		public List<Interest> getInterest() {
			return interest;
		}

		public void setInterest(List<Interest> interestList) {
			this.interest = interestList;
		}

		public Byte getCustStatus() {
			return custStatus;
		}

		public void setCustStatus(Byte custStatus) {
			this.custStatus = custStatus;
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

		public String getOccupation() {
			return occupation;
		}

		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}


}
