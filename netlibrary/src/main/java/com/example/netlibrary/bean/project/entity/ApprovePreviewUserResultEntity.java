package com.example.netlibrary.bean.project.entity;

import com.example.commonlibrary.bean.StaffPerson;
import com.example.netlibrary.bean.approve.entity.FlowBaseEntity;
import com.example.netlibrary.bean.common.entity.ApproveActionEntity;

import java.util.ArrayList;
import java.util.List;

public class ApprovePreviewUserResultEntity {

    List<FlowBaseEntity> defUserTaskList;
    List<StaffPerson> approvers;
    List<ApproveActionEntity> instUserTaskList;

    public List<FlowBaseEntity> getDefUserTaskList() {
        return defUserTaskList;
    }

    public void setDefUserTaskList(List<FlowBaseEntity> defUserTaskList) {
        this.defUserTaskList = defUserTaskList;
    }

    public List<StaffPerson> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<StaffPerson> approvers) {
        this.approvers = approvers;
    }

    public List<ApproveActionEntity> getInstUserTaskList() {
        return instUserTaskList;
    }

    public void setInstUserTaskList(List<ApproveActionEntity> instUserTaskList) {
        this.instUserTaskList = instUserTaskList;
    }

}
