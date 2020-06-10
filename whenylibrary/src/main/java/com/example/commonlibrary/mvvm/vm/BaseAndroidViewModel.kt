package com.example.commonlibrary.mvvm.vm

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application),BaseViewModelApi,DefaultLifecycleObserver {

    protected var mCompositeDisposable = CompositeDisposable()
    private var activityWR: WeakReference<Activity>?=null

    fun addDisposable(compose:Disposable){
        mCompositeDisposable.add(compose)
    }

    fun setAcitivity(avtivity:Activity){
        activityWR = WeakReference(avtivity)
    }

    fun getActivity() : Activity?{
        return  activityWR?.get()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        mCompositeDisposable.clear()
        activityWR?.clear()
        activityWR=null
        Log.e("ViewModel","onDestroy")
    }

}
