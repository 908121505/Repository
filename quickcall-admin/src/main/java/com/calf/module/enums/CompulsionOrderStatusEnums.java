package com.calf.module.enums;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;


public enum CompulsionOrderStatusEnums {

	qzqx("29","强制取消","2,10,18,24"),
	qzwc("42","强制完成","26,28,30,31,32,33,34,36,38,40");
	
	private final String value;
    private final String desc;
    private final String subset;

    CompulsionOrderStatusEnums(String value, String desc, String subset) {
        this.value = value;
        this.desc = desc;
        this.subset = subset;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public String getSubset() {
        return subset;
    }
    
    public static CompulsionOrderStatusEnums getCompulsionOrderStatusEnums(String value){
    	for(CompulsionOrderStatusEnums compulsionOrderStatusEnums:CompulsionOrderStatusEnums.values()){
    		if(StringUtils.isNotBlank(value) && StringUtils.equals(value, compulsionOrderStatusEnums.getValue())){
    			return compulsionOrderStatusEnums;
    		}
    	}
    	return null;
    }
    
    public static boolean isContainSubset(Integer orderCompulsionType,String orderStatus){
    	CompulsionOrderStatusEnums compulsionOrderStatusEnums = CompulsionOrderStatusEnums.getCompulsionOrderStatusEnums(String.valueOf(orderCompulsionType));
		String orderListString = compulsionOrderStatusEnums.getSubset();
		if(orderListString != null){
			List<String> orderList = Arrays.asList(orderListString.split(","));
			return orderList.contains(orderStatus);
		}
		return false;
    }
}
