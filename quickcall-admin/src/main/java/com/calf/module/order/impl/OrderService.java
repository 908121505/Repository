package com.calf.module.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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

@Service("orderService")
public class OrderService {

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
		if (CompulsionOrderStatusEnums.isContainSubset(OrderSkillConstants.ORDER_STATUS_CANCEL_FORCE, String.valueOf(orderStatus))) {
			// 可以强制取消
			selectFlag = 1;
		} /*else if (orderStatus == OrderSkillConstants.ORDER_STATUS_CANCEL_FORCE
				|| orderStatus == OrderSkillConstants.ORDER_STATUS_FINISHED_FORCE) {
			// 已经强制取消或者强制完成
			selectFlag = 3;
		} */else if(CompulsionOrderStatusEnums.isContainSubset(OrderSkillConstants.ORDER_STATUS_FINISHED_FORCE, String.valueOf(orderStatus))) {
			// 可以强制完成
			selectFlag = 2;
		}else{
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
			return 0;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", entity.getOrderId());
		paramMap.put("compulsion_reason", entity.getCompulsionReason());
		Integer orderStatus = Integer.valueOf(entity.getOrderStatus());

		BigDecimal orderAmount = new BigDecimal(entity.getOrderAmount());
		Long orderId = Long.valueOf(entity.getOrderId());
		int update = 0;
		if (OrderSkillConstants.ORDER_STATUS_CANCEL_FORCE == orderStatus) {
			paramMap.put("orderStatus", orderStatus);
			update = compulsoryCancellation(orderId, orderAmount);
			// 退款给用户
		} else if (OrderSkillConstants.ORDER_STATUS_FINISHED_FORCE == orderStatus) { // 给大V冻结金额
			paramMap.put("orderStatus", orderStatus);
			update = compulsoryCompletion(orderId, orderAmount);
		}

		if (update == 1) {
			update = baseManager.update("Order.updateOrder", paramMap);
			if(update > 0 && OrderSkillConstants.ORDER_STATUS_CANCEL_FORCE == orderStatus){
				RongYunUtil.sendOrderMessage(Long.valueOf(entity.getReceivedOrderId()), OrderSkillConstants.IM_MSG_CONTENT_CANCEL_FORCE_ORDER_TO_DV,OrderSkillConstants.MSG_CONTENT_DAV);
				RongYunUtil.sendOrderMessage(Long.valueOf(entity.getPlaceOrderId()), OrderSkillConstants.IM_MSG_CONTENT_CANCEL_FORCE_ORDER_TO_CUST,OrderSkillConstants.MSG_CONTENT_C);
				
				//推动订单IM消息
				sendOrderMsg(Long.valueOf(entity.getPlaceOrderId()), Long.valueOf(entity.getReceivedOrderId()), orderId, OrderSkillConstants.IM_MSG_CONTENT_CANCEL_FORCE_ORDER_TO_DV);
				sendOrderMsg(Long.valueOf(entity.getReceivedOrderId()), Long.valueOf(entity.getPlaceOrderId()),orderId, OrderSkillConstants.IM_MSG_CONTENT_CANCEL_FORCE_ORDER_TO_CUST);
			}else if (OrderSkillConstants.ORDER_STATUS_FINISHED_FORCE == orderStatus){
				RongYunUtil.sendOrderMessage(Long.valueOf(entity.getReceivedOrderId()), OrderSkillConstants.IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_DV,OrderSkillConstants.MSG_CONTENT_DAV);
				RongYunUtil.sendOrderMessage(Long.valueOf(entity.getPlaceOrderId()), OrderSkillConstants.IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_CUST,OrderSkillConstants.MSG_CONTENT_C);
				
				//推动订单IM消息
				sendOrderMsg(Long.valueOf(entity.getPlaceOrderId()), Long.valueOf(entity.getReceivedOrderId()),orderId, OrderSkillConstants.IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_DV);
				sendOrderMsg(Long.valueOf(entity.getReceivedOrderId()), Long.valueOf(entity.getPlaceOrderId()),orderId, OrderSkillConstants.IM_MSG_CONTENT_DAV_CUST_CONFIRM_TO_CUST);

			}
			
			
		}else{
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
		map.put("userId", order.getServiceId());
		Account account = baseManager.get("Account.queryAccount", map);
		if (account.getFrozenAmounts().compareTo(amount) == -1) {

			return -1;
		}
		map.put("orderNo", order.getOrderNo());
		TradeDetail tradeDetail = baseManager.get("TradeDetail.selectFrozenByOrderNo", map);
		if (tradeDetail != null) {
			String userFrozenkey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + order.getServiceId();
			String steamFrozenKey = RedisKeyConstants.ACCOUNT_USERFROZEN_STREAM + tradeDetail.getTradeId();
			String steamFrozenValue = JedisUtil.get(steamFrozenKey);
			String userFrozenValue = JedisUtil.get(userFrozenkey);
			if (StringUtils.isNotBlank(userFrozenValue) && StringUtils.isNotBlank(steamFrozenValue)) {
				if (userFrozenValue.contains(steamFrozenValue)) {
					flag = true;
					JedisUtil.del(steamFrozenKey);
					String[] arys = userFrozenValue.split(",");
					arys = remove(arys, tradeDetail.getTradeId() + "");
					userFrozenValue = StringUtils.join(arys, ",");
					if (userFrozenValue.length() > 0) {
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
			// 入账
			Map<String, Object> pram = new HashMap<String, Object>();
			if (flag) {// 有冻结流水
				pram.put("type", 3);
			} else {// 无冻结流水
				pram.put("type", 2);
			}
			pram.put("amount", amount);
			pram.put("userId", order.getServiceId());
			baseManager.update("Account.outAccount", pram);
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
				if (userFrozenValue.contains(steamFrozenValue)) {
					flag = true;// 有冻结流水
					JedisUtil.del(steamFrozenKey);
					String[] arys = userFrozenValue.split(",");
					arys = remove(arys, tradeDetail.getTradeId() + "");
					userFrozenValue = StringUtils.join(arys, ",");
					if (userFrozenValue.length() > 0) {
						JedisUtil.set(userFrozenkey, userFrozenValue);
					} else {
						JedisUtil.del(userFrozenkey);
					}
				}
			}
		}
		map.put("customerId", order.getCustomerId());
		map.put("orderNo", order.getOrderNo());
		// 检查用户 该订单是否有下单流水
		tradeDetail = baseManager.get("TradeDetail.queryCountByOrderNoAndCustomerId", map);
		if (tradeDetail != null && tradeDetail.getType() == 3) {
			// 用户强制取消金额 回账
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
	
	private void sendOrderMsg(Long  customerId,Long  serviceId,Long  orderId,String  orderDesc) {
		//customer  ---->>>  serviceId
		String  custStr = JedisUtil.get(RedisKeyConstants.USER_CUSTOMER_INFO+customerId) ;
		if(StringUtils.isNotBlank(custStr)){
			try {
				Customer customer = JSON.parseObject(custStr,  Customer.class);
				RongYunUtil.sendOrderIMMessage(customer.getNickName(),customerId, serviceId, "", orderId, orderDesc, customer.getHeadPortraitUrl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}