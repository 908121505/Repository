package com.calf.module.appconfig.impl;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.appconfig.entity.Advertisement;
import com.calf.module.common.impl.CommonUtilService;
import com.honglu.quickcall.common.core.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
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

        //插入广告表
        int insertAdCount = baseManager.insert(advertisement);
        if(insertAdCount < 1){
            return -1;
        }
        //插入广告和app版本对应表
        String appVersionIds = advertisement.getAppVersionIdList();
        if(StringUtil.isBlank(appVersionIds)) {
            String[] appVersionIdArr = appVersionIds.split(",");
            for (String appVersionId : appVersionIdArr) {
                advertisement.setAppVersionId(appVersionId);
                int insertAdAppCount = baseManager.insert("Advertisement.insertAdAppVersion", advertisement);
                if (insertAdAppCount < 1) {
                    return -1;
                }
            }
        }

        return 0;
    }

    public int saveUpdate(Advertisement entity) {
        Advertisement  advertisement =  new Advertisement();
        BeanUtils.copyProperties(entity, advertisement);
        advertisement.setModifyMan(commonUtilService.getCurrUser());
        advertisement.setType(0);
        advertisement.setAdStatus(1);

        //更新广告表 advertisement
        int updateAdCount = baseManager.update(advertisement);
        if(updateAdCount < 1){
            return -1;
        }
        //删除此广告在 广告与版本对应表中的数据 ad_app_version
        if(StringUtil.isBlank(advertisement.getAppVersion())){
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("advertisementId", advertisement.getId());
            int deleteAdAppCount = baseManager.delete("Advertisement.deleteAdAppVersion", paramMap);
            if(deleteAdAppCount < 1){
                return -1;
            }
        }

        //插入表 ad_app_version
        String appVersionIds = advertisement.getAppVersionIdList();
        if(StringUtil.isBlank(appVersionIds)) {
            String[] appVersionIdArr = appVersionIds.split(",");
            for (String appVersionId : appVersionIdArr) {
                advertisement.setAppVersionId(appVersionId);
                int insertAdAppCount = baseManager.insert("Advertisement.insertAdAppVersion", advertisement);
                if (insertAdAppCount < 1) {
                    return -1;
                }
            }
        }

        return 0;

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

    public List getAppVersionList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, Object>> appVersionList = baseManager.query("Advertisement.selectAppVersionList", paramMap);
        return appVersionList;
    }
}
