package com.calf.module.order.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.order.entity.SkillItem;
import com.calf.module.order.impl.ReceiveOrderService;
import com.calf.module.order.vo.ReceiveOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/receiveOrder")
public class ReceiveOrderController implements BaseController<ReceiveOrderVO>{

	@Autowired
	private ReceiveOrderService receiveOrderService;
	
	@Override
	public String home() {
		return "order/receiveOrderList";
	}

	@RequestMapping(value = "/list.htm")
	public String home1(HttpServletRequest request,Model model) {
		List<SkillItem> itemsList = receiveOrderService.getSkillItemsList();
		model.addAttribute("itemsList",itemsList);
		return "order/receiveOrderList";
	}

	@Override
	public DataTables<ReceiveOrderVO> initTable(HttpServletRequest request) {
		return receiveOrderService.queryOrderPageList(request);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		String ids[] = id.split("-");
		receiveOrderService.queryOrderDetail(model,ids[0]);
		if (ids[1].equals("detail")){
			return "order/detailReceiveOrder";
		}
		return  "order/editReceiveOrder";
	}

	@ResponseBody
	@RequestMapping(value = "/skillItemsList.htm")
	public List<SkillItem> getSkillItemsList(HttpServletRequest request) {
		return receiveOrderService.getSkillItemsList();
	}

	@Override
	public int saveAdd(ReceiveOrderVO entity) {
		return 0;
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public int saveUpdate(ReceiveOrderVO entity) {
		return receiveOrderService.updateOrder(entity);
	}

	@Override
	public int delete(String id) {
		return 0;
	}
}