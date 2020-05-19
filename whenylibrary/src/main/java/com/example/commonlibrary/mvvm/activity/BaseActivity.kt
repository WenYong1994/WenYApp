package com.example.commonlibrary.mvvm.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.DataBinderMapperImpl
import com.example.commonlibrary.annotation_api.ViewModelInjector

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(){

    protected lateinit var mDataBing : T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = getLayoutId()
        setContentView(layoutId)
        mDataBing = DataBindingUtil.setContentView<T>(this,layoutId)
        mDataBing.lifecycleOwner = this
        ViewModelInjector.inject(this,mDataBing)
        init(savedInstanceState)
    }

    abstract fun getLayoutId():Int

    abstract fun init(savedInstanceState: Bundle?);



}