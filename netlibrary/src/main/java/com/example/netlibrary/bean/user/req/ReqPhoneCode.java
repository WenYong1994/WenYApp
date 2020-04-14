package com.example.netlibrary.bean.user.req;

import com.example.netlibrary.bean.body.BaseRequest;

public class ReqPhoneCode extends BaseRequest {
    private String emailOrPhone;
    private String type = "phone";

    public String getEmailOrPhone() {
        return emailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        this.emailOrPhone = emailOrPhone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
