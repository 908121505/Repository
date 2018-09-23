package com.honglu.quickcall.user.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.service.service.CustomerRedisManagement;
import com.honglu.quickcall.user.service.service.CustomerService;
import com.honglu.quickcall.user.service.util.RedisKeyConstants;

import cn.jiguang.commom.utils.StringUtils;
import net.sf.json.JSONObject;
@Service
public class CustomerRedisManagementImpl implements CustomerRedisManagement{
	private static final Logger logger = LoggerFactory.getLogger(CustomerRedisManagementImpl.class);
	@Autowired
	private CustomerService customerService; 
	@Autowired
    private BaseManager baseManager;
	@Override
	public Customer getCustomer(Long customerId) {
		Customer customer = null;
//		try {
//			customer = customerService.selectByCustomerId(customerId);
//			JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO+customerId, customer==null?"": JSON.toJSONString(customer));
//		} catch (Exception e) {
//			logger.error("获取用户失败");
//			e.printStackTrace();
//		}
		try{
			String customerJson= JedisUtil.get(RedisKeyConstants.USER_CUSTOMER_INFO+customerId);
			if(StringUtils.isNotEmpty(customerJson)){
				JSONObject jsonobject = JSONObject.fromObject(customerJson);
				customer= (Customer)JSONObject.toBean(jsonobject,Customer.class);
			}else{
				customer = baseManager.get("Customer.getCustomerId", new Object[] { customerId });
				JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO+customerId, customer==null?"": JSON.toJSONString(customer));
			}
			}catch(Exception e){
				logger.error("获取用户失败");
				e.printStackTrace();
			}
		return customer;
	}

}