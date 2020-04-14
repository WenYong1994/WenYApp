package com.example.netlibrary.bean.home.entity;

import com.example.commonlibrary.constant.EnumConstant;

public class WaitApproveEntity {

    private EnumConstant.FlowableGroup flowableGroup;
    private int count;

    public EnumConstant.FlowableGroup getFlowableGroup() {
        return flowableGroup;
    }

    public void setFlowableGroup(EnumConstant.FlowableGroup flowableGroup) {
        this.flowableGroup = flowableGroup;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
