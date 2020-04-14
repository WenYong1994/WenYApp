package com.example.netlibrary.bean.refund.entity;

import com.example.commonlibrary.bean.StaffPerson;
import com.example.netlibrary.bean.common.entity.Attachment;
import com.example.netlibrary.bean.common.entity.BasicFlowableEntity;

import java.util.List;

public class BaseRefundEntity extends BasicFlowableEntity {

    private String id;
    private String avatar;//头像
    private String title;//标题
    private String customerId;//客户ID
    private String customerName;//客户姓名
    private double refundPrice;//退款金额
    private List<Attachment> attachments;//附件
    private String note;//备注
    private List<StaffPerson> ccList;

    public List<StaffPerson> getCcList() {
        return ccList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCcList(List<StaffPerson> ccList) {
        this.ccList = ccList;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(double refundPrice) {
        this.refundPrice = refundPrice;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}

