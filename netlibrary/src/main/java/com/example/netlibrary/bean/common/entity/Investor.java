package com.example.netlibrary.bean.common.entity;

public class Investor {

    private String investor;
    private String investorPhone;
    private Float investorRatio;
    private boolean investorManage;

    public void setInvestor(String investor) {
        this.investor = investor;
    }
    public String getInvestor() {
        return investor;
    }

    public void setInvestorPhone(String investorPhone) {
        this.investorPhone = investorPhone;
    }
    public String getInvestorPhone() {
        return investorPhone;
    }

    public void setInvestorRatio(Float investorRatio) {
        this.investorRatio = investorRatio;
    }
    public Float getInvestorRatio() {
        return investorRatio;
    }

    public void setInvestorManage(boolean investorManage) {
        this.investorManage = investorManage;
    }
    public boolean getInvestorManage() {
        return investorManage;
    }

}