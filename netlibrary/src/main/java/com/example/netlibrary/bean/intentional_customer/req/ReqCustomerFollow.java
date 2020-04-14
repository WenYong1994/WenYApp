package com.example.netlibrary.bean.intentional_customer.req;

import com.example.netlibrary.bean.common.req.ReqPageList;

public class ReqCustomerFollow extends ReqPageList {
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
