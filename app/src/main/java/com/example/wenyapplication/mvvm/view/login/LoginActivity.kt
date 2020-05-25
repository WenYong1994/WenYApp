package com.example.wenyapplication.mvvm.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wenyapplication.R
import com.example.wenyapplication.application.App
import com.example.wenyapplication.databinding.ActivityLoginBinding
import com.example.whenyannotationapilib.ViewModelInjector
import com.example.whenyannotationlib.InjectViewModel

class LoginActivity : AppCompatActivity() {

    @InjectViewModel(dataBindFieldName = "LoginVm")
    var mLoginVm: LoginVm?=null
    @InjectViewModel(dataBindFieldName = "LoginVm2",needFactory = true)
    var mLoginVm2: LoginVm2?=null
    @InjectViewModel(needFactory = true)
    var mLoginVm3: LoginVm2?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var layoutId= R.layout.activity_login
        setContentView(layoutId)
        val loginBinding = ViewModelInjector.inject<ActivityLoginBinding>(this, layoutId)
        ViewModelInjector.injectByFactory(this, LoginVm2Factory("ssss", App.getApplication()),"mLoginVm2",loginBinding)
        ViewModelInjector.injectByFactory(this, LoginVm2Factory("33333", App.getApplication()),"mLoginVm3",loginBinding)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

