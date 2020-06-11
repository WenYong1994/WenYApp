package com.wheny.wenyapplication.mvvm.view.login

import android.app.Application
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wheny.wenyapplication.data.LoginBean
import com.wheny.whenylibrary.mvvm.contract.VmContract
import com.wheny.whenylibrary.mvvm.vm.BaseAndroidViewModel
import com.wheny.whenylibrary.rxjava.RxSchedulers
import com.wheny.whenynetlibrary.api.WhenYApiService
import com.wheny.whenynetlibrary.manager.RetrofitServiceManager
import io.reactivex.Flowable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class LoginVm2<T : VmContract>(string:String, app: Application) : BaseAndroidViewModel<T>(app){

    var loginBean = MutableLiveData<LoginBean>()

    var account:String = ""
    var pwd : String =""
    var msg :String = string
        set(value) {
            field = value
        }

    fun doLogin() {
        var json = "{\"channel\":\"android\",\"data\":\"{\\\"password\\\":\\\"123456\\\",\\\"os\\\":\\\"android\\\",\\\"uid\\\":0,\\\"username\\\":\\\"kred001\\\"}\",\"salt\":\"064797\",\"service\":\"functionaryLoginService\",\"test\":true,\"time\":1590024509,\"version\":\"1.0\"}"
        val requestBody = json.toRequestBody("application/json; charset=utf-8;".toMediaTypeOrNull())
        val compose = RetrofitServiceManager.getInstance().create(WhenYApiService::class.java)
                .login(requestBody)
                .flatMap {
                    SystemClock.sleep(2000)
                    Flowable.just(it.data.toString())
                }
                .compose(RxSchedulers.netio_main())
                .subscribe({
                    Log.e("doLogin","doLogindoLogindoLogindoLogindoLogindoLogindoLogin");
                    loginBean.value= LoginBean(0, it)
                },{
                    loginBean.value=LoginBean(0,it.message.toString())
                })
        addDisposable(compose)
    }


    fun test(){
        Handler().postDelayed({ loginBean.value?.message?.postValue(loginBean.value?.message?.value+"1") }, 2000)
    }


}


class LoginVm2Factory<C:VmContract>(private val string:String,private val app: Application) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginVm2<C>(string, app) as T
    }
}

