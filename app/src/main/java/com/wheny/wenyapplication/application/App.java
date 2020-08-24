package com.wheny.wenyapplication.application;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.wheny.whenylibrary.utils.ToastUtils;
import com.wheny.whenynetlibrary.manager.CoroutineRetrofitServiceManager;
import com.wheny.whenynetlibrary.retrofit_net.NetGlobalScope;
import com.wheny.whenynetlibrary.retrofit_net.ResultException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        // 主要是添加下面这句代码
        MultiDex.install(this);
        instance = this;
        NetGlobalScope.INSTANCE.initHandErrorFun(new Function1<Exception, Unit>() {
            @Override
            public Unit invoke(Exception e) {
                if(e instanceof ConnectException||e instanceof UnknownHostException){
                    ToastUtils.showShortToast(App.this,"当前无网络连接");
                }else if(e instanceof ResultException){
                    switch (((ResultException) e).getCode()){
                        case -217:
                            ToastUtils.showShortToast(App.this,"当前无网络连接");
                            break;
                    }
                }


                return null;
            }
        });
    }


    public static App getApplication() {
        return instance;
    }

}
