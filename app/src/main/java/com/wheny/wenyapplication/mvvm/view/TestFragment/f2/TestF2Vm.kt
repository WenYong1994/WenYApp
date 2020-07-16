package com.wheny.wenyapplication.mvvm.view.TestFragment.f2

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.wheny.wenyapplication.base.BaseVmContract
import com.wheny.whenylibrary.mvvm.vm.BaseViewModel

class TestF2Vm : BaseViewModel<BaseVmContract>(){

    val TAG = "2222222222222222222222"

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.e("testFragment","onCreate:${TAG}")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.e("testFragment","onStart:${TAG}")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.e("testFragment","onResume:${TAG}")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.e("testFragment","onPause:${TAG}")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.e("testFragment","onStop:${TAG}")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.e("testFragment","onDestroy:${TAG}")
    }
}