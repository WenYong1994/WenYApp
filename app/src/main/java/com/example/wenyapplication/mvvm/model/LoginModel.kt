package com.example.wenyapplication.mvvm.model

import com.example.wenyapplication.mvvm.data.LoginBean

class LoginModel {

    fun login(account:String):LoginBean{
        return LoginBean(1,"登录成功${account}")
    }


}