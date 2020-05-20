package com.example.wenyapplication.mvvm.view_model

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wenyapplication.mvvm.data.LoginBean
import com.example.wenyapplication.mvvm.model.LoginModel
import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable
import java.util.*

class LoginVm2(string:String,app: Application) : AndroidViewModel(app){
    private var loginModel = LoginModel()

    var loginBean = MutableLiveData<LoginBean>()


    var account:String = ""
    var pwd : String =""
    var msg :String = string
        set(value) {
            field = value
        }

    fun doLogin(){
        Handler().postDelayed(object : Runnable {
            override fun run() {
                /*通知Activity刷新数据*/
                loginBean.value = loginModel.login(account)
                msg = loginBean.value?.message.toString()
            }
        }, 3000)
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
    }

}

class LoginVm2Factory(private val string:String,private val app: Application) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginVm2(string,app) as T
    }
}

