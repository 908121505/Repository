package com.calf.module.appconfig.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.module.appconfig.entity.Advertisement;
import com.calf.module.appconfig.entity.Occupation;
import com.calf.module.appconfig.impl.AdvertisementService;
import com.calf.module.customerservice.controller.AppearanceController;
import com.calf.module.customerservice.entity.CustomerAppearance;
import com.calf.module.customerservice.service.AppearanceService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: 弹窗广告管理
 *
 * @author chenpeng
 * @date 2018/10/25 10:23
 */
@Controller
@RequestMapping(value = "/advertisement")
public class AdvertisementController implements BaseController<Advertisement> {
    private static final Logger logger = LoggerFactory.getLogger(AppearanceController.class);

    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private BaseManager baseManager;

    @Override
    public String home() {
        return "app_config/advertisement/advertisementList";
    }

    @Override
    public DataTables<Advertisement> initTable(HttpServletRequest request) {
        return advertisementService.getAdvertisementPageList(request);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        if(StringUtils.isNotBlank(id) && !"0".equals(id)){
            advertisementService.getAdvertisementDetail(model,id);
        }
        return  "app_config/advertisement/updateAdvertisement";
    }

    @Override
    public int saveAdd(Advertisement entity) {
        return advertisementService.saveAdd(entity);
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int saveUpdate(Advertisement entity) {
        return advertisementService.saveUpdate(entity);
    }

    @Override
    public int delete(String id){return baseManager.delete(Occupation.class, new Object[]{id});
    }

    /**
     * 删除禁用(广告逻辑删除)
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/disable")
    public int disable(String id){
        return advertisementService.disable(id);
    }

    /**
     * 获取版本列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAppVersionList")
    public List getAppVersionList(){
        return advertisementService.getAppVersionList();
    }

}
