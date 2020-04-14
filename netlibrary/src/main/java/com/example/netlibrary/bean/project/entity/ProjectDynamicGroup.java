package com.example.netlibrary.bean.project.entity;

import com.example.commonlibrary.bean.IFlowDynamic;
import com.example.commonlibrary.bean.IFlowDynamicItem;

import java.util.ArrayList;
import java.util.List;

public class ProjectDynamicGroup implements IFlowDynamic {
    private List<IFlowDynamicItem> list;
    private int year;
    private int passIndex;
    private int refuseIndex=-1;

    public ArrayList<IFlowDynamicItem> getList() {
        return (ArrayList<IFlowDynamicItem>) list;
    }

    public void setList(List<IFlowDynamicItem> list) {
        this.list = list;
        setPassIndex(list.size()-1);
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPassIndex() {
        return passIndex;
    }

    public void setPassIndex(int passIndex) {
        this.passIndex = passIndex;
    }

    public void setRefuseIndex(int refuseIndex) {
        this.refuseIndex = refuseIndex;
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
    public void setAbsYear(int year) {
        setYear(year);
    }

    @Override
    public void setAbsList(List<IFlowDynamicItem> list) {
        setList(list);
    }

    @Override
    public int getProgresPassIndex() {
        return passIndex;
    }

    @Override
    public int getRefuseIndex() {
        return refuseIndex;
    }
}
