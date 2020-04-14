package com.example.netlibrary.bean.project.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.commonlibrary.constant.JMJConstant;
import com.example.netlibrary.bean.common.req.ReqPageList;
import com.example.netlibrary.bean.task.entity.BaseWorkTaskEntity;

public class ReqProjectList extends ReqPageList {

    private String keyword;
    private Long beginDate;
    private Long endData;
    private EnumConstant.ApproveStatus taskStatus;
    private EnumConstant.ConstructStatus constructStatus;
    private EnumConstant.DrawingSheetEnum drawingSheetEnum;
    private EnumConstant.ConstructSupervisionEnum constructSupervisionEnum;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Long beginDate) {
        this.beginDate = beginDate;
    }

    public Long getEndData() {
        return endData;
    }

    public void setEndData(Long endData) {
        this.endData = endData;
    }

    public EnumConstant.ApproveStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(EnumConstant.ApproveStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public EnumConstant.ConstructStatus getConstructStatus() {
        return constructStatus;
    }

    public void setConstructStatus(EnumConstant.ConstructStatus constructStatus) {
        this.constructStatus = constructStatus;
    }

    public EnumConstant.DrawingSheetEnum getDrawingSheetEnum() {
        return drawingSheetEnum;
    }

    public void setDrawingSheetEnum(EnumConstant.DrawingSheetEnum drawingSheetEnum) {
        this.drawingSheetEnum = drawingSheetEnum;
    }

    public EnumConstant.ConstructSupervisionEnum getConstructSupervisionEnum() {
        return constructSupervisionEnum;
    }

    public void setConstructSupervisionEnum(EnumConstant.ConstructSupervisionEnum constructSupervisionEnum) {
        this.constructSupervisionEnum = constructSupervisionEnum;
    }
}
