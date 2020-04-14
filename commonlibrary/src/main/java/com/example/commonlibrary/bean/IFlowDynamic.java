package com.example.commonlibrary.bean;

import com.example.commonlibrary.constant.EnumConstant;

import java.util.List;

public interface IFlowDynamic {

    List<IFlowDynamicItem> getAbsList();

    int getYear();

    void setAbsYear(int year);

    void setAbsList(List<IFlowDynamicItem> list);


    default int getProgresPassIndex(){
        return -1;
    }

    default int getRefuseIndex(){
        return -1;
    }

    interface ItemCreator{
        IFlowDynamic create();
    }

}
