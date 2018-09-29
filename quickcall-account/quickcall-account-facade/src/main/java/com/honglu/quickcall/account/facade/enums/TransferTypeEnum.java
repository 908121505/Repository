package com.honglu.quickcall.account.facade.enums;

public enum TransferTypeEnum {
	RECHARGE(1, "可用金额"), REMAINDER(2, "提现金额");

	private Integer type;
	private String desc;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	TransferTypeEnum(Integer type, String desc) {
		this.type = type;
		this.desc = desc;
	}
}
