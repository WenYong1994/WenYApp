package com.example.netlibrary.bean.refund.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.req.ReqFilterPageList;

public class ReqRefundList extends ReqFilterPageList {

    private EnumConstant.ApproveStatus taskStatus;


    public EnumConstant.ApproveStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(EnumConstant.ApproveStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
