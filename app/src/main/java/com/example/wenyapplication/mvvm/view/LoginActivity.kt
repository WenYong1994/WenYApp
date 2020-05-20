package com.example.wenyapplication.mvvm.view

import android.os.Bundle
import com.example.commonlibrary.mvvm.activity.BaseActivity
import com.example.wenyapplication.R
import com.example.wenyapplication.databinding.ActivityLoginBinding
import com.example.wenyapplication.mvvm.view_model.LoginVm
import com.example.whenyannotationlib.InjectViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    @InjectViewModel(name = "LoginVm")
    var mLoginVm: LoginVm?=null
//    @InjectViewModel(value = LoginVm2::class,name = "LoginVm2")
//    var mLoginVm2: LoginVm2?=null


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun init(savedInstanceState: Bundle?) {

    }
}

