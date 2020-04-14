package com.example.netlibrary.bean.project.entity;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.approve.entity.FlowBaseEntity;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.common.entity.Attachment;
import com.example.netlibrary.bean.common.entity.BasicFlowableEntity;

import java.util.List;

public class BaseChargeEntity extends BasicFlowableEntity {
    private EnumConstant.ChargeType type;
    private String intentionalCustomerId;//关联意向客户ID
    private String customerName;//客户姓名
    private String projectApprovalId;//关联立项书ID
    private String sn;//订单号
    private String title;//订单标题，店铺名称
    private double totalPrice;//实收服务费金额 收款金额
    private double actualPrice;//应收服务费金额
    private EnumConstant.PayType payType = EnumConstant.PayType.cash;//支付方式和付款凭证：必填项，支付方式分为：现金、银行转账、刷卡、微信、支付宝
    private String note;//备注
    private List<Attachment> vouchers;//付款凭证
    private List<Attachment> sampleList;//样品清单



    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public EnumConstant.ChargeType getType() {
        return type;
    }

    public void setType(EnumConstant.ChargeType type) {
        this.type = type;
    }

    public String getIntentionalCustomerId() {
        return intentionalCustomerId;
    }

    public void setIntentionalCustomerId(String intentionalCustomerId) {
        this.intentionalCustomerId  = intentionalCustomerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectApprovalId() {
        return projectApprovalId;
    }

    public void setProjectApprovalId(String projectApprovalId) {
        this.projectApprovalId = projectApprovalId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public EnumConstant.PayType getPayType() {
        return payType;
    }

    public void setPayType(EnumConstant.PayType payType) {
        this.payType = payType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Attachment> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Attachment> vouchers) {
        this.vouchers = vouchers;
    }

    public List<Attachment> getSampleList() {
        return sampleList;
    }

    public void setSampleList(List<Attachment> sampleList) {
        this.sampleList = sampleList;
    }
}
