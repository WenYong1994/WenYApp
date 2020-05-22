package com.example.wenyapplication.mvvm.main

import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.commonlibrary.mvvm.vm.BaseViewModel
import rx.schedulers.Schedulers.test

class TestFraVm : BaseViewModel() {
    var test = MutableLiveData<String>()

    fun testCl(){
        test.postValue("三餐阿拉基拉萨")

    }
}