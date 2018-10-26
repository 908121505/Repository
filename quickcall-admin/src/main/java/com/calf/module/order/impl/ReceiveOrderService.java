package com.calf.module.order.impl;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.order.entity.SkillItem;
import com.calf.module.order.vo.ReceiveOrderVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("receiveOrderService")
public class ReceiveOrderService {

	@Autowired
	private BaseManager  baseManager;
	@Autowired
	private CommonUtilService  commonUtilService;

	/**
	 * 分页查询订单
	 * @param request
	 * @return
	 */
	public DataTables<ReceiveOrderVO> queryOrderPageList(HttpServletRequest request) {
		HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId",parameters.get("userId"));
		paramMap.put("userNickName",parameters.get("userNickName"));
		paramMap.put("createTime",parameters.get("createTime"));
		paramMap.put("modifyTime",parameters.get("modifyTime"));
		String discountType = (String)parameters.get("discountTypeVal");
		String serviceType = (String)parameters.get("serviceType");
		if (serviceType !=null && (!serviceType.equals("-1"))){
			paramMap.put("serviceType",parameters.get("serviceType"));
		}
		if (discountType !=null && (!discountType.equals("-1"))){
			String[] discountTypes = discountType.split("-");
			paramMap.put("discountTypeMin",discountTypes[0]);
			paramMap.put("discountTypeMax",discountTypes[1]);
		}
		paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
		paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
		List<ReceiveOrderVO> skillList = baseManager.query("Customer.queryPageList", paramMap);
		for(ReceiveOrderVO vo:skillList){
			if (StringUtils.isNotBlank(vo.getDiscountType())) {
				double dis = Double.parseDouble(vo.getDiscountType());
				dis = dis * 10;
				vo.setDiscountType(dis + "%");
			}
		}
		String sEcho = (String) parameters.get("sEcho");
		int total = baseManager.get("Customer.selectCount", paramMap);

		//获取订单状态


		return new DataTables<ReceiveOrderVO>(sEcho, skillList, skillList.size(), total);
	}


	public List<SkillItem> getSkillItemsList(){
		//获取服务类型
		List<SkillItem> skillItems = baseManager.query("SkillItem.selectAll",null);
		return skillItems;
	}

	public void queryOrderDetail(Model model, String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customerId", id);
		List<ReceiveOrderVO> vos = baseManager.query("Customer.queryOrderDetail",  paramMap);
		if (vos != null&&vos.size()>0){
			model.addAttribute("entity", vos.get(0));
		}
	}

    /**
     * 修改订单
     * @param entity
     * @return
     */
    public int updateOrder(ReceiveOrderVO entity) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId", entity.getCustomerId());
		paramMap.put("receiveStatus", entity.getReceiveStatus());
        int update = baseManager.update("CustomerSkill.updateReceiveStatus",  paramMap);
        return update;
    }
}