package com.example.netlibrary.bean.common.entity;

import com.example.commonlibrary.constant.EnumConstant;

public class ApproveActionEntity {

    private String workTimeInMillis;
    private String historyTaskId;
    private String name;
    private EnumConstant.ApprovalResultTypeEnum commentType;
    private long startTime;
    private String endTime;
    private String assignee;
    public void setWorkTimeInMillis(String workTimeInMillis) {
        this.workTimeInMillis = workTimeInMillis;
    }
    public String getWorkTimeInMillis() {
        return workTimeInMillis;
    }

    public void setHistoryTaskId(String historyTaskId) {
        this.historyTaskId = historyTaskId;
    }
    public String getHistoryTaskId() {
        return historyTaskId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public EnumConstant.ApprovalResultTypeEnum getCommentType() {
        return commentType;
    }

    public void setCommentType(EnumConstant.ApprovalResultTypeEnum commentType) {
        this.commentType = commentType;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public long getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getEndTime() {
        return endTime;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public String getAssignee() {
        return assignee;
    }

}