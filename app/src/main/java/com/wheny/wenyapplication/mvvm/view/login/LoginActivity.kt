package com.wheny.wenyapplication.mvvm.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.application.App
import com.wheny.wenyapplication.databinding.ActivityLoginBinding
import com.wheny.whenyannotationapilib.ViewModelInjector
import com.wheny.whenyannotationlib.InjectViewModel
import com.wheny.whenylibrary.mvvm.contract.VmContract

class LoginActivity : AppCompatActivity(), VmContract {

    @InjectViewModel(dataBindFieldName = "LoginVm")
    var mLoginVm: LoginVm<LoginActivity>?=null
    @InjectViewModel(dataBindFieldName = "LoginVm2",needFactory = true)
    var mLoginVm2: LoginVm2<LoginActivity>?=null
    @InjectViewModel(needFactory = true)
    var mLoginVm3: LoginVm2<LoginActivity>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginBinding = ViewModelInjector.inject<ActivityLoginBinding>(this, R.layout.activity_login)

        ViewModelInjector.injectByFactory(this, LoginVm2Factory<LoginActivity>("ssss", App.getApplication()),"mLoginVm2",loginBinding)
        ViewModelInjector.injectByFactory(this, LoginVm2Factory<LoginActivity>("33333", App.getApplication()),"mLoginVm3",loginBinding)

    }

}

