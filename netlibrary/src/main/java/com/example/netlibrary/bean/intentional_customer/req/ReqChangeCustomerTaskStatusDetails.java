package com.example.netlibrary.bean.intentional_customer.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;

public class ReqChangeCustomerTaskStatusDetails extends BaseReqCustomerTask {
    private EnumConstant.TaskStatusEnum taskStatus;

    public EnumConstant.TaskStatusEnum getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(EnumConstant.TaskStatusEnum taskStatus) {
        this.taskStatus = taskStatus;
    }
}
