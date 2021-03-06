package com.wheny.whenylibrary.mvvm.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.wheny.whenyannotationapilib.ViewModelInjector

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