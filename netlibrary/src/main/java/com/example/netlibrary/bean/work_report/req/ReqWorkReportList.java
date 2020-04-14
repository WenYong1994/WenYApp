package com.example.netlibrary.bean.work_report.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.req.ReqFilterPageList;
import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqWorkReportList extends ReqFilterPageList {
    private EnumConstant.WorkListType listType = EnumConstant.WorkListType.me;
    private EnumConstant.WorkReportType type;
    private EnumConstant.WorkReportReadStatus approvalStatus;

    public EnumConstant.WorkReportReadStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(EnumConstant.WorkReportReadStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public EnumConstant.WorkReportType getType() {
        return type;
    }

    public void setType(EnumConstant.WorkReportType type) {
        this.type = type;
    }

    public EnumConstant.WorkListType getListType() {
        return listType;
    }

    public void setListType(EnumConstant.WorkListType listType) {
        this.listType = listType;
    }
}
