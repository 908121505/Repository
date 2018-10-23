package com.calf.module.customerservice.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.customerservice.entity.CustomerAppearance;
import com.calf.module.customerservice.service.AppearanceService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 用户头像、形象照、声鉴卡
 *
 * @author chenpeng
 * @date 2018/10/22 15:16
 */
@Controller
@RequestMapping(value = "/appearance")
public class AppearanceController implements BaseController<CustomerAppearance> {
    private static final Logger logger = LoggerFactory.getLogger(AppearanceController.class);

    @Autowired
    private AppearanceService appearanceService;

    @Override
    public String home() {
        return "customer_service/appearance/appearanceList";
    }

    @Override
    public DataTables<CustomerAppearance> initTable(HttpServletRequest request) {
        return appearanceService.getAppearancePageList(request);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        if(StringUtils.isNotBlank(id) && !"0".equals(id)){
            appearanceService.getAppearanceDetail(model,id);
        }
        return  "customer_service/appearance/updateAppearance";
    }

    @Override
    public int saveAdd(CustomerAppearance entity) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int saveUpdate(CustomerAppearance entity) {
        return appearanceService.saveUpdate(entity);
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    /**
     * 审核通过
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/approve")
    public int approve(String id){
        return appearanceService.approve(id);
    }

    /**
     * 审核拒绝
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/reject")
    public int reject(String id){
        return appearanceService.reject(id);
    }

}
