package com.qk.party.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 作者：Think
 * 创建于 2017/10/27 10:44
 */

public class DateUtils {
    /**
     * 默认显示日期时间的格式
     */
    static SimpleDateFormat format_Stringtodate = new SimpleDateFormat("MM-dd HH:mm");
    static SimpleDateFormat format_time3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String time(String data){
        try {
            return format_Stringtodate.format(format_time3.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "10-27 10:48";
    }
    public static String time(long data){
        try {
            return format_Stringtodate.format(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "10-27 10:48";
    }
}
