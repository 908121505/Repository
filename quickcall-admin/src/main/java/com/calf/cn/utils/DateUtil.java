package com.calf.cn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * 日期工具类
 *
 * @author guixin
 *
 */
public class DateUtil {
	
	public static final  String  PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final  String  PATTERN_HHMM = "HH:mm";

	/**
	 * 将日期转换为字符串
	 *
	 * @return
	 */
	public static String dateYMD() {
		return dateFormat(new Date(), "yyyy-MM-dd");
	}
	public static String dateYMDEXT(Date  date) {
		return dateFormat(date, "yyyy-MM-dd");
	}

	public static String dateFormat() {
		return dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	public static String hhmm(Date  date) {
		return dateFormat(date, PATTERN_HHMM);
	}
	public static String totalStr(Date  date) {
		if(date == null){
			return null;
		}
		return dateFormat(date, PATTERN_YMDHMS);
	}

	public static String dateFormat(String pattern) {
		return dateFormat(new Date(), pattern);
	}

	public static String dateFormat(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date dateFormat1(Date date, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String source = dateFormat(date, pattern);
		return sdf.parse(source);
	}

	/**
	 * 字符串转换为日期
	 *
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date StrToDate(String source, String patten) {
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成最近几天的日期
	 *
	 * @param day
	 * @return
	 */
	public static Map<String, Integer> genDate(int day) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i <= day; i++) {
			Calendar calendar = Calendar.getInstance();
			int date = calendar.get(Calendar.DAY_OF_MONTH) - (day - i);

			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), date);
			String d = dateFormat(calendar.getTime(), "yyyy-MM-dd");
			map.put(d, i);
		}
		return map;
	}

	/**
	 * 生成几天图表的x轴说明
	 *
	 * @param day
	 * @return
	 */
	public static List<String> genDateArray(int day) {
		List<String> list = new ArrayList<String>();

		for (int i = 0; i <= day; i++) {
			Calendar calendar = Calendar.getInstance();
			int date = calendar.get(Calendar.DAY_OF_MONTH) - (day - i);

			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), date);
			list.add(dateFormat(calendar.getTime(), "yyyy-MM-dd"));
		}
		return list;
	}

	/**
	 * 判断两个日期是否相等
	 *
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static boolean judgeDateSample(Date date1, Date date2, String patten) throws ParseException {
		date1 = dateFormat1(date1, patten);
		date2 = dateFormat1(date2, patten);
		if (date1.getTime() == date2.getTime())
			return true;
		return false;
	}

	public static String getDateTime() {
		return dateFormat(new Date(), "yyyyMMddHHmmss");
	}

	public static String getDateTime(String date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dt= null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DAY_OF_YEAR,-15);
		Date result=calendar.getTime();
		return sdf.format(result);
	}
	
	public static String getIncreaseOneDate(String date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dt= null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DAY_OF_YEAR,1);
		Date result=calendar.getTime();
		return sdf.format(result);
	}
	/***
	 * 获取days天前的日期
	 * @param date
	 * @return
	 */
	public static String getDecreaseSomeDate(Integer days){
		SimpleDateFormat sdf=new SimpleDateFormat(PATTERN_YMDHMS);
		Date dt= new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DAY_OF_YEAR,(0-days));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date result=calendar.getTime();
		return sdf.format(result);
	}
	public static String getIncreaseOneWeek(String date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dt= null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DAY_OF_WEEK,-6);
		Date result=calendar.getTime();
		return sdf.format(result);
	}

	public static String getDateTime(String date,int day){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dt= null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DAY_OF_YEAR,day);
		Date result=calendar.getTime();
		return sdf.format(result);
	}
	
	
	
	
	public  static  void   getBetweenDateStr(Date  startTime,Date endTime,TreeSet<String>  dateStrSet ){
		try {
			String  startTimeStr = dateYMDEXT(startTime);
			String  endTimeStr = dateYMDEXT(endTime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar cal = Calendar.getInstance();  
			cal.setTime(sdf.parse(startTimeStr));
			for (long d = cal.getTimeInMillis(); d <= sdf.parse(endTimeStr).getTime(); d = get_D_Plaus_1(cal)) {  
				dateStrSet.add(sdf.format(d));
			}
			String  currStr = dateYMD();
			dateStrSet.remove(currStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	}
	public  static  void   getBetweenDateStr2(Date  startTime,Date endTime,TreeSet<String>  dateStrSet ){
		try {
			String  startTimeStr = dateYMDEXT(startTime);
			String  endTimeStr = dateYMDEXT(endTime);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar cal = Calendar.getInstance();  
			cal.setTime(sdf.parse(startTimeStr));
			for (long d = cal.getTimeInMillis(); d <= sdf.parse(endTimeStr).getTime(); d = get_D_Plaus_1(cal)) {  
				dateStrSet.add(sdf.format(d));
			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	}
	
	
	private static long get_D_Plaus_1(Calendar c) {  
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);  
        return c.getTimeInMillis();  
    }  
	
	public static String[] getSixMonth() {  
		Calendar  cal =  Calendar.getInstance();
	    Date  startTime =  cal.getTime();
//	    cal.add(Calendar.MONTH, -6);
	    cal.add(Calendar.DAY_OF_YEAR, -10);
	    Date  endTime =  cal.getTime();
	    
	    String startTimeStr = totalStr(startTime);
	    String endTimeStr = totalStr(endTime);
		String[]  retArry =  {startTimeStr,endTimeStr};
		return retArry ;
	}  

}
