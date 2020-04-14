package com.example.netlibrary.bean.task.req;

import com.example.netlibrary.bean.task.entity.BaseWorkTaskEntity;

public class ReqEditWorkTask extends BaseWorkTaskEntity {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
