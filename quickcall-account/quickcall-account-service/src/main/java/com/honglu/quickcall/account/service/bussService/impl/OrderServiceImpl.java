package com.honglu.quickcall.account.service.bussService.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.entity.Account;
import com.honglu.quickcall.account.facade.entity.CustomerSkill;
import com.honglu.quickcall.account.facade.entity.EvaluationLabel;
import com.honglu.quickcall.account.facade.entity.Order;
import com.honglu.quickcall.account.facade.entity.SkillItem;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.account.facade.exchange.request.CancelOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.ConfirmOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.CustConfirmFinishRequest;
import com.honglu.quickcall.account.facade.exchange.request.DetailOrderForIMRequest;
import com.honglu.quickcall.account.facade.exchange.request.DetailOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvReceiveOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.DvStartServiceRequest;
import com.honglu.quickcall.account.facade.exchange.request.FinishOrderRequest;
import com.honglu.quickcall.account.facade.exchange.request.MsgOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderDaVSkillRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderEvaluationRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderEvaluationSubmitRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderReceiveOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSaveRequest;
import com.honglu.quickcall.account.facade.exchange.request.OrderSendOrderListRequest;
import com.honglu.quickcall.account.facade.exchange.request.QueryIngOrderCountRequest;
import com.honglu.quickcall.account.facade.vo.CustomerSkillIMVO;
import com.honglu.quickcall.account.facade.vo.OrderDaVSkillVO;
import com.honglu.quickcall.account.facade.vo.OrderDetailForIMVO;
import com.honglu.quickcall.account.facade.vo.OrderDetailVO;
import com.honglu.quickcall.account.facade.vo.OrderEvaluationVo;
import com.honglu.quickcall.account.facade.vo.OrderIMVO;
import com.honglu.quickcall.account.facade.vo.OrderMsgOrderListVO;
import com.honglu.quickcall.account.facade.vo.OrderReceiveOrderListVO;
import com.honglu.quickcall.account.facade.vo.OrderSendOrderListVO;
import com.honglu.quickcall.account.facade.vo.OrderSkillItemVO;
import com.honglu.quickcall.account.facade.vo.OrderTempResponseVO;
import com.honglu.quickcall.account.service.bussService.AccountService;
import com.honglu.quickcall.account.service.bussService.BarrageMessageService;
import com.honglu.quickcall.account.service.bussService.CommonService;
import com.honglu.quickcall.account.service.bussService.IOrderService;
import com.honglu.quickcall.account.service.dao.CustomerSkillMapper;
import com.honglu.quickcall.account.service.dao.OrderMapper;
import com.honglu.quickcall.account.service.dao.SkillItemMapper;
import com.honglu.quickcall.activity.facade.business.CouponDubboBusiness;
import com.honglu.quickcall.activity.facade.entity.CustomerCoupon;
import com.honglu.quickcall.activity.facade.vo.CouponOrderVo;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.AliyunSms.utils.SendSmsUtil;
import com.honglu.quickcall.common.third.push.GtPushUtil;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.producer.facade.business.DataDuriedPointBusiness;
import com.honglu.quickcall.producer.facade.req.databury.DataBuriedPointSubmitOrderReq;
import com.honglu.quickcall.user.facade.business.UserCenterSendMqMessageService;
import com.honglu.quickcall.user.facade.entity.Customer;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单相关逻辑
 * 
 * @Package: com.honglu.quickcall.account.web.service.impl
 * @author: chenliuguang
 * @date: 2018年9月22日 下午3:17:04
 */
@Service
public class OrderServiceImpl implements IOrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private CommonService commonService;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private AccountService accountService;
	@Autowired
	private CustomerSkillMapper customerSkillMapper;
	@Autowired
	private BarrageMessageService barrageMessageService;
	@Autowired
	private SkillItemMapper skillItemMapper;
	@Autowired
	private UserCenterSendMqMessageService userCenterSendMqMessageService;
	@Autowired
	private DataDuriedPointBusiness dataDuriedPointBusiness;
	@Autowired
	private CouponDubboBusiness couponDubboBusiness;

	@Override
	public CommonResponse queryDaVSkill(OrderDaVSkillRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询技能信息参数异常");
		}
		// String customerJson = JedisUtil.get(RedisKeyConstants.USER_CUSTOMER_INFO +
		// request.getCustomerId());
		// if (StringUtils.isEmpty(customerJson)) {
		// return ResultUtils.result(BizCode.CustomerNotExist);
		// }
		LOGGER.info("======>>>>>queryDaVSkill()入参：" + request.toString());

		Long customerId = request.getCustomerId();
		OrderDaVSkillVO resultVO = orderMapper.getCustomerByCustomerId(customerId);

		List<OrderSkillItemVO> custSkillList = new ArrayList<>();

		List<CustomerSkill> skillList = customerSkillMapper.querySkillInfoPersonal(customerId);
		if (!CollectionUtils.isEmpty(skillList)) {
			for (CustomerSkill skill : skillList) {
				OrderSkillItemVO vo = new OrderSkillItemVO();
				vo.setPrice(skill.getSkillPrice());
				vo.setDiscontPrice(skill.getDiscountPrice());
				vo.setUserSkillItemId(skill.getCustomerSkillId());
				Long skillItemId = skill.getSkillItemId();
				SkillItem skillItem = skillItemMapper.selectByPrimaryKey(skillItemId);
				vo.setSkillType(skillItem.getSkillType());
				vo.setSkillIcon(skillItem.getUnlockIcon());
				vo.setServiceUnit(skill.getServiceUnit());
				vo.setSkillItemName(skillItem.getSkillItemName());

				// 技能-客户优惠券
				CouponOrderVo cov = null;
				try {
					cov = couponDubboBusiness.showActivityCouponForOrder(skillItemId + "", customerId + "");
				} catch (Exception e) {
					LOGGER.warn("根据技能ID获取券信息发生异常，异常信息：", e);
				}
				if (cov != null) {
					vo.setShowTip(cov.getShowTip());
					vo.setCouponPrice(cov.getCouponPrice());
					// vo.setTipHtml(cov.getTipHtml());
					vo.setCouponId(cov.getCouponId());
					vo.setCouponName(cov.getCouponName());
					vo.setCouponDeductPrice(cov.getCouponDeductPrice());
					vo.setCustomerCouponId(cov.getCustomerCouponId());
				}

				custSkillList.add(vo);
			}
		}

		resultVO.setCustSkillList(custSkillList);
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultVO);
		LOGGER.info("======>>>>>用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}

	@Override
	public CommonResponse saveOrder(OrderSaveRequest request) {
		if (request == null || request.getCustomerId() == null || request.getCustomerSkillId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "下单参数异常");
		}
		// String customerJson = JedisUtil.get(RedisKeyConstants.USER_CUSTOMER_INFO +
		// request.getCustomerId());
		// if (StringUtils.isEmpty(customerJson)) {
		// return ResultUtils.result(BizCode.CustomerNotExist);
		// }
		LOGGER.info("======>>>>>saveOrder()入参：" + request.toString());

		CommonResponse commonResponse = commonService.getCommonResponse();
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("retCode", OrderSkillConstants.DEFAULT_NULL_STR);
		resultMap.put("downLoadStr", OrderSkillConstants.DEFAULT_NULL_STR);
		resultMap.put("orderId", OrderSkillConstants.DEFAULT_NULL_STR);

		try {
			Date currTime = new Date();
			// 1.首先判断大V是否可以接单
			Long customerId = request.getCustomerId();
			Long serviceId = request.getServiceId();

			List<Integer> statusList = new ArrayList<Integer>();
			statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START);// 待开始
			statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE);// 大V发起开始服务
			statusList.add(OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT);// 进行中
			statusList.add(OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH);// 进行中（大V发起完成服务）

			List<Order> gongIngOrderList = orderMapper.selectGongIngOrderListByCustomerId(serviceId,
					OrderSkillConstants.SKILL_TYPE_YES, statusList);
			if (!CollectionUtils.isEmpty(gongIngOrderList)) {
				// 记录不为空说明大V正在接单，不能下单
				Order order = gongIngOrderList.get(0);
				// 订单预计结束时间
				Date expectEndTime = order.getExpectEndTime();

				if (expectEndTime != null) {
					// 当前时间和截止时间的间隔秒数
					Long remainStr = DateUtils.getDiffSeconds(currTime, expectEndTime);
					/*
					 * String downLoadStr = null; if (remainStr != null && remainStr > 0) {
					 * downLoadStr = DateUtils.getDiffSeconds(remainStr); }
					 */
					resultMap.put("downLoadStr", remainStr);
				}
				resultMap.put("retCode", OrderSkillConstants.RET_CODE_DV_BUSY);
				commonResponse.setData(resultMap);
				// 返回大V正忙，以及结束时间
				return commonResponse;

			}
			DataBuriedPointSubmitOrderReq req = new DataBuriedPointSubmitOrderReq();

			Long customerSkillId = request.getCustomerSkillId();

			Integer weekIndex = DateUtils.getDayOfWeek();
			Integer skillSwitch = 1;
			// String endTimeStr = DateUtils.formatDateHHSS(new Date()).replaceAll(":", "")
			// ;

			// 根据技能ID 获取等级获取价格信息
			CustomerSkill customerSkill = customerSkillMapper.selectByPrimaryKeyExt(customerSkillId, weekIndex,
					skillSwitch, new Date());

			if (customerSkill == null) {
				resultMap.put("retCode", OrderSkillConstants.RET_CODE_DV_NOT_ACCEPTE_ORDER);
				commonResponse.setData(resultMap);
				// 返回大V正忙，以及结束时间
				return commonResponse;
			}
			Long orderId = UUIDUtils.getId();
			Integer orderNum = request.getOrderNum();
			BigDecimal price = customerSkill.getDiscountPrice();

			// 根据券判断真正的订单金额
			BigDecimal orderAmounts = BigDecimal.ZERO;

			// 用券状态
			Integer couponFlag = OrderSkillConstants.ORDER_COUPON_FLAG_DEFAULT;
			// 获取券金额
			BigDecimal couponPrice = request.getCouponPrice();
			Integer customerCouponId = request.getCustomerCouponId();
			if (customerCouponId != null && couponPrice != null) {
				BigDecimal discountPrice = price.subtract(couponPrice);
				orderAmounts = new BigDecimal(orderNum - 1).multiply(price);
				if (discountPrice.compareTo(BigDecimal.ZERO) > 0) {
					orderAmounts = orderAmounts.add(discountPrice);
				}
				couponFlag = OrderSkillConstants.ORDER_COUPON_FLAG_USE;
			} else {
				// 根据券判断真正的订单金额
				orderAmounts = new BigDecimal(orderNum).multiply(price);
			}

			// 判断余额是否充足
			Account account = accountService.queryAccount(customerId);
			if (account != null) {
				// 消费用户的充值金额
				BigDecimal rechargeAmounts = account.getRechargeAmounts();
				if (rechargeAmounts != null) {
					if (orderAmounts.compareTo(rechargeAmounts) > 0) {
						// 大V忙
						resultMap.put("retCode", OrderSkillConstants.RET_CODE_BALANCE_NOT_ENOUTH);
						commonResponse.setData(resultMap);
						// 返回余额不足状态
						return commonResponse;
					} else {

						// 用户下单，用户出账
						LOGGER.info("---------saveOrder：customerId=" + customerId + ";orderAmounts=" + orderAmounts);
						accountService.outAccount(customerId, orderAmounts, TransferTypeEnum.RECHARGE,
								AccountBusinessTypeEnum.PlaceOrder, orderId);

					}
				}
			}

			// 创建订单
			Order record = new Order();

			Long skillItemId = customerSkill.getSkillItemId();
			SkillItem skillItem = skillItemMapper.selectByPrimaryKey(skillItemId);
			Integer skillType = skillItem.getSkillType();
			record.setSkillType(skillType);
			if (OrderSkillConstants.SKILL_TYPE_NO == skillType) {
				Date appointTime = DateUtils.formatDate(request.getAppointTimeStr());
				// Calendar cal = Calendar.getInstance();
				// cal.setTime(appointTime);
				// cal.add(Calendar.MINUTE, 5);
				// Date expectEndTime = cal.getTime();
				record.setAppointTime(appointTime);
				// record.setExpectEndTime(expectEndTime);
			}

			record.setServiceUnit(customerSkill.getServiceUnit());
			record.setServicePrice(customerSkill.getSkillPrice());
			record.setDiscountRate(customerSkill.getDiscountRate());
			record.setSkillItemId(skillItemId);

			record.setOrderNo(orderId);
			record.setCustomerSkillId(customerSkillId);
			record.setCustomerId(customerId);
			record.setOrderId(orderId);
			record.setServiceId(serviceId);
			record.setCreateTime(new Date());
			record.setOrderAmounts(orderAmounts);
			record.setOrderNum(orderNum);
			record.setOrderStatus(OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE);
			// 设置用券标识
			record.setCouponFlag(couponFlag);
			record.setOrderTime(currTime);
			record.setRemark(request.getRemark());
			orderMapper.insert(record);

			CustomerCoupon customerCoupon = new CustomerCoupon();
			customerCoupon.setId(customerCouponId);
			try {
				couponDubboBusiness.updateCustomerCouponById(customerCoupon);
			} catch (Exception e1) {
				LOGGER.warn("======>>>>>saveOrder()消费券发生异常：", e1);
			}
			resultMap.put("retCode", OrderSkillConstants.RET_CODE_SUCCESS);
			resultMap.put("orderId", orderId + "");

			// ADUAN 下单支付成功后 -- 发送弹幕消息
			LOGGER.info("下单成功 -- 发送弹幕消息：", orderId);
			barrageMessageService.lpushMessage(orderId);

			// 获取大V手机号码
			Customer service = commonService.getPhoneByCustomerId(serviceId);
			LOGGER.info("用户下单大V信息：" + service);
			if (service != null) {
				String phone = service.getPhone();
				LOGGER.info("用户下单大V手机号码" + phone);
				if (StringUtils.isNotBlank(phone)) {
					SendSmsUtil.sendSms(UUIDUtils.getUUID(), phone, 2, customerSkill.getSkillName());
				}

				String gtId = service.getGtClientId();
				if (StringUtils.isNotBlank(gtId)) {
					// 用户下单需要使用个推推送消息
					GtPushUtil.sendNotificationTemplateToList(gtId, OrderSkillConstants.GT_MSG_ORDER_TITLE,
							OrderSkillConstants.GT_MSG_CONTENT_RECEIVE_ORDER,
							OrderSkillConstants.GT_MSG_CONTENT_RECEIVE_ORDER_URL);
				}
			}
			// 下单成功后推送IM消息
			RongYunUtil.sendOrderMessage(serviceId, OrderSkillConstants.IM_MSG_CONTENT_RECEIVE_ORDER_TO_DV,
					OrderSkillConstants.MSG_CONTENT_DAV);
			RongYunUtil.sendOrderMessage(customerId, OrderSkillConstants.IM_MSG_CONTENT_RECEIVE_ORDER_TO_CUST,
					OrderSkillConstants.MSG_CONTENT_C);
			LOGGER.info("======>>>>>用户编号为：" + request.getCustomerId() + "下单成功");

			// 下单触发埋点
			req.setActual_payment_amount(orderAmounts.doubleValue());
			req.setOrder_amount(orderAmounts.doubleValue());
			req.setOrder_id(orderId + "");
			req.setOrder_quantity(Double.valueOf(orderNum + ""));
			req.setOrder_type(skillItem.getSkillItemName());
			req.setUser_id(customerId + "");
			try {
				dataDuriedPointBusiness.burySubmitOrderData(req);
			} catch (Exception e) {
				LOGGER.error("下单埋点发生异常，异常信息：", e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("retCode", OrderSkillConstants.RET_CODE_SYSTEM_ERROR);
		}
		commonResponse.setData(resultMap);
		return commonResponse;
	}

	@Override
	public CommonResponse queryMsgOrderList(MsgOrderListRequest request) {
		if (request == null || request.getCustomerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询接收订单参数异常");
		}
		LOGGER.info("======>>>>>queryMsgOrderList()入参：" + request.toString());
		Long customerId = request.getCustomerId();
		String customerJson = JedisUtil.get(RedisKeyConstants.USER_CUSTOMER_INFO + customerId);
		if (StringUtils.isEmpty(customerJson)) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}

		List<OrderMsgOrderListVO> queryList = orderMapper.queryMsgOrderList(customerId);
		List<OrderMsgOrderListVO> resultList = new ArrayList<OrderMsgOrderListVO>();
		if (!CollectionUtils.isEmpty(queryList)) {
			for (OrderMsgOrderListVO order : queryList) {
				String msgContent = commonService.getMsgContent(order.getCustomerFlag(), order.getOrderStatus());
				order.setMsgContent(msgContent);
				resultList.add(order);
				CommonResponse commonResponse = commonService.getCommonResponse();
				commonResponse.setData(resultList);
				LOGGER.info("======>>>>>查询收到的订单，用户编号为：" + request.getCustomerId() + "查询成功");
				return commonResponse;
			}
		}
		return null;
	}

	/**
	 * 已收到的订单只能是已支付状态
	 */
	@Override
	public CommonResponse queryReceiveOrderList(OrderReceiveOrderListRequest request) {
		if (request == null || request.getCustomerId() == null || request.getOrderStatus() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询接收订单参数异常");
		}
		LOGGER.info("======>>>>>queryReceiveOrderList()入参：" + request.toString());
		Long customerId = request.getCustomerId();
		// String customerJson = JedisUtil.get(RedisKeyConstants.USER_CUSTOMER_INFO +
		// customerId);
		// if (StringUtils.isEmpty(customerJson)) {
		// return ResultUtils.result(BizCode.CustomerNotExist);
		// }
		Integer orderStatusParam = request.getOrderStatus();
		List<Integer> statusList = commonService.getReceiveOrderStatusList(orderStatusParam);

		List<OrderReceiveOrderListVO> resultList = orderMapper.queryReceiveOrderList(customerId, statusList);

		if (!CollectionUtils.isEmpty(resultList)) {
			for (OrderReceiveOrderListVO info : resultList) {
				OrderTempResponseVO responseVO = commonService.getCountDownSeconds(info.getOrderStatus(),
						info.getOrderTime(), info.getReceiveOrderTime(), info.getStartServiceTime(),
						info.getExpectEndTime(), info.getAppointTime());
				info.setCountDownSeconds(responseVO.getCountDownSeconds());
				info.setOrderStatus(responseVO.getOrderStatus());
			}
		}

		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultList);
		LOGGER.info("======>>>>>查询收到的订单，用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}

	/**
	 * 发起的订单是全状态
	 */
	@Override
	public CommonResponse querySendOrderList(OrderSendOrderListRequest request) {
		if (request == null || request.getCustomerId() == null || request.getOrderStatus() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询发起订单参数异常");
		}
		// String customerJson = JedisUtil.get(RedisKeyConstants.USER_CUSTOMER_INFO +
		// request.getCustomerId());
		// if (StringUtils.isEmpty(customerJson)) {
		// return ResultUtils.result(BizCode.CustomerNotExist);
		// }
		LOGGER.info("======>>>>>querySendOrderList()入参：" + request.toString());
		Long customerId = request.getCustomerId();
		Integer orderStatusParam = request.getOrderStatus();
		List<Integer> statusList = commonService.getSendOrderStatusList(orderStatusParam);
		List<OrderSendOrderListVO> resultList = orderMapper.querySendOrderList(customerId, statusList);
		if (!CollectionUtils.isEmpty(resultList)) {
			for (OrderSendOrderListVO info : resultList) {
				OrderTempResponseVO responseVO = commonService.getCountDownSeconds(info.getOrderStatus(),
						info.getOrderTime(), info.getReceiveOrderTime(), info.getStartServiceTime(),
						info.getExpectEndTime(), info.getAppointTime());
				info.setCountDownSeconds(responseVO.getCountDownSeconds());

				info.setOrderStatus(responseVO.getOrderStatus());

			}
		}

		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(resultList);
		LOGGER.info("======>>>>>查询发送的订单，用户编号为：" + request.getCustomerId() + "查询成功");
		return commonResponse;
	}

	// ==================================发起的订单页相关开始==================================
	@Override
	public CommonResponse cancelOrder(CancelOrderRequest request) {
		if (request == null || request.getOrderId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "取消订单参数异常");
		}
		LOGGER.info("======>>>>>cancelOrder()入参：" + request.toString());
		Long orderId = request.getOrderId();
		// 查询订单详情
		Order order = orderMapper.selectByPrimaryKey(orderId);
		Integer orderStatus = null;
		Long customerId = order.getCustomerId();
		if (order != null) {
			Integer oldOrderStatus = order.getOrderStatus();
			// 根据不同状态进行取消
			// 待接单取消
			if (OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE == oldOrderStatus) {
				// 大V接受订单之前取消订单
				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_RECEIVE;
				// 待开始 大V接单
			} else if (OrderSkillConstants.ORDER_STATUS_WAITING_START == oldOrderStatus) {
				// 订单状态10.大V接单后用户自主取消
				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_DAV_START;
				// 大V发起开始服务
			} else if (OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE == oldOrderStatus) {
				// 订单状态8.大V接受订单之后开始之前用户自主取消
				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCLE_USER_SELF_BEFORE_SERVICE;
			} else if (OrderSkillConstants.ORDER_STATUS_GOING_WAITING_START == oldOrderStatus) {
				// 订单状态23.取消（叫醒预约时间之前取消）
				orderStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_BEFORE_APPOINT_TIME;
			} else {
				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
			}
			BigDecimal payAmount = order.getOrderAmounts();
			// TODO 需要关注一下订单用券逻辑
			Integer couponFlag = order.getCouponFlag();
			commonService.cancelUpdateOrder(orderId, orderStatus, new Date(), request.getSelectReason(),
					request.getRemarkReason(), couponFlag);
			// 金额不为空，说明需要退款给用户
			if (payAmount != null) {

				// 订单取消，用户回款
				LOGGER.info("---------cancelOrder：customerId=" + customerId + ";orderAmounts=" + payAmount);
				accountService.inAccount(customerId, payAmount, TransferTypeEnum.RECHARGE,
						AccountBusinessTypeEnum.OrderRefund, orderId);

			}
			// ----start---chenpeng 2018.11.1 取消订单时，查询是否使用优惠券，如果有则退回优惠券
			// 查询用户此订单是否使用优惠券
			CustomerCoupon customerCoupon = couponDubboBusiness.queryCustomerCouponByCustomerIdAndOrderId(customerId,
					orderId);
			if (customerCoupon != null) {
				int cancelUpdateCustomerCouponCount = couponDubboBusiness
						.cancelUpdateCustomerCoupon(customerCoupon.getId());
				LOGGER.info("取消订单 退回优惠券 id：" + customerCoupon.getId() + "更新数量：" + cancelUpdateCustomerCouponCount);
			}
			// -----end---chenpeng 2018.11.1
		}

		Long serviceId = order.getServiceId();
		// 用户取消订单通知大V查看详情
		RongYunUtil.sendOrderMessage(serviceId, OrderSkillConstants.IM_MSG_CONTENT_CANCEL_ORDER_TO_DV,
				OrderSkillConstants.MSG_CONTENT_DAV);
		RongYunUtil.sendOrderMessage(customerId, OrderSkillConstants.IM_MSG_CONTENT_CANCEL_ORDER_TO_CUST,
				OrderSkillConstants.MSG_CONTENT_C);

		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(orderStatus);
		LOGGER.info("======>>>>>订单编号：" + orderId + "，取消订单完成");
		return commonResponse;
	}

	@Override
	public CommonResponse detailOrder(DetailOrderRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询订单详情参数异常");
		}

		LOGGER.info("======>>>>>detailOrder()入参：" + request.toString());
		Integer type = request.getType();
		if (type != OrderSkillConstants.REQUEST_TYPE_CUST && type != OrderSkillConstants.REQUEST_TYPE_DV) {
			throw new BizException(AccountBizReturnCode.paramError, "查询订单详情参数异常");
		}

		Long orderId = request.getOrderId();
		OrderDetailVO orderDetail = null;
		// 订单详情时需要有倒计时业务
		if (type == OrderSkillConstants.REQUEST_TYPE_CUST) {
			orderDetail = orderMapper.queryCustOrderDetail(orderId);
		} else {
			orderDetail = orderMapper.queryDvOrderDetail(orderId);
		}

		if (orderDetail != null) {
			Date birthday = orderDetail.getBirthday();
			int age = DateUtils.getAgeByBirthYear(birthday);
			orderDetail.setAge(age);
			OrderTempResponseVO responseVO = commonService.getCountDownSeconds(orderDetail.getOrderStatus(),
					orderDetail.getOrderTime(), orderDetail.getReceiveOrderTime(), orderDetail.getStartServiceTime(),
					orderDetail.getExpectEndTime(), orderDetail.getAppointTime());
			orderDetail.setCountDownSeconds(responseVO.getCountDownSeconds());
			orderDetail.setOrderStatus(responseVO.getOrderStatus());

			// 根据订单ID查询客户优惠券
			Map<String, String> map = new HashMap<String, String>();
			try {
				map = couponDubboBusiness.getCustomerCouponByOrderId(orderId);
			} catch (Exception e) {
				LOGGER.warn("获取券信息发生异常,异常信息：", e);
			}
			if (map != null) {
				orderDetail.setCouponName(map.get("couponName") == null ? "" : map.get("couponName"));
				orderDetail
						.setCouponPrice(map.get("couponPrice") == null ? null : new BigDecimal(map.get("couponPrice")));
			}

		}

		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(orderDetail);
		LOGGER.info("======>>>>>查询发送的订单，用户编号为：" + orderId + "查询成功");
		return commonResponse;
	}

	@Override
	public CommonResponse detailOrderForIM(DetailOrderForIMRequest request) {
		if (request == null || request.getCustomerId() == null || request.getServiceId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询订单详情参数异常");
		}

		/**
		 * 逻辑： 1.判断serviceId是否有技能项（可以进行接单）， 2.判断二者之间有没有订单关系
		 * 
		 * 
		 */
		CommonResponse commonResponse = commonService.getCommonResponse();
		OrderDetailForIMVO orderDetailForIMVO = new OrderDetailForIMVO();
		// 判断服务方可不可以下单

		Long serviceId = request.getServiceId();
		Long customerId = request.getCustomerId();
		// 判断双方是否存在订单关系
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE);// 待接单
		statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START);// 待开始
		statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE);// 大V发起开始服务
		statusList.add(OrderSkillConstants.ORDER_STATUS_GOING_WAITING_START);// 进行中（大V发起完成服务）
		statusList.add(OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT);// 进行中
		statusList.add(OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH);// 进行中（大V发起完成服务）
		// 判断双方有没有订单关系
		Order order = orderMapper.queryOrderByCustomerIdAndServiceId(customerId, serviceId, statusList);

		if (order == null) {
			order = orderMapper.queryOrderByCustomerIdAndServiceId(serviceId, customerId, statusList);
		}

		/**** 1.必须首先判断双方存在订单关系 */
		if (order != null) {
			orderDetailForIMVO.setRetCode(OrderSkillConstants.IM_RETCODE_ORDER_EXIST);// 不可下单
			OrderIMVO orderIMVO = new OrderIMVO();
			Long customerSkillId = order.getCustomerSkillId();
			orderIMVO = customerSkillMapper.selectCustSkillItem(customerSkillId);

			orderIMVO.setOrderId(order.getOrderId());
			orderIMVO.setServicePrice(order.getServicePrice());
			orderIMVO.setServiceUnit(order.getServiceUnit());

			// 订单倒计时计算
			OrderTempResponseVO responseVO = commonService.getCountDownSeconds(order.getOrderStatus(),
					order.getOrderTime(), order.getReceiveOrderTime(), order.getStartServiceTime(),
					order.getExpectEndTime(), order.getAppointTime());
			orderIMVO.setCountDownSeconds(responseVO.getCountDownSeconds());
			orderIMVO.setOrderStatus(responseVO.getOrderStatus());
			orderDetailForIMVO.setServiceId(order.getServiceId());
			orderDetailForIMVO.setCustomerId(order.getCustomerId());
			orderDetailForIMVO.setOrderIMVO(orderIMVO);

			commonResponse.setData(orderDetailForIMVO);
			Integer newOrderStatus = responseVO.getOrderStatus();
			if (OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT == newOrderStatus
					|| OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH == newOrderStatus) {
				List<Long> orderIdList = new ArrayList<Long>();
				orderIdList.add(order.getOrderId());
				// 更改订单状态为31
				orderMapper.updateOrderReceiveOrder(orderIdList,
						OrderSkillConstants.ORDER_STATUS_GOING_USER_NOT_PING_JIA);
			}

			// 需要计算倒计时时间

			return commonResponse;
		}

		Integer weekIndex = DateUtils.getDayOfWeek();
		Integer skillSwitch = 1;
		// String endTimeStr = DateUtils.formatDateHHSS(new Date()).replaceAll(":", "")
		// ;

		orderDetailForIMVO.setServiceId(serviceId);
		orderDetailForIMVO.setCustomerId(customerId);
		// 根据技能ID 获取等级获取价格信息
		CustomerSkillIMVO customerSkillIMVO = customerSkillMapper.selectCustomerSkillByCustomerId(serviceId, weekIndex,
				skillSwitch, new Date());

		// 服务方当天技能不存在，则关注对方
		if (customerSkillIMVO == null) {
			orderDetailForIMVO.setRetCode(OrderSkillConstants.IM_RETCODE_ORDER_COUND_ORDER);// 不可下单
			commonResponse.setData(orderDetailForIMVO);
			return commonResponse;
		}

		// 用户技能存在，则返回技能相关信息
		// 双方不存在订单关系，判断大V是否在忙
		statusList = new ArrayList<Integer>();
		statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START);// 待开始
		statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE);// 大V发起开始服务
		statusList.add(OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT);// 进行中
		statusList.add(OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH);// 进行中（大V发起完成服务）
		List<Order> gongIngOrderList = orderMapper.selectGongIngOrderListByCustomerId(serviceId,
				OrderSkillConstants.SKILL_TYPE_YES, statusList);
		// 不管大V是否在忙，都展示大V技能信息
		orderDetailForIMVO.setSkillIMVO(customerSkillIMVO);
		if (!CollectionUtils.isEmpty(gongIngOrderList)) {
			// 用户存在进行中或者即将进行中订单，说明大V正忙
			orderDetailForIMVO.setRetCode(OrderSkillConstants.IM_RETCODE_DV_BUSY);
			commonResponse.setData(orderDetailForIMVO);
			return commonResponse;
		}

		// 大V可以下单
		orderDetailForIMVO.setRetCode(OrderSkillConstants.IM_RETCODE_CAN_ORDER);
		orderDetailForIMVO.setSkillIMVO(customerSkillIMVO);

		commonResponse.setData(orderDetailForIMVO);
		LOGGER.info("======>>>>>查询发送的订单，用户编号为：" + customerId + "查询成功");
		return commonResponse;
	}

	@Override
	public CommonResponse custConfirmFinish(CustConfirmFinishRequest request) {
		if (request == null || request.getOrderId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "用户同意大V服务完成参数异常");
		}

		LOGGER.info("======>>>>>applayRefund()入参：" + request.toString());
		Long orderId = request.getOrderId();

		Integer newOrderStatus = null;
		// 查询订单详情
		Order order = orderMapper.selectByPrimaryKey(orderId);
		if (order != null) {
			Integer orderStatus = order.getOrderStatus();
			if (OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH != orderStatus) {
				// 只有进行中才能进行退款
				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
			}
			newOrderStatus = OrderSkillConstants.ORDER_STATUS_FINISHED_USER_ACCEPCT;
			// 修改订单状态为：已完成
			// commonService.updateOrder(orderId, newOrderStatus);
			commonService.custConfirmFinishUpdateOrder(orderId, newOrderStatus);
			// 大V冻结
			LOGGER.info("---------custConfirmFinish：serviceId=" + order.getServiceId() + ";orderAmounts="
					+ order.getOrderAmounts());
			accountService.inAccount(order.getServiceId(), order.getOrderAmounts(), TransferTypeEnum.FROZEN,
					AccountBusinessTypeEnum.FroZen, orderId);

			// 结束后用户获得券
			couponDubboBusiness.getCouponInOrder(order.getSkillItemId(), order.getCustomerId());
			// ADUAN 订单服务完成推送MQ消息
			userCenterSendMqMessageService.sendOrderCostMqMessage(orderId);

			// 大V接受订单通知大V
			RongYunUtil.sendOrderMessage(order.getServiceId(),
					OrderSkillConstants.IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_DV, OrderSkillConstants.MSG_CONTENT_DAV);

		} else {
			// 订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}

		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(newOrderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，用户同意大V服务完成订单完成");
		return commonResponse;
	}

	@Override
	public CommonResponse confirmOrder(ConfirmOrderRequest request) {
		if (request == null || request.getOrderId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "同意/拒绝订单参数异常");
		}
		LOGGER.info("======>>>>>confirmOrder()入参：" + request.toString());
		Long orderId = request.getOrderId();

		// 查询订单详情
		Order order = orderMapper.selectByPrimaryKey(orderId);
		Integer newOrderStatus = null;
		if (order != null) {
			Integer orderStatus = order.getOrderStatus();
			if (OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE != orderStatus) {
				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
			}

			Integer skillType = order.getSkillType();
			if (OrderSkillConstants.SKILL_TYPE_YES == skillType) {

				// 用户同意，修改状态
				newOrderStatus = OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT;
			} else {
				// 用户同意，修改状态:叫醒类型服务专享
				newOrderStatus = OrderSkillConstants.ORDER_STATUS_GOING_WAITING_START;
			}

			Date currTime = new Date();

			String serviceUnit = order.getServiceUnit();
			int addMinute = 0;
			Integer orderNum = order.getOrderNum();

			Calendar cal = Calendar.getInstance();
			Date startTime = order.getStartTime();
			if (startTime != null) {
				// 应该取订单进行中起始时间
				cal.setTime(startTime);
			} else {
				cal.setTime(currTime);
			}
			if (serviceUnit.contains(OrderSkillConstants.SERVICE_UNIT_TIMES)) {
				// 叫醒服务时需要添加5分钟过期时间+时间起点是预期开始时间
				addMinute = 5;
				cal.setTime(order.getAppointTime());
			} else if (serviceUnit.contains(OrderSkillConstants.SERVICE_UNIT_HALF_HOUR)) {
				addMinute = orderNum * 30;
			} else {
				addMinute = orderNum * 60;
			}

			Date endTime = null;
			if (addMinute > 0) {
				cal.add(Calendar.MINUTE, addMinute);
			}
			endTime = cal.getTime();
			commonService.confirmOrderUpdateOrder(orderId, newOrderStatus, new Date(), endTime);
		} else {
			// 订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}

		// 用户同意大V服务完成，通知大V查单订单状态
		Long serviceId = order.getServiceId();
		RongYunUtil.sendOrderMessage(serviceId, OrderSkillConstants.IM_MSG_CONTENT_USER_CONFIRM_START_SERVICE_TO_DAV,
				OrderSkillConstants.MSG_CONTENT_DAV);
		RongYunUtil.sendOrderMessage(order.getCustomerId(),
				OrderSkillConstants.IM_MSG_CONTENT_USER_CONFIRM_START_SERVICE_TO_CUST,
				OrderSkillConstants.MSG_CONTENT_C);

		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(newOrderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，同意/拒绝订单完成");
		return commonResponse;
	}

	@Override
	public CommonResponse dvReceiveOrder(DvReceiveOrderRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "大V同意/拒绝订单参数异常");
		}

		LOGGER.info("======>>>>>dvReceiveOrder()入参：" + request.toString());
		Long orderId = request.getOrderId();
		Integer type = request.getType();
		if (type != OrderSkillConstants.REQUEST_DV_CONFIRM_TYPE_YES
				&& type != OrderSkillConstants.REQUEST_DV_CONFIRM_TYPE_NO) {
			throw new BizException(AccountBizReturnCode.paramError, "大V同意/拒绝订单参数异常");
		}
		// 查询订单详情
		Order order = orderMapper.selectByPrimaryKey(orderId);
		Integer newOrderStatus = null;
		if (order != null) {
			Integer orderStatus = order.getOrderStatus();
			// 大V只能接受订单状态为：待接单
			if (OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE != orderStatus) {
				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
			}

			Long customerId = order.getCustomerId();
			Long serviceId = order.getServiceId();
			// 大V同意，状态为大V接受
			if (OrderSkillConstants.REQUEST_DV_CONFIRM_TYPE_YES == type) {
				newOrderStatus = OrderSkillConstants.ORDER_STATUS_WAITING_START;
				// 设置大V接单时间
				commonService.dvReciveOrderUpdateOrder(orderId, newOrderStatus, new Date());
				// 大V接受订单通知消息
				// 大V同意需要将接收到的其他订单取消
				// 判断是否是叫醒类型
				Integer skillType = order.getSkillType();
				if (OrderSkillConstants.SKILL_TYPE_YES == skillType) {
					List<Order> orderList = commonService.selectOrderReceiveOrder(order.getServiceId(), orderId,
							OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE, OrderSkillConstants.SKILL_TYPE_YES);
					if (!CollectionUtils.isEmpty(orderList)) {
						List<Long> orderIdList = new ArrayList<Long>();
						for (Order od : orderList) {
							orderIdList.add(od.getOrderId());
							try {
								LOGGER.info("---------dvReceiveOrder：customerId=" + order.getCustomerId()
										+ ";orderAmounts=" + od.getOrderAmounts());
								accountService.inAccount(od.getCustomerId(), od.getOrderAmounts(),
										TransferTypeEnum.RECHARGE, AccountBusinessTypeEnum.OrderRefund,
										od.getOrderId());
								RongYunUtil.sendOrderMessage(od.getCustomerId(),
										OrderSkillConstants.IM_MSG_CONTENT_DAV_CONFIRM_OTHER_CANCEL_TO_CUST,
										OrderSkillConstants.MSG_CONTENT_C);
								RongYunUtil.sendOrderMessage(od.getServiceId(),
										OrderSkillConstants.IM_MSG_CONTENT_DAV_CONFIRM_OTHER_CANCEL_TO_DAV,
										OrderSkillConstants.MSG_CONTENT_DAV);
							} catch (Exception e) {
								LOGGER.error("用户退款异常异常信息：", e);
							}

						}
						commonService.updateOrderReceiveOrder(orderIdList,
								OrderSkillConstants.ORDER_STATUS_CANCEL_DAV_START_ONE_ORDER);

					}
				}

				if (OrderSkillConstants.SKILL_TYPE_YES == skillType) {
					// 大V接受订单通知用户
					RongYunUtil.sendOrderMessage(customerId, OrderSkillConstants.IM_MSG_CONTENT_DAV_CONFIRM_TO_CUST,
							OrderSkillConstants.MSG_CONTENT_C);
				} else {
					// 大V接受订单通知用户
					RongYunUtil.sendOrderMessage(customerId,
							OrderSkillConstants.IM_MSG_CONTENT_DAV_CONFIRM_TO_CUST_JIAO_XING,
							OrderSkillConstants.MSG_CONTENT_C);
				}

				// 大V接受订单通知大V
				RongYunUtil.sendOrderMessage(serviceId, OrderSkillConstants.IM_MSG_CONTENT_DAV_CONFIRM_TO_DAV,
						OrderSkillConstants.MSG_CONTENT_DAV);

				// 大V不同意，状态为大V拒绝，退款给购买者
			} else {
				newOrderStatus = OrderSkillConstants.ORDER_STATUS_DAV_REFUSED_RECEIVE;
				BigDecimal payAmount = order.getOrderAmounts();
				LOGGER.info("---------dvReceiveOrder--refuse：customerId=" + order.getCustomerId() + ";orderAmounts="
						+ order.getOrderAmounts());
				accountService.inAccount(customerId, payAmount, TransferTypeEnum.RECHARGE,
						AccountBusinessTypeEnum.OrderRefund, orderId);
				commonService.updateOrder(orderId, newOrderStatus);

				// 大V拒绝订单通知用户
				RongYunUtil.sendOrderMessage(customerId, OrderSkillConstants.IM_MSG_CONTENT_DAV_REFUSE_TO_CUST,
						OrderSkillConstants.MSG_CONTENT_C);
				RongYunUtil.sendOrderMessage(serviceId, OrderSkillConstants.IM_MSG_CONTENT_DAV_REFUSE_TO_DV,
						OrderSkillConstants.MSG_CONTENT_DAV);
			}
		} else {
			// 订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}

		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(newOrderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，大V同意/拒绝订单订单完成");
		return commonResponse;
	}

	@Override
	public CommonResponse dvStartService(DvStartServiceRequest request) {
		if (request == null || request.getOrderId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "大V立即开始参数异常");
		}
		LOGGER.info("======>>>>>dvStartService()入参：" + request.toString());
		Long orderId = request.getOrderId();
		// 查询订单详情
		Order order = orderMapper.selectByPrimaryKey(orderId);
		Integer orderStatus = null;
		if (order != null) {
			Integer oldOrderStatus = order.getOrderStatus();
			// 订单状态为10.大V接受订单 大V接受订单之后可以开启立即服务
			if (OrderSkillConstants.ORDER_STATUS_WAITING_START != oldOrderStatus) {
				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
			}
			orderStatus = OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE;
			commonService.dvStartServiceUpdateOrder(orderId, orderStatus, new Date());

			// 大V开始服务请求，向用户发送消息
			Long customerId = order.getCustomerId();
			Long serviceId = order.getServiceId();

			// 获取大V手机号码
			Customer service = commonService.getPhoneByCustomerId(serviceId);
			LOGGER.info("大V发起服务，大V信息" + service);
			if (service != null) {
				String gtId = service.getGtClientId();
				LOGGER.info("大V发起服务，大V个推ID" + gtId);
				if (StringUtils.isNotBlank(gtId)) {
					GtPushUtil.sendNotificationTemplateToList(gtId, OrderSkillConstants.GT_MSG_ORDER_TITLE,
							OrderSkillConstants.GT_MSG_CONTENT_START_SERVICE_TO_DAV,
							OrderSkillConstants.GT_MSG_CONTENT_START_SERVICE_TO_DAV_URL);
				}
			}
			// 获取用户手机号码
			Customer customer = commonService.getPhoneByCustomerId(customerId);
			LOGGER.info("大V发起服务，用户信息" + customer);
			if (customer != null) {
				String gtId = customer.getGtClientId();
				LOGGER.info("大V发起服务，用户个推ID" + gtId);
				if (StringUtils.isNotBlank(gtId)) {
					// 用户下单需要使用个推推送消息
					GtPushUtil.sendNotificationTemplateToList(gtId, OrderSkillConstants.GT_MSG_ORDER_TITLE,
							OrderSkillConstants.GT_MSG_CONTENT_START_SERVICE_TO_CUST,
							OrderSkillConstants.GT_MSG_CONTENT_START_SERVICE_TO_CUST_URL);
				}
			}

			RongYunUtil.sendOrderMessage(customerId, OrderSkillConstants.IM_MSG_CONTENT_DAV_START_SERVICE_TO_CUST,
					OrderSkillConstants.MSG_CONTENT_C);
			RongYunUtil.sendOrderMessage(serviceId, OrderSkillConstants.IM_MSG_CONTENT_DAV_START_SERVICE_TO_DAV,
					OrderSkillConstants.MSG_CONTENT_DAV);
		} else {
			// 订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(orderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，大V立即开始完成");
		return commonResponse;
	}

	@Override
	public CommonResponse finishOrder(FinishOrderRequest request) {
		if (request == null || request.getOrderId() == null || request.getType() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "用户/大V完成服务参数异常");
		}

		LOGGER.info("======>>>>>dvConfirmRefund()入参：" + request.toString());
		Long orderId = request.getOrderId();
		Integer type = request.getType();

		if (type != OrderSkillConstants.REQUEST_DV_FINISH_TYPE
				&& type != OrderSkillConstants.REQUEST_CUST_FINISH_TYPE) {
			throw new BizException(AccountBizReturnCode.paramError, "用户/大V完成服务参数异常");
		}

		// 查询订单详情
		Order order = orderMapper.selectByPrimaryKey(orderId);
		Integer newOrderStatus = null;
		if (order != null) {
			Integer oldOrderStatus = order.getOrderStatus();
			// 只有在订单状态为26.进行中（大V发起开始服务用户5分钟内同意）;双方才可以进行服务完成操作
			if (OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT != oldOrderStatus) {
				throw new BizException(AccountBizReturnCode.ORDER_STATUS_ERROR, "订单状态异常");
			}
			Long serviceId = order.getServiceId();
			Long customerId = order.getCustomerId();

			Integer sendMsgIndex = null;

			// 大V发起完成服务
			if (OrderSkillConstants.REQUEST_DV_FINISH_TYPE == type) {
				// 判断时间是否在服务时间内
				Date expectEndTime = order.getExpectEndTime();
				if (expectEndTime.before(new Date())) {
					// 已经在服务时间之外了，可以立即结束
					newOrderStatus = OrderSkillConstants.ORDER_STATUS_FINISH_DAV_FINISH_AFTER_SERVICE_TIME;
					// 冻结大V金额
					LOGGER.info("---------finishOrder--outtime：serviceId=" + order.getServiceId() + ";orderAmounts="
							+ order.getOrderAmounts());
					accountService.inAccount(order.getServiceId(), order.getOrderAmounts(), TransferTypeEnum.FROZEN,
							AccountBusinessTypeEnum.FroZen, orderId);
					// 结束后用户获得券
					couponDubboBusiness.getCouponInOrder(order.getSkillItemId(), customerId);
					// 用户未评价
					RongYunUtil.sendOrderMessage(serviceId, OrderSkillConstants.IM_MSG_CONTENT_CUST_NOT_PING_JIA_TO_DV,
							OrderSkillConstants.MSG_CONTENT_DAV);
					sendMsgIndex = 1;
				} else {
					// 大V在服务时间内发起完成服务
					newOrderStatus = OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH;
					RongYunUtil.sendOrderMessage(serviceId, OrderSkillConstants.IM_MSG_CONTENT_CUST_FINISH_TO_DAV,
							OrderSkillConstants.MSG_CONTENT_DAV);
					RongYunUtil.sendOrderMessage(customerId, OrderSkillConstants.IM_MSG_CONTENT_CUST_FINISH_TO_CUST,
							OrderSkillConstants.MSG_CONTENT_C);
				}
			} else {
				// 用户发起完成服务
				newOrderStatus = OrderSkillConstants.ORDER_STATUS_GOING_USRE_APPAY_FINISH;
				sendMsgIndex = 1;
				// 冻结大V金额
				LOGGER.info("---------finishOrder--cust：serviceId=" + order.getServiceId() + ";orderAmounts="
						+ order.getOrderAmounts());
				accountService.inAccount(order.getServiceId(), order.getOrderAmounts(), TransferTypeEnum.FROZEN,
						AccountBusinessTypeEnum.FroZen, orderId);
				// 结束后用户获得券
				couponDubboBusiness.getCouponInOrder(order.getSkillItemId(), customerId);
				RongYunUtil.sendOrderMessage(customerId, OrderSkillConstants.IM_MSG_CONTENT_CUST_NOT_PING_JIA_TO_CUST,
						OrderSkillConstants.MSG_CONTENT_C);
			}

			// 设置请求结束时间
			commonService.finishUpdateOrder(orderId, newOrderStatus, new Date(), sendMsgIndex);
			if (sendMsgIndex != null) {
				// ADUAN 订单服务完成推送MQ消息
				userCenterSendMqMessageService.sendOrderCostMqMessage(orderId);
			}

		} else {
			// 订单不存在
			throw new BizException(AccountBizReturnCode.ORDER_NOT_EXIST, "订单不存在，无法对订单操作");
		}
		CommonResponse commonResponse = commonService.getCommonResponse();
		commonResponse.setData(newOrderStatus);
		LOGGER.info("======>>>>>订单支付，订单编号：" + orderId + "，用户/大V完成服务订单完成");
		return commonResponse;
	}

	// ================================发起的订单页相关结束==================================

	@Override
	public CommonResponse queryIngOrderCount(QueryIngOrderCountRequest request) {
		if (request == null || request.getBuyerId() == null || request.getSellerId() == null) {
			throw new BizException(AccountBizReturnCode.paramError, "查询进行中订单数量参数异常");
		}
		LOGGER.info("======>>>>>queryIngOrderCount()入参：" + request.toString());
		Long buyerId = request.getBuyerId();
		Long sellerId = request.getSellerId();
		CommonResponse commonResponse = commonService.getCommonResponse();
		LOGGER.info("======>>>>>查询进行中订单数量，客户编号为：" + buyerId + ",大V客户编号：" + sellerId + "查询成功");
		return commonResponse;
	}

	// @Override
	// public CommonResponse queryRefundReason(QueryRefundReasonRequest request) {
	// CommonResponse commonResponse = commonService.getCommonResponse();
	// List<String> reasonList = new ArrayList<String>();
	// reasonList.add("态度恶劣");
	// reasonList.add("迟到早退");
	// reasonList.add("消极怠工");
	// reasonList.add("不是本人");
	// commonResponse.setData(reasonList);
	// return commonResponse;
	// }

	@Override
	public CommonResponse orderEvaluation(OrderEvaluationRequest request) {
		// 根据订单Id查询评价页面需要的数据
		OrderDetailVO orderDetailVO = orderMapper.queryEvaluationData(request.getOrderId());
		if (orderDetailVO == null) {
			return ResultUtils.resultDataNotExist("订单信息不存在");
		}

		OrderEvaluationVo orderEvaluationVo = new OrderEvaluationVo();
		orderEvaluationVo.setOrderId(request.getOrderId());
		orderEvaluationVo.setNickName(orderDetailVO.getNickName());
		orderEvaluationVo.setHeadPortraitUrl(orderDetailVO.getHeadPortraitUrl());

		// 查询技能评价标签
		List<EvaluationLabel> labelList = skillItemMapper.queryEvaluationLabel(orderDetailVO.getSkillItemId(),
				orderDetailVO.getSex());
		List<OrderEvaluationVo.EvaluationLabel> labelLists = new ArrayList<>();
		for (EvaluationLabel bean : labelList) {
			OrderEvaluationVo.EvaluationLabel label = orderEvaluationVo.new EvaluationLabel();
			label.setLabelId(bean.getLabelId());
			label.setLabelName(bean.getLabelName());
			label.setBorderColor(bean.getBorderColor());
			labelLists.add(label);
		}
		orderEvaluationVo.setEvaluationLabelList(labelLists);

		// 查询客户对声优的关注状态
		orderEvaluationVo.setAttentionFlag(
				orderMapper.findServicerIsAttentioned(orderDetailVO.getServiceId(), orderDetailVO.getCustomerId()) > 0
						? 1
						: 0);

		return ResultUtils.resultSuccess(orderEvaluationVo);
	}

	@Override
	public CommonResponse submitOrderEvaluation(OrderEvaluationSubmitRequest request) {
		// 根据订单Id查询评价页面需要的数据
		OrderDetailVO orderDetail = orderMapper.queryEvaluationData(request.getOrderId());
		if (orderDetail == null) {
			return ResultUtils.resultDataNotExist("订单信息不存在");
		}
		// 保存订单表评价信息
		Order evaluationInfo = new Order();
		evaluationInfo.setOrderId(request.getOrderId());
		evaluationInfo.setEvaluateStart(request.getEvaluateStart() == null ? 3 : request.getEvaluateStart());
		evaluationInfo.setCustomerEvaluate(request.getEvaluateContent());
		orderMapper.saveEvaluationInfo(evaluationInfo);

		// 如果选择了标签，保存评价的标签
		if (request.getLabelIds() != null && request.getLabelIds().length > 0) {
			// 查询订单的服务方ID
			OrderDetailVO orderDetailVO = orderMapper.queryEvaluationData(request.getOrderId());
			if (orderDetailVO.getCustomerIsEvaluate() == 1) {
				// 删除旧的评价标签
				orderMapper.deleteEvaluationLabels(request.getOrderId());
			}

			List<EvaluationLabel> list = new ArrayList<>();
			for (Integer labelId : request.getLabelIds()) {
				if (labelId == null) {
					continue;
				}
				EvaluationLabel label = new EvaluationLabel();
				label.setEvaluationId(UUIDUtils.getId());
				label.setCustomerId(orderDetailVO.getServiceId());
				label.setSkillItemId(orderDetailVO.getSkillItemId());
				label.setOrderId(request.getOrderId());
				label.setLabelId(labelId);
				list.add(label);
			}
			if (list.size() > 0) {
				orderMapper.saveEvaluationLabels(list);
			}
			RongYunUtil.sendOrderMessage(orderDetailVO.getServiceId(),
					OrderSkillConstants.IM_MSG_CONTENT_PING_JIA_FINISH_TO_DV, OrderSkillConstants.MSG_CONTENT_DAV);
			RongYunUtil.sendOrderMessage(orderDetailVO.getCustomerId(),
					OrderSkillConstants.IM_MSG_CONTENT_PING_JIA_FINISH_TO_CUST, OrderSkillConstants.MSG_CONTENT_C);
		}

		commonService.updateOrder(request.getOrderId(), OrderSkillConstants.ORDER_STATUS_FINISHED_AND_PINGJIA);

		// 插入关注
		if (Objects.equals(request.getAttentionFlag(), 1)) {
			orderMapper.insertOrderServicerFans(UUIDUtils.getId(), orderDetail.getServiceId(),
					orderDetail.getCustomerId());
		}

		// ADUAN -- 用户评价，计算主播评分排名，发送MQ消息
		userCenterSendMqMessageService.sendEvaluationOrderMqMessage(request.getOrderId());

		return ResultUtils.resultSuccess();

	}

	/**
	 * temp方法 兼容测试
	 * 
	 * @param status
	 * @return
	 */
	private Integer setOrderStatus(Integer status) {
		if (status.equals(OrderSkillConstants.ORDER_STATUS_FINISHED_FORCE)) {
			return OrderSkillConstants.ORDER_STATUS_FINISHED_AND_PINGJIA;
		} else if (status.equals(OrderSkillConstants.ORDER_STATUS_CANCEL_FORCE)) {
			return OrderSkillConstants.ORDER_STATUS_CANCEL_USER_NOT_ACCEPCT;
		}
		return status;
	}
}
