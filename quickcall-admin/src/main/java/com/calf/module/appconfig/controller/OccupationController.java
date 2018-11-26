package com.calf.module.appconfig.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.module.appconfig.entity.Occupation;
import com.calf.module.appconfig.impl.OccupationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 职业管理
 *
 * @author chenpeng
 * @date 2018/10/16 17:06
 */
@Controller
@RequestMapping(value = "/occupation")
public class OccupationController implements BaseController<Occupation> {
    @Autowired
    private OccupationService occupationService;
    @Autowired
    private BaseManager baseManager;

    @Override
    public String home() {
        return "app_config/occupation/occupationList";
    }

    @Override
    public DataTables<Occupation> initTable(HttpServletRequest request) {
        return occupationService.getOcupationPageList(request);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        if(StringUtils.isNotBlank(id) && !"0".equals(id)){
            occupationService.getOcupationDetail(model,id);
        }
        return  "app_config/occupation/addOccupation";
    }

    @Override
    public int saveAdd(Occupation entity) {
        return occupationService.saveAdd(entity);
    }

    @Override
    public int saveUpdate(Occupation entity) {
        return occupationService.saveUpdate(entity);
    }

    @Override
    public int delete(String id) {
        return baseManager.delete(Occupation.class, new Object[]{id});
    }

    @Override
    public int delete(Long id) {
        return 0;
    }


}
