package com.example.netlibrary.bean.project.entity;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.entity.Locale;

import java.io.Serializable;

public class ProjectListItemEntity implements Serializable {
    private String intentionalCustomerId;
    private String shopName;
    private String id;
    private Locale locale;
    private int userId;
    private String customerName;
    private EnumConstant.ConstructSupervisionEnum constructSupervisionEnum;
    private EnumConstant.ConstructStatus constructStatus;
    private EnumConstant.DrawingSheetEnum drawingSheetEnum;
    private long createAt;
    private String username;

    private String processDefId;
    private String processInstId;


    public String getProcessDefId() {
        return processDefId;
    }

    public void setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
    }

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    private EnumConstant.ApproveStatus taskStatus;

    public EnumConstant.ApproveStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(EnumConstant.ApproveStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setIntentionalCustomerId(String intentionalCustomerId) {
        this.intentionalCustomerId = intentionalCustomerId;
    }
    public String getIntentionalCustomerId() {
        return intentionalCustomerId;
    }


    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getShopName() {
        return shopName;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    public Locale getLocale() {
        return locale;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }

    public void setConstructStatus(EnumConstant.ConstructStatus constructStatus) {
        this.constructStatus = constructStatus;
    }
    public EnumConstant.ConstructStatus getConstructStatus() {
        return constructStatus;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }



    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
    public long getCreateAt() {
        return createAt;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public EnumConstant.ConstructSupervisionEnum getConstructSupervisionEnum() {
        return constructSupervisionEnum;
    }

    public void setConstructSupervisionEnum(EnumConstant.ConstructSupervisionEnum constructSupervisionEnum) {
        this.constructSupervisionEnum = constructSupervisionEnum;
    }

    public EnumConstant.DrawingSheetEnum getDrawingSheetEnum() {
        return drawingSheetEnum;
    }

    public void setDrawingSheetEnum(EnumConstant.DrawingSheetEnum drawingSheetEnum) {
        this.drawingSheetEnum = drawingSheetEnum;
    }
}