package com.wheny.wenyapplication.mvvm.view.main

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.wheny.whenylibrary.mvvm.contract.VmContract
import com.wheny.whenylibrary.mvvm.vm.BaseViewModel

class TestFraVm<T : VmContract> : BaseViewModel<T>() {
    var test = MutableLiveData<String>()

    fun testCl(){
        test.postValue("三餐阿拉基拉萨")
    }


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.e("ssss","${this.toString()}onCreate1111")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.e("ssss","${this.toString()}onDestroy1111")
    }

}