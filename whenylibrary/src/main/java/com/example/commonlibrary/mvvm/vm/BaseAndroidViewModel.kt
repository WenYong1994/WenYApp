package com.example.commonlibrary.mvvm.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application),BaseViewModelApi {

    protected var mCompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        mCompositeDisposable.clear()
    }

}