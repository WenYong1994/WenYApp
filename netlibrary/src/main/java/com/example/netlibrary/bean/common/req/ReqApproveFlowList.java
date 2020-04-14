package com.example.netlibrary.bean.common.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;

public class ReqApproveFlowList extends BaseRequest {
    private EnumConstant.FlowableGroup flowableGroup;

    public EnumConstant.FlowableGroup getFlowableGroup() {
        return flowableGroup;
    }

    public void setFlowableGroup(EnumConstant.FlowableGroup flowableGroup) {
        this.flowableGroup = flowableGroup;
    }
}
