package com.honglu.quickcall.common.third.shumei.request;

import java.io.Serializable;
import java.util.Arrays;

public class ImageFilterRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -731305414063666963L;
	/**
	 * 要检测的图片
	 */
	private Image[] imgs;
	/**
	 * 客户端用户唯一标识
	 */
	private String tokenId;
	/**
	 * 业务渠道标识
	 */
	private String channel;
	
	/**
	 * 客户端 IP
	 */
	private String ip;
	/**
	 * 用户手机号
	 */
	private String phone;
	/**
	 * 数美设备指纹标识
	 */
	private String deviceId;
	/**
	 * 用户 android 设备唯一标识
	 */
	private String imei;
	private String mac;
	/**
	 * 用户 iOS 应用唯一标识
	 */
	private String idfv;
	private String idfa;
					
	
	public Image[] getImgs() {
		return imgs;
	}


	public void setImgs(Image[] imgs) {
		this.imgs = imgs;
	}


	public String getTokenId() {
		return tokenId;
	}


	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}


	public String getChannel() {
		return channel;
	}


	public void setChannel(String channel) {
		this.channel = channel;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public String getImei() {
		return imei;
	}


	public void setImei(String imei) {
		this.imei = imei;
	}


	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}


	public String getIdfv() {
		return idfv;
	}


	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}


	public String getIdfa() {
		return idfa;
	}


	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}


	


	@Override
	public String toString() {
		return "ImageFilterRequest [imgs=" + Arrays.toString(imgs) + ", tokenId=" + tokenId + ", channel=" + channel
				+ ", ip=" + ip + ", phone=" + phone + ", deviceId=" + deviceId + ", imei=" + imei + ", mac=" + mac
				+ ", idfv=" + idfv + ", idfa=" + idfa + "]";
	}





	public static class Image implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 53705589996404177L;
		/**
		 * 要检测的图片
		 */
		private String img;
		/**
		 * 图片唯一标识
		 */
		private String btId;
		
		/**
		 * 人物性别
		 */
		private String sex;
		
		/**
		 * 年龄
		 */
		
		private String age;

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public String getBtId() {
			return btId;
		}

		public void setBtId(String btId) {
			this.btId = btId;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Image [img=" + img + ", btId=" + btId + ", sex=" + sex + ", age=" + age + "]";
		}
		
		
	}
}
