package com.calf.cn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日历控件工具类
 * @author zhaozheyi
 *
 */
public class CalendarUtil {
	/**
	 * 清空时分秒
	 * @param cal
	 */
	public static void clearTime(Calendar cal){
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
	}
	
	/**
	 * 10分钟为界限
	 * @param cal
	 */
	public static void setTenMinutes(Calendar cal){
		int min = cal.get(Calendar.MINUTE);
		cal.set(Calendar.MINUTE,min-(min%10));
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
	}
	
	public static int getDiff(Calendar start,Calendar end){
		Long days=(end.getTimeInMillis()-start.getTimeInMillis())/(1000*3600*24);
		return days.intValue();
	}
	
	/**
	 * 获取某年的第一天
	 * @param year
	 * @return
	 */
	public static Calendar getCalendarByYear(int year){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		try {
			Date d = sdf.parse(year+"");
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			return cal;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Calendar getCalendarByDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			return cal;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
//		Calendar cal = Calendar.getInstance();
//		setTenMinutes(cal);
//		System.out.println(cal.getTime());
//		Calendar cal1 = getCalendarByYear(2018);
//		System.out.println(cal1.getTime());
		
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		System.out.println(getDiff(cal,cal1));
	}
}
