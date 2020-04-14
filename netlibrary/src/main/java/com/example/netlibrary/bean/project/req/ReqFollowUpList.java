package com.example.netlibrary.bean.project.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqFollowUpList extends ReqPageList {
    private String projectApprovalId;
    private EnumConstant.ConstructSupervisionEnum constructSupervisionEnum;

    public String getProjectApprovalId() {
        return projectApprovalId;
    }

    public void setProjectApprovalId(String projectApprovalId) {
        this.projectApprovalId = projectApprovalId;
    }

    public EnumConstant.ConstructSupervisionEnum getConstructSupervisionEnum() {
        return constructSupervisionEnum;
    }

    public void setConstructSupervisionEnum(EnumConstant.ConstructSupervisionEnum constructSupervisionEnum) {
        this.constructSupervisionEnum = constructSupervisionEnum;
    }
}
