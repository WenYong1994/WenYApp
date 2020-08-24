package com.wheny.wenyapplication.mvvm.view.login

import android.app.Activity
import android.app.Application
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.annotation.RestrictTo
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.JsonParseException
import com.wheny.wenyapplication.application.App
import com.wheny.wenyapplication.data.LoginBean
import com.wheny.whenylibrary.mvvm.contract.VmContract
import com.wheny.whenylibrary.mvvm.vm.BaseAndroidViewModel
import com.wheny.whenylibrary.rxjava.RxSchedulers
import com.wheny.whenylibrary.utils.ToastUtils
import com.wheny.whenynetlibrary.api.WhenYApiService
import com.wheny.whenynetlibrary.data.BaseResp
import com.wheny.whenynetlibrary.entity.UserInfo
import com.wheny.whenynetlibrary.manager.CoroutineRetrofitServiceManager
import com.wheny.whenynetlibrary.manager.RxRetrofitServiceManager
import com.wheny.whenynetlibrary.retrofit_net.NetGlobalScope
import io.reactivex.*
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.lang.Exception

class LoginVm2<T : VmContract>(string:String, app: Application) : BaseAndroidViewModel<T>(app){

    var loginBean = MutableLiveData<LoginBean>()

    var account:String = ""
    var pwd : String =""
    var msg :String = string
        set(value) {
            field = value
        }

    fun doLogin() {
//        login1()
//        login2()
        login3()
    }

    private fun login1() {
        var json = "{\"channel\":\"android\",\"data\":\"{\\\"password\\\":\\\"123456\\\",\\\"os\\\":\\\"android\\\",\\\"uid\\\":0,\\\"username\\\":\\\"kred001\\\"}\",\"salt\":\"064797\",\"service\":\"functionaryLoginService\",\"test\":true,\"time\":1590024509,\"version\":\"1.0\"}"
        val requestBody = json.toRequestBody("application/json; charset=utf-8;".toMediaTypeOrNull())
        val compose = RxRetrofitServiceManager.getInstance().create(WhenYApiService::class.java)
                .login(requestBody)
                .compose(RxSchedulers.netio_main())
                .flatMap{
//                    RxRetrofitServiceManager.getInstance().create(WhenYApiService::class.java).login(requestBody)
                    return@flatMap Flowable.create<BaseResp<UserInfo?>>({ e -> e.onNext(BaseResp<UserInfo?>()) },BackpressureStrategy.BUFFER)
                }
                .subscribe({
                    Log.e("doLogin", "doLogindoLogindoLogindoLogindoLogindoLogindoLogin")
                    loginBean.value = LoginBean(0, it.data?.toString()!!)
                }, {
                    loginBean.value = LoginBean(0, it.message.toString())
                })
        addDisposable(compose)
    }

    private fun login2() {
        val job = GlobalScope.launch {
            try {
                val json = "{\"channel\":\"android\",\"data\":\"{\\\"password\\\":\\\"123456\\\",\\\"os\\\":\\\"android\\\",\\\"uid\\\":0,\\\"username\\\":\\\"kred0011\\\"}\",\"salt\":\"064797\",\"service\":\"functionaryLoginService\",\"test\":true,\"time\":1590024509,\"version\":\"1.0\"}"
                val requestBody = json.toRequestBody("application/json; charset=utf-8;".toMediaTypeOrNull())
                Log.e("weny","---------------------------------${Thread.currentThread().name}")
                val startTime = System.currentTimeMillis()
                Log.e("weny","===============================222222222222${Thread.currentThread().name}")
                val res = CoroutineRetrofitServiceManager.getInstance()
                        .create(WhenYApiService::class.java)
                        .login1(requestBody)
                        .await()
                val result = res.data.toString()
                val endTime = System.currentTimeMillis()
                Log.e("weny","间隔：${endTime-startTime}")
                withContext(Dispatchers.Main) {
                    loginBean.value = LoginBean(0, result)
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main) {
                    ToastUtils.showShortToast(contract() as Activity,e.message)
                }
            }
        }
        addJob(job)
    }

    private fun login3() {
        val job = NetGlobalScope.doNet(contract() as? Activity) {
            val json = "{\"channel\":\"android\",\"data\":\"{\\\"password\\\":\\\"123456\\\",\\\"os\\\":\\\"android\\\",\\\"uid\\\":0,\\\"username\\\":\\\"kred001\\\"}\",\"salt\":\"064797\",\"service\":\"functionaryLoginService\",\"test\":true,\"time\":1590024509,\"version\":\"1.0\"}"
            val requestBody = json.toRequestBody("application/json; charset=utf-8;".toMediaTypeOrNull())
            val res = CoroutineRetrofitServiceManager.getInstance()
                    .create(WhenYApiService::class.java)
                    .login1(requestBody)
                    .await()
            if(res.code==200){
                val result = res.data.toString()
                withContext(Dispatchers.Main) {
                    loginBean.value = LoginBean(0, result)
                }
            }else{
                onNetError(res.code,res.msg)
            }
        }
        addJob(job)
    }

    fun test(){
        Handler().postDelayed({ loginBean.value?.message?.postValue(loginBean.value?.message?.value+"1") }, 2000)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }

}


class LoginVm2Factory<C:VmContract>(private val string:String,private val app: Application) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginVm2<C>(string, app) as T
    }
}



