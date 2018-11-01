package com.calf.module.system.controller;

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.*;
import com.calf.module.system.entity.BizParam;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by bruce on 2017/5/12.
 */
@Controller
@RequestMapping("/bizparam")
public class BizParamController implements BaseController<BizParam> {


    private static final Logger logger = LoggerFactory.getLogger(BizParamController.class);

    @Autowired
    private BaseManager baseManager;

    @Override
    public String home() {
        return null;
    }

    /**
     * 跳转到初始化页面
     *
     * @return
     */
    @RequestMapping(value = "/newhome.htm")
    public String home(Model model) {
        model.addAttribute("bizParamTypes", baseManager.query("BizParamType.queryAll", null));
        return "system/bizparam/bizParamList";
    }


    @Override
    public DataTables<BizParam> initTable(HttpServletRequest request) {
        Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sEcho = (String) parameters.get("sEcho");
        List<BizParam> list = baseManager.query(BizParam.class, parameters);
        int total = baseManager.get("BizParam.queryCount", parameters);
        return new DataTables<BizParam>(sEcho, list, list.size(), total);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        if (StringUtils.isNotBlank(id)) {
            model.addAttribute("entity", baseManager.get("BizParam.getAppParamById", new Object[]{id}));
        }
        model.addAttribute("bizParamTypes", baseManager.query("BizParamType.queryAll", null));
        return "system/bizparam/addBizParam";
    }

    @Override
    @MethodLog(operType = "新增")
    @RequiresPermissions(value = "bizparam:add")
    public int saveAdd(BizParam entity) {
        Subject currentUser = SecurityUtils.getSubject();
        entity.setCreateMan((String) currentUser.getPrincipal());
        entity.setCreateTime(DateUtil.dateFormat());
        entity.setBizParamId(UUIDUtils.getUUID());
        if (entity.getState() == 1) {
            JedisUtil.set(Constants.BIZ_PARAM + entity.getBizParamCode(), entity.getBizParamValue());
        }
        return baseManager.insert(entity);
    }

    @Override
    @MethodLog(operType = "删除")
    @RequiresPermissions(value = "bizparam:delete")
    public int delete(Long id) {
        return baseManager.delete(BizParam.class, new Object[]{id});
    }

    @Override
    public int saveUpdate(BizParam entity) {
        Subject currentUser = SecurityUtils.getSubject();
        entity.setModifyMan((String) currentUser.getPrincipal());
        entity.setModifyTime(DateUtil.dateFormat());
        try {
            if (entity.getState() == 1) {
                JedisUtil.set(Constants.BIZ_PARAM + entity.getBizParamCode(), entity.getBizParamValue());
            } else {
                JedisUtil.del(Constants.BIZ_PARAM + entity.getBizParamCode());
            }
        } catch (Exception e) {
            logger.info("22222222" + e);
            e.printStackTrace();
        }
        return baseManager.update(entity);
    }

    @Override
    @MethodLog(operType = "删除")
    @RequiresPermissions(value = "bizparam:delete")
    public int delete(String id) {
        BizParam param = baseManager.get("BizParam.getAppParamById", new Object[]{id});
        if (StringUtils.isNotBlank(JedisUtil.get(Constants.BIZ_PARAM + param.getBizParamCode()))) {
            // 删除redis key
        }
        return baseManager.delete(BizParam.class, new Object[]{id});
    }
}
