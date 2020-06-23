package com.wheny.whenylibrary.mvvm.vm

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.wheny.whenylibrary.mvvm.contract.VmContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

open class BaseViewModel<T : VmContract> : ViewModel(),BaseViewModelApi, DefaultLifecycleObserver {

    protected var mCompositeDisposable = CompositeDisposable()
    private var activityWR:WeakReference<T>?=null

    fun addDisposable(compose: Disposable){
        mCompositeDisposable.add(compose)
    }

    fun setContract(contract : T){
        activityWR = WeakReference(contract)
    }

    fun contract() : T?{
        return  activityWR?.get()
    }


    override fun onCreate(owner: LifecycleOwner) {

    }

    override fun onStart(owner: LifecycleOwner) {

    }

    override fun onResume(owner: LifecycleOwner) {
    }

    override fun onPause(owner: LifecycleOwner) {
    }

    override fun onStop(owner: LifecycleOwner) {

    }

    override fun onDestroy(owner: LifecycleOwner) {
        mCompositeDisposable.clear()
        activityWR?.clear()
        activityWR=null
    }

}