package com.example.netlibrary.bean.intentional_customer.req;

import com.example.commonlibrary.constant.EnumConstant;
import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqIntentionalCustomerList extends ReqPageList {
    private String keyword;//可以是用户name，或者firstPhone
    private EnumConstant.IntentionalCustomerStatus customerStatus;
    private EnumConstant.Level level;
    private EnumConstant.Source source;
    private int associate;
    private Long startTime;
    private Long endTime;

    public int getAssociate() {
        return associate;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public EnumConstant.IntentionalCustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(EnumConstant.IntentionalCustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }

    public EnumConstant.Level getLevel() {
        return level;
    }

    public void setLevel(EnumConstant.Level level) {
        this.level = level;
    }

    public EnumConstant.Source getSource() {
        return source;
    }

    public void setSource(EnumConstant.Source source) {
        this.source = source;
    }

    public void setAssociate(int associate) {
        this.associate = associate;
    }
}
