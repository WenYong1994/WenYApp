package com.wheny.whenylibrary.mvvm.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.wheny.whenylibrary.mvvm.contract.VmContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

open class  BaseAndroidViewModel<T : VmContract>(application: Application) : AndroidViewModel(application),BaseViewModelApi,DefaultLifecycleObserver {

    protected var mCompositeDisposable = CompositeDisposable()
    private var activityWR: WeakReference<T>?=null

    fun addDisposable(compose:Disposable){
        mCompositeDisposable.add(compose)
    }

    fun setContract(avtivity : T){
        activityWR = WeakReference(avtivity)
    }

    fun contract() : T?{
        return  activityWR?.get()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        mCompositeDisposable.clear()
        activityWR?.clear()
        activityWR=null
        Log.e("ViewModel","onDestroy")
    }

}
