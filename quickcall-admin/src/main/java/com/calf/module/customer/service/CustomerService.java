package com.calf.module.customer.service;

import com.calf.cn.service.BaseManager;
import com.calf.module.customer.entity.CustomerVo;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private BaseManager baseManager;

    private static final String OPERATE_UNLOCK = "0";

    private static final String OPERATE_LOCK = "1";

    private static final String UNLOCK_SYSMSG = "您好,您已经被解封";

    private static final String LOCK_REMARK = "prohibition_login_out";

    private static final ImmutableMap<Integer, String> LOCK_MAP = new ImmutableMap.Builder<Integer, String>()
            .put(4, "已封禁-无法接单")
            .put(6, "已封禁-无法接指定技能")
            .put(8, "已封禁-账户登录权限")
            .put(10, "已封禁-设备登录权限").build();
    private static final ImmutableMap<Object, Object> DATE_MAP = new ImmutableMap.Builder<>()
            .put(1, "1天")
            .put(3, "3天")
            .put(7, "7天")
            .put(30, "30天")
            .put(-1, "永久")
            .build();
    private static final ImmutableList<Integer> LOCK_LOGIN_ID = ImmutableList.of(8, 10);


    public void updateCustomer(CustomerVo customerVo) {
        String operate = customerVo.getOperate();
        boolean isForever = false;
        boolean isUnLcckSkill = false;
        if (OPERATE_UNLOCK.equals(operate)) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", customerVo.getCustomerId());
            baseManager.update("Customer.unlockCustomers", params);
            RongYunUtil.sendSystemMessage(Long.valueOf(customerVo.getCustomerId()), UNLOCK_SYSMSG);
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
                params.put("blockStartTime", Date.from(zonedDateTime.toInstant()));
                params.put("blockEndTime", Date.from(zonedDateTime1.toInstant()));
                params.put("id", customerVo.getCustomerId());
                baseManager.update("Customer.lockCustomers", params);
            } else {
                Map<String, Object> params = new HashMap<>();
                params.put("blockStartTime", new Date());
                params.put("id", customerVo.getCustomerId());
                baseManager.update("Customer.lockForever", params);
                isForever = true;
            }

            if (customerVo.getCustStatus() == 6 && customerVo.getCustomerSkills().size() > 0) {
                Map<String, Object> params = new HashMap<>();
                params.put("id", customerVo.getCustomerId());
                for (String s : customerVo.getCustomerSkills()) {
                    params.put("skillName", s);
                    baseManager.update("Customer.lockSkill", params);
                }
                isUnLcckSkill = true;
            }
            StringBuilder builder = new StringBuilder();
            if (isUnLcckSkill) {
                builder.append("您的");
                for (String customerSkill : customerVo.getCustomerSkills()) {
                    builder.append(customerSkill);
                    builder.append(",");
                }
                builder.deleteCharAt(builder.lastIndexOf(","));
                builder.append("技能将被封禁");
                builder.append(DATE_MAP.get(Integer.valueOf(customerVo.getClosureDate())));
            } else {
                builder.append("您因为");
                builder.append(LOCK_MAP.get(customerVo.getCustStatus()));
                builder.append("被封禁");
                builder.append(DATE_MAP.get(Integer.valueOf(customerVo.getClosureDate())));
                System.out.println(builder.toString());
            }
            if (isForever || LOCK_LOGIN_ID.contains(customerVo.getCustStatus())) {
                RongYunUtil.sendSystemMessage(Long.valueOf(customerVo.getCustomerId()), builder.toString(), LOCK_REMARK);
            } else {
                RongYunUtil.sendSystemMessage(Long.valueOf(customerVo.getCustomerId()), builder.toString());
            }

        } else {
            throw new RuntimeException("非法操作");
        }

    }

}
