package com.example.netlibrary.bean.project.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqSuperviseList extends ReqPageList {
    private EnumConstant.ConstructSupervisionEnum constructSupervisionEnum;
    private String projectApprovalId;

    public EnumConstant.ConstructSupervisionEnum getConstructSupervisionEnum() {
        return constructSupervisionEnum;
    }

    public void setConstructSupervisionEnum(EnumConstant.ConstructSupervisionEnum constructSupervisionEnum) {
        this.constructSupervisionEnum = constructSupervisionEnum;
    }

    public String getProjectApprovalId() {
        return projectApprovalId;
    }

    public void setProjectApprovalId(String projectApprovalId) {
        this.projectApprovalId = projectApprovalId;
    }
}
