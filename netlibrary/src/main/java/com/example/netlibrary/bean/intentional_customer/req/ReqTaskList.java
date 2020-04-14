package com.example.netlibrary.bean.intentional_customer.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqTaskList extends ReqPageList {

    private String customerId;
    private EnumConstant.TaskStatusEnum taskStatus;


    public EnumConstant.TaskStatusEnum getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(EnumConstant.TaskStatusEnum taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
