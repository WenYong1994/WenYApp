package com.example.netlibrary.bean.work_report.entity;

import com.example.commonlibrary.bean.StaffPerson;
import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Attachment;

import java.util.List;

public class BaseWorkReportEntity extends BaseRequest {

    private String id;

    private long createAt;

    private long updateAt;

    private long top;

    private String userAvatar;//用户头像
    private long date;//汇报时间
    private String completeWork;//完成工作
    private String workSituation;//工作疑难及解决方案
    private String unCompleteWork;//未完成工作
    private String workPlan;//工作计划
    private List<Attachment> attachments;//附件
    private EnumConstant.WorkReportType type;
    private List<StaffPerson> ccList;//抄送人（说明：目前预设抄送人为吴总和直接主管）
    private String comment;//批阅评语
    private EnumConstant.WorkReportReadStatus approvalStatus = EnumConstant.WorkReportReadStatus.to_be_audited;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public long getTop() {
        return top;
    }

    public void setTop(long top) {
        this.top = top;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getCompleteWork() {
        return completeWork;
    }

    public void setCompleteWork(String completeWork) {
        this.completeWork = completeWork;
    }

    public String getWorkSituation() {
        return workSituation;
    }

    public void setWorkSituation(String workSituation) {
        this.workSituation = workSituation;
    }

    public String getUnCompleteWork() {
        return unCompleteWork;
    }

    public void setUnCompleteWork(String unCompleteWork) {
        this.unCompleteWork = unCompleteWork;
    }

    public String getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(String workPlan) {
        this.workPlan = workPlan;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public EnumConstant.WorkReportType getType() {
        return type;
    }

    public void setType(EnumConstant.WorkReportType type) {
        this.type = type;
    }

    public List<StaffPerson> getCcList() {
        return ccList;
    }

    public void setCcList(List<StaffPerson> ccList) {
        this.ccList = ccList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public EnumConstant.WorkReportReadStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(EnumConstant.WorkReportReadStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
