package com.calf.module.order.impl;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.DateUtil;
import com.calf.cn.utils.RadomUtil;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.customer.entity.Customer;
import com.calf.module.enums.OrderStatusEnums;
import com.calf.module.enums.SmallOrderStatusEnums;
import com.calf.module.order.entity.*;
import com.calf.module.order.vo.OrderStatusVO;
import com.calf.module.order.vo.OrderVO;
import com.calf.module.order.vo.SmallOrderStatusVO;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service("orderService")
public class OrderService {

	@Autowired
	private BaseManager  baseManager;
	@Autowired
	private CommonUtilService  commonUtilService;


	@SuppressWarnings("unchecked")
	public DataTables<OrderVO> getOrderPageList(HttpServletRequest request) {
		
		HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", parameters.get("orderId"));
		paramMap.put("custNickName", parameters.get("custNickName"));
		paramMap.put("servNickName", parameters.get("servNickName"));
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
	 * @param request
	 * @return
	 */
	public DataTables<OrderVO> queryOrderPageList(HttpServletRequest request) {
		HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId",parameters.get("orderId"));
		paramMap.put("placeOrderId",parameters.get("placeOrderId"));
		paramMap.put("receivedOrderId",parameters.get("receivedOrderId"));
		paramMap.put("startTime",parameters.get("startTime"));
		paramMap.put("endTime",parameters.get("endTime"));
		paramMap.put("placeOrderUserNickName",parameters.get("placeOrderUserNickName"));
		paramMap.put("receiveOrderUserNickName",parameters.get("receiveOrderUserNickName"));

		String orderStatus = (String)parameters.get("orderStatus");
		String serviceType = (String)parameters.get("serviceType");
		String discountType = (String)parameters.get("discountTypeVal");
		if (serviceType !=null && (!serviceType.equals("-1"))){
			paramMap.put("serviceType",parameters.get("serviceType"));
		}
		if (orderStatus !=null && (!orderStatus.equals("-1"))){
			paramMap.put("orderStatus",parameters.get("orderStatus"));
		}
		if (discountType !=null && (!discountType.equals("-1"))){
			String[] discountTypes = discountType.split("-");
			paramMap.put("discountTypeMin",discountTypes[0]);
			paramMap.put("discountTypeMax",discountTypes[1]);
		}
		paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
		paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
		List<OrderVO> skillList = baseManager.query("Order.queryPageList", paramMap);
		for(OrderVO vo:skillList){
			if (StringUtils.isNotBlank(vo.getDiscountType())) {
				double dis = Double.parseDouble(vo.getDiscountType());
				dis = dis * 10;
				vo.setDiscountType(dis + "%");
			}
		}
		String sEcho = (String) parameters.get("sEcho");
		int total = baseManager.get("Order.selectCount", paramMap);
		return new DataTables<OrderVO>(sEcho, skillList, skillList.size(), total);
	}

	public List<OrderStatusVO> getOrderStatusList(){
		//获取订单状态
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

	public List<SmallOrderStatusVO> getSamllOrderStatusList(){
		//获取小类订单状态
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

	public List<SkillItem> getSkillItemsList(){
		//获取服务类型
		List<SkillItem> skillItems = baseManager.query("SkillItem.selectAll",null);
		return skillItems;
	}

	public void getOrderDetail(Model model, String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", id);
		Order order = baseManager.get("Order.selectByPrimaryKey",  paramMap);
		model.addAttribute("entity", order);
	}

	public OrderVO queryOrderDetail(Model model, String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", id);
		OrderVO order = baseManager.get("Order.queryOrderDetail",  paramMap);
        SmallOrderStatusEnums status = SmallOrderStatusEnums.getSmallOrderStatusEnums(order.getOrderStatus());
        if (status==null) {
			order.setOrderStatusVal(order.getOrderStatus());
		}else{
			order.setOrderStatusVal(status.getDesc());
			String[] cancels = OrderStatusEnums.Cancel.getSubset().split(",");
			boolean isShow = false;
			for (String can:cancels){
				if (can.equals(status.getValue())){
					isShow = true;
					break;
				}
			}
			model.addAttribute("isShow", isShow);
		}

        model.addAttribute("entity", order);
        return order;
	}

	public int saveAdd(OrderVO entity) {
		//根据名称获取公司信息
		Skill  skill =  new Skill();
		Long  id = UUIDUtils.getId();
		BeanUtils.copyProperties(entity, skill);
		skill.setId(id+"");
		skill.setCreateMan(commonUtilService.getCurrUser());
		skill.setSkillStatus(0);//有效
		skill.setCreateTime(new Date());
		baseManager.insert(skill);
		return 0;
	}

    /**
     * 添加订单
     * @param entity
     * @return
     */
    public int addOrder(OrderVO entity) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId", entity.getPlaceOrderId());
		Customer customer = baseManager.get("Customer.selectByPrimaryKey",paramMap);
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("customerId", entity.getReceivedOrderId());
		List<CustomerSkill> skillList = baseManager.query("CustomerSkill.queryCustomerSkill",paramMap1);
        if (customer == null){
            //不存在下单用户
            return 0;
        }
        if (skillList==null||skillList.size()<=0){
            //接单用户不存在技能
            return 0;
        }
        CustomerSkill skill = skillList.get(0);
        Orders order = new Orders();
        String orderId = DateUtil.dateFormat(new Date(), "yyyyMMddHHmmss")+ RadomUtil.generateNumber2(4);
        order.setOrderId(Long.parseLong(orderId));
        order.setOrderNo(Long.parseLong(orderId));
        order.setCustomerSkillId(skill.getCustomerSkillId());
        order.setCustomerId(Long.parseLong(entity.getPlaceOrderId()));
        order.setOrderAmounts(new BigDecimal(entity.getOrderAmount()));
        byte status = (byte) Integer.parseInt(entity.getOrderStatus());
        order.setOrderStatus(status);
        order.setServiceId(skill.getCustomerId());
        order.setOrderNum(Integer.parseInt(entity.getOrderNum()));
        order.setDiscountRate(new BigDecimal(entity.getDiscountType()));
        order.setStartTime(DateUtil.StrToDate(entity.getStartTime(),"yyyy-MM-dd HH:mm:dd"));
        order.setEndTime(DateUtil.StrToDate(entity.getEndTime(),"yyyy-MM-dd HH:mm:dd"));
        order.setCreateTime(new Date());
        int insert = baseManager.insert(order);
        return insert;
    }

    /**
     * 修改订单
     * @param entity
     * @return
     */
    public int updateOrder(OrderVO entity) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", entity.getOrderId());
		paramMap.put("orderStatus", entity.getOrderStatus());
		paramMap.put("remarkReason", entity.getRemarkReason());
		int update = baseManager.update("Order.updateOrder",  paramMap);
        return update;
    }

	public int saveUpdate(OrderVO entity) {
		Skill  skill =  new Skill();
		BeanUtils.copyProperties(entity, skill);
		skill.setModifyMan(commonUtilService.getCurrUser());
		skill.setModifyTime(new Date());
		baseManager.update(skill);
		return 0;
	}

	
	
	
	

	
}