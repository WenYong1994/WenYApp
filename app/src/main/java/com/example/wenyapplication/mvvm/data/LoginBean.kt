package com.example.wenyapplication.mvvm.data

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData

class LoginBean (var code:Int): BaseObservable() {

    var message = MutableLiveData<String>()
    var str =""

    constructor(code:Int,message:String) : this(code){
        this.message.postValue(message)
    }





}
