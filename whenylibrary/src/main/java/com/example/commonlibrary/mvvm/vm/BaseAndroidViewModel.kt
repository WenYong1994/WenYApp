package com.example.commonlibrary.mvvm.vm

import android.app.Application
import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application),BaseViewModelApi,DefaultLifecycleObserver {

    protected var mCompositeDisposable = CompositeDisposable()


    fun addDisposable(compose:Disposable){
        mCompositeDisposable.add(compose)
    }

    override fun onDestroy() {
        mCompositeDisposable.clear()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        mCompositeDisposable.clear()
    }


}
