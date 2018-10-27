package com.calf.cn.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/alignment")
public class AlignmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(AlignmentController.class);
	
	@ResponseBody
	@RequestMapping(value = "/connection")
	public Map<String, Object> getConnection(Model model) {
		logger.info("监控接口收到请求==============");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("returnCode", "200");
		logger.info("请求成功 returnCode===========200");
		return map;
	}
	
}
