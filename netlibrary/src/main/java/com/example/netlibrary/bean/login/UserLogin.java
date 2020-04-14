package com.example.netlibrary.bean.login;

import com.example.netlibrary.bean.body.BaseRequest;

public class UserLogin extends BaseRequest {
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
