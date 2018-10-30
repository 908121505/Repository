package com.calf.module.activity.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.module.activity.entity.Activity;
import com.calf.module.activity.service.ActivityService;
import com.calf.module.appconfig.entity.Occupation;
import com.calf.module.customerservice.controller.AppearanceController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 活动管理
 *
 * @author chenpeng
 * @date 2018/10/30 14:23
 */
@Controller
@RequestMapping(value = "/activity")
public class ActivityController  implements BaseController<Activity> {
    private static final Logger logger = LoggerFactory.getLogger(AppearanceController.class);

    @Autowired
    private ActivityService activityService;
    @Autowired
    private BaseManager baseManager;

    @Override
    public String home() {
        return "activity/activity/activityList";
    }

    @Override
    public DataTables<Activity> initTable(HttpServletRequest request) {
        return activityService.getActivityPageList(request);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        if(StringUtils.isNotBlank(id) && !"0".equals(id)){
            activityService.getActivityDetail(model,id);
        }
        return  "app_config/advertisement/updateActivity";
    }

    @Override
    public int saveAdd(Activity entity) {
        return activityService.saveAdd(entity);
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int saveUpdate(Activity entity) {
        return activityService.saveUpdate(entity);
    }

    @Override
    public int delete(String id){return baseManager.delete(Occupation.class, new Object[]{id});
    }

    /**
     * 删除禁用(逻辑删除)
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/disable")
    public int disable(String id){
        return activityService.disable(id);
    }

}
