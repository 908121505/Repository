package com.calf.module.appconfig.impl;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.appconfig.entity.Advertisement;
import com.calf.module.common.impl.CommonUtilService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 弹窗广告管理
 *
 * @author chenpeng
 * @date 2018/10/25 10:28
 */
@Service
public class AdvertisementService {

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementService.class);

    @Autowired
    private BaseManager baseManager;
    @Autowired
    private CommonUtilService commonUtilService ;

    public DataTables<Advertisement> getAdvertisementPageList(HttpServletRequest request) {
        HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
        paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
       /* String startTime = (String) parameters.get("startTime");
        if (StringUtils.isNotBlank(startTime)) {
            startTime = startTime + " 00:00:00";
            paramMap.put("startTime", startTime);
        }
        String eTime = (String) parameters.get("endTime");
        if (StringUtils.isNotBlank(eTime)) {
            eTime = eTime + " 23:59:59";
            paramMap.put("endTime", eTime);
        }*/

        paramMap.put("name", parameters.get("name"));
        paramMap.put("startTime", parameters.get("startTime"));
        paramMap.put("endTime", parameters.get("endTime"));
        List<Advertisement> advertisementList = baseManager.query("Advertisement.selectPageList", paramMap);
        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("Advertisement.selectCount", paramMap);
        return new DataTables<Advertisement>(sEcho, advertisementList, advertisementList.size(), total);
    }

    public void getAdvertisementDetail(Model model, String id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        Advertisement advertisement = baseManager.get("Advertisement.selectById",  paramMap);
        model.addAttribute("entity", advertisement);
    }

    public int saveAdd(Advertisement entity) {
        Advertisement  advertisement =  new Advertisement();
        BeanUtils.copyProperties(entity, advertisement);
        advertisement.setCreateMan(commonUtilService.getCurrUser());
        advertisement.setType(0);
        advertisement.setAdStatus(1);
        int result = baseManager.insert(advertisement);
        if(result > 0){
            return 0;
        }else {
            return -1;
        }
    }

    public int saveUpdate(Advertisement entity) {
        Advertisement  advertisement =  new Advertisement();
        BeanUtils.copyProperties(entity, advertisement);
        advertisement.setModifyMan(commonUtilService.getCurrUser());
        advertisement.setType(0);
        advertisement.setAdStatus(1);
        int result = baseManager.update(advertisement);
        if(result > 0){
            return 0;
        }else {
            return -1;
        }
    }

    public int disable(String id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        int count = baseManager.update("Advertisement.disable", paramMap);
        if(count > 0){
            return 0;
        }
        return -1;
    }
}
