package com.calf.module.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.MD5;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.system.entity.QueryOperUserRole;
import com.calf.module.system.entity.SysUser;
import com.calf.module.system.entity.SysUserRole;

@Controller
@RequestMapping(value="/system/user")
public class SysUserController implements BaseController<SysUser> {
	
	@Autowired
	private BaseManager baseManager;

	@Override
	public String home() {
		// TODO Auto-generated method stub
		return "system/user/userlist";
	}

	@Override
	@SuppressWarnings("unchecked")
	public DataTables<SysUser> initTable(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String,Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
		String sEcho = (String)parameters.get("sEcho");
		
		List<SysUser> aaData = baseManager.query(SysUser.class, parameters);
		Integer iTotalDisplayRecords = baseManager.get("SysUser.queryCount", parameters);
		
		return new DataTables<SysUser>(sEcho, aaData, aaData.size(), iTotalDisplayRecords);
	}
	
	@Override
	@RequiresPermissions(value={"user:add","user:update"},logical=Logical.OR)
	public String addAndUpdateHome(Model model, String id) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(id)){
			model.addAttribute("entity", baseManager.get("SysUser.getUserById", new Object[]{id}));
		}
		return "system/user/addUser";
	}
	
	@RequestMapping(value="/modifyPass.htm",method=RequestMethod.GET)
	public String modifyPass(Model model, String id,HttpServletRequest request) {
		if(StringUtils.isNotBlank(id)){
			
			model.addAttribute("entity", baseManager.get("SysUser.getUserById", new Object[]{id}));
		}
		return "home/updatePass";
	}

	@Override
	@MethodLog(operType="新增")
	@RequiresPermissions(value="user:add")
	public int saveAdd(SysUser entity) {
		// TODO Auto-generated method stub
		entity.setPassword(MD5.MD5Encode(entity.getPassword()));
		return baseManager.insert(entity);
	}

	@Override
	@MethodLog(operType="删除")
	@RequiresPermissions(value="user:delete")
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return baseManager.delete(SysUser.class, new Object[]{id});
	}

	@Override
	@MethodLog(operType="修改")
	@RequiresPermissions(value="user:update")
	public int saveUpdate(SysUser entity) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(entity.getPassword())){
			entity.setPassword(MD5.MD5Encode(entity.getPassword()));
		}else{
			entity.setPassword(null);
		}
		return baseManager.update(entity);
	}
	
	
	@MethodLog(operType="修改用户信息")
	@ResponseBody
	@RequestMapping(value = "/modifyUserInfo.htm",method = RequestMethod.POST)
	public int modifyUserInfo(SysUser entity) {
		if(StringUtils.isNotBlank(entity.getPassword())){
			entity.setPassword(MD5.MD5Encode(entity.getPassword()));
		}else{
			entity.setPassword(null);
		}
		return baseManager.update(entity);
	}
	
	
	
	

	/**
	 * 分配用户角色
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addUserRole.htm",method=RequestMethod.GET)
	public String addUserRole(String id,Model model,HttpServletRequest request){
		SysUser user = baseManager.get("SysUser.getUserById", new Object[]{id});
		QueryOperUserRole userRoles = baseManager.get("SysUser.queryRolesByAccount", new Object[]{user.getAccount()});
		model.addAttribute("uRoles", userRoles);
		model.addAttribute("user",user);
		return "system/user/addUserRole";
	}
	
	/**
	 * 保存用户角色
	 * @param account
	 * @param role_code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveInsertAndUpdate.htm", method = RequestMethod.POST)
	@RequiresPermissions(value="user:role")
	public Integer saveUserRole(String account, String role_code,HttpServletRequest request) {
		
		List<SysUserRole> userlist = new ArrayList<SysUserRole>();
		if(baseManager.delete("SysUserRole.deleteUserRole", new Object[]{account}) >=0 ){
			for (String role_id : role_code.split(",")) {
				SysUserRole userRole = new SysUserRole();
				userRole.setAccount(account);
				userRole.setRole_code(role_id);
				userlist.add(userRole);
			}
		}
		return baseManager.batchInsert(userlist);
	}

	/* (非 Javadoc)
	 * Description:
	 * @see com.calf.cn.controller.BaseController#delete(java.lang.String)
	 */
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
