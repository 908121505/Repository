package com.calf.module.customer.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.customer.entity.FadeCustomer;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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
import java.util.ResourceBundle;

/**
 * 随机用户管理模块
 *
 * @author duanjun
 * @date 2018-10-17 17:13
 */
@Controller
@RequestMapping("/fadeCustomer")
public class FadeCustomerController implements BaseController<FadeCustomer> {
    private static final Logger log = LoggerFactory.getLogger(FadeCustomerController.class);

    /**
     * 默认头像
     */
    private static String DEFAULT_IMG = ResourceBundle.getBundle("thirdconfig").getString("defaultImg");

    @Autowired
    private BaseManager baseManager;

    private static final String JSP_PATH = "customer/fade_customer/%s";

    @Override
    public String home() {
        return String.format(JSP_PATH, "list");
    }

    @Override
    public DataTables<FadeCustomer> initTable(HttpServletRequest request) {
        Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sEcho = (String) parameters.get("sEcho");
        List<FadeCustomer> list = baseManager.query(FadeCustomer.class, parameters);
        int total = baseManager.get("FadeCustomer.queryCount", parameters);
        return new DataTables<>(sEcho, list, list.size(), total);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        if (StringUtils.isNotBlank(id)) {
            model.addAttribute("entity", baseManager.get("FadeCustomer.selectByPrimaryKey", new Object[]{Integer.valueOf(id)}));
        }
        model.addAttribute("DEFAULT_IMG", DEFAULT_IMG);
        return String.format(JSP_PATH, "edit");
    }

    @Override
    public int saveAdd(FadeCustomer entity) {
        Subject currentUser = SecurityUtils.getSubject();
        entity.setCreateMan(currentUser.getPrincipal().toString());
        entity.setModifyMan(currentUser.getPrincipal().toString());
        if(StringUtils.isBlank(entity.getHeadPortraitUrl())){
            entity.setHeadPortraitUrl(DEFAULT_IMG);// 没有上传图片存默认头像
        }
        return baseManager.insert(entity);
    }

    @Override
    public int delete(Long id) {
        return baseManager.delete(FadeCustomer.class, new Object[]{id.intValue()});
    }

    @Override
    public int saveUpdate(FadeCustomer entity) {
        Subject currentUser = SecurityUtils.getSubject();
        entity.setModifyMan(currentUser.getPrincipal().toString());
        return baseManager.update(entity);
    }

    @Override
    public int delete(String id) {
        return baseManager.delete(FadeCustomer.class, new Object[]{Integer.valueOf(id)});
    }


}
