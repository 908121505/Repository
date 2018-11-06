package com.honglu.quickcall.producer.core.buisness;

import com.honglu.quickcall.producer.core.service.DataDuriedPointService;
import com.honglu.quickcall.producer.facade.business.DataDuriedPointBusiness;
import com.honglu.quickcall.producer.facade.req.databury.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author xiangping
 * @date 2018-10-30 16:14
 */
@Service(group = "buryDataCenter",version = "1.0.0", retries = -1, timeout = 10_000)
public class DataDuriedPointBusinessImpl implements DataDuriedPointBusiness {

    @Autowired
    private DataDuriedPointService dataDuriedPointService;

    @Override
    public void buryGetCodeData(DataBuriedPointGetCodeReq req) {
        //dataDuriedPointService.buryGetCodeData(req);
    }

    @Override
    public void burySignUpResultData(DataBuriedPointRegistReq req) {
        //dataDuriedPointService.burySignUpResultData(req);
    }

    @Override
    public void buryUserIdLoginResultData(DataBuriedPointLoginReq req) {
        //dataDuriedPointService.buryUserIdLoginResultData(req);
    }

    @Override
    public void buryOrderButtonData(DataBuriedPointOrderButtonReq req) {
        //dataDuriedPointService.buryOrderButtonData(req);
    }

    @Override
    public void burySubmitOrderData(DataBuriedPointSubmitOrderReq req) {
        //dataDuriedPointService.burySubmitOrderData(req);
    }

}
