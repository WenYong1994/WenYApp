package com.example.netlibrary.bean.intentional_customer.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.body.BaseRequest;

public class ReqTransferCustomer extends BaseRequest {
    private String customerId;
    private int partnerId;
    private String partnerName;
    private String partnerDepartment;
    private EnumConstant.FollowType followType;
    private String note;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerDepartment() {
        return partnerDepartment;
    }

    public void setPartnerDepartment(String partnerDepartment) {
        this.partnerDepartment = partnerDepartment;
    }

    public EnumConstant.FollowType getFollowType() {
        return followType;
    }

    public void setFollowType(EnumConstant.FollowType followType) {
        this.followType = followType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
