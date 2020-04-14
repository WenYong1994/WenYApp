package com.example.netlibrary.bean.project.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Attachment;

import java.util.List;

public class ReqSubmitDrawing extends BaseRequest {
    private String projectApprovalId;
    private EnumConstant.DrawingSheetEnum drawingSheetEnum;
    private long beginDate;
    private long endDate;
    private List<Attachment> attachments;
    private boolean redesign;


    public String getProjectApprovalId() {
        return projectApprovalId;
    }

    public void setProjectApprovalId(String projectApprovalId) {
        this.projectApprovalId = projectApprovalId;
    }

    public EnumConstant.DrawingSheetEnum getDrawingSheetEnum() {
        return drawingSheetEnum;
    }

    public void setDrawingSheetEnum(EnumConstant.DrawingSheetEnum drawingSheetEnum) {
        this.drawingSheetEnum = drawingSheetEnum;
    }

    public long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public boolean isRedesign() {
        return redesign;
    }

    public void setRedesign(boolean redesign) {
        this.redesign = redesign;
    }
}
