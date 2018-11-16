package com.honglu.quickcall.databury.core.service.impl;

import com.honglu.quickcall.databury.core.enums.EventEnums;
import com.honglu.quickcall.databury.core.service.DataBuriedPointService;
import com.honglu.quickcall.databury.core.utils.BuryiedPointDataConvertor;
import com.honglu.quickcall.databury.core.utils.BuryiedPointUtil;
import com.honglu.quickcall.databury.facade.exception.DataBuriedPointException;
import com.honglu.quickcall.databury.facade.req.databury.*;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * 数据埋点处理
 * @author xiangping
 * @date 2018-10-28 22:33
 */
@Service("dataBuriedPointService")
public class DataBuriedPointServiceImpl implements DataBuriedPointService {

    private static final Logger logger = LoggerFactory.getLogger(DataBuriedPointServiceImpl.class);

    @Autowired
    private BuryiedPointUtil buryiedPointUtil;

    @Override
    public void saveGetCodeData(DataBuryPointGetCodeReq req) throws DataBuriedPointException {
        logger.info("====【获取验证码--神策埋点】埋点开始--参数为:{}", req);
        if(req==null){
            throw new DataBuriedPointException("神策埋点-获取验证码数据--消费的mq参数为空");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("phone",req.getPhone());
        if (req.isSuccess()==true){
            params.put("isSuccess","true");
        }else{
            params.put("isSuccess","false");
        }

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_getCode.getValue(),(String)params.get("phone"),params);

        buryiedPointUtil.buryData(event);
        logger.info("----【获取验证码--神策埋点】埋点结束");
    }

    @Override
    public void saveSignUpResultData(DataBuryPointRegistReq req) throws DataBuriedPointException {
        logger.info("====【注册成功--神策埋点】埋点开始--参数为:{}",req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-注册成功数据-参数的mq参数为空");
        }
        Map<String,Object> event = new HashMap<>();
        event.put("vc_user_id",req.getUser_id());
        event.put("phoneNumber",req.getPhoneNumber());
        if (req.getRegistDate()!=null){
            event.put("registDate", DateUtils.formatDate(req.getRegistDate(),"yyyy-MM-dd HH:mm:ss"));
        }else{
            event.put("registDate",req.getRegistDate());
        }
        event.put("registSource",req.getRegistSource());

        Map<String, Object> user = getUserMap(req.getUserBean(),req.getPhoneNumber());

        Map<String,Object> data = BuryiedPointDataConvertor.newInstanceAll(EventEnums.EVENT_Sign_up_result.getValue(),req.getUser_id(),event,user,req.getVirUserId());

        buryiedPointUtil.buryData(data);
        logger.info("----【注册成功--神策埋点】埋点结束");
    }

    @Override
    public void saveUserIdLoginResultData(DataBuryPointLoginReq req) throws DataBuriedPointException {
        logger.info("====【登陆成功--神策埋点】埋点开始--参数为:{}", req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-登陆成功数据-参数的mq参数为空");
        }

        Map<String,Object> event = new HashMap<>();
        event.put("loginMethod",req.getLoginmethod());
        event.put("vc_user_id",req.getUser_id());
        event.put("phoneNumber",req.getPhoneNumber());

        Map<String, Object> user = getUserMap(req.getUserBean(),req.getPhoneNumber());

        Map<String,Object> map = BuryiedPointDataConvertor.newInstanceAll(EventEnums.EVENT_User_id_login_result.getValue(),req.getUser_id(),event,user,req.getVirUserId());

        buryiedPointUtil.buryData(map);
        logger.info("----【登陆成功--神策埋点】埋点结束");
    }

    @Override
    public void saveOrderButtonData(DataBuryPointOrderButtonReq req) throws DataBuriedPointException {
        logger.info("====【接单按钮状态--神策埋点】埋点开始--参数为:{}", req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-接单按钮状态数据-参数的mq参数为空");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("orderbutton_status",req.getOrderbutton_status());
        params.put("vc_user_id",req.getUser_id());
        params.put("buttonexecution_time",req.getButtonexecution_time());

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_Order_button.getValue(),(String)params.get("vc_user_id"),params);

        buryiedPointUtil.buryData(event);
        logger.info("----【接单按钮状态--神策埋点】埋点结束");
    }

    @Override
    public void saveSubmitOrderData(DataBuryPointSubmitOrderReq req) throws DataBuriedPointException {
        logger.info("====【提交订单--神策埋点】埋点开始--参数为:{}", req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-提交订单数据-消费的mq参数为空");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("order_id",req.getOrder_id());
        params.put("order_amount",req.getOrder_amount());
        params.put("vc_user_id",req.getUser_id());
        params.put("order_type",req.getOrder_type());
        params.put("order_quantity",req.getOrder_quantity());
        params.put("actual_payment_amount",req.getActual_payment_amount());

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_submitorder.getValue(),(String)params.get("vc_user_id"),params);

        buryiedPointUtil.buryData(event);
        logger.info("----【提交订单--神策埋点】埋点结束");
    }

    @Override
    public void buryMakeOrderData(DataBuryMakeOrderReq req) throws DataBuriedPointException {
        logger.info("====【下单--神策埋点】埋点开始--参数为:{}", req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-下单数据-消费的mq参数为空");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("vc_user_id",req.getVcUserId());
        params.put("vc_user_phone_num",req.getVcUserPhoneNum());
        params.put("vc_owner_userid",req.getVcOwnerUserId());
        params.put("does_succeed",req.getDoesSucceed());
        params.put("skill_id",req.getSkillId());
        params.put("skill_name",req.getSkillName());

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_Make_Order.getValue(),(String)params.get("vc_user_id"),params);

        buryiedPointUtil.buryData(event);
        logger.info("----【下单--神策埋点】埋点结束");
    }

    @Override
    public void burySetPwdDurationData(DataBurySetPwdDurationReq req) throws DataBuriedPointException {
        logger.info("====【修改密码页面--神策埋点】埋点开始--参数为:{}", req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-修改密码页面-消费的mq参数为空");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("vc_user_id",req.getVcUserId());
        params.put("dose_succeed",req.isDoseSucceed());

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_Set_Password_Duration.getValue(),(String)params.get("vc_user_id"),params);

        buryiedPointUtil.buryData(event);
        logger.info("----【修改密码页面--神策埋点】埋点结束");
    }

    @Override
    public void buryFirstChargeData(DataBuryFirstChargeReq req) throws DataBuriedPointException {
        logger.info("====【首次充值--神策埋点】埋点开始--参数为:{}", req);
        if(req == null){
            throw new DataBuriedPointException("神策埋点-首次充值-消费的mq参数为空");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("is_first_time",req.isFirstTime());
        params.put("vc_user_id",req.getVcUserId());
        params.put("vc_user_phone_num",req.getVcUserPhoneNum());

        Map<String,Object> event = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_First_Charge.getValue(),(String)params.get("vc_user_id"),params);

        buryiedPointUtil.buryData(event);
        logger.info("----【首次充值--神策埋点】埋点结束");
    }


    /**
     * 组装用户属性
     * @param userBean
     * @param phoneNumber
     * @return
     */
    private Map<String,Object> getUserMap(UserBean userBean,String phoneNumber){
        Map<String, Object> user = new HashMap<>(16);
        if (userBean!=null) {
            user.put("gender", userBean.getGender());
            user.put("phoneNumber", phoneNumber);
            user.put("yearOfBirth", userBean.getYearOfBirth());
            user.put("vc_user_id", userBean.getVc_user_id());
            user.put("registSource", userBean.getRegistSource());
            user.put("registDate", userBean.getRegistDate());
            user.put("nick", userBean.getNick());
            user.put("nickname", userBean.getNick());
            user.put("Vermicelli", userBean.getVermicelli());
            user.put("Number_of_concerns", userBean.getNumberOfCencerns());
            user.put("User_identity", userBean.getUserIdentity());
            user.put("User_equipment", userBean.getUserEquipment());
        }
        return user;
    }
}
