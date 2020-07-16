package com.wheny.wenyapplication.mvvm.view.main

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.wheny.whenylibrary.mvvm.contract.VmContract
import com.wheny.whenylibrary.mvvm.vm.BaseViewModel

class TestFraVm<T : VmContract> : BaseViewModel<T>() {
    var test = MutableLiveData<String>("safasfafasfas")

    fun testCl(){
        test.postValue("三餐阿拉基拉萨")
    }


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.e("sss1s","${this.toString()}onCreate1111")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.e("sss1s","${this.toString()}onDestroy1111")
    }


    override fun onStart(owner: LifecycleOwner) {
        Log.e("sss1s","${this.toString()}onStart222222")
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.e("sss1s","${this.toString()}onResume22222")
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.e("sss1s","${this.toString()}onPause222222")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.e("sss1s","${this.toString()}onStop22222")
    }





}