package com.example.wenyapplication.mvvm.view.login

import android.app.Application
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.commonlibrary.mvvm.vm.BaseAndroidViewModel
import com.example.commonlibrary.rxjava.RxSchedulers
import com.example.netlibrary.api.WhenYApiService
import com.example.netlibrary.manager.RetrofitServiceManager
import com.example.wenyapplication.data.LoginBean
import io.reactivex.Flowable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class LoginVm2(string:String,app: Application) : BaseAndroidViewModel(app){

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
                .compose(RxSchedulers.io_main())
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


class LoginVm2Factory(private val string:String,private val app: Application) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginVm2(string, app) as T
    }
}

