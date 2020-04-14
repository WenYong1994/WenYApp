package com.example.netlibrary.bean.user.req;

import com.example.netlibrary.bean.body.BaseRequest;

public class ReqFindPw extends BaseRequest {
    private String newPwd;
    private String phone;
    private String code;

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}