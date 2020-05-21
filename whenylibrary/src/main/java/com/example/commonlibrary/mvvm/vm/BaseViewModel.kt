package com.example.commonlibrary.mvvm.vm

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DefaultObserver

open class BaseViewModel : ViewModel(),BaseViewModelApi,LifecycleObserver {

    protected var mCompositeDisposable = CompositeDisposable()


    override fun onDestroy() {
        mCompositeDisposable.clear()
    }


}