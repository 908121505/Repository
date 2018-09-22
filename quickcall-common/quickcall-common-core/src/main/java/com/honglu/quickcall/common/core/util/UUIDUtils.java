package com.honglu.quickcall.common.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class UUIDUtils {

	/** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
    /**
     * 获取随机19位数字
     * @return
     */
    public static Long getId() {
    	Random rdom=new Random();
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmssSSS");
		String numStr= sdf.format(new Date())+StringUtils.leftPad(rdom.nextInt(10000)+"", 4, "0");
		return Long.parseLong(numStr);
    }
    
    
    /** 
     * 获得指定数目的UUID 
     * @param number int 需要获得的UUID数量 
     * @return String[] UUID数组 
     */ 
    public static String[] getUUID(int number){ 
        if(number < 1){ 
            return null; 
        } 
        String[] ss = new String[number]; 
        for(int i=0;i<number;i++){ 
            ss[i] = getUUID(); 
        } 
        return ss; 
    } 
    
    
    public static void main(String[] args) {
    	
		System.out.println(StringUtils.leftPad("1", 4));
	}
}
