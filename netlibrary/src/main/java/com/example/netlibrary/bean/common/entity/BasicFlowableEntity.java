package com.example.netlibrary.bean.common.entity;

import com.example.commonlibrary.bean.StaffPerson;
import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;

public class BasicFlowableEntity extends BaseRequest {
    //当前任务ID
    private String curTaskId;
    //当前任务名称
    private String curTaskName;
    //任务状态
    private EnumConstant.ApproveStatus taskStatus = EnumConstant.ApproveStatus.to_be_audited;
    //流程实例ID（启动流程后产生此ID）
    private String processInstId;
    //流程定义ID（模版ID）
    private String processDefId;
    //执行ID
    private String executionId;
    //当前处理人
    private String curAssignee;

    private Long taskUpdateTime;

    private Long submitAt;
    private long createAt;



    private EnumConstant.FlowableGroup flowableGroup;//审批类型

    private StaffPerson initiator;//发起人

    private String rid;//关联ID

    private EnumConstant.ApproveAllStatus approvalStatus = EnumConstant.ApproveAllStatus.to_be_audited;

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public EnumConstant.ApproveAllStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(EnumConstant.ApproveAllStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public StaffPerson getInitiator() {
        return initiator;
    }

    public void setInitiator(StaffPerson initiator) {
        this.initiator = initiator;
    }

    public void setTaskUpdateTime(Long taskUpdateTime) {
        this.taskUpdateTime = taskUpdateTime;
    }

    public Long getSubmitAt() {
        return submitAt==null?0:submitAt;
    }

    public void setSubmitAt(Long submitAt) {
        this.submitAt = submitAt;
    }

    public EnumConstant.FlowableGroup getFlowableGroup() {
        return flowableGroup;
    }

    public void setFlowableGroup(EnumConstant.FlowableGroup flowableGroup) {
        this.flowableGroup = flowableGroup;
    }

    public String getCurTaskId() {
        return curTaskId;
    }

    public void setCurTaskId(String curTaskId) {
        this.curTaskId = curTaskId;
    }

    public String getCurTaskName() {
        return curTaskName;
    }

    public void setCurTaskName(String curTaskName) {
        this.curTaskName = curTaskName;
    }

    public EnumConstant.ApproveStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(EnumConstant.ApproveStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public void setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getCurAssignee() {
        return curAssignee;
    }

    public void setCurAssignee(String curAssignee) {
        this.curAssignee = curAssignee;
    }

    public long getTaskUpdateTime() {
        return taskUpdateTime;
    }

    public void setTaskUpdateTime(long taskUpdateTime) {
        this.taskUpdateTime = taskUpdateTime;
    }
}
