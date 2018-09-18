package com.honglu.quickcall.common.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LoveUtils {
	
	//升级标准
	public static final int LOVE1=0;
	public static final int LOVE2=131;
	public static final int LOVE3=197;
	public static final int LOVE4=295;
	public static final int LOVE5=442;
	public static final int LOVE6=663;
	public static final int LOVE7=995;
	
	public static Map<Integer,Integer> getLove(){
		Map<Integer, Integer> loveMap=new HashMap<>();
		loveMap.put(1,LOVE1);
		loveMap.put(2,LOVE2);
		loveMap.put(3,LOVE3);
		loveMap.put(4,LOVE4);
		loveMap.put(5,LOVE5);
		loveMap.put(6,LOVE6);
		loveMap.put(7,LOVE7);
		return loveMap;
	}

	
}
