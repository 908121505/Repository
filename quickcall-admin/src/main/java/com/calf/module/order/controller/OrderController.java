package com.calf.module.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.order.impl.OrderService;
import com.calf.module.order.vo.OrderVO;

import shaded.org.apache.commons.lang3.StringUtils;

@Controller
@RequestMapping("/order")
public class OrderController implements BaseController<OrderVO>{

	@Autowired
	private OrderService  orderService;
	
	@Override
	public String home() {
		return "order/orderList";
	}

	@Override
	public DataTables<OrderVO> initTable(HttpServletRequest request) {
		return orderService.getOrderPageList(request);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		if(StringUtils.isNotBlank(id)){
			orderService.getOrderDetail(model,id);
		}
		return  "order/orderAdd";
	}

	@Override
	public int saveAdd(OrderVO entity) {
		return orderService.saveAdd(entity);
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public int saveUpdate(OrderVO entity) {
		return orderService.saveUpdate(entity);
	}

	@Override
	public int delete(String id) {
		return 0;
	}

	
}