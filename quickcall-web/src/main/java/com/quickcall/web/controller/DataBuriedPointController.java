package com.quickcall.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.honglu.quickcall.producer.facade.business.DataDuriedPointBusiness;
import com.honglu.quickcall.producer.facade.req.databury.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @author xiangping
 * @date 2018-10-28 23:24
 */
@Controller
@RequestMapping("/data")
public class DataBuriedPointController {

    @Reference(group = "buryDataCenter",version = "1.0.0", timeout = 10000, retries = 0)
    private DataDuriedPointBusiness dataDuriedPointBusiness;

    /**
     * 发送消息
     *
     * @return
     */
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    @ResponseBody
    public Object code(HttpServletRequest request) {
        // 发送消息
        DataBuriedPointGetCodeReq req = new DataBuriedPointGetCodeReq();
        req.setPhone("17356985474");
        req.setSuccess(false);
        dataDuriedPointBusiness.buryGetCodeData(req);
        return "success";
    }

    /**
     * 发送消息
     *
     * @return
     */
    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    @ResponseBody
    public Object regist(HttpServletRequest request) {
        // 发送消息
        DataBuriedPointRegistReq req = new DataBuriedPointRegistReq();
        req.setPhoneNumber("17356985474");
        req.setRegistDate(new Date());
        req.setRegistSource("http://www.baidu.com");
        req.setUser_id("17356985474");
        req.setVirUserId("1233");
        UserBean userBean = new UserBean();
        userBean.setGender("男");
        userBean.setNick("xp");
        userBean.setYearOfBirth(new Date());
        req.setUserBean(userBean);
        dataDuriedPointBusiness.burySignUpResultData(req);
        return "success";
    }

    /**
     * 发送消息
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Object login(HttpServletRequest request) {
        // 发送消息
        DataBuriedPointLoginReq req = new DataBuriedPointLoginReq();
        req.setLoginmethod("login");
        req.setPhoneNumber("17356985474");
        req.setUser_id("17356985474");
        dataDuriedPointBusiness.buryUserIdLoginResultData(req);
        return "success";
    }

    /**
     * 发送消息
     *
     * @return
     */
    @RequestMapping(value = "/button", method = RequestMethod.GET)
    @ResponseBody
    public Object button(HttpServletRequest request) {
        // 发送消息
        DataBuriedPointOrderButtonReq req = new DataBuriedPointOrderButtonReq();
        req.setButtonexecution_time("2018-11-01 12:12:12");
        req.setOrderbutton_status("yes");
        req.setUser_id("17658456684");
        dataDuriedPointBusiness.buryOrderButtonData(req);
        return "success";
    }

    /**
     * 发送消息
     *
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    @ResponseBody
    public Object submit(HttpServletRequest request) {
        // 发送消息
        DataBuriedPointSubmitOrderReq req = new DataBuriedPointSubmitOrderReq();
        req.setActual_payment_amount(23.43);
        req.setOrder_amount(12.33);
        req.setOrder_type("123");
        req.setOrder_id(UUID.randomUUID().toString());
        req.setOrder_quantity(43.1);
        req.setUser_id("1702145685");
        dataDuriedPointBusiness.burySubmitOrderData(req);
        return "success";
    }
}
