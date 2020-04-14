package com.example.netlibrary.bean.task.req;

import com.example.netlibrary.bean.common.req.ReqFilterPageList;
import com.example.netlibrary.bean.task.entity.BaseWorkTaskEntity;

public class ReqWorkTaskList extends ReqFilterPageList{

    private BaseWorkTaskEntity.TaskStatus taskStatus;

    public BaseWorkTaskEntity.TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(BaseWorkTaskEntity.TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
