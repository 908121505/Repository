package com.honglu.quickcall.account.facade.enums;

/**
 * Created by len.song on 2017-12-16. 账户操作的业务类别
 */
public enum AccountBusinessTypeEnum {
	Recharge(1, "充值"), Withdraw(2, "提现"), PlaceOrder(3, "下单"), CompleteOrder(4, "完成订单"), OrderRefund(5,
			"订单退款"), PlatformReward(6, "平台奖励"), FroZen(7, "冻结金额"), FroZenAccount(8,
					"冻结回账"), AppRecharge(9, "内购充值"), CompulsoryCancel(10, "强制取消"), CompulsoryCompletion(11, "强制完成");

	private Integer type;
	private String desc;

	AccountBusinessTypeEnum(Integer code, String desc) {
		this.type = code;
		this.desc = desc;
	}

	public static AccountBusinessTypeEnum fromValue(Integer value) {
		for (AccountBusinessTypeEnum type : AccountBusinessTypeEnum.values()) {
			if (type.getType().equals(value)) {
				return type;
			}
		}
		return null;
	}

	public static String getNameByValue(Integer value) {
		for (AccountBusinessTypeEnum type : AccountBusinessTypeEnum.values()) {
			if (type.getType().equals(value)) {
				return type.getDesc();
			}
		}
		return "";
	}

	public Integer getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}
}
