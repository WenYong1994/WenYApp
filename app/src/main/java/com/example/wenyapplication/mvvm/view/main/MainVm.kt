package com.example.wenyapplication.mvvm.view.main

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.commonlibrary.mvvm.vm.BaseViewModel
import com.example.wenyapplication.databind.TestDataBindActivity
import com.example.wenyapplication.mvvm.view.list.TestListActivity
import com.example.wenyapplication.mvvm.view.login.LoginActivity

class MainVm : BaseViewModel() {
    var dataBindingBtnTxt = MutableLiveData("数据绑定")
    var loginVmTxt =MutableLiveData("loginVm")
    var testNetTxt = MutableLiveData("测试网络")
    var testListTxt = MutableLiveData("测试列表")

    fun testTest(view : View,string: String){
        view.context.startActivity(Intent(view.context, TestDataBindActivity::class.java))
    }

    fun testLogin(view:View){
        view.context.startActivity(Intent(view.context, LoginActivity::class.java))
    }

    fun testList(view : View){
        view.context.startActivity(Intent(view.context, TestListActivity::class.java))
    }

}