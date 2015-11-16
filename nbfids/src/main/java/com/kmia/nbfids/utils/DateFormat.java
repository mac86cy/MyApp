package com.kmia.nbfids.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015/11/15 17:57
 *  *
 *  * 类说明：日期格式工具类
 *  
 */
@SuppressLint("SimpleDateFormat")
public class DateFormat {
    public static String getDateCN() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        return format.format(new Date(System.currentTimeMillis()));// 2012年10月03日 23:41:31
    }

    public static String getDateEN() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format1.format(new Date(System.currentTimeMillis()));// 2012-10-03 23:41:31
    }

    public static Date getDateToday() {// 当日起始，用于运营日
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    public static Date getDateThree() {// 3天前
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR); // 获取年份
        int month = cal.get(Calendar.MONTH); // 获取月份
        int day = cal.get(Calendar.DATE); // 获取日期
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day - 3); // 设置到每月的第一天
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String getTestDate(Date now) {// 测试用于显示日期和时间
        SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm");
        String date = "";
        if (now != null) {
            date = format.format(now);
        }
        return date;// 10/03 23:41
    }

    public static String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(System.currentTimeMillis()));
    }

    public static String getDate(Date now) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String date = "";
        if (now != null) {
            date = format.format(now);
        }
        return date;
    }

    public static Date addHour(Date now, int hour) {// 当前时间+多少小时
        return new Date(now.getTime() + hour * 60 * 60 * 1000);
    }

    public static void main(String args[]) {
        System.out.println(getDateToday());
    }
}
