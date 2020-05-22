package com.example.wenyapplication.mvvm.main

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.commonlibrary.mvvm.vm.BaseAndroidViewModel
import com.example.commonlibrary.mvvm.vm.BaseViewModel
import com.example.wenyapplication.application.App
import com.example.wenyapplication.databind.TestDataBindActivity
import com.example.wenyapplication.mvvm.view.LoginActivity

class MainVm : BaseViewModel() {
    var dataBindingBtnTxt = "数据绑定"
    var loginVmTxt = "loginVm"
    var testNetTxt = "测试网络"







    fun testTest(view : View,string: String){
        view.context.startActivity(Intent(view.context, TestDataBindActivity::class.java))
    }


    fun testLogin(view:View){
        view.context.startActivity(Intent(view.context,LoginActivity::class.java))
    }


}