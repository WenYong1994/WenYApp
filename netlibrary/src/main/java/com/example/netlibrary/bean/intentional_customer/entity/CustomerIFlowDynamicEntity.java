package com.example.netlibrary.bean.intentional_customer.entity;

import com.example.commonlibrary.bean.IFlowDynamic;
import com.example.commonlibrary.bean.IFlowDynamicItem;

import java.util.ArrayList;
import java.util.List;

public class CustomerIFlowDynamicEntity implements IFlowDynamic {
    List<IFlowDynamicItem> list;
    int year;

    public List<IFlowDynamicItem> getList() {
        return list;
    }

    public void setList(ArrayList<IFlowDynamicItem> list) {
        this.list = list;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void setAbsYear(int year) {
        setYear(year);
    }

    @Override
    public void setAbsList(List<IFlowDynamicItem> list) {
        this.list= list;
    }

    @Override
    public List<IFlowDynamicItem> getAbsList() {
        return  list;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getProgresPassIndex() {
        return list==null?-1:list.size()-1;
    }

}
