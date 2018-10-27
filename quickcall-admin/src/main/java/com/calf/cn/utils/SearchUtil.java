package com.calf.cn.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * 请求参数转换为map
 * @author guixin
 *
 */
public class SearchUtil {

	/**
	 * 将表单数据封装为Map
	 * @param paramMap
	 * @return
	 */	
	public static Map<String,Object> convertorEntitysToMap(Map<String, String[]> paramMap){
		Map<String,Object> map = new HashMap<String, Object>();
		
		Set<Entry<String, String[]>> set = paramMap.entrySet();  
        Iterator<Entry<String, String[]>> it = set.iterator();  
        while (it.hasNext()) {  
            Entry<String, String[]> entry = it.next();  
            for (String i : entry.getValue()) {  
            	if(!map.containsKey(entry.getKey())){
            		if(StringUtils.isNotBlank(i)){
            			if("iDisplayStart".equals(entry.getKey()) || "iDisplayLength".equals(entry.getKey())){
            				map.put(entry.getKey(), Integer.valueOf(i));
            			} else{
            				map.put(entry.getKey(), i);
            			}
            		}
            	}
            }  
        }  
		return map;
	}
}
