package com.honglu.quickcall.account.service.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.business.AccountDubboBusiness;
import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.account.facade.exchange.request.AlipayNotifyRequest;
import com.honglu.quickcall.account.facade.exchange.request.ApplayRefundRequest;
import com.honglu.quickcall.account.facade.exchange.request.BindAliaccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.CancelOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.ConfirmOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.CreateUserAccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.DetailOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvConfirmRefundRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvReceiveOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvStartServiceRequest;
import com.honglu.quickcall.account.facade.exchange.request.FirstPageDaVinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.FirstPageSkillinfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderDaVProductRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderReceiveOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSaveRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSendOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.PayOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.QueryAccountRequest;
import com.honglu.quickcall.account.facade.exchange.request.QueryIngOrderCountRequest;
import com.honglu.quickcall.account.facade.exchange.request.QueryRefundReasonRequest;
import com.honglu.quickcall.account.facade.exchange.request.RechargeRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillInfoRequest;
import com.honglu.quickcall.account.facade.exchange.request.SkillUpdateRequest;
import com.honglu.quickcall.account.facade.exchange.request.WhthdrawRequest;
import com.honglu.quickcall.account.service.service.AliPayService;
import com.honglu.quickcall.account.service.service.IOrderService;
import com.honglu.quickcall.account.service.service.ISkillService;
import com.honglu.quickcall.account.service.service.UserAccountService;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BaseException;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;

/**
 * Created by len.song on 2017-12-18.
 */
@Service("Account.AccountDubboBusiness")
public class AccountDubboBusinessImpl implements AccountDubboBusiness {
	private static final Logger logger = LoggerFactory.getLogger(AccountDubboBusinessImpl.class);

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private AliPayService aliPayService;
	@Autowired
	private ISkillService skillService;
	@Autowired
	private IOrderService orderService;

	@Override
	public CommonResponse excute(AbstractRequest request) {
		logger.info("请求参数为:{}", request);
		if (request == null) {
			throw new BizException(BizCode.ParamError, BizCode.ParamError.desc());
		}

		CommonResponse response = new CommonResponse();
		try {
			switch (request.getBizCode()) {
			/////////////////////////////////////////////////////////////////
			/** 大V接单设置展示 */
			case OrderRequestType.QUERY_SKILL_INFO:
				response = skillService.querySkillInfoPersonal((SkillInfoRequest) request);
				break;
			/** 大V接单设置（开启关闭修改） */
			case OrderRequestType.UPDATE_SKILL_INFO:
				response = skillService.updateSkillInfoPersonal((SkillUpdateRequest) request);
				break;

			/** 首页大V技能展示 */
			case OrderRequestType.QUERY_ORDER_FOR_FIRST_PAGE:
				response = skillService.getFirstPageDaVinfo((FirstPageDaVinfoRequest) request);
				break;
			/** 首页技能种类展示 */
			case OrderRequestType.QUERY_SKILL_NAME_FOR_FIRST_PAGE:
				response = skillService.getFirstPageSkillinfo((FirstPageSkillinfoRequest) request);
				break;
			/////////////////////////////////////////////////////////////////
			/** 获取主播开启产品 */
			case OrderRequestType.ORDER_DAV_PRODUCT_LIST:
				response = orderService.queryDaVProduct((OrderDaVProductRequest) request);
				break;
			/** 用户下单 */
			case OrderRequestType.ORDER_SAVE:
				response = orderService.saveOrder((OrderSaveRequest) request);
				break;
			/** 收到的订单 */
			case OrderRequestType.ORDER_RECEIVE_ORDER_LIST:
				response = orderService.queryReceiveOrderList((OrderReceiveOrderListRequest) request);
				break;
			/** 发起的订单 */
			case OrderRequestType.ORDER_SEND_ORDER_LIST:
				response = orderService.querySendOrderList((OrderSendOrderListRequest) request);
				break;

			/////////////////////////////////////////////////////////////////
			/** 取消订单 */
			case OrderRequestType.CANCEL_ORDER:
				response = orderService.cancelOrder((CancelOrderRequest) request);
				break;
				/** 订单详情 */
			case OrderRequestType.DETAIL_ORDER:
				response = orderService.detailOrder((DetailOrderRequest) request);
				break;
			/** 发起的订单页--去支付 */
			case OrderRequestType.CUST_PAY_ORDER:
				response = orderService.payOrder((PayOrderRequest) request);
				break;
			/** 发起的订单页--申请退款/完成 */
			case OrderRequestType.CUST_APPLAY_REFUND:
				response = orderService.applayRefund((ApplayRefundRequest) request);
				break;
			/** 发起的订单页--同意/拒绝 */
			case OrderRequestType.CUST_CONFIRM_ORDER:
				response = orderService.confirmOrder((ConfirmOrderRequest) request);
				break;
			/////////////////////////////////////////////////////////////////
			/** 收到的订单页--大V接受/拒绝 */
			case OrderRequestType.DV_RECEIVE_ORDER:
				response = orderService.dvReceiveOrder((DvReceiveOrderRequest) request);
				break;
			/** 收到的订单页--大V立即服务 */
			case OrderRequestType.DV_START_SERVICE:
				response = orderService.dvStartService((DvStartServiceRequest) request);
				break;
			/** 收到的订单页--大V同意退款/拒绝 */
			case OrderRequestType.DV_CONFRIM_ORDER:
				response = orderService.dvConfirmRefund((DvConfirmRefundRequest) request);
				break;
			///////////////////////////
				/** 查询进行中订单数量 */
			case OrderRequestType.QUERY_ING_ORDER_COUNT:
				response = orderService.queryIngOrderCount((QueryIngOrderCountRequest) request);
				break;
				/**查询退款理由 */
			case OrderRequestType.QUERY_REFUND_REASON:
				response = orderService.queryRefundReason((QueryRefundReasonRequest) request);
				break;

		   /////////////////////////////////订单相关结束///////////////////////////////////
			case AccountFunctionType.CreateUserAccount:
				// 创建账户
				response = userAccountService.createAccount((CreateUserAccountRequest) request);
				break;
			case AccountFunctionType.AlipayRecharge:
				// 支付宝充值
				response = aliPayService.recharge((RechargeRequest) request);
				break;
			case AccountFunctionType.AlipayWhithdraw:
				// 支付宝提现
				response = aliPayService.whthdraw((WhthdrawRequest) request);
				break;
			case AccountFunctionType.BindAliaccount:
				// 绑定支付宝
				response = aliPayService.bindAliaccount((BindAliaccountRequest) request);
				break;
			case AccountFunctionType.AlipayNotify:
				response = aliPayService.alipayNotify((AlipayNotifyRequest) request);
				break;
			case AccountFunctionType.QueryAccount:
				response = userAccountService.queryAccount((QueryAccountRequest) request);
				break;
			default:

			}
		} catch (BaseException e) {
			logger.error("接口编码为：" + request.getBizCode() + "异常：" + e.getMessage(), e);
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("接口编码为：" + request.getBizCode() + "异常：" + e.getMessage(), e);
			response.setCode(UserBizReturnCode.Unknown);
			response.setMessage(e.getMessage() == null ? e + "" : e.getMessage() + e);
		}
		logger.info("返回结果{}", response);
		return response;

	}
}
