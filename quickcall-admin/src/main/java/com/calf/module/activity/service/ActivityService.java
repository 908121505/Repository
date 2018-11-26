package com.calf.module.activity.service;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.activity.entity.Activity;
import com.calf.module.appconfig.impl.AdvertisementService;
import com.calf.module.common.impl.CommonUtilService;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 活动管理
 *
 * @author chenpeng
 * @date 2018/10/30 14:34
 */
@Service
@Transactional
public class ActivityService {

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementService.class);

    @Autowired
    private BaseManager baseManager;
    @Autowired
    private CommonUtilService commonUtilService ;

    public DataTables<Activity> getActivityPageList(HttpServletRequest request) {
        HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
        paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));

        paramMap.put("startTime", parameters.get("startTime"));
        paramMap.put("endTime", parameters.get("endTime"));
        paramMap.put("activityName", parameters.get("activityName"));
        List<Activity> activityList = baseManager.query("Activity.selectPageList", paramMap);
        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("Activity.selectCount", paramMap);
        return new DataTables<Activity>(sEcho, activityList, activityList.size(), total);
    }

    public void getActivityDetail(Model model, String id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("activityId", id);
        Activity activity = baseManager.get("Activity.selectById",  paramMap);
        model.addAttribute("entity", activity);
    }

    public int saveAdd(Activity entity) {
        Activity  activity =  new Activity();
        BeanUtils.copyProperties(entity, activity);
        activity.setCreateMan(commonUtilService.getCurrUser());

        activity.setActivityId(UUIDUtils.getId().toString());
        int insertAdCount = baseManager.insert(activity);
        if(insertAdCount < 1){
            return -1;
        }

        return 0;
    }

    public int saveUpdate(Activity entity) {
        Activity  activity =  new Activity();
        BeanUtils.copyProperties(entity, activity);
        activity.setModifyMan(commonUtilService.getCurrUser());

        int updateAdCount = baseManager.update(activity);
        if(updateAdCount < 1){
            return -1;
        }

        return 0;

    }

    public int disable(String id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        int count = baseManager.update("Activity.disable", paramMap);
        if(count > 0){
            return 0;
        }
        return -1;
    }


}
