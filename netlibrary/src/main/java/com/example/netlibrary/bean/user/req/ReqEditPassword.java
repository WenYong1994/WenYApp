package com.example.netlibrary.bean.user.req;

import com.example.netlibrary.bean.body.BaseRequest;

public class ReqEditPassword extends BaseRequest {
    private Integer id;
    private String newPwd;
    private String oldPwd;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }
}
