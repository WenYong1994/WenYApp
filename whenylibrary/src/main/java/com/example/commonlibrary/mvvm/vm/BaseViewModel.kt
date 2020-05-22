package com.example.commonlibrary.mvvm.vm

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DefaultObserver

open class BaseViewModel : ViewModel(),BaseViewModelApi, DefaultLifecycleObserver {

    protected var mCompositeDisposable = CompositeDisposable()


    fun addDisposable(compose: Disposable){
        mCompositeDisposable.add(compose)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        mCompositeDisposable.clear()
    }


}