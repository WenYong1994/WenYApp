package com.example.netlibrary.bean.project.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;

public class ReqChangeConstructionStatus extends BaseRequest {

    private String projectApprovalId;
    private EnumConstant.ConstructStatus constructStatus;


    public String getProjectApprovalId() {
        return projectApprovalId;
    }

    public void setProjectApprovalId(String projectApprovalId) {
        this.projectApprovalId = projectApprovalId;
    }

    public void setConstructStatus(EnumConstant.ConstructStatus constructStatus) {
        this.constructStatus = constructStatus;
    }
}
