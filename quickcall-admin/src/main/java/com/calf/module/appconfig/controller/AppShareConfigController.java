package com.calf.module.appconfig.controller;

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.DateUtil;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.appconfig.entity.AppShareConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * app分享配置Controller
 *
 * @author Sunju
 * @date 2018-11-05 21:23
 */
@Controller
@RequestMapping("/appShareConfig")
public class AppShareConfigController implements BaseController<AppShareConfig> {

    @Autowired
    private BaseManager baseManager;

    @Override
    public String home() {
        ModelMap modelMap = new ModelMap();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iDisplayStart", 0);
        parameters.put("iDisplayLength", 100);
        List<AppShareConfig> AppShareConfig = baseManager.query(AppShareConfig.class, parameters);
        modelMap.addAttribute("AppShareConfig", AppShareConfig);
        return "app_config/app_share_config/list";
    }

    @Override
    public DataTables<AppShareConfig> initTable(HttpServletRequest request) {
        Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sEcho = (String) parameters.get("sEcho");
        List<AppShareConfig> AppShareConfig = baseManager.query(AppShareConfig.class, parameters);
        int total = baseManager.get("AppShareConfig.queryCount", parameters);
        return new DataTables<AppShareConfig>(sEcho, AppShareConfig, AppShareConfig.size(), total);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        if (StringUtils.isNotBlank(id)) {
            model.addAttribute("entity", baseManager.get("AppShareConfig.getAppShareConfigById", new Object[]{id}));
        }
        return "app_config/app_share_config/edit";
    }

    @Override
    @MethodLog(operType = "新增")
    @RequiresPermissions(value = "appShareConfig:add")
    public int saveAdd(AppShareConfig entity) {
        Subject currentUser = SecurityUtils.getSubject();
        entity.setCreateMan((String) currentUser.getPrincipal());
        entity.setCreateTime(DateUtil.dateFormat());
        return baseManager.insert(entity);
    }

    @Override
    @MethodLog(operType = "删除")
    @RequiresPermissions(value = "appShareConfig:delete")
    public int delete(Long id) {
        return baseManager.delete(AppShareConfig.class, new Object[]{id});
    }

    @Override
    @MethodLog(operType = "删除")
    @RequiresPermissions(value = "appShareConfig:delete")
    public int delete(String id) {
        return baseManager.delete(AppShareConfig.class, new Object[]{id});
    }

    @Override
    public int saveUpdate(AppShareConfig entity) {
        Subject currentUser = SecurityUtils.getSubject();
        entity.setModifyMan((String) currentUser.getPrincipal());
        entity.setModifyTime(DateUtil.dateFormat());
        return baseManager.update(entity);
    }

}
