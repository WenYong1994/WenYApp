package com.wheny.whenylibrary.utils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Jimmy on 2016/8/27 0027.
 */
public class StringUtils {

    public static DecimalFormat    df   = new DecimalFormat("######0.00");

    /**
     * 获取随机字符串
     *
     * @param length 字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijkmnopqrstuvwxyz0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static boolean isEmpty(String string){
        if(null == string || "".equals(string)){
            return true;
        }
        return false;
    }



    public static String checkEmpty(String string){
        if(isEmpty(string)){
            return "";
        }
        return string;
    }


    public static String getString(String s){
        if(isEmpty(s)){
            return null;
        }else {
            return s;
        }
    }


    public static String getNotNullString2(String s){
        if(isEmpty(s)){
            return " ";
        }else {
            return s;
        }
    }

    public static String getNotNullString3(String s){
        if(isEmpty(s)){
            return "";
        }else {
            return s;
        }
    }
    public static String Obj2NotNullString2(Object s){
        if(s == null){
            return " ";
        }else {
            return s.toString();
        }
    }

    public static String Obj2NotNullString3(Object s){
        if(s == null){
            return "";
        }else {
            return s.toString();
        }
    }



    public static String numberToCH(int intInput) {
        String si = String.valueOf(intInput);
        String sd = "";
        if (si.length() == 1) // 個
        {
            sd += GetCH(intInput);
            return sd;
        } else if (si.length() == 2)// 十
        {
            if (si.substring(0, 1).equals("1"))
                sd += "十";
            else
                sd += (GetCH(intInput / 10) + "十");
            sd += numberToCH(intInput % 10);
        } else if (si.length() == 3)// 百
        {
            sd += (GetCH(intInput / 100) + "百");
            if (String.valueOf(intInput % 100).length() < 2)
                sd += "零";
            sd += numberToCH(intInput % 100);
        } else if (si.length() == 4)// 千
        {
            sd += (GetCH(intInput / 1000) + "千");
            if (String.valueOf(intInput % 1000).length() < 3)
                sd += "零";
            sd += numberToCH(intInput % 1000);
        } else if (si.length() == 5)// 萬
        {
            sd += (GetCH(intInput / 10000) + "萬");
            if (String.valueOf(intInput % 10000).length() < 4)
                sd += "零";
            sd += numberToCH(intInput % 10000);
        }

        return sd;
    }
    private static String GetCH(int input) {
        String sd = "";
        switch (input) {
            case 1:
                sd = "一";
                break;
            case 2:
                sd = "二";
                break;
            case 3:
                sd = "三";
                break;
            case 4:
                sd = "四";
                break;
            case 5:
                sd = "五";
                break;
            case 6:
                sd = "六";
                break;
            case 7:
                sd = "七";
                break;
            case 8:
                sd = "八";
                break;
            case 9:
                sd = "九";
                break;
            default:
                break;
        }
        return sd;
    }

    /**
     *
     * @param d 浮点数
     *
     * @return 返回保留两位小数
     *
     * */
    public static String formatDoulbe(double d){
        if(d == 0){
            return " ";
        }
        return df.format(d);
    }

    /**
     *
     * @param f 浮点数
     *
     * @return 返回保留两位小数
     *
     * */
    public static String formatFloat(float f){
        if(f == 0){
            return "0";
        }
        return df.format(f);
    }

    /**
     *
     * @param i 浮点数
     *
     * @return 返回保留两位小数
     *
     * */
    public static String formatInt(int i){
        if(i == 0){
            return "0";
        }
        return String.valueOf(i);
    }

    /**
     *
     * @param i 浮点数
     *
     * @return 返回保留两位小数
     *
     * */
    public static String formatInt2(int i){
        if(i == 0){
            return " ";
        }
        return String.valueOf(i);
    }

    /**
     *
     * @param f 浮点数
     *
     * @return 返回保留两位小数
     *
     * */
    public static String formatDoulbe(float f){
        if(f == 0){
            return " ";
        }
        return df.format(f);
    }
    /**
     *
     * @param f 浮点数
     *
     * @return 返回保留两位小数
     *
     * */
    public static String formatDouble2(float f){
        if(f == 0){
            return "0.00";
        }
        return df.format(f);
    }

    /**
     *
     * @param f 浮点数
     *
     * @return 返回保留两位小数
     *
     * */
    public static String formatAmountWan(float f){
        f = f/10000;//后台是元，需要需要先除1000转换为 单位 wan
        if(f == 0){
            return "0";
        }
        return df.format(f);
    }

    /**
     *
     * @param d 整型
     *
     * @return 返回保留两位小数
     *
     * */
    public static String valueOf(int d){
        return String.valueOf(d);
    }

    /**
     *
     *
     * */
    public static String valueOfNull(String s){
        return StringUtils.isEmpty(s)?null:s;
    }


    public static boolean isPhone(String phone) {
        if(phone.length() == 11&&phone.startsWith("1")){
            return true;
        }
        return false;
    }
}
