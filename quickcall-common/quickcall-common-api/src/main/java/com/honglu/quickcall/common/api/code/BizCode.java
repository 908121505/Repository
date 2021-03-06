package com.honglu.quickcall.common.api.code;

/**
 * Created by len.song on 2017-12-07.
 */
public class BizCode extends AbstractCodedEnum {
	public static final BizCode Success;
	public static final BizCode ParamNull;
	public static final BizCode ParamError;
	public static final BizCode ParamNotDigital;
	public static final BizCode ParamGT0;
	public static final BizCode ParamLT0;
	public static final BizCode ParamEmpty;
	public static final BizCode DataNotExist;
	public static final BizCode DataIsExist;
	public static final BizCode DBError;
	public static final BizCode DuplicateOperation;
	public static final BizCode SerializationException;
	public static final BizCode InstantiationError;
	public static final BizCode ClassCastError;
	public static final BizCode Unknown;
	public static final BizCode UnIdent;
	public static final BizCode ActivityUnBegin;
	public static final BizCode ActivityIsEnd;
	public static final BizCode ActivityIsNotExist;
	public static final BizCode ChargeOrderSaveFail;
	public static final BizCode ChargeOrderFail;
	public static final BizCode DataStateError;

	/***** 与用户相关的 ****/
	// 用户不存在
	public static final BizCode CustomerNotExist;
	// 用户封禁 统一code
	public static final BizCode CustomerClosure;
	// 用户操作异常 前端显示 统一code
	public static final BizCode CustomerError;
	// 404 永久封禁
	public static final BizCode CustomerClosureNx;
	// 405 密码
	public static final BizCode CustomerPwdError;
	// 406 验证码
	public static final BizCode CustomerSmsError;

	private ServiceCode serviceCode;

	public BizCode() {

	}

	public BizCode(String name, String code, ServiceCode serviceCode, String desc) {
		super(serviceCode.name() + "#" + name, serviceCode.code() + code, desc);
		this.serviceCode = serviceCode;
	}

	@Override
	protected Class<? extends AbstractEnum> getEnumType() {
		return BizCode.class;
	}

	public ServiceCode getServiceCode() {
		return this.serviceCode;
	}

	static {
		Success = new BizCode("Success", "000", ServiceCode.Default, "成功");
		ParamNull = new BizCode("ParamNull", "001", ServiceCode.Default, "参数为空");
		ParamError = new BizCode("ParamError", "002", ServiceCode.Default, "参数错误");
		ParamNotDigital = new BizCode("ParamNotDigital", "003", ServiceCode.Default, "参数非数字");
		ParamGT0 = new BizCode("ParamGT0", "004", ServiceCode.Default, "参数大于0");
		ParamLT0 = new BizCode("ParamLT0", "005", ServiceCode.Default, "参数小于0");
		ParamEmpty = new BizCode("ParamEmpty", "006", ServiceCode.Default, "参数为空");
		DataNotExist = new BizCode("DataNotExist", "100", ServiceCode.Default, "数据不存在");
		DataIsExist = new BizCode("DataIsExist", "101", ServiceCode.Default, "数据已存在");
		DBError = new BizCode("DBError", "102", ServiceCode.Default, "数据异常");
		DuplicateOperation = new BizCode("DuplicateOperation", "200", ServiceCode.Default, "重复操作");
		SerializationException = new BizCode("SerializationException", "201", ServiceCode.Default, "序列化操作异常");
		InstantiationError = new BizCode("InstantiationError", "202", ServiceCode.Default, "实例化异常");
		ClassCastError = new BizCode("ClassCastError", "203", ServiceCode.Default, "类型转换异常");
		Unknown = new BizCode("Unknown", "999", ServiceCode.Default, "系统异常");
		UnIdent = new BizCode("UnIdent", "696", ServiceCode.Default, "女神未认证");
		ActivityUnBegin = new BizCode("ActivityUnBegin", "695", ServiceCode.Default, "活动未开始");
		ActivityIsEnd = new BizCode("ActivityIsEnd", "694", ServiceCode.Default, "活动已结束");
		ActivityIsNotExist = new BizCode("ActivityIsNotExist", "699", ServiceCode.Default, "活动不存在");
		DataStateError = new BizCode("DataStateError", "700", ServiceCode.Default, "数据状态异常");

		/**
		 * 300 开头，与支付订单相关
		 */
		ChargeOrderSaveFail = new BizCode("ChargeOrderSaveFail", "301", ServiceCode.Default, "保存充值订单失败");
		ChargeOrderFail = new BizCode("ChargeOrderFail", "302", ServiceCode.Default, "充值失败");

		/************************** 与用户相关的 - start **********************/
		CustomerNotExist = new BizCode("CustomerNotExist", "401", ServiceCode.Default, "用户不存在");
		CustomerClosure = new BizCode("CustomerClosure", "402", ServiceCode.Default, "用户临时封禁");
		CustomerError = new BizCode("CustomerError", "403", ServiceCode.Default, "");

		CustomerClosureNx = new BizCode("CustomerClosureNx", "404", ServiceCode.Default, "用户永久封禁");

		CustomerPwdError = new BizCode("CustomerPwdError", "405", ServiceCode.Default, "密码错误");

		CustomerSmsError = new BizCode("CustomerSmsError", "406", ServiceCode.Default, "验证码错误");

		/************************** 与用户相关的 - end **********************/
	}

	public String getCode() {
		return this.code();
	}
}
