package com.calf.module.system.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.gexin.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.DateUtil;
import com.calf.cn.utils.MD5;
import com.calf.module.system.entity.QueryHomeAllClick;
import com.calf.module.system.entity.QueryHomeMenu;
import com.calf.module.system.entity.SysUser;

/**
 * 主页控制类
 * @author guixin
 *
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private BaseManager baseManager;
	
	/**
	 * 登录页
	 * @return
	 */
	@RequestMapping(value="/login.htm",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	/**
	 * 点击登陆
	 * @param account
	 * @param password
	 * @return
	 */
	@MethodLog(operType="登录")
	@RequestMapping(value="/login.htm",method=RequestMethod.POST)
	public String login(Model model, String account, String password,HttpServletRequest request){
		UsernamePasswordToken token = new UsernamePasswordToken(account, MD5.MD5Encode(password));
		Subject currentUser = SecurityUtils.getSubject();
		try{
			currentUser.login(token);
			SecurityUtils.getSubject().getSession().setTimeout(-10001);
			return "redirect:/index.htm";
		} catch(Exception e){
			logger.warn("登录失败：{}", e.getMessage());
		}
		request.setAttribute("loginFail", "1");
		return "login";
	}

	/**
	 * 登录页
	 * @return
	 */
	@RequestMapping(value="/index.htm",method=RequestMethod.GET)
	public String index(Model model){
		top(model);
		left(model);
		return "index";
	}

	/**
	 * 头部信息页
	 * @return
	 */
	private void top(Model model){
		Subject currentUser = SecurityUtils.getSubject();
		model.addAttribute("account", (String)currentUser.getPrincipal());
	}
	
	/**
	 * 左侧导航栏
	 * @return
	 */
	private void left(Model model){
		Subject currentUser = SecurityUtils.getSubject();
		Map<String,Object> paramters = new HashMap<String, Object>();
		paramters.put("account", currentUser.getPrincipal());		
		List<QueryHomeMenu> list = baseManager.query("Home.queryLeftMenus", paramters);
		logger.info("查询结果集----------"+ JSON.toJSONString(list)+"左侧菜单-------------------------------");
		model.addAttribute("menus", list);
	}
	
	/**
	 * 欢迎页
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/welcome.htm",method=RequestMethod.GET)
	public String welcome(Model model,Integer days) throws ParseException{
		return "home/welcome";
	}
	
	
	
	/**
	 * 获取首页图标数据
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/welcomeChart.htm",method=RequestMethod.POST)
	public List<QueryHomeAllClick> chart() throws ParseException{
		List<QueryHomeAllClick> list = baseManager.query("Home.queryHomeChart", null);
		return countDaysClickNum(list);
	}
	
	/**
	 * 设置y轴的值
	 * @param list
	 * @return
	 * @throws ParseException
	 */
	private List<QueryHomeAllClick> countDaysClickNum(List<QueryHomeAllClick> list) throws ParseException{
		Map<String,Integer> dmap = DateUtil.genDate(6);
		Map<String,QueryHomeAllClick> map = new HashMap<String, QueryHomeAllClick>();
		
		for (QueryHomeAllClick qhac : list) {
			String key = qhac.getCodes();
			String date = qhac.getLevel();
			
			if(map.containsKey(key)){
				QueryHomeAllClick qhac1 = map.get(key);
				int index = dmap.get(date);
				qhac1.getSenvenDay()[index] = qhac.getPvNum();
			} else {
				int index = dmap.get(date);
				
				QueryHomeAllClick qhac1 = new QueryHomeAllClick();
				qhac1.setNames(qhac.getNames());
				qhac1.getSenvenDay()[index] = qhac.getPvNum();
				
				map.put(key, qhac1);
			}
		}
		
		List<QueryHomeAllClick> list1 = new ArrayList<QueryHomeAllClick>();
		Iterator<Entry<String, QueryHomeAllClick>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, QueryHomeAllClick> entry = it.next();
			list1.add(entry.getValue());
		}
		return list1;
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	@MethodLog(operType="退出")
	@RequestMapping(value="/logout.htm",method=RequestMethod.GET)
	public String logout(){
		SecurityUtils.getSubject().logout();
		return "login";
	}
}
