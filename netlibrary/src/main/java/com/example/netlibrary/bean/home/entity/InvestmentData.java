package com.example.netlibrary.bean.home.entity;


public class InvestmentData {

    private int departmentId;
    private String departmentName;
    private float serviceFeeTotal;//服务费收款
    private int sincerityPriceCount;  //诚意金个数
    private int projectApprovalCount; //立项个数
    private float securityDepositTotal;//保证金收款
    private float sincerityPriceTotal;//诚意金收款
    private float softDecorationTotal;//软装收款
    private float sampleTotal;//样品收款
    private float hardDecorationTotal;//硬装收款
    private int customerInspectNum;//到店人数
    private int customerInviteNum;//邀约人数
    private int visitCustomerNum; //拜访人数


    public float getHardDecorationTotal() {
        return hardDecorationTotal;
    }

    public void setHardDecorationTotal(float hardDecorationTotal) {
        this.hardDecorationTotal = hardDecorationTotal;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public float getServiceFeeTotal() {
        return serviceFeeTotal;
    }

    public void setServiceFeeTotal(float serviceFeeTotal) {
        this.serviceFeeTotal = serviceFeeTotal;
    }

    public int getSincerityPriceCount() {
        return sincerityPriceCount;
    }

    public void setSincerityPriceCount(int sincerityPriceCount) {
        this.sincerityPriceCount = sincerityPriceCount;
    }

    public int getProjectApprovalCount() {
        return projectApprovalCount;
    }

    public void setProjectApprovalCount(int projectApprovalCount) {
        this.projectApprovalCount = projectApprovalCount;
    }

    public float getSecurityDepositTotal() {
        return securityDepositTotal;
    }

    public void setSecurityDepositTotal(float securityDepositTotal) {
        this.securityDepositTotal = securityDepositTotal;
    }

    public float getSincerityPriceTotal() {
        return sincerityPriceTotal;
    }

    public void setSincerityPriceTotal(float sincerityPriceTotal) {
        this.sincerityPriceTotal = sincerityPriceTotal;
    }

    public float getSoftDecorationTotal() {
        return softDecorationTotal;
    }

    public void setSoftDecorationTotal(float softDecorationTotal) {
        this.softDecorationTotal = softDecorationTotal;
    }

    public float getSampleTotal() {
        return sampleTotal;
    }

    public void setSampleTotal(float sampleTotal) {
        this.sampleTotal = sampleTotal;
    }

    public int getCustomerInspectNum() {
        return customerInspectNum;
    }

    public void setCustomerInspectNum(int customerInspectNum) {
        this.customerInspectNum = customerInspectNum;
    }


    public int getCustomerInviteNum() {
        return customerInviteNum;
    }

    public void setCustomerInviteNum(int customerInviteNum) {
        this.customerInviteNum = customerInviteNum;
    }

    public int getVisitCustomerNum() {
        return visitCustomerNum;
    }

    public void setVisitCustomerNum(int visitCustomerNum) {
        this.visitCustomerNum = visitCustomerNum;
    }




}