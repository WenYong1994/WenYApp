package com.example.commonlibrary.bean;

import com.example.commonlibrary.constant.EnumConstant;

public interface IFlowDynamicItem {

    String getAbsTitle();

    String getAbsContent();

    String getAbsHanlder();

    default EnumConstant.FollowType getFollowType(){
        return null;
    }

    long getAbsTime();


}
