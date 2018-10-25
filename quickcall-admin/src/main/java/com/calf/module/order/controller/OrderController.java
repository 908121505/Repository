package com.calf.module.order.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.order.entity.SkillItem;
import com.calf.module.order.impl.OrderService;
import com.calf.module.order.vo.OrderStatusVO;
import com.calf.module.order.vo.OrderVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController implements BaseController<OrderVO>{

	@Autowired
	private OrderService  orderService;
	
	@Override
	public String home() {
		return "order/orderList";
	}

	@RequestMapping(value = "/list.htm")
	public String home1(HttpServletRequest request,Model model) {
		List<OrderStatusVO> statusList = orderService.getOrderStatusList();
		List<SkillItem> itemsList = orderService.getSkillItemsList();
		model.addAttribute("services",itemsList);
		model.addAttribute("statusList",statusList);
		return "order/orderList";
	}

	@Override
	public DataTables<OrderVO> initTable(HttpServletRequest request) {
		return orderService.queryOrderPageList(request);
	}

	@ResponseBody
	@RequestMapping(value = "/orderStatusList.htm")
	public List<OrderStatusVO> getOrderStatusList(HttpServletRequest request) {
		return orderService.getOrderStatusList();
	}

	@ResponseBody
	@RequestMapping(value = "/skillItemsList.htm")
	public List<SkillItem> getSkillItemsList(HttpServletRequest request) {
		return orderService.getSkillItemsList();
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		if(StringUtils.isNotBlank(id)&&(!"999".equals(id))){
			String ids[] = id.split("-");
			orderService.queryOrderDetail(model,ids[0]);
			if (ids[1].equals("detail")){
				return "order/detailOrder";
			}
			return  "order/editOrder";
		}
		return  "order/addOrder";
	}

	@Override
	public int saveAdd(OrderVO entity) {
		return orderService.addOrder(entity);
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public int saveUpdate(OrderVO entity) {
		return orderService.updateOrder(entity);
	}

	@Override
	public int delete(String id) {
		return 0;
	}
}