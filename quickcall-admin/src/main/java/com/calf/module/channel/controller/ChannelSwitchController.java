package com.calf.module.channel.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.channel.entity.ChannelSwitch;
import com.calf.module.channel.service.ChannelSwitchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 渠道开关Controller
 */
@Controller
@RequestMapping("/channelSwitch")
public class ChannelSwitchController implements BaseController<ChannelSwitch>{

	@Autowired
	private ChannelSwitchService channelSwitchService;

	private static final String JSP_PATH = "channel/%s";

	/**
	 * 进入列表页面的方法
	 * @return
	 */
	@Override
	public String home() {
		return String.format(JSP_PATH, "channelSwitchList");
	}

	/**
	 * 初始化页面列表
	 * @param request
	 * @return
	 */
	@Override
	public DataTables<ChannelSwitch> initTable(HttpServletRequest request) {
		return channelSwitchService.getChannelSwitchPageList(request);
	}

	/**
	 * 进入新增或修改页面的方法
	 * @param model
	 * @param id
	 * @return
	 */
	@Override
	public String addAndUpdateHome(Model model, String id) {
		if(StringUtils.isNotBlank(id) && !"0".equals(id)){
            channelSwitchService.getChannelSwitchDetail(model,id);
		}
		return String.format(JSP_PATH, "channelSwitchAdd");
	}

	/**
	 * 保存新增的方法
	 * @param entity
	 * @return
	 */
	@Override
	public int saveAdd(ChannelSwitch entity) {
		return channelSwitchService.saveAdd(entity);
	}

	/**
	 * 保存修改的方法
	 * @param entity
	 * @return
	 */
	@Override
	public int saveUpdate(ChannelSwitch entity) {
		return channelSwitchService.saveUpdate(entity);
	}

	/**
	 * 删除的方法
	 * @param id
	 * @return
	 */
	@Override
	public int delete(Long id) {
		return channelSwitchService.delete(id);
	}

	/**
	 * 删除的方法
	 * @param id
	 * @return
	 */
	@Override
	public int delete(String id) {
		return channelSwitchService.delete(id);
	}


}