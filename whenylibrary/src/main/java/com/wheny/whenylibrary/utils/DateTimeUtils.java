package com.wheny.whenylibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    public static final  String formatStr = "yyyy-MM-dd HH:mm:ss";

    public static String format(long data, String format){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(data);
        }catch (Exception e){
            return "";
        }
    }

    public static String format(Date data, String format){
        return format(data.getTime(),format);
    }

    public static String format(long data){
        return format(data,formatStr);
    }

    public static String formatTimeStr(long time){
        int day = (int) (time/(60*60*24));//获取天
        time = time%(60*60*24);//获取剩余小时的秒数
        int hour = (int) (time/(60*60));//获取小时
        time = time%(60*60);//获取剩余分钟的秒数
        int minute = (int) (time/(60));
        String str = "";
        if(day!=0){
            str = day+"天";
        }
        if(hour!=0){
            str = hour+"小时";
        }
        if(minute!=0){
            str = minute+"分钟";
        }
        return str;
    }



    public static String formatAlertStr(long time){
        if(time<0){//不提醒
            return "不提醒";
        }else if (time == 0){
            return "准时提醒";
        }else {
            return "提前"+formatTimeStr(time);
        }



    }



}
