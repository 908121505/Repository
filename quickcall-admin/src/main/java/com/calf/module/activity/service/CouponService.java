package com.calf.module.activity.service;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.activity.entity.Coupon;
import com.calf.module.appconfig.impl.AdvertisementService;
import com.calf.module.common.impl.CommonUtilService;
import com.honglu.quickcall.common.core.util.StringUtil;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.apache.commons.lang.StringUtils;
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
 * Description: 优惠券管理
 *
 * @author chenpeng
 * @date 2018/10/30 14:35
 */
@Service
@Transactional
public class CouponService {

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementService.class);

    @Autowired
    private BaseManager baseManager;
    @Autowired
    private CommonUtilService commonUtilService ;

    public DataTables<Coupon> getCouponPageList(HttpServletRequest request) {
        HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
        paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));

        paramMap.put("couponName", parameters.get("couponName"));
        paramMap.put("startTime", parameters.get("startTime"));
        paramMap.put("endTime", parameters.get("endTime"));
        paramMap.put("activityName", parameters.get("activityName"));
        paramMap.put("activityCode", parameters.get("activityCode"));
        List<Coupon> CouponList = baseManager.query("Coupon.selectPageList", paramMap);
        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("Coupon.selectCount", paramMap);
        return new DataTables<Coupon>(sEcho, CouponList, CouponList.size(), total);
    }

    public void getCouponDetail(Model model, String id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if(StringUtils.isNotBlank(id) && !"0".equals(id)){
            paramMap.put("couponId", id);
            Coupon coupon = baseManager.get("Coupon.selectById",  paramMap);
            model.addAttribute("entity", coupon);
        }
        List<Map<String, String>> activityList = baseManager.query("Coupon.selectActivityList", paramMap);
        model.addAttribute("activityList", activityList);

        List<Map<String, String>> skillItemList = baseManager.query("Coupon.selectSkillItemList", paramMap);
        model.addAttribute("skillItemList", skillItemList);
    }

    public int saveAdd(Coupon entity) {
        Coupon  coupon =  new Coupon();
        BeanUtils.copyProperties(entity, coupon);
        coupon.setCreateMan(commonUtilService.getCurrUser());

        coupon.setCouponId(UUIDUtils.getId().toString());
        int insertAdCount = baseManager.insert(coupon);
        if(insertAdCount < 1){
            return -1;
        }
        //插入coupon_skill 券与品类对应表
        String skillItemIds = coupon.getSkillItemIdList();
        if(!StringUtil.isBlank(skillItemIds)) {
            String[] skillItemIdArr = skillItemIds.split(",");
            for (String skillItemId : skillItemIdArr) {
                coupon.setSkillItemId(skillItemId);
                int insertCouponSkillCount = baseManager.insert("Coupon.insertCouponSkill", coupon);
                if (insertCouponSkillCount < 1) {
                    return -1;
                }
            }
        }

        return 0;
    }

    public int saveUpdate(Coupon entity) {
        Coupon  coupon =  new Coupon();
        BeanUtils.copyProperties(entity, coupon);
        coupon.setModifyMan(commonUtilService.getCurrUser());

        int updateAdCount = baseManager.update(coupon);
        if(updateAdCount < 1){
            return -1;
        }

        String skillItemIds = coupon.getSkillItemIdList();
        if(!StringUtil.isBlank(skillItemIds)) {

            //如果多选框不为空，则删除coupon_skill 券与品类对应表中的数据
            if(!StringUtil.isBlank(coupon.getSkillItemName())){
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("couponId", coupon.getCouponId());
                int deleteCouponSkillCount = baseManager.delete("Coupon.deleteCouponSkill", paramMap);
                if(deleteCouponSkillCount < 1){
                    return -1;
                }
            }
            //用户选择的不是清空，则插入表 coupon_skill
            if(!"clear".equals(skillItemIds)){
                String[] skillItemIdArr = skillItemIds.split(",");
                for (String skillItemId : skillItemIdArr) {
                    coupon.setSkillItemId(skillItemId);
                    int insertCouponSkillCount = baseManager.insert("Coupon.insertCouponSkill", coupon);
                    if (insertCouponSkillCount < 1) {
                        return -1;
                    }
                }
            }

        }

        return 0;

    }


    public List getActivityList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, String>> activityList = baseManager.query("Coupon.selectActivityList", paramMap);
        return activityList;
    }
}
