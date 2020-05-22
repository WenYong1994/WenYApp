package com.example.whenyannotationapilib

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

interface ViewModelInjectorApi {

    fun <T : ViewDataBinding?> injectViewModels(tag: Any?, activity: AppCompatActivity?, inflater: LayoutInflater?, layoutId: Int, viewGroup: ViewGroup?): T?{
        return null
    }


    fun injectViewModelByFactory(tag: Any?, activity: AppCompatActivity?, factory: NewInstanceFactory?, filedName: String?, viewDataBinding: ViewDataBinding?){


    }

}