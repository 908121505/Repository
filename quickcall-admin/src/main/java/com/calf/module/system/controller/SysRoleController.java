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
import com.calf.cn.utils.SearchUtil;
import com.calf.module.system.entity.SysMenu;
import com.calf.module.system.entity.SysRole;
import com.calf.module.system.entity.SysRoleMenu;

/**
 * 
 * @author guixin
 *
 */
@Controller
@RequestMapping(value="/system/role")
public class SysRoleController implements BaseController<SysRole>{

	@Autowired
	private BaseManager baseManager;
	
	@Override
	public String home() {
		// TODO Auto-generated method stub
		return "system/role/rolelist";
	}

	@Override
	@SuppressWarnings("unchecked")
	public DataTables<SysRole> initTable(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String,Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
		String sEcho = (String)parameters.get("sEcho");
		
		List<SysRole> aaData = baseManager.query(SysRole.class, parameters);
		Integer iTotalDisplayRecords = baseManager.get("SysRole.queryCount", parameters);
		
		return new DataTables<SysRole>(sEcho, aaData, aaData.size(), iTotalDisplayRecords);
	}

	@Override
	@RequiresPermissions(value={"role:add","role:update"},logical=Logical.OR)
	public String addAndUpdateHome(Model model, String id) {
		// TODO Auto-generated method stub
		
		if(StringUtils.isNotBlank(id)){
			SysRole sysRole = baseManager.get(SysRole.class, new Object[]{id});
			model.addAttribute("entity", sysRole);
		}
		return "system/role/addRole";
	}
	/**
	 * 跳转到增加菜单页面
	 * @param model
	 * @param codes
	 * @return
	 */
	@RequestMapping(value="/addTreeHome.htm",method=RequestMethod.GET)
	@RequiresPermissions(value="role:menu")
	public String addTreeHome(Model model, String codes){
		if(StringUtils.isNotBlank(codes)){
			SysRole sysRole = baseManager.get("SysRole.getCodes", new Object[]{codes});
			model.addAttribute("entity", sysRole);
		}
		return "system/role/addRoleMenu";
	}
	/**
	 * 初始化菜单树
	 * @return
	 */
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/initTree.htm",method=RequestMethod.POST)
	public DataTables<SysMenu> initTree(HttpServletRequest request){
		Map<String,Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
		String sEcho = (String)parameters.get("sEcho");
		
		List<SysMenu> aaData = baseManager.query("SysMenu.queryTree", parameters);
		Integer iTotalDisplayRecords = baseManager.get("SysMenu.queryCount", parameters);

		return new DataTables<SysMenu>(sEcho,aaData,aaData.size(),iTotalDisplayRecords);
	}
	/**
	 * 保存分配菜单
	 * @param role_code
	 * @param menu_id
	 * @return
	 */
	@ResponseBody 
	@RequestMapping(value="/AddMenus.htm",method=RequestMethod.POST)
	@RequiresPermissions(value="role:menu")
	public int  AddMenus(String role_code,String menu_id){
		List<SysRoleMenu> roleMenus = new ArrayList<SysRoleMenu>();
		if(baseManager.delete("Home.deleteRoleMenus", new Object[]{role_code})>=0){
			for (String menu : menu_id.split(",")) {
				SysRoleMenu sysRoleMenu = new SysRoleMenu();
				sysRoleMenu.setMenu_id(menu);
				sysRoleMenu.setRole_code(role_code);
				roleMenus.add(sysRoleMenu);
			}
		}
		return baseManager.batchInsert("Home.batchInsertRoleMenus", roleMenus);
	}
	
	@Override
	@MethodLog(operType="新增")
	@RequiresPermissions(value="role:add")
	public int saveAdd(SysRole entity) {
		// TODO Auto-generated method stub
		return baseManager.insert(entity);
	}

	@Override
	@MethodLog(operType="删除")
	@RequiresPermissions(value="role:delete")
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return baseManager.delete(SysRole.class, new Object[]{id});
	}

	@Override
	@MethodLog(operType="修改")
	@RequiresPermissions(value="role:update")
	public int saveUpdate(SysRole entity) {
		// TODO Auto-generated method stub
		return baseManager.update(entity);
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
