package com.example.wenyapplication.databind

import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable
import com.example.wenyapplication.entity.UserEntity

class UserEntityViewModul{
    public val TAG = "UserEntityViewModul";

    var userEntity:UserEntity

    constructor(userEntity: UserEntity){
        this.userEntity=userEntity
    }

    fun test(view: View){
        Log.e(TAG,userEntity.toString())
    }

    fun test2(view: View){
        userEntity.name = "name_name"


    }

}