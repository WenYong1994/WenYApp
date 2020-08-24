package com.wheny.whenynetlibrary.manager;


import com.wheny.whenynetlibrary.constant.Constant;
import com.wheny.whenynetlibrary.retrofit_net.WhenYCoroutineCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoroutineRetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit mRetrofit;

    private CoroutineRetrofitServiceManager(){
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间

//         添加公共参数拦截器
//        Interceptor commonInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                //判断当前有无网络连接
//                if(context!=null&&!NetUtils.isConnectedNetwork(context)){
//                    throw new IOException("当前无网络连接");
//                }
//
//                return null;
//            }
//        };
//        builder.addInterceptor(commonInterceptor);
//        CallAdapter callAdapter = new CallAdapter() {
//            @Override
//            public Type responseType() {
//                return null;
//            }
//
//            @Override
//            public Object adapt(Call call) {
//                return null;
//            }
//        };

        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(WhenYCoroutineCallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
    }

    //用的是静态内部类方式实现的懒汉线程安全单列
    private static class SingletonHolder{
        private static final CoroutineRetrofitServiceManager INSTANCE = new CoroutineRetrofitServiceManager();
    }

    /**
     * 获取RetrofitServiceManager
     * @return
     */
    public static CoroutineRetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }

}