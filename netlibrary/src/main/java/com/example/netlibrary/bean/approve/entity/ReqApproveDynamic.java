package com.example.netlibrary.bean.approve.entity;

import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqApproveDynamic extends ReqPageList {
    private String typeId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

}