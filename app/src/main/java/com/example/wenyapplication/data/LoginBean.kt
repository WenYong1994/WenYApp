package com.example.wenyapplication.data

import androidx.lifecycle.MutableLiveData

class LoginBean {
    var code =0;
    var message = MutableLiveData<String>()

    constructor(code :Int,value :String){
        this.message.postValue(value)
        this.code = code
    }

}