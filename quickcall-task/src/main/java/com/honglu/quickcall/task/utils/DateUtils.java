package com.honglu.quickcall.task.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    static SimpleDateFormat dateFormat = null;

    static final String date_format = "yyyy-MM-dd HH:mm:ss";

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
     * @throws ParseException 
     */
    public static Date getDste(String date) throws ParseException {
        dateFormat = new SimpleDateFormat(date_format);       
		return dateFormat.parse(date);
		
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
     * 格式化Date日期
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        dateFormat = new SimpleDateFormat(date_format);
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
    
    public static Date getNextDay(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();  
        return date;  
    } 
}
