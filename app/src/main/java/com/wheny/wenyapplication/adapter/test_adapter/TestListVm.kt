package com.wheny.wenyapplication.adapter.test_adapter

import android.database.Observable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.example.commonlibrary.mvvm.vm.BaseViewModel
import com.wheny.wenyapplication.data.TestListBean

class TestListVm : BaseObservable() {


    var testListBean:TestListBean?=null
    set(value) {
        field = value
        txt2 = value?.txt.toString()
    }

    @Bindable
    var txt2 = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.txt2)
        testListBean?.txt=value
    }




}