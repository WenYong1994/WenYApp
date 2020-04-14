package com.example.netlibrary.bean.work_report.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;

public class ReqAuditWorkReport extends BaseRequest {
    private String id;
    private EnumConstant.WorkReportReadStatus approvalStatus = EnumConstant.WorkReportReadStatus.to_be_audited;
    private String comment ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EnumConstant.WorkReportReadStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(EnumConstant.WorkReportReadStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
