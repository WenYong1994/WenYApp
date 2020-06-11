package com.wheny.wenyapplication.mvvm.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.commonlibrary.mvvm.contract.VmContract
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.application.App
import com.wheny.wenyapplication.databinding.ActivityLoginBinding
import com.example.whenyannotationapilib.ViewModelInjector
import com.example.whenyannotationlib.InjectViewModel

class LoginActivity : AppCompatActivity(),VmContract {

    @InjectViewModel(dataBindFieldName = "LoginVm")
    var mLoginVm: LoginVm<LoginActivity>?=null
    @InjectViewModel(dataBindFieldName = "LoginVm2",needFactory = true)
    var mLoginVm2: LoginVm2<LoginActivity>?=null
    @InjectViewModel(needFactory = true)
    var mLoginVm3: LoginVm2<LoginActivity>?=null

//    var testLive = MutableLiveData<String>("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var layoutId= R.layout.activity_login
//        setContentView(layoutId)
        val loginBinding = ViewModelInjector.inject<ActivityLoginBinding>(this, layoutId)

        ViewModelInjector.injectByFactory(this, LoginVm2Factory<LoginActivity>("ssss", App.getApplication()),"mLoginVm2",loginBinding)
        ViewModelInjector.injectByFactory(this, LoginVm2Factory<LoginActivity>("33333", App.getApplication()),"mLoginVm3",loginBinding)

//        mLoginVm2 = LoginVm2Factory("ssss", App.getApplication()).create(LoginVm2::class.java)
//        loginBinding.loginVm2=mLoginVm2

//        mLoginVm = ViewModelProviders.of(this).get(LoginVm::class.java)

//        testLive.observe(this, Observer<String> {
//            Log.e("testLive",it)
//        })
//        test_2.setOnClickListener {
//            testLive.postValue("test")
//        }

//        lifecycle.addObserver(object : LifecycleEventObserver{
//            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
//                when (event){
//                    Lifecycle.Event.ON_START->{
//
//                    }
//                    Lifecycle.Event.ON_DESTROY->{
//
//                    }
//                }
//            }
//        })



    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

