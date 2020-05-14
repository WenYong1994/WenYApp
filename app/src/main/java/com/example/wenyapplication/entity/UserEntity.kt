package com.example.wenyapplication.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

open class UserEntity : BaseObservable() {

    var id:Long?=0
    var eid:String?=null

    var age:Int?=null
    @Bindable
    var name:String?=null
    set(value) {
        field = value
        notifyPropertyChanged(BR.name)
    }

    var tag:String?=null



    fun hahaha():String{
        return "sssssfaafaf"
    }

    override fun toString(): String {
        return "UserEntity(id=$id, eid=$eid, age=$age, name=$name, tag=$tag)"
    }


}
