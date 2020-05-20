package com.example.commonlibrary.mvvm.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.DataBinderMapperImpl
import com.example.commonlibrary.annotation_api.ViewModelInjector

abstract class BaseMVVMActivity<T : ViewDataBinding> : AppCompatActivity(){

    lateinit var mDataBing : T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = layoutId()
        setContentView(layoutId)
        mDataBing = ViewModelInjector.inject(this,layoutId)
    }

    abstract fun layoutId():Int


}