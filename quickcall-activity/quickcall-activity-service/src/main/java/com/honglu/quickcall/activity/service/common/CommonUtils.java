package com.honglu.quickcall.activity.service.common;

import com.honglu.quickcall.common.api.util.DateUtils;

import java.util.*;

public class CommonUtils {
    /**
     * 获取日期列表
     *
     * @param currentTime
     * @param days
     * @return
     */
    public static Map<String, String> getDateMap(String currentTime, Integer days) {
        Map<String, String> dateList = new HashMap<String, String>();
        try {
            long current = DateUtils.formatDate(currentTime).getTime();
            for (int i = 1; i <= days; i++) {
                long date = current - DateUtils.DAY * i;
                dateList.put(DateUtils.formatDate(date, "yyyy-MM-dd"), "value");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateList;
    }

    /**
     * 获取日期列表
     *
     * @param startTime
     * @param days
     * @return
     */
    public static List<String> getDateList(String startTime, Integer days) {
        List<String> dateList = new ArrayList<String>();
        try {
            long current = DateUtils.formatDate(startTime + " 00:00:00").getTime();
            for (int i = 0; i < days; i++) {
                long date = current + DateUtils.DAY * i;
                dateList.add(DateUtils.formatDate(date, "yyyy-MM-dd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateList;
    }

    /**
     * 计算两个日期的天数差
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDateDiffByDay(Date startDate, Date endDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(startDate);
        c2.setTime(endDate);

        return c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
    }
}
