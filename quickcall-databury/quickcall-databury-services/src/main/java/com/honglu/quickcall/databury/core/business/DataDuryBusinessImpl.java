package com.honglu.quickcall.databury.core.business;

import com.alibaba.dubbo.config.annotation.Service;
import com.honglu.quickcall.databury.core.service.DataBuriedPointService;
import com.honglu.quickcall.databury.facade.business.DataBuryBusiness;
import com.honglu.quickcall.databury.facade.exception.DataBuriedPointException;
import com.honglu.quickcall.databury.facade.req.databury.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiangping
 * @date 2018-10-30 18:27
 */
@Service(group = "buryDataCenter",version = "1.0.0", retries = -1, timeout = 10_000)
public class DataDuryBusinessImpl implements DataBuryBusiness {

    @Autowired
    private DataBuriedPointService dataBuriedPointService;


    @Override
    public void saveGetCodeData(DataBuryPointGetCodeReq req) throws DataBuriedPointException {
        dataBuriedPointService.saveGetCodeData(req);
    }

    @Override
    public void saveSignUpResultData(DataBuryPointRegistReq req) throws DataBuriedPointException {
        dataBuriedPointService.saveSignUpResultData(req);
    }

    @Override
    public void saveUserIdLoginResultData(DataBuryPointLoginReq req) throws DataBuriedPointException {
        dataBuriedPointService.saveUserIdLoginResultData(req);
    }

    @Override
    public void saveOrderButtonData(DataBuryPointOrderButtonReq req) throws DataBuriedPointException {
        dataBuriedPointService.saveOrderButtonData(req);
    }

    @Override
    public void saveSubmitOrderData(DataBuryPointSubmitOrderReq req) throws DataBuriedPointException {
        dataBuriedPointService.saveSubmitOrderData(req);
    }

    @Override
    public void buryMakeOrderData(DataBuryMakeOrderReq req) throws DataBuriedPointException{
        dataBuriedPointService.buryMakeOrderData(req);
    }

    @Override
    public void burySetPwdDurationData(DataBurySetPwdDurationReq req) throws DataBuriedPointException {
        dataBuriedPointService.burySetPwdDurationData(req);
    }

    @Override
    public void buryFirstChargeData(DataBuryFirstChargeReq req) throws DataBuriedPointException{
        dataBuriedPointService.buryFirstChargeData(req);
    }

}
