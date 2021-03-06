package com.honglu.quickcall.common.api.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtils {

    static SimpleDateFormat dateFormat = null;

    static final String date_format = "yyyy-MM-dd HH:mm:ss";
    static final String DATE_FORMAT_EXT = "yyyy-MM-dd HH:mm";
    
    static final String DATE_FORMAT_HH_MM = "HH:mm";
    
    static final String format = "yyyy-MM-dd";

    //一分钟
    public static final long MINUTE = 1000 * 60;
    //一小时
    public static final long HOUR = 1000 * 60 * 60;
    //一天
    public static final long DAY = 1000 * 60 * 60 * 24;

    /**
     * 获取当前时间，默认格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentDateTime() {
        dateFormat = new SimpleDateFormat(date_format);
        return dateFormat.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取当前时间，默认格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static Date getCurrentDate() throws ParseException {
        dateFormat = new SimpleDateFormat(date_format);
        return dateFormat.parse(dateFormat.format(new Date()));
    }

    /**
     * 获取当前时间，默认格式yyyy-MM-dd HH:mm:ss
     *
     * @param pattern 自定义格式
     * @return
     */
    public static String getCurrentDateTime(String pattern) {
        if (pattern == null || pattern == "") {
            return getCurrentDateTime();
        }
        dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date(System.currentTimeMillis()));
    }
    /**
     * 获取当前时间的小时：分钟
     * @param date
     * @return
     */
    public static String getDateHHMMTime(Date  date) {
    	dateFormat = new SimpleDateFormat(DATE_FORMAT_HH_MM);
    	return dateFormat.format(date);
    }

    /**
     * 格式化Date日期
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        dateFormat = new SimpleDateFormat(date_format);
        return dateFormat.format(date);
    }
    public static String formatDateExt(Date date) {
    	dateFormat = new SimpleDateFormat(DATE_FORMAT_EXT);
    	return dateFormat.format(date);
    }
    
    /**
     * 格式化Date日期
     *
     * @param date
     * @return
     */
    public static String formatDateHHSS(Date date) {
    	dateFormat = new SimpleDateFormat("HH:mm");
    	return dateFormat.format(date);
    }
    public static String formatDateHHMM(Date date) {
    	dateFormat = new SimpleDateFormat("HHmm");
    	return dateFormat.format(date);
    }
    
    /**
     * 格式化Date日期
     *
     * @param date
     * @return
     */
    public static String formatDateReachDate(Date date) {
        dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 将long值格式化Date日期
     *
     * @param date
     * @return
     */
    public static String formatDate(long date) {
        dateFormat = new SimpleDateFormat(date_format);
        return dateFormat.format(new Date(date));
    }

    /**
     * 将long值格式化指定格式日期
     *
     * @param date
     * @return
     */
    public static String formatDate(long date, String pattern) {
        dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date(date));
    }

    /**
     * 格式化当前字符串形式的日期格式
     *
     * @param date
     * @return
     */
    public static String formatDate(String date, String pattern) throws ParseException {
        dateFormat = new SimpleDateFormat(pattern);
        Date parseDate = dateFormat.parse(date);
        return dateFormat.format(parseDate);
    }

    /**
     * 将字符串日期转换为Date类型
     *
     * @param date
     * @return
     */
    public static Date formatDate(String date) throws ParseException {
        dateFormat = new SimpleDateFormat(date_format);
        return dateFormat.parse(date);
    }
    
    /**
     * 将字符串日期转换为Date类型
     *
     * @param date
     * @return
     */
    public static Date formatDateExt(String dateStr) {
    	if(StringUtils.isBlank(dateStr)){
    		return null ;
    	}
        dateFormat = new SimpleDateFormat(date_format);
        Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			
		}
        return date;
    }

    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }


    public static int getZeroTimestamp() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();

        Long zeroTimestamp = start.getTime() + 24 * 60 * 60 * 1000L;
        int result = (int) ((zeroTimestamp - System.currentTimeMillis()) / 1000);

        return result;
    }

    /**
     * 转换微信的无分隔符日期格式
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatWechatDate(String date) throws ParseException {
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date currentDate = sFormat.parse(date);
        dateFormat = new SimpleDateFormat(date_format);
        return dateFormat.format(currentDate);
    }
    
    
    /**
     * 日期添加对应的分钟数
     * @param date
     * @param minutes
     * @return
     */
    public static Date getAddDate(Date  date,int  minutes)  {
    	Calendar  cal =  Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MINUTE, minutes);
    	return   cal.getTime()  ;
    }
    /**
     * 获取当前天在每周的第几天(例如周一就是第一天，周日是第七天)
     * @return
     */
    public static int getDayOfWeek()  {
    	Calendar  cal =  Calendar.getInstance();
    	Integer   dayIndex =  cal.get(Calendar.DAY_OF_WEEK);
    	if(dayIndex == 1){
    		return 7;
    	}else{
    		return dayIndex - 1 ;
    	}
    }
    
    
    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        if(birthday == null ){
        	return age ;
        }
        
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
           return 0;
        }
    }

    /**
     * 根据出生年份计算年龄
     * @param birthday
     * @return
     */
    public static int getAgeByBirthYear(Date birthday) {
        int age = 0;
        if(birthday == null ){
            return age ;
        }
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
            }
            return age;
        } catch (Exception e) {
            return 0;
        }
    }
    
    /**
     * 获取两个时间之间的秒数
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getDiffSeconds(Date  startTime,Date  endTime)  {
    	if(startTime == null ||  endTime == null){
    		return 0L;
    	}
    	return (endTime.getTime() -  startTime.getTime())/1000;
    }
    
    /**
     * 根据秒数获取倒计时字符串
     * @param second
     * @return
     */
    public static String getDiffSeconds(long  remainStr)  {


    	if(remainStr <= 0){
    		return null ;
    	}
		long hour =  remainStr / 3600 ;
		long minutes =  (remainStr - hour * 3600 ) / 60 ;
		long seconds =  remainStr % 60;
		String  hourStr = null ;
		if(hour == 0){
			hourStr  = "";
		}else if(hour > 0){
			hourStr = hour +":";
		}
		String  minuteStr = null ;
		if(minutes == 0){
			minuteStr  = "00";
		}else if(minutes > 0){
			if(minutes > 9){
				minuteStr = "" +minutes ;
			}else{
				minuteStr = "0" +minutes ;
			}
		}
		
		
		String  secondsStr = null ;
		if(seconds == 0){
			secondsStr  = "00";
		}else if(seconds > 0){
			if(seconds > 9){
				secondsStr = "" +seconds ;
			}else{
				secondsStr = "0" +seconds ;
			}
		}
		
		return  (hourStr +minuteStr + ":"+secondsStr);  
		
		
    }
    
    
    /**
     * 获取每天的最后一秒钟的时刻即：23:59:59
     * @param date
     * @return
     */
    public static   Date  getDayEndTime(Date   date){
    	if(date == null){
    		return null ;
    	}
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.set(Calendar.HOUR_OF_DAY, 23);
    	cal.set(Calendar.MINUTE,59);
    	cal.set(Calendar.SECOND, 59);
    	cal.set(Calendar.MILLISECOND, 0);
    	return cal.getTime();
    }
    
    

}
