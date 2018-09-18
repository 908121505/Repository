package com.honglu.quickcall.common.third.weixin.models;

import java.io.Serializable;


/**
 * 第三方登录的用户信息
 * @author GunnyZeng
 *
 */
public class User implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -6607016140281732955L;
	private String id;                      //用户UID
	private String nickName;            //昵称
	private String province;                 //省份编码（参考省份编码表）
	private String city;                     //城市编码（参考城市编码表）
	private String location;              //地址
	private String gender;                //性别,m--男，f--女,n--未知
	private String avatarLarge;           //大头像地址
	private String source;	//来源于哪一个第三方登录
	private Integer isRegister;//是否注册
	public Integer getIsRegister() {
		return isRegister;
	}
	public void setIsRegister(Integer isRegister) {
		this.isRegister = isRegister;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAvatarLarge() {
		return avatarLarge;
	}
	public void setAvatarLarge(String avatarLarge) {
		this.avatarLarge = avatarLarge;
	}
	
	


}
