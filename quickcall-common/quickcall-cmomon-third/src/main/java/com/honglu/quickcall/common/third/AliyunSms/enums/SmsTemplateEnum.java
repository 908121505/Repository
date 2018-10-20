package com.honglu.quickcall.common.third.AliyunSms.enums;

/**
 * Created by len.song on 2018-05-14.
 */
public enum SmsTemplateEnum {
	login_auth_code_template("login_auth_code_template",
			"【轻音互动】您的验证码是{0}，请在10分钟内完成操作。为了您的账户安全，请勿泄露给他人。"), mdwy_login_auth_code_template(
					"mdwy_login_auth_code_template", "【喵无忧】您的验证码是{0}，请在10分钟内完成操作。为了您的账户安全，请勿泄露给他人。")

	;

	private String name;
	private String content;

	SmsTemplateEnum(String name, String content) {
		this.name = name;
		this.content = content;
	}

	public static SmsTemplateEnum fromValue(String name) {
		for (SmsTemplateEnum type : SmsTemplateEnum.values()) {
			if (type.getName().equals(name)) {
				return type;
			}
		}
		return null;
	}

	public static String getNameByValue(String name) {
		for (SmsTemplateEnum type : SmsTemplateEnum.values()) {
			if (type.getName().equals(name)) {
				return type.getContent();
			}
		}
		return "";
	}

	public String getName() {
		return name;
	}

	public String getContent() {
		return content;
	}
}
