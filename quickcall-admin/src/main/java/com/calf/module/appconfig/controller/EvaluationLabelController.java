package com.calf.module.appconfig.controller;

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.appconfig.entity.EvaluationLabel;
import com.calf.module.order.entity.SkillItem;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评价标签配置管理Controller
 *
 * @author duanjun
 * @date 2018-10-21 16:07
 */
@Controller
@RequestMapping(value = "/evaluationLabel")
public class EvaluationLabelController implements BaseController<EvaluationLabel> {
    private static final Logger log = LoggerFactory.getLogger(BannerController.class);

    @Autowired
    private BaseManager baseManager;

    private static final String JSP_PATH = "app_config/evaluation_label/%s";

    @Override
    public String home() {
        return null;
    }

    @RequestMapping(value = "/list.htm")
    public String home(Model model) {
        // 查询技能类型
        List<SkillItem> skillList = baseManager.query("SkillItem.selectAllValidData", null);
        model.addAttribute("skillList", skillList);
        return String.format(JSP_PATH, "list");
    }

    @Override
    public DataTables<EvaluationLabel> initTable(HttpServletRequest request) {
        Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sEcho = (String) parameters.get("sEcho");
        List<EvaluationLabel> banners = baseManager.query(EvaluationLabel.class, parameters);
        int total = baseManager.get("EvaluationLabel.queryCount", parameters);
        return new DataTables<>(sEcho, banners, banners.size(), total);
    }

    @Override
    @RequiresPermissions(value = {"evaluationLabel:add", "evaluationLabel:update"}, logical = Logical.OR)
    public String addAndUpdateHome(Model model, String labelId) {
        if (StringUtils.isNotBlank(labelId)) {
            model.addAttribute("entity", baseManager.get("EvaluationLabel.selectByPrimaryKey", new Object[]{labelId}));
        }

        // 查询技能类型
        List<SkillItem> skillList = baseManager.query("SkillItem.selectAllValidData", null);
        model.addAttribute("skillList", skillList);

        return String.format(JSP_PATH, "edit");
    }

    @Override
    @MethodLog(operType = "新增")
    @RequiresPermissions(value = {"evaluationLabel:add"}, logical = Logical.OR)
    public int saveAdd(EvaluationLabel entity) {
        Subject currentUser = SecurityUtils.getSubject();
        if (StringUtils.isNotBlank(entity.getLabelName())) {
            entity.setLabelName(entity.getLabelName().trim());//去除前后空格
        }
        entity.setCreateMan(currentUser.getPrincipal().toString());
        entity.setModifyMan(currentUser.getPrincipal().toString());
        return baseManager.insert(entity);
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    @RequiresPermissions(value = {"evaluationLabel:update"}, logical = Logical.OR)
    public int saveUpdate(EvaluationLabel entity) {
        if (StringUtils.isNotBlank(entity.getLabelName())) {
            entity.setLabelName(entity.getLabelName().trim());//去除前后空格
        }

        Subject currentUser = SecurityUtils.getSubject();
        entity.setModifyMan(currentUser.getPrincipal().toString());
        return baseManager.update(entity);
    }

    @Override
    public int delete(String id) {
        return 0;
    }
}
