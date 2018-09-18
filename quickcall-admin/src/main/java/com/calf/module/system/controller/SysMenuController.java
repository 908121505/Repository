package com.calf.module.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.system.entity.SysMenu;

@Controller
@RequestMapping(value="/system/menu")
public class SysMenuController implements BaseController<SysMenu> {

	@Autowired
	private BaseManager baseManager;
	
	@Override
	public String home() {
		// TODO Auto-generated method stub
		return "system/menu/menulist";
	}

	@Override
	@SuppressWarnings("unchecked")
	public DataTables<SysMenu> initTable(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String,Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
		String sEcho = (String)parameters.get("sEcho");
		
		List<SysMenu> aaData = baseManager.query(SysMenu.class, parameters);
		parameters.put("menu_type", "1");
		Integer iTotalDisplayRecords = baseManager.get("SysMenu.queryCount", parameters);
		
		return new DataTables<SysMenu>(sEcho, aaData, aaData.size(), iTotalDisplayRecords);
	}
	
	
	
	@Override
	@RequiresPermissions(value={"menu:add","menu:update"},logical=Logical.OR)
	public String addAndUpdateHome(Model model, String id) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(id)){
			model.addAttribute("entity", baseManager.get(SysMenu.class, new Object[]{id}));
		}
		model.addAttribute("menus", baseManager.query("SysMenu.getAllMenus", null));
		
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("parent_id", id);
		model.addAttribute("menu_authority", 
				baseManager.query("SysMenu.queryMenuAuthority", parameters));
		return "system/menu/addMenu";
	}

	@Override
	@MethodLog(operType="新增")
	@RequiresPermissions(value="menu:add")
	public int saveAdd(SysMenu entity) {
		// TODO Auto-generated method stub
		return baseManager.insert(entity);
	}

	@Override
	@MethodLog(operType="删除")
	@RequiresPermissions(value="menu:delete")
	public int delete(Long id) {
		// TODO Auto-generated method stub
//		return baseManager.delete(SysMenu.class, new Object[]{id});
		int result = baseManager.delete("SysMenu.deleteMenu1", new Object[] {id});
		if(result >= 0 )
			baseManager.delete(SysMenu.class, new Object[] { id });
		return 0;
	}

	@Override
	@MethodLog(operType="修改")
	@RequiresPermissions(value="menu:update")
	public int saveUpdate(SysMenu entity) {
		// TODO Auto-generated method stub
//		return baseManager.update(entity);
		
		if(StringUtils.isNotBlank(entity.getMenu_authority())){
			List<SysMenu> list = new ArrayList<SysMenu>();
			String[] menuAuthority =  entity.getMenu_authority().split(",");
			for (int i = 0; i < menuAuthority.length; i++) {
				SysMenu menu = new SysMenu();
				if(!menuAuthority[i].equals("")){
				if(menuAuthority[i].equals("select"))
					menu.setNames("查询");
				else if(menuAuthority[i].equals("add"))
					menu.setNames("添加");
				else if(menuAuthority[i].equals("update"))
					menu.setNames("修改");
				else if(menuAuthority[i].equals("delete"))
					menu.setNames("删除");
				else if(menuAuthority[i].equals("upload"))
					menu.setNames("上传");
				else if(menuAuthority[i].equals("download"))
					menu.setNames("下载");
				
					menu.setAuthor(entity.getAuthor()+":"+menuAuthority[i]);
					menu.setMenu_type("2");
					menu.setParent_id(entity.getId());
					list.add(menu);
				}
			}
			int res = baseManager.batchInsert(list);
			if(res>=0){
				return baseManager.update(entity);
		}
	}else{
		return baseManager.update(entity);
	}
	return 0;
		
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
