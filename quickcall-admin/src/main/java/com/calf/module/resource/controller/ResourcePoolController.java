package com.calf.module.resource.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.module.resource.entity.ResourcePool;
import com.calf.module.resource.impl.ResourcePoolService;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/resource")
public class ResourcePoolController implements BaseController<ResourcePool>{

	@Autowired
	private ResourcePoolService resourcePoolService;
	
	@Override
	public String home() {

		return "resource/resourceList";
	}

	@Override
	public DataTables<ResourcePool> initTable(HttpServletRequest request) {
		//return resourcePoolService.getResourcePoolPageList(request);
		return new DataTables<ResourcePool>();
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		/*if(StringUtils.isNotBlank(id)){
			resourcePoolService.getResourcePoolDetail(model,id);
		}*/
		return  "resource/resourcePoolAdd";
	}
	@Autowired
	private BaseManager baseManager;
	@Override
	public int saveAdd(ResourcePool entity) {

		ResourcePool rp = new ResourcePool();
		BeanUtils.copyProperties(entity, rp);
		Long id = UUIDUtils.getId();
		rp.setId(id+"");
		rp.setStatus(1);//有效

		rp.setCreateTime(new Date());

		return baseManager.insert(rp);
	}

	@Override
	public int saveUpdate(ResourcePool entity) {
		return 0;
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