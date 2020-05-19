package com.example.wenyapplication.mvvm.view_model

import android.app.Application
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.commonlibrary.utils.ToastUtils
import com.example.wenyapplication.application.App
import com.example.wenyapplication.mvvm.data.LoginBean
import com.example.wenyapplication.mvvm.model.LoginModel
import io.reactivex.Flowable

class LoginVm() : ViewModel(){


    private var loginModel = LoginModel()

    var loginBean = MutableLiveData<LoginBean>()

    var account:String = ""
    var pwd : String =""

    fun doLogin(){
        Handler().postDelayed(object : Runnable {
            override fun run() {
                /*通知Activity刷新数据*/
                loginBean.postValue(loginModel.login(account))
            }
        }, 2000)
    }

    fun test(){
        Handler().postDelayed(object : Runnable {
            override fun run() {
                loginBean.value?.message?.postValue(loginBean.value?.message?.value+"1")
            }
        }, 2000)
    }


    override fun onCleared() {
        super.onCleared()
        loginBean.value=null
    }


}