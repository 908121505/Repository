package com.honglu.quickcall.account.service.business;

import com.honglu.quickcall.account.facade.exchange.request.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.business.AccountDubboBusiness;
import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.account.service.bussService.AliPayService;
import com.honglu.quickcall.account.service.bussService.ApplePayService;
import com.honglu.quickcall.account.service.bussService.BarrageMessageService;
import com.honglu.quickcall.account.service.bussService.IOrderService;
import com.honglu.quickcall.account.service.bussService.ISkillBussService;
import com.honglu.quickcall.account.service.bussService.UserAccountService;
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
	private ISkillBussService skillService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private BarrageMessageService barrageMessageService;
	@Autowired
	private ApplePayService applePayService;

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
				response = skillService.getFirstPageSkillItemInfo((FirstPageSkillinfoRequest) request);
				break;
				/** 首页技能种类展示 */
			case OrderRequestType.QUERY_DV_LIST_BY_TYPE:
				response = skillService.getDaVListBySkillItemId((DaVListBySkillItemIdRequest) request);
				break;
			/////////////////////////////////////////////////////////////////
			/** 获取主播开启产品 */
			case OrderRequestType.ORDER_DAV_PRODUCT_LIST:
				response = orderService.queryDaVSkill((OrderDaVSkillRequest) request);
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
				/** IM订单详情 */
			case OrderRequestType.DETAIL_ORDER_FOR_IM:
				response = orderService.detailOrderForIM((DetailOrderForIMRequest) request);
				break;
			/** 发起的订单页--去支付 */
//			case OrderRequestType.CUST_PAY_ORDER:
//				response = orderService.payOrder((PayOrderRequest) req);
//				break;
			/** 发起的订单页--申请退款/完成 */
			case OrderRequestType.CUST_CONFIRM_FINISH_REFUND:
				response = orderService.custConfirmFinish((CustConfirmFinishRequest) request);
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
				response = orderService.finishOrder((FinishOrderRequest) request);
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
			/** 订单评价页面 **/
			case OrderRequestType.ORDER_EVALUATION:
				response = orderService.orderEvaluation((OrderEvaluationRequest) request);
				break;
			/** 提交订单评价 **/
			case OrderRequestType.ORDER_EVALUATION_SUBMIT:
				response = orderService.submitOrderEvaluation((OrderEvaluationSubmitRequest) request);
				break;

		    /////////////////////////////////订单相关结束///////////////////////////////////

			/////////////////////////////////充值相关结束///////////////////////////////////
			case AccountFunctionType.APPLY_PAY_RECHARGE:
				//苹果内购充值
				response = applePayService.createOrder((ApplePayRequest) request);
				break;
			case AccountFunctionType.APPLY_PAY_NOTIFY:
				//苹果内购回调验证
				response = applePayService.applePurchase((ApplePurchaseRequest) request);
				break;
			/////////////////////////////////充值相关结束///////////////////////////////////

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
			/** 获取弹幕消息 **/
			case AccountFunctionType.GET_BARRAGE_MESSAGE:
				response = barrageMessageService.rpopMessage((BarrageMessageRequest) request);
				break;
			/** 获取弹幕消息 **/
			case AccountFunctionType.FirstOnceWindowEverthDay:
				response = barrageMessageService.popWindowOnce((FirstBarrageRequest) request);
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
