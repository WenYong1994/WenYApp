package com.example.wenyapplication.mvvm.view.main

import androidx.lifecycle.MutableLiveData
import com.example.commonlibrary.mvvm.contract.VmContract
import com.example.commonlibrary.mvvm.vm.BaseViewModel

class TestFraVm<T : VmContract> : BaseViewModel<T>() {
    var test = MutableLiveData<String>()

    fun testCl(){
        test.postValue("三餐阿拉基拉萨")
    }
}