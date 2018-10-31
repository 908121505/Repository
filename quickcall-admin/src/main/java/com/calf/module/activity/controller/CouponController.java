package com.calf.module.activity.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.module.activity.entity.Coupon;
import com.calf.module.activity.service.CouponService;
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
import java.util.List;

/**
 * Description: 优惠券
 *
 * @author chenpeng
 * @date 2018/10/30 14:24
 */
@Controller
@RequestMapping(value = "/coupon")
public class CouponController implements BaseController<Coupon> {
    private static final Logger logger = LoggerFactory.getLogger(AppearanceController.class);

    @Autowired
    private CouponService CouponService;
    @Autowired
    private BaseManager baseManager;

    @Override
    public String home() {
        return "activity/coupon/couponList";
    }

    @Override
    public DataTables<Coupon> initTable(HttpServletRequest request) {
        return CouponService.getCouponPageList(request);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        CouponService.getCouponDetail(model,id);
        return  "activity/coupon/updateCoupon";
    }

    @Override
    public int saveAdd(Coupon entity) {
        return CouponService.saveAdd(entity);
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int saveUpdate(Coupon entity) {
        return CouponService.saveUpdate(entity);
    }

    @Override
    public int delete(String id){return baseManager.delete(Coupon.class, new Object[]{id});
    }
    /**
     * 获取活动列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getActivityList")
    public List getActivityList(){
        return CouponService.getActivityList();
    }

}
