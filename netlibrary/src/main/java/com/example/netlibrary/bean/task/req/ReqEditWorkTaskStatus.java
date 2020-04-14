package com.example.netlibrary.bean.task.req;

import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.task.entity.BaseWorkTaskEntity;
import com.example.netlibrary.bean.task.entity.WorkTaskEntity;

public class ReqEditWorkTaskStatus  extends BaseRequest {

    private String id;

    private WorkTaskEntity.TaskStatus taskStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WorkTaskEntity.TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(WorkTaskEntity.TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
