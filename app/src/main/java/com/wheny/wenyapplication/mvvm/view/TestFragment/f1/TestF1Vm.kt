package com.wheny.wenyapplication.mvvm.view.TestFragment.f1

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.wheny.wenyapplication.base.BaseVmContract
import com.wheny.whenylibrary.mvvm.contract.VmContract
import com.wheny.whenylibrary.mvvm.vm.BaseViewModel
import com.wheny.whenynetlibrary.data.BaseResp

class TestF1Vm :BaseViewModel<BaseVmContract>(){

    val TAG = "111111111111111111111111"

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