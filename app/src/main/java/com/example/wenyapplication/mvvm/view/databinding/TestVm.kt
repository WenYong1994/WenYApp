package com.example.wenyapplication.mvvm.view.databinding

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class TestVm : BaseObservable() {

    @Bindable
    var txt1 = "1"
    set(value) {
        field = value
        notifyPropertyChanged(BR.txt1)
    }

    fun onClickTest(view: View){
        txt1 = "test"
    }


}