package com.example.wenyapplication.mvvm.view

import android.os.Bundle
import androidx.lifecycle.*
import com.example.commonlibrary.mvvm.activity.BaseActivity
import com.example.wenyapplication.R
import com.example.wenyapplication.application.App
import com.example.wenyapplication.databinding.ActivityLoginBinding
import com.example.wenyapplication.mvvm.view_model.LoginVm
import com.example.wenyapplication.mvvm.view_model.LoginVm2
import com.example.wenyapplication.mvvm.view_model.LoginVm2Factory

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun init(savedInstanceState: Bundle?) {
        val loginVm = ViewModelProviders.of(this).get(LoginVm::class.java)
        mDataBing.loginVm = loginVm
        val loginVm2 = LoginVm2Factory("", App.getApplication()).create(LoginVm2::class.java)

    }
}

