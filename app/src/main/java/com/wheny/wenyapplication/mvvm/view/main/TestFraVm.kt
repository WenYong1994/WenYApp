package com.wheny.wenyapplication.mvvm.view.main

import androidx.lifecycle.MutableLiveData
import com.wheny.whenylibrary.mvvm.contract.VmContract
import com.wheny.whenylibrary.mvvm.vm.BaseViewModel

class TestFraVm<T : VmContract> : BaseViewModel<T>() {
    var test = MutableLiveData<String>()

    fun testCl(){
        test.postValue("三餐阿拉基拉萨")
    }
}