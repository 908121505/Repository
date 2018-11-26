package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.CustomerDelate;
import com.honglu.quickcall.user.facade.entity.Delate;
import com.honglu.quickcall.user.facade.exchange.request.DelateInsertRequest;
import com.honglu.quickcall.user.facade.vo.DelateVO;
import com.honglu.quickcall.user.service.dao.CustomerDelateMapper;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.dao.DelateMapper;
import com.honglu.quickcall.user.service.service.DelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testng.util.Strings;

import java.util.Date;
import java.util.List;

@Service("delateServiceImpl")
@Transactional
public class DelateServiceImpl implements DelateService {

    @Autowired
    private DelateMapper delateMapper;

    @Autowired
    private CustomerDelateMapper customerDelateMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CommonResponse getAllDelatesExcludeOther() {
        CommonResponse commonResponse = new CommonResponse();
        List<DelateVO> results = delateMapper.getAllDelateExcludeOther();
        commonResponse.setCode(BizCode.Success);
        commonResponse.setMessage(BizCode.Success.desc());
        commonResponse.setData(results);
        return commonResponse;
    }

    @Override
    public CommonResponse insertDelate(DelateInsertRequest request) {
        Long customerId = request.getCustomerId();
        Long delateCustId = request.getDelateCustId();
        String otherReason = request.getOtherReason();
        Integer isBlack = request.getIsBlack();
        List<Integer> delateIds = request.getDelateIds();
        CommonResponse commonResponse = new CommonResponse();
        if (customerId == null || delateCustId == null) {
            throw new BizException(BizCode.ParamError, "举报人或者被举报人不能为null");
        }

       Customer customer = customerMapper.selectByPrimaryKey(customerId);
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
        if(delateIds == null && Strings.isNullOrEmpty(otherReason)){
            throw new BizException(BizCode.ParamError, "举报内容不能为空");
        }

        /*
        if (delateIds != null) {
            for (Integer id : delateIds) {
                CustomerDelate customerDelate = new CustomerDelate();
                customerDelate.setCustomerId(customerId);
                customerDelate.setDelateCustId(delateCustId);
                customerDelate.setCreateTime(new Date());
                customerDelate.setCreateMan(delateCustId + "");
                customerDelate.setDelateId(id);
                customerDelateMapper.insert(customerDelate);
            }
        }

        if (Strings.isNotNullAndNotEmpty(otherReason)) {
            CustomerDelate customerDelate = new CustomerDelate();
            customerDelate.setCustomerId(customerId);
            customerDelate.setDelateCustId(delateCustId);
            customerDelate.setCreateTime(new Date());
            customerDelate.setCreateMan(delateCustId + "");
            customerDelate.setDelateContent(otherReason);
            customerDelateMapper.insert(customerDelate);
        }
        */
        if (delateIds != null) {
            for (Integer id : delateIds) {
                CustomerDelate customerDelate = new CustomerDelate();
                customerDelate.setCustomerId(customerId);
                customerDelate.setDelateCustId(delateCustId);
                customerDelate.setCreateTime(new Date());
                customerDelate.setCreateMan(delateCustId + "");
                customerDelate.setDelateId(id);
                if(id==7){
                    //其他原因
                    if (Strings.isNotNullAndNotEmpty(otherReason)) {
                        customerDelate.setDelateContent(otherReason);
                    }else{
                        throw new BizException(BizCode.ParamError, "举报内容不能为空");
                    }
                }
                customerDelateMapper.insert(customerDelate);
            }
        }

        commonResponse.setCode(BizCode.Success);
        commonResponse.setMessage(BizCode.Success.desc());

        return commonResponse;
    }
}
