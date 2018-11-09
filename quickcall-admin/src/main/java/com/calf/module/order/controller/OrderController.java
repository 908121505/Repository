package com.calf.module.order.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.order.entity.SkillItem;
import com.calf.module.order.impl.OrderService;
import com.calf.module.order.vo.OrderStatusVO;
import com.calf.module.order.vo.OrderVO;
import com.calf.module.order.vo.SmallOrderStatusVO;
import com.honglu.quickcall.common.api.util.DateUtils;

@Controller
@RequestMapping("/order")
public class OrderController implements BaseController<OrderVO> {

	@Autowired
	private OrderService orderService;

	@Override
	public String home() {
		return "order/orderList";
	}

	@RequestMapping(value = "/list.htm")
	public String home1(HttpServletRequest request, Model model) {
		List<OrderStatusVO> statusList = orderService.getOrderStatusList();
		List<SkillItem> itemsList = orderService.getSkillItemsList();
		model.addAttribute("services", itemsList);
		model.addAttribute("statusList", statusList);
		Date curDate = new Date();
		Date sDate = DateUtils.getAddDate(curDate, -60 * 24);
		String startTime = DateUtil.formatDate(sDate, "yyyy-MM-dd");
		String endTime = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
		model.addAttribute("showStartTime", startTime + " 00:00:00");
		model.addAttribute("showEndTime", endTime + " 23:59:59");
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
		if (StringUtils.isNotBlank(id) && (!"999".equals(id))) {
			String ids[] = id.split("-");
			OrderVO order = orderService.queryOrderDetail(model, ids[0]);
			if (ids[1].equals("detail")) {
				List<SmallOrderStatusVO> list = orderService.getSamllOrderStatusList();
				for (SmallOrderStatusVO vo : list) {
					if (vo.getValue().equals(order.getOrderStatus())) {
						model.addAttribute("orderStatus", vo.getDesc());
						break;
					}
				}
				return "order/detailOrder";
			}
			List<SmallOrderStatusVO> lists = orderService.getSamllOrderStatusList();
			for (SmallOrderStatusVO vo : lists) {
				if (vo.getValue().equals(order.getOrderStatus())) {
					vo.setShow(true);
					model.addAttribute("orderStatusDesc", vo.getDesc());
					break;
				}
			}
			model.addAttribute("editSmallStatus", lists);
			return "order/editOrder";
		}
		List<SmallOrderStatusVO> list = orderService.getSamllOrderStatusList();
		List<SkillItem> itemsList = orderService.getSkillItemsList();
		model.addAttribute("smallStatus", list);
		model.addAttribute("services", itemsList);
		return "order/addOrder";
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