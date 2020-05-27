package com.example.wenyapplication.mvvm.view.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.wenyapplication.R
import com.example.wenyapplication.application.App
import com.example.wenyapplication.databinding.ActivityLoginBinding
import com.example.whenyannotationapilib.ViewModelInjector
import com.example.whenyannotationlib.InjectViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    @InjectViewModel(dataBindFieldName = "LoginVm")
    var mLoginVm: LoginVm?=null
    @InjectViewModel(dataBindFieldName = "LoginVm2",needFactory = true)
    var mLoginVm2: LoginVm2?=null
    @InjectViewModel(needFactory = true)
    var mLoginVm3: LoginVm2?=null

//    var testLive = MutableLiveData<String>("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var layoutId= R.layout.activity_login
//        setContentView(layoutId)
        val loginBinding = ViewModelInjector.inject<ActivityLoginBinding>(this, layoutId)

        ViewModelInjector.injectByFactory(this, LoginVm2Factory("ssss", App.getApplication()),"mLoginVm2",loginBinding)
        ViewModelInjector.injectByFactory(this, LoginVm2Factory("33333", App.getApplication()),"mLoginVm3",loginBinding)

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

