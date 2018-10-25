package com.calf.module.customer.service;

import com.calf.cn.service.BaseManager;
import com.calf.module.customer.entity.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private BaseManager baseManager;

    private static final String OPERATE_UNLOCK = "0";

    private static final String OPERATE_LOCK = "1";


    public void updateCustomer(CustomerVo customerVo) {
        String operate = customerVo.getOperate();

        if (OPERATE_UNLOCK.equals(operate)) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", customerVo.getCustomerId());
            baseManager.update("Customer.unlockCustomers", params);
        } else if (OPERATE_LOCK.equals(operate)) {
            Long addDays = Long.valueOf(customerVo.getClosureDate());
            if (addDays > 0) {
                Map<String, Object> params = new HashMap<>();
                params.put("custStatus", customerVo.getCustStatus());
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDateTime blockStartTime = LocalDateTime.now();
                ZonedDateTime zonedDateTime = blockStartTime.atZone(zoneId);
                LocalDateTime blockEndTime = blockStartTime.plusDays(addDays);
                ZonedDateTime zonedDateTime1 = blockEndTime.atZone(zoneId);
                params.put("blockStartTime",Date.from(zonedDateTime.toInstant()) );
                params.put("blockEndTime",Date.from(zonedDateTime1.toInstant()));
                params.put("id", customerVo.getCustomerId());
                baseManager.update("Customer.lockCustomers", params);


            } else {
                Map<String, Object> params = new HashMap<>();
                params.put("blockStartTime",new Date());
                params.put("id", customerVo.getCustomerId());
                baseManager.update("Customer.lockForever", params);
                //lockForever
            }

            if (customerVo.getCustStatus() == 6 && customerVo.getCustomerSkills().size()>0){
                Map<String,Object> params = new HashMap<>();
                params.put("id",customerVo.getCustomerId());
                for(String s: customerVo.getCustomerSkills()){
                    params.put("skillName",s);
                    baseManager.update("Customer.lockSkill",params);
                }
            }



        } else {
            throw new RuntimeException("非法操作");
        }

    }

}
