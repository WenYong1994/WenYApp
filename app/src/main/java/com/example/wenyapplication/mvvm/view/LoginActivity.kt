package com.example.wenyapplication.mvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.commonlibrary.annotation_api.ViewModelInjector
import com.example.wenyapplication.R
import com.example.wenyapplication.application.App
import com.example.wenyapplication.databinding.ActivityLoginBinding
import com.example.wenyapplication.mvvm.view_model.LoginVm
import com.example.wenyapplication.mvvm.view_model.LoginVm2
import com.example.wenyapplication.mvvm.view_model.LoginVm2Factory
import com.example.whenyannotationlib.InjectViewModel

class LoginActivity : AppCompatActivity() {

    @InjectViewModel(dataBindFieldName = "LoginVm")
    var mLoginVm: LoginVm?=null
    @InjectViewModel(dataBindFieldName = "LoginVm2",needFactory = true)
    var mLoginVm2: LoginVm2?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var layoutId= R.layout.activity_login
        setContentView(layoutId)
        val loginBinding = ViewModelInjector.inject<ActivityLoginBinding>(this, layoutId)
        ViewModelInjector.injectByFactory(this, LoginVm2Factory("ssss", App.getApplication()),"mLoginVm2",loginBinding)


    }

    override fun onDestroy() {
        super.onDestroy()
        mLoginVm2?.onDestroy()
    }

}

