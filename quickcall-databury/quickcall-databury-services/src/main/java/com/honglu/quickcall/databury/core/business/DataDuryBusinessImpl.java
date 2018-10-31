package com.honglu.quickcall.databury.core.business;

import com.alibaba.dubbo.config.annotation.Service;
import com.honglu.quickcall.databury.core.service.DataBuriedPointService;
import com.honglu.quickcall.databury.facade.business.DataBuryBusiness;
import com.honglu.quickcall.databury.facade.exception.DataBuriedPointException;
import com.honglu.quickcall.databury.facade.req.databury.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiangping
 * @date 2018-10-30 18:27
 */
@Service(version = "1.0.0", retries = -1, timeout = 10_000)
public class DataDuryBusinessImpl implements DataBuryBusiness {
    private static final Logger logger = LoggerFactory.getLogger(DataDuryBusinessImpl.class);

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
}
