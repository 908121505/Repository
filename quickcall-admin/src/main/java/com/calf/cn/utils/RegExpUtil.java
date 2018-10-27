package com.calf.cn.utils;

import org.apache.commons.lang.StringUtils;
/**
 * 
 * 正则工具类
 * @author zdf
 * 
 */
public class RegExpUtil {

	 
	   /**
	    * 过滤正则表达式特殊字符
	    * @param resouceString
	    * @param filterString
	    * @return String
	    */
		public  static String filterRegExpString(String resouceString,String filterString){
			if(StringUtils.isBlank(resouceString)){
			   return resouceString;
			}
			String oldFilterString = "\\[](){}^$?*+.,|";
			if(filterString==null){
				filterString = oldFilterString;
			}
			StringBuilder newResouceString = new StringBuilder();
			for(int i=0;i<filterString.length();i++){
				 if (resouceString.contains(String.valueOf(filterString.charAt(i)))) { 
					 newResouceString = new StringBuilder(resouceString.replace(String.valueOf(filterString.charAt(i)), "\\"+filterString.charAt(i)));
					 resouceString=newResouceString.toString(); 
				}
			}
			return resouceString;
		  }
}
