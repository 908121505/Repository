package com.calf.module.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.customer.entity.CustomerSkillCertify;
import com.calf.module.customer.vo.CustomerSkillCertifyVO;
import com.honglu.quickcall.common.core.util.UUIDUtils;

/**
 *用户技能审核页面
 * @author zhaozheyi
 *
 */
@Controller
@RequestMapping("/skillCertify")
public class CustomerSkillCertifyController implements BaseController<CustomerSkillCertifyVO> {
    @Autowired
    private BaseManager baseManager;
    

    private static final String JSP_PATH = "customer/skill_certify/%s";
    
	@Override
	public String home() {
		return String.format(JSP_PATH, "customerSkillVoice_list");
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataTables<CustomerSkillCertifyVO> initTable(HttpServletRequest request) {
		Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sEcho = (String) parameters.get("sEcho");
        List<CustomerSkillCertifyVO> banners = baseManager.query("CustomerSkillCertify.selectSkillCertifyTable", parameters);
        int total = baseManager.get("CustomerSkillCertify.selectTableCount", parameters);
        return new DataTables<>(sEcho, banners, banners.size(), total);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveAdd(CustomerSkillCertifyVO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Transactional
	@Override
	public int saveUpdate(CustomerSkillCertifyVO entity) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		parameters.put("certifyId", entity.getCertifyId());
		CustomerSkillCertify csc = baseManager.get("CustomerSkillCertify.getEntityById",parameters);
		param.put("certifyId", entity.getCertifyId());
		param.put("auditStatus", entity.getAuditStatus());
		
		if(entity.getAuditStatus() != null && entity.getAuditStatus() == 2){
			//审核通过
			param.put("skillVoiceTime", csc.getSkillVoiceTimeTmp());
			param.put("skillVoiceUrl", csc.getSkillVoiceUrlTmp());
			param.put("isAudited", 1);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", csc.getCustomerId());
			map.put("skillItemId", csc.getSkillItemId());
//			int count = baseManager.get("CustomerSkill.selectCustomerSkillExist",map);
			if(csc.getIsAudited() == 0){
				//如果没有则初始化用户技能表
				Map<String, Object> customerSkill = new HashMap<String, Object>();
				customerSkill.put("customerSkillId", UUIDUtils.getId());
				customerSkill.put("certifyId", entity.getCertifyId());
				customerSkill.put("customerId", csc.getCustomerId());
				customerSkill.put("skillItemId", csc.getSkillItemId());
				customerSkill.put("skillName", entity.getSkillItemName());
				baseManager.update("CustomerSkill.insertSelective",customerSkill);
			}
		}
		return baseManager.update("CustomerSkillCertify.updateEntity",param);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
