package com.example.netlibrary.bean.project.req;

import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.project.entity.BaseTrainingInvitationEntity;

public class ReqAddTrainingInvitation extends BaseTrainingInvitationEntity {
    private String projectApprovalId;

    public String getProjectApprovalId() {
        return projectApprovalId;
    }

    public void setProjectApprovalId(String projectApprovalId) {
        this.projectApprovalId = projectApprovalId;
    }
}
