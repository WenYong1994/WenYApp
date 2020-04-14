package com.jeek.calendar.widget.calendar;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HintHelper  {
    private static Map<String,HintHelper> helperMap = new HashMap<>();

    public static HintHelper getHintHelper(String tag){
        synchronized (helperMap){
            HintHelper hintHelper = helperMap.get(tag);
            if(hintHelper == null){
                hintHelper = new HintHelper();
                helperMap.put(tag,hintHelper);
            }
            return hintHelper;
        }
    }

    public HintHelper() {
        yearMonthDayMap = new HashMap<>();
    }

    private Map<Integer,Map<Integer,List<Integer>>> yearMonthDayMap;

    public List<Integer> getHintList(int year,int month){
        List<Integer> list = new ArrayList<>();
        Map<Integer, List<Integer>> monthDayMap =yearMonthDayMap.get(year);
        if(monthDayMap==null){
            return list;
        }
        List<Integer> dayList = monthDayMap.get(month);
        if(dayList!=null){
            return dayList;
        }
        return list;
    }

    /**
     *
     * @param month 1-12
     *
     * */
    @SuppressLint("UseSparseArrays")
    public void addHint(int year, int month, int day){
        month = month -1;
        Map<Integer, List<Integer>> monthDayMap =yearMonthDayMap.get(year);
        if(monthDayMap==null){
            monthDayMap = new HashMap<>();
            yearMonthDayMap.put(year,monthDayMap);
        }
        List<Integer> dayList = monthDayMap.get(month);
        if(dayList==null){
            dayList = new ArrayList<>();
            monthDayMap.put(month,dayList);
        }
        if(!dayList.contains(day)){
            dayList.add(day);
        }
    }

    public void removeHint(int year, int month, int day){
        Map<Integer, List<Integer>> monthDayMap =yearMonthDayMap.get(year);
        if(monthDayMap==null){
            monthDayMap = new HashMap<>();
            yearMonthDayMap.put(year,monthDayMap);
        }
        List<Integer> dayList = monthDayMap.get(month);
        if(dayList==null){
            dayList = new ArrayList<>();
            monthDayMap.put(month,dayList);
        }
        if(!dayList.contains(day)){
            dayList.remove(day);
        }
    }

    public void removeAllHint(){
        for(Integer year:yearMonthDayMap.keySet()){
            yearMonthDayMap.remove(year);
        }

    }








}
