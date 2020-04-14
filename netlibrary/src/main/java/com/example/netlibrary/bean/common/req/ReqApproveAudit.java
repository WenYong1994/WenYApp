package com.example.netlibrary.bean.common.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;

public class ReqApproveAudit extends BaseRequest {
    private String id;
    private String comment;
    private EnumConstant.ApprovalResultTypeEnum approvalResultTypeEnum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public EnumConstant.ApprovalResultTypeEnum getApprovalResultTypeEnum() {
        return approvalResultTypeEnum;
    }

    public void setApprovalResultTypeEnum(EnumConstant.ApprovalResultTypeEnum approvalResultTypeEnum) {
        this.approvalResultTypeEnum = approvalResultTypeEnum;
    }

}
