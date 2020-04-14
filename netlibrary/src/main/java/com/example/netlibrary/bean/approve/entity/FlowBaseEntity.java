package com.example.netlibrary.bean.approve.entity;

public class FlowBaseEntity {

    private String taskName;
    private int assignee;


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getAssignee() {
        return assignee;
    }

    public void setAssignee(int assignee) {
        this.assignee = assignee;
    }
}
