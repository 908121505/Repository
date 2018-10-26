package com.calf.module.common.entity;

public class FileUpload {

	private String result;//上传标识
	private String msg;//上传结果说明
	private String imgUrl;//上传图片地址
	private String md5Str;//文件加密
	
	public String getMd5Str() {
		return md5Str;
	}
	public void setMd5Str(String md5Str) {
		this.md5Str = md5Str;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public FileUpload(String result, String msg, String imgUrl) {
		super();
		this.result = result;
		this.msg = msg;
		this.imgUrl = imgUrl;
	}
	
	
}
