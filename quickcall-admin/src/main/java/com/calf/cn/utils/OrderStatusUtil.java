package com.calf.cn.utils;

import java.util.ArrayList;
import java.util.List;

import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;

public class OrderStatusUtil {
	
	public  static  List<Integer>   getSmallOrderStatus (Integer  orderStatus){
		List<Integer>   orderStatusList =  new ArrayList<Integer>();
		
		if(orderStatus == 1){
			orderStatusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE);
		}else if(orderStatus == 2){
			
		}
		
		
		
		
		
		return orderStatusList;
		
	}

}
