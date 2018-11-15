package com.calf.module.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.DateUtil;
import com.calf.cn.utils.RadomUtil;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.customer.entity.Customer;
import com.calf.module.enums.CompulsionOrderStatusEnums;
import com.calf.module.enums.OrderStatusEnums;
import com.calf.module.enums.SmallOrderStatusEnums;
import com.calf.module.order.entity.Account;
import com.calf.module.order.entity.CustomerSkill;
import com.calf.module.order.entity.Order;
import com.calf.module.order.entity.Orders;
import com.calf.module.order.entity.Skill;
import com.calf.module.order.entity.SkillItem;
import com.calf.module.order.entity.TradeDetail;
import com.calf.module.order.vo.OrderStatusVO;
import com.calf.module.order.vo.OrderVO;
import com.calf.module.order.vo.SmallOrderStatusVO;
import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.constants.ScoreRankConstants;
import com.honglu.quickcall.user.facade.entity.BigvScore;
import com.honglu.quickcall.user.facade.entity.BigvSkillScore;

@Service("orderService")
public class OrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private BaseManager baseManager;
	@Autowired
	private CommonUtilService commonUtilService;

	@SuppressWarnings("unchecked")
	public DataTables<OrderVO> getOrderPageList(HttpServletRequest request) {

		HashMap<String, Object> parameters = (HashMap<String, Object>) SearchUtil
				.convertorEntitysToMap(request.getParameterMap());

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", parameters.get("orderId"));
		paramMap.put("custNickName", parameters.get("custNickName"));
		paramMap.put("servNickName", parameters.get("servNickName"));
		paramMap.put("orderType", parameters.get("orderType"));

		// 接单人手机号码
		paramMap.put("servicePhone", parameters.get("servicePhone"));
		// 下单人手机号码
		paramMap.put("customerPhone", parameters.get("customerPhone"));
		paramMap.put("orderType", parameters.get("orderType"));
		paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
		paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
		List<OrderVO> skillList = baseManager.query("Order.selectPageList", paramMap);
		String sEcho = (String) parameters.get("sEcho");
		int total = baseManager.get("Order.slectCount", paramMap);
		return new DataTables<OrderVO>(sEcho, skillList, skillList.size(), total);
	}

	/**
	 * 分页查询订单
	 * 
	 * @param request
	 * @return
	 */
	public DataTables<OrderVO> queryOrderPageList(HttpServletRequest request) {
		HashMap<String, Object> parameters = (HashMap<String, Object>) SearchUtil
				.convertorEntitysToMap(request.getParameterMap());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", parameters.get("orderId"));
		paramMap.put("placeOrderId", parameters.get("placeOrderId"));
		paramMap.put("receivedOrderId", parameters.get("receivedOrderId"));
		paramMap.put("startTime", parameters.get("startTime"));
		paramMap.put("endTime", parameters.get("endTime"));

		String placeOrderUserNickName = (String) parameters.get("placeOrderUserNickName");
		if (StringUtils.isNotBlank(placeOrderUserNickName)) {
			placeOrderUserNickName = placeOrderUserNickName.trim();
		}
		paramMap.put("placeOrderUserNickName", placeOrderUserNickName);

		String receiveOrderUserNickName = (String) parameters.get("receiveOrderUserNickName");
		if (StringUtils.isNotBlank(receiveOrderUserNickName)) {
			receiveOrderUserNickName = receiveOrderUserNickName.trim();
		}
		paramMap.put("receiveOrderUserNickName", receiveOrderUserNickName);

		// 接单人手机号码
		paramMap.put("servicePhone", parameters.get("servicePhone"));
		// 下单人手机号码
		paramMap.put("customerPhone", parameters.get("customerPhone"));

		String orderStatus = (String) parameters.get("orderStatus");
		String serviceType = (String) parameters.get("serviceType");
		String discountType = (String) parameters.get("discountTypeVal");
		if (serviceType != null && (!serviceType.equals("-1"))) {
			paramMap.put("serviceType", parameters.get("serviceType"));
		}
		if (orderStatus != null && (!orderStatus.equals("-1"))) {
			OrderStatusEnums order = OrderStatusEnums.getOrderStautsEnums(orderStatus);
			String smallOrderItem = order.getSubset();
			String[] items = smallOrderItem.split(",");
			paramMap.put("orderStatus", Arrays.asList(items));
		}

		if (discountType != null && (!discountType.equals("-1"))) {
			String[] discountTypes = discountType.split("-");
			paramMap.put("discountTypeMin", discountTypes[0]);
			paramMap.put("discountTypeMax", discountTypes[1]);
		}
		paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
		paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
		List<OrderVO> skillList = baseManager.query("Order.queryPageList", paramMap);
		for (OrderVO vo : skillList) {
			if (StringUtils.isNotBlank(vo.getDiscountType())) {
				double dis = Double.parseDouble(vo.getDiscountType());
				dis = dis * 10;
				vo.setDiscountType(dis + "%");
			}
			SmallOrderStatusEnums status = SmallOrderStatusEnums.getSmallOrderStatusEnums(vo.getOrderStatus());
			if (status != null) {
				vo.setOrderStatusVal(status.getDesc());
			}
		}
		String sEcho = (String) parameters.get("sEcho");
		int total = baseManager.get("Order.selectCount", paramMap);
		return new DataTables<OrderVO>(sEcho, skillList, skillList.size(), total);
	}

	public List<OrderStatusVO> getOrderStatusList() {
		// 获取订单状态
		List<OrderStatusVO> statusVOS = new ArrayList<>();
		for (OrderStatusEnums orderStatusEnums : OrderStatusEnums.values()) {
			OrderStatusVO vo = new OrderStatusVO();
			vo.setValue(orderStatusEnums.getValue());
			vo.setDesc(orderStatusEnums.getDesc());
			vo.setSubset(orderStatusEnums.getSubset());
			statusVOS.add(vo);
		}
		return statusVOS;
	}

	public List<SmallOrderStatusVO> getSamllOrderStatusList() {
		// 获取小类订单状态
		List<SmallOrderStatusVO> statusVOS = new ArrayList<>();
		for (SmallOrderStatusEnums orderStatusEnums : SmallOrderStatusEnums.values()) {
			SmallOrderStatusVO vo = new SmallOrderStatusVO();
			vo.setValue(orderStatusEnums.getValue());
			vo.setDesc(orderStatusEnums.getDesc());
			vo.setShow(false);
			statusVOS.add(vo);
		}
		return statusVOS;
	}

	public List<SkillItem> getSkillItemsList() {
		// 获取服务类型
		List<SkillItem> skillItems = baseManager.query("SkillItem.selectAll", null);
		return skillItems;
	}

	public void getOrderDetail(Model model, String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", id);
		Order order = baseManager.get("Order.selectByPrimaryKey", paramMap);
		model.addAttribute("entity", order);
	}

	public OrderVO queryOrderDetail(Model model, String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", id);
		OrderVO order = baseManager.get("Order.queryOrderDetail", paramMap);
		SmallOrderStatusEnums status = SmallOrderStatusEnums.getSmallOrderStatusEnums(order.getOrderStatus());
		if (status == null) {
			order.setOrderStatusVal(order.getOrderStatus());
		} else {
			order.setOrderStatusVal(status.getDesc());
			String[] cancels = OrderStatusEnums.Cancel.getSubset().split(",");
			boolean isShow = false;
			for (String can : cancels) {
				if (can.equals(status.getValue())) {
					isShow = true;
					break;
				}
			}
			model.addAttribute("isShow", isShow);
		}

		Integer orderStatus = Integer.valueOf(order.getOrderStatus());
		Integer selectFlag = null;
		if (CompulsionOrderStatusEnums.isContainSubset(OrderSkillConstants.ORDER_STATUS_CANCEL_FORCE,
				String.valueOf(orderStatus))) {
			// 可以强制取消
			selectFlag = 1;
		} /*
			 * else if (orderStatus == OrderSkillConstants.ORDER_STATUS_CANCEL_FORCE ||
			 * orderStatus == OrderSkillConstants.ORDER_STATUS_FINISHED_FORCE) { //
			 * 已经强制取消或者强制完成 selectFlag = 3; }
			 */else if (CompulsionOrderStatusEnums.isContainSubset(OrderSkillConstants.ORDER_STATUS_FINISHED_FORCE,
				String.valueOf(orderStatus))) {
			// 可以强制完成
			selectFlag = 2;
		} else {
			selectFlag = 3;
		}

		model.addAttribute("selectFlag", selectFlag);

		model.addAttribute("entity", order);
		return order;
	}

	public int saveAdd(OrderVO entity) {
		// 根据名称获取公司信息
		Skill skill = new Skill();
		Long id = UUIDUtils.getId();
		BeanUtils.copyProperties(entity, skill);
		skill.setId(id + "");
		skill.setCreateMan(commonUtilService.getCurrUser());
		skill.setSkillStatus(0);// 有效
		skill.setCreateTime(new Date());
		baseManager.insert(skill);
		return 0;
	}

	/**
	 * 添加订单
	 * 
	 * @param entity
	 * @return
	 */
	public int addOrder(OrderVO entity) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customerId", entity.getPlaceOrderId());
		Customer customer = baseManager.get("Customer.selectByPrimaryKey", paramMap);
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("customerId", entity.getReceivedOrderId());
		List<CustomerSkill> skillList = baseManager.query("CustomerSkill.queryCustomerSkill", paramMap1);
		if (customer == null) {
			// 不存在下单用户
			return 0;
		}
		if (skillList == null || skillList.size() <= 0) {
			// 接单用户不存在技能
			return 0;
		}
		CustomerSkill skill = skillList.get(0);
		Orders order = new Orders();
		String orderId = DateUtil.dateFormat(new Date(), "yyyyMMddHHmmss") + RadomUtil.generateNumber2(4);
		order.setOrderId(Long.parseLong(orderId));
		order.setOrderNo(Long.parseLong(orderId));
		order.setCustomerSkillId(skill.getCustomerSkillId());
		order.setCustomerId(Long.parseLong(entity.getPlaceOrderId()));

		if (StringUtils.isNotBlank(entity.getOrderAmount()) && StringUtils.isNotBlank(entity.getOrderNum())
				&& StringUtils.isNotBlank(entity.getDiscountType()) && (!"-1".equals(entity.getDiscountType()))) {
			double amount = Double.parseDouble(entity.getOrderAmount());
			double num = Double.parseDouble(entity.getOrderNum());
			double rate = Integer.parseInt(entity.getDiscountType()) * 0.1;
			double amountS = amount * num;
			double amountSum = amountS * rate;
			order.setOrderAmounts(new BigDecimal(amountSum));
		}
		byte status = (byte) Integer.parseInt(entity.getOrderStatus());
		order.setOrderStatus(status);
		order.setServiceId(skill.getCustomerId());
		order.setServicePrice(new BigDecimal(entity.getOrderAmount()));
		order.setOrderNum(Integer.parseInt(entity.getOrderNum()));
		order.setDiscountRate(new BigDecimal(entity.getDiscountType()));
		order.setStartTime(DateUtil.StrToDate(entity.getStartTime(), "yyyy-MM-dd HH:mm:dd"));
		order.setEndTime(DateUtil.StrToDate(entity.getEndTime(), "yyyy-MM-dd HH:mm:dd"));
		order.setCreateTime(new Date());
		int insert = baseManager.insert(order);
		return insert;
	}

	/**
	 * 修改订单强制取消，强制完成
	 * 
	 * @param entity
	 * @return
	 */
	@Transactional
	public int updateOrder(OrderVO entity) {

		if (entity == null) {
			return -1;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", entity.getOrderId());
		// 订单状态二次判断是否能进行强制取消或者强制完成
		Orders order = baseManager.get("Orders.selectByPrimaryKey", paramMap);
		paramMap.put("compulsion_reason", entity.getCompulsionReason());
		Integer orderStatus = Integer.valueOf(entity.getOrderStatus());

		BigDecimal orderAmount = new BigDecimal(entity.getOrderAmount());
		Long orderId = Long.valueOf(entity.getOrderId());
		int update = 0;
		// 强制取消
		if (OrderSkillConstants.ORDER_STATUS_CANCEL_FORCE == orderStatus) {
			// 二次校验，如果在业务处理之时，防止实时订单状态改变（变为不符合订单强制取消状态）
			if (!(CompulsionOrderStatusEnums.isContainSubset(OrderSkillConstants.ORDER_STATUS_CANCEL_FORCE,
					String.valueOf(order.getOrderStatus()))
					|| CompulsionOrderStatusEnums.isContainSubset(OrderSkillConstants.ORDER_STATUS_FINISHED_FORCE,
							String.valueOf(order.getOrderStatus())))) {
				return -1;
			}
			paramMap.put("orderStatus", orderStatus);
			// 强制取消账单流水处理
			update = compulsoryCancellation(orderId, orderAmount);
			// 退款给用户
			// 强制完成
		} else if (OrderSkillConstants.ORDER_STATUS_FINISHED_FORCE == orderStatus) { // 给大V冻结金额
			// 二次校验，如果在业务处理之时，防止实时订单状态改变（变为不符合订单强制完成状态）
			if (!CompulsionOrderStatusEnums.isContainSubset(OrderSkillConstants.ORDER_STATUS_FINISHED_FORCE,
					String.valueOf(order.getOrderStatus()))) {
				return -1;
			}
			paramMap.put("orderStatus", orderStatus);
			// 强制完成账单流水处理
			update = compulsoryCompletion(orderId, orderAmount);
		}

		if (update == 1) {
			// 强制取消和强制完成账务流水处理完成，更改订单状态
			update = baseManager.update("Order.updateOrder", paramMap);
			// 强制取消完成，发送融云消息和im消息
			if (update > 0 && OrderSkillConstants.ORDER_STATUS_CANCEL_FORCE == orderStatus) {
				RongYunUtil.sendOrderMessage(Long.valueOf(entity.getReceivedOrderId()),
						OrderSkillConstants.IM_MSG_CONTENT_CANCEL_FORCE_ORDER_TO_DV,
						OrderSkillConstants.MSG_CONTENT_DAV);
				RongYunUtil.sendOrderMessage(Long.valueOf(entity.getPlaceOrderId()),
						OrderSkillConstants.IM_MSG_CONTENT_CANCEL_FORCE_ORDER_TO_CUST,
						OrderSkillConstants.MSG_CONTENT_C);

				// 推动订单IM消息
				sendOrderMsg(Long.valueOf(entity.getPlaceOrderId()), Long.valueOf(entity.getReceivedOrderId()), orderId,
						OrderSkillConstants.IM_MSG_CONTENT_CANCEL_FORCE_ORDER_TO_DV);
				sendOrderMsg(Long.valueOf(entity.getReceivedOrderId()), Long.valueOf(entity.getPlaceOrderId()), orderId,
						OrderSkillConstants.IM_MSG_CONTENT_CANCEL_FORCE_ORDER_TO_CUST);
				// 强制完成 完成，发送融云消息和im消息
			} else if (update > 0 && OrderSkillConstants.ORDER_STATUS_FINISHED_FORCE == orderStatus) {
				RongYunUtil.sendOrderMessage(Long.valueOf(entity.getReceivedOrderId()),
						OrderSkillConstants.IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_DV, OrderSkillConstants.MSG_CONTENT_DAV);
				RongYunUtil.sendOrderMessage(Long.valueOf(entity.getPlaceOrderId()),
						OrderSkillConstants.IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_CUST, OrderSkillConstants.MSG_CONTENT_C);

				// 推动订单IM消息
				sendOrderMsg(Long.valueOf(entity.getPlaceOrderId()), Long.valueOf(entity.getReceivedOrderId()), orderId,
						OrderSkillConstants.IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_DV);
				sendOrderMsg(Long.valueOf(entity.getReceivedOrderId()), Long.valueOf(entity.getPlaceOrderId()), orderId,
						OrderSkillConstants.IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_CUST);

			}

		} else {
			update = -1;
		}

		return update;
	}

	public int saveUpdate(OrderVO entity) {
		Skill skill = new Skill();
		BeanUtils.copyProperties(entity, skill);
		skill.setModifyMan(commonUtilService.getCurrUser());
		skill.setModifyTime(new Date());
		baseManager.update(skill);
		return 0;
	}

	/**
	 * 强制完成
	 * 
	 * @param orderNo
	 * @return
	 */
	@Transactional
	public int compulsoryCompletion(Long orderId, BigDecimal amount) {
		boolean flag = false;
		int row = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		Orders order = baseManager.get("Orders.selectByPrimaryKey", map);

		map.put("orderNo", order.getOrderNo());
		TradeDetail tradeDetail = baseManager.get("TradeDetail.selectFrozenByOrderNo", map);
		if (tradeDetail != null) {
			String userFrozenkey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + order.getServiceId();
			String steamFrozenKey = RedisKeyConstants.ACCOUNT_USERFROZEN_STREAM + tradeDetail.getTradeId();
			String steamFrozenValue = JedisUtil.get(steamFrozenKey);
			String userFrozenValue = JedisUtil.get(userFrozenkey);
			if (StringUtils.isNotBlank(userFrozenValue) && StringUtils.isNotBlank(steamFrozenValue)) {
				if (userFrozenValue.contains(tradeDetail.getTradeId() + "")) {
					flag = true;// 有冻结流水
					JedisUtil.del(steamFrozenKey);
					String[] arys = userFrozenValue.split(",");
					if (userFrozenValue.length() > 0) {
						if (userFrozenValue.length() == 1) {
							JedisUtil.del(userFrozenkey);
						} else {
							arys = remove(arys, tradeDetail.getTradeId() + "");
							userFrozenValue = StringUtils.join(arys, ",");
						}
						JedisUtil.set(userFrozenkey, userFrozenValue);
					} else {
						JedisUtil.del(userFrozenkey);
					}
				}
			}
		}
		map.put("orderNo", order.getOrderNo());
		map.put("customerId", order.getServiceId());
		tradeDetail = baseManager.get("TradeDetail.queryCountByOrderNoAndCustomerId", map);
		// 声优无流水，或者 有流水，且只有冻结流水
		if (tradeDetail == null || tradeDetail.getType() == 7) {
			// 声优 有流水，且排除解冻流水 和强制完成流水
			if (tradeDetail == null || (tradeDetail.getType() != 8 && tradeDetail.getType() != 11)) {
				Map<String, Object> pram = new HashMap<String, Object>();
				pram.put("amount", amount);
				pram.put("userId", order.getServiceId());
				if (flag) {// 有冻结流水
					pram.put("type", 3);
					baseManager.update("Account.outAccount", pram);
				} else {// 无冻结流水
					pram.put("type", 2);
					baseManager.update("Account.inAccount", pram);
				}

				// 记录流水
				tradeDetail = new TradeDetail();
				tradeDetail.setTradeId(UUIDUtils.getId());
				tradeDetail.setOrderNo(order.getOrderNo());
				tradeDetail.setCustomerId(order.getServiceId());
				tradeDetail.setCreateTime(new Date());
				tradeDetail.setType(11);
				tradeDetail.setInPrice(amount);
				row = baseManager.insert("TradeDetail.insertSelective", tradeDetail);
			}
		}
		return row;

	}

	/**
	 * 数组删除一个元素
	 * 
	 * @param arr
	 * @param str
	 * @return
	 */
	private static String[] remove(String[] arr, String str) {
		String[] tmp = new String[arr.length - 1];
		int idx = 0;
		boolean hasRemove = false;
		for (int i = 0; i < arr.length; i++) {

			if (!hasRemove && arr[i] == str) {
				hasRemove = true;
				continue;
			}

			tmp[idx++] = arr[i];
		}

		return tmp;
	}

	/**
	 * 强制取消
	 * 
	 * @param orderNo
	 * @return
	 */
	@Transactional
	public int compulsoryCancellation(Long orderId, BigDecimal amount) {
		boolean flag = false;
		int row = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		Orders order = baseManager.get("Orders.selectByPrimaryKey", map);

		map.put("userId", order.getServiceId());
		Account account = baseManager.get("Account.queryAccount", map);

		map.put("orderNo", order.getOrderNo());
		TradeDetail tradeDetail = baseManager.get("TradeDetail.selectFrozenByOrderNo", map);
		if (tradeDetail != null) {
			String userFrozenkey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + order.getServiceId();
			String steamFrozenKey = RedisKeyConstants.ACCOUNT_USERFROZEN_STREAM + tradeDetail.getTradeId();
			String steamFrozenValue = JedisUtil.get(steamFrozenKey);
			String userFrozenValue = JedisUtil.get(userFrozenkey);
			if (StringUtils.isNotBlank(userFrozenValue) && StringUtils.isNotBlank(steamFrozenValue)) {
				if (userFrozenValue.contains(tradeDetail.getTradeId() + "")) {
					flag = true;// 有冻结流水
					JedisUtil.del(steamFrozenKey);
					String[] arys = userFrozenValue.split(",");
					if (userFrozenValue.length() > 0) {
						if (userFrozenValue.length() == 1) {
							JedisUtil.del(userFrozenkey);
						} else {
							arys = remove(arys, tradeDetail.getTradeId() + "");
							userFrozenValue = StringUtils.join(arys, ",");
						}
						JedisUtil.set(userFrozenkey, userFrozenValue);
					} else {
						JedisUtil.del(userFrozenkey);
					}
				}
			}
		}
		map.put("customerId", order.getCustomerId());
		map.put("orderNo", order.getOrderNo());
		// 检查用户 该订单是否有下单流水 并且无强制取消流水
		tradeDetail = baseManager.get("TradeDetail.queryCountByOrderNoAndCustomerId", map);
		if (tradeDetail != null && tradeDetail.getType() == 3 && tradeDetail.getType() != 10) {
			// 用户强制取消金额 回账
			map.put("customerId", order.getServiceId());
			map.put("orderNo", order.getOrderNo());
			tradeDetail = baseManager.get("TradeDetail.queryCountByOrderNoAndCustomerId", map);
			if (tradeDetail == null || tradeDetail.getType() != 8) {
				Map<String, Object> pram = new HashMap<String, Object>();
				pram.put("type", 1);
				pram.put("amount", amount);
				pram.put("userId", order.getCustomerId());
				baseManager.update("Account.inAccount", pram);
				// 记录流水
				tradeDetail = new TradeDetail();
				tradeDetail.setTradeId(UUIDUtils.getId());
				tradeDetail.setOrderNo(order.getOrderNo());
				tradeDetail.setCustomerId(order.getCustomerId());
				tradeDetail.setCreateTime(new Date());
				tradeDetail.setType(10);
				tradeDetail.setInPrice(amount);
				row = baseManager.insert("TradeDetail.insertSelective", tradeDetail);
			}
		}
		// 判断该订单状态是否完成并且到冻结状态
		if (flag) {
			// 冻结的钱比实际操作的要少
			if (account.getFrozenAmounts().compareTo(amount) == -1) {
				return -1;
			}

			// 声优冻结金额 去除
			Map<String, Object> pram = new HashMap<String, Object>();
			pram.put("type", 4);
			pram.put("amount", amount);
			pram.put("userId", order.getServiceId());
			baseManager.update("Account.outAccount", pram);
			// 记录流水
			tradeDetail = new TradeDetail();
			tradeDetail.setTradeId(UUIDUtils.getId());
			tradeDetail.setOrderNo(order.getOrderNo());
			tradeDetail.setCustomerId(order.getServiceId());
			tradeDetail.setCreateTime(new Date());
			tradeDetail.setType(10);
			tradeDetail.setOutPrice(amount);
			row = baseManager.insert("TradeDetail.insertSelective", tradeDetail);
		}

		return row;

	}

	private void sendOrderMsg(Long customerId, Long serviceId, Long orderId, String orderDesc) {
		// customer ---->>> serviceId
		String custStr = JedisUtil.get(RedisKeyConstants.USER_CUSTOMER_INFO + customerId);
		if (StringUtils.isNotBlank(custStr)) {
			try {
				Customer customer = JSON.parseObject(custStr, Customer.class);
				RongYunUtil.sendOrderIMMessage(customer.getNickName(), customerId, serviceId, "", orderId, orderDesc,
						customer.getHeadPortraitUrl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 【强制完成】给声优增加技能声量 + 给客户增加经验值
	 * 
	 * @param orderId
	 * @param oldOrderStatus
	 *            强制完成前订单状态
	 * @author duanjun
	 */
	private void forceDoneAddBigvScoreAndCustomerExperience(Long orderId, Integer oldOrderStatus) {
		// 订单状态是已完成状态，就不用再增加了，因为已经发送MQ消息增加相应值了（防止重复增加）
		if (oldOrderStatus >= 30) {
			return;
		}
		com.honglu.quickcall.account.facade.entity.Order order;
		try {
			// 查询订单数据
			order = baseManager.get("Order.selectUsedToCalculateBigvScore", new Object[] { orderId });
			if (order == null) {
				LOGGER.error("【强制完成】 -- （给声优增加技能声量 + 给客户增加经验值）未查询到订单信息，orderId：" + orderId);
				return;
			}
		} catch (Exception e) {
			LOGGER.error(orderId + "【强制完成】 -- （给声优增加技能声量 + 给客户增加经验值）查询订单信息异常" + orderId, e);
			return;
		}

		addBigvScore(order);

		addCustomerExperience(order);
	}

	private void addCustomerExperience(com.honglu.quickcall.account.facade.entity.Order order) {
		try {
			// 计算客户需要获取的经验值
			Integer experience = order.getOrderAmounts().intValue();

			LOGGER.info("【强制完成】 -- 给客户增加经验值--客户ID：" + order.getCustomerId() + " ， 增加经验值：" + experience);
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("customerId", order.getCustomerId());
			paramsMap.put("experience", experience);
			baseManager.update("Customer.updateCustomerExperienceAndLevel", paramsMap);
		} catch (Exception e) {
			LOGGER.error(order.getOrderId() + "【强制完成】 -- 给客户增加经验值异常：", e);
		}
	}

	private void addBigvScore(com.honglu.quickcall.account.facade.entity.Order order) {
		try {
			// 计算过评分 || 客户已评价
			if (order.getValueScore() != null || Objects.equals(order.getCustomerIsEvaluate(), 1)) {
				LOGGER.info("【强制完成】 -- 给声优添加技能声量 -- 该订单已计算过评分，不再重新添加：" + order.getOrderId());
				return;
			}

			// 查询声优技能总订单数
			Integer orderTotal = baseManager.get("BigvSkillScore.selectBigvSkillOrderTotal",
					new Object[] { order.getCustomerSkillId() });

			// 计算该订单对应的技能的评分
			BigDecimal score = ScoreRankConstants.calculateOrderSkillScore(orderTotal, order.getServicePrice(),
					order.getOrderNum(), order.getCouponFlag(), ScoreRankConstants.DEFAULT_EVALUATION_LEVEL);

			// 更新订单价值得分
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("orderId", order.getOrderId());
			paramsMap.put("valueScore", score);
			baseManager.update("Order.updateValueScoreToOrder", paramsMap);

			// 更新评分到大V技能评分表和总评分排名表
			updateToBigvScore(order.getServiceId(), order.getSkillItemId(), order.getCustomerSkillId(), score, 1);
		} catch (Exception e) {
			LOGGER.error(order.getOrderId() + "【强制完成】 -- 给声优增加技能声量异常：", e);
		}
	}

	/**
	 * 【强制取消】扣除声优技能声量 + 扣除客户经验
	 * 
	 * @param orderId
	 * @param oldOrderStatus
	 *            强制取消前订单状态
	 * @author duanjun
	 */
	private void forceCancelDeductBigvScoreAndCustomerExperience(Long orderId, Integer oldOrderStatus) {
		// 订单状态是不是已完成状态，就不用扣除，因为还没有增加相应值
		if (oldOrderStatus < 30) {
			return;
		}
		com.honglu.quickcall.account.facade.entity.Order order;
		try {
			// 查询订单数据
			order = baseManager.get("Order.selectUsedToCalculateBigvScore", new Object[] { orderId });
			if (order == null) {
				LOGGER.error("【强制取消】 -- （扣除声优技能声量 + 扣除客户经验）未查询到订单信息，orderId：" + orderId);
				return;
			}
		} catch (Exception e) {
			LOGGER.error(orderId + "【强制取消】 -- （扣除声优技能声量 + 扣除客户经验）查询订单信息异常" + orderId, e);
			return;
		}

		deductBigvScore(order);

		deductCustomerExperience(order);

	}

	private void deductCustomerExperience(com.honglu.quickcall.account.facade.entity.Order order) {
		try {
			// 计算客户需要获取的经验值
			Integer experience = order.getOrderAmounts().intValue();

			LOGGER.info("【强制取消】 -- 扣除客户经验--客户ID：" + order.getCustomerId() + " ， 扣除客户经验：" + experience);
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("customerId", order.getCustomerId());
			paramsMap.put("experience", -experience);
			baseManager.update("Customer.updateCustomerExperienceAndLevel", paramsMap);
		} catch (Exception e) {
			LOGGER.error(order.getOrderId() + "【强制取消】 -- 扣除客户经验异常：", e);
		}
	}

	private void deductBigvScore(com.honglu.quickcall.account.facade.entity.Order order) {
		try {
			if (order.getValueScore() == null) {
				LOGGER.warn("【强制取消】 -- 扣除声优技能声量 -- 该订单还未计算过评分，不用扣减声量：" + order.getOrderId());
				return;
			}

			// 扣减声量
			BigDecimal removeScore = new BigDecimal(0).subtract(order.getValueScore());

			// 更新技能声量表
			Map<String, Object> bigvSkillScoreMap = new HashMap<>();
			bigvSkillScoreMap.put("customerSkillId", order.getCustomerSkillId());
			bigvSkillScoreMap.put("addOrderTotal", -1);
			bigvSkillScoreMap.put("valueScore", removeScore);
			baseManager.update("BigvSkillScore.updateBigvSkillScore", bigvSkillScoreMap);

			// 更新总声量表
			Map<String, Object> bigvSkillMap = new HashMap<>();
			bigvSkillMap.put("customerId", order.getServiceId());
			bigvSkillMap.put("addOrderTotal", -1);
			bigvSkillMap.put("valueScore", removeScore);
			baseManager.update("BigvScore.updateBigvScore", bigvSkillScoreMap);
		} catch (Exception e) {
			LOGGER.error(order.getOrderId() + "【强制取消】 -- 扣除声优技能声量异常：", e);
		}
	}

	/**
	 * 更新评分到大V技能评分表和总评分排名表
	 *
	 * @param customerId
	 * @param skillItemId
	 * @param score
	 * @param addOrderTotal
	 *            累加订单的笔数
	 * @author duanjun
	 */
	private void updateToBigvScore(Long customerId, Long skillItemId, Long customerSkillId, BigDecimal score,
			Integer addOrderTotal) {
		// 存入技能排名表
		Map<String, Object> bigvSkillScoreMap = new HashMap<>();
		bigvSkillScoreMap.put("customerSkillId", customerSkillId);
		bigvSkillScoreMap.put("addOrderTotal", addOrderTotal);
		bigvSkillScoreMap.put("valueScore", score);
		if (baseManager.update("BigvSkillScore.updateBigvSkillScore", bigvSkillScoreMap) == 0) {
			// 更新失败则插入
			BigvSkillScore bigvSkillScore = new BigvSkillScore();
			bigvSkillScore.setId(UUIDUtils.getId());
			bigvSkillScore.setCustomerId(customerId);
			bigvSkillScore.setSkillItemId(skillItemId);
			bigvSkillScore.setCustomerSkillId(customerSkillId);
			bigvSkillScore.setOrderTotal(1);
			bigvSkillScore.setScoreTotal(score);
			baseManager.insert("BigvSkillScore.insertBigvSkillScore", bigvSkillScore);
		}

		// 存入大V排名表
		Map<String, Object> bigvSkillMap = new HashMap<>();
		bigvSkillMap.put("customerId", customerId);
		bigvSkillMap.put("addOrderTotal", addOrderTotal);
		bigvSkillMap.put("valueScore", score);
		if (baseManager.update("BigvScore.updateBigvScore", bigvSkillMap) == 0) {
			// 更新失败则插入
			BigvScore bigvScore = new BigvScore();
			bigvScore.setId(UUIDUtils.getId());
			bigvScore.setCustomerId(customerId);
			bigvScore.setOrderTotal(1);
			bigvScore.setScoreTotal(score);
			baseManager.insert("BigvScore.insertBigvScore", bigvScore);
		}
	}
}