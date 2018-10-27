package com.calf.module.resource.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
//import com.calf.cn.service.BaseManager;
import com.calf.module.resource.impl.ResourcePoolService;
import com.calf.module.resource.vo.ResourcePoolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import shaded.org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/resource")
public class ResourcePoolController implements BaseController<ResourcePoolVo>{

	@Autowired
	private ResourcePoolService resourcePoolService;

	@Override
	public String home() {

		return "resource/resourceList";
	}

	@Override
	public DataTables<ResourcePoolVo> initTable(HttpServletRequest request) {
		return resourcePoolService.getResourcePoolPageList(request);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		if(StringUtils.isNotBlank(id) && !"0".equals(id)){
			resourcePoolService.getResourcePoolDetail(model,id);
		}
		return  "resource/resourcePoolAdd";
	}

	@Override
	public int saveAdd(ResourcePoolVo entity) {
		return resourcePoolService.saveAdd(entity);
	}

	@Override
	public int saveUpdate(ResourcePoolVo entity) {
		return resourcePoolService.saveUpdate(entity);
	}

	@Override
	public int delete(Long id) {
		return resourcePoolService.delete(id);
	}

	@Override
	public int delete(String id) {
		return resourcePoolService.delete(id);
	}


}