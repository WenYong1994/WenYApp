package com.example.commonlibrary.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import com.example.commonlibrary.bean.BaseAppCompatActivity;
import com.example.commonlibrary.bean.BaseResult;
import com.example.commonlibrary.utils.HttpsTrustManager;
import com.example.commonlibrary.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHelper<T>  {
    public static final int NET_ERROR_CODE = 500;

    public static final int NETWORK_WIFI = 2;
    public static final int NETWORK_NO = 0;
    public static final int NETWOR_FLOW =1;


    private static final int FAIL = 1;
    private static final int SUCCESS = 2;
    private static final int RESULT = 3;
    private static final int RESULT_START=4;

    private static OkHttpClient mOkHttpClient;
    private Context mContext;
    private OkHttpClient.Builder mBuilder;
    private Handler mHandler;
    private boolean isShowProgress = false;
    private String mUrl;
    private Request.Builder requestBuilder;
    FormBody.Builder fromBodyBuilder;
    private OnCompleteListener<T> mOnCompleteListener;
    private boolean doInBackground = false;//默认执行在主线程

    BaseAppCompatActivity needShowPbActivity;
    private static OkHttpClient specialOkhttpClient;//有的接口需要设置更长的超时时间，这是特例，一般不需要
    private boolean isRemove = false;



    /**
     * 构造器，mOkHttpClient必须单例，无论创建多少个OkHttpUtils的实例。
     * 都由mOkHttpClient一个对象处理所有的网络请求。
     */
    public HttpHelper(final Context context) {
        this.mContext = context;
        if (mOkHttpClient == null) {//线程安全的单例
            synchronized (HttpHelper.class) {
                if (mOkHttpClient == null) {
                    mBuilder = new OkHttpClient.Builder();
                    //获取sd卡的缓存文件夹
                    final File cacheDir = context.getExternalCacheDir();
                    mOkHttpClient = mBuilder
                            .sslSocketFactory(HttpsTrustManager.createSSLSocketFactory(),new HttpsTrustManager())//设置https请求
                            .hostnameVerifier(new HttpsTrustManager.TrustAllHostnameVerifier())

//                            .cookieJar(new CookieJar() {
//
//                                @Override
//                                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                                    String host = url.host();
//                                    DataHelper.getCookieStore().put(host, cookies);
//                                }
//                                @Override
//                                public List<Cookie> loadForRequest(HttpUrl url) {
//                                    String host = url.host();
////                                    List<Cookie> cookies = DataHelper.getCookieStore().get(host);
////                                    if(url.toString().contains("handleGetAdcpgList_toCheck.php")){//模拟cookies 过期
////                                        Cookie cookie = cookies.get(0);
////                                        if(cookie!=null){
////                                            //(String name, String value, long expiresAt, String domain, String path,
////                                            //      boolean secure, boolean httpOnly, boolean hostOnly, boolean persistent)
////                                            Cookie cookieNew = new Cookie.Builder().name(cookie.name())
////                                                    .domain(cookie.domain())
////                                                    .value(cookie.value()+"12")
////                                                    .expiresAt(cookie.expiresAt())
////                                                    .hostOnlyDomain(cookie.domain())
////                                                    .path(cookie.path())
////                                                    .build();
////                                            cookies =new ArrayList<>();
////                                            cookies.add(cookieNew);
////                                        }
////                                        DataHelper.getCookieStore().put(host, cookies);
////                                    }
////                                    return cookies != null ? cookies : new ArrayList<Cookie>();
//                                    return null;
//                                }
//                            })
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10,TimeUnit.SECONDS)
                            .readTimeout(10,TimeUnit.SECONDS)
                            .cache(new Cache(cacheDir,10*(1<<20)))//设置缓存位置和缓存大小
                            .build();
                }
            }
        }
        initHandler(context);
    }



    public HttpHelper<T> url(String url){
        mUrl = url;
        requestBuilder = new Request.Builder().url(mUrl.toString());
        return this;
    }

    public HttpHelper<T> header(String key,String value){
        requestBuilder.addHeader(key,value);
        return this;
    }

    public HttpHelper<T> postJson(String content){
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8;"), content);
        requestBuilder.post(requestBody);
        return this;
    }


    public HttpHelper<T> post(){
        fromBodyBuilder = new FormBody.Builder();
        return this;
    }

    public HttpHelper<T> addPostParams(String key,String value){
        if(fromBodyBuilder!=null){
            fromBodyBuilder =  new FormBody.Builder();
        }
        fromBodyBuilder.add(key, value);
        return this;
    }

    public HttpHelper<T> get(){
        requestBuilder.get();
        return this;
    }

    public HttpHelper<T> doInBackground(boolean doInBackground){
        this.doInBackground = doInBackground;
        return this;
    }

    public HttpHelper<T> showProgress(boolean isShowProgress,BaseAppCompatActivity BaseAppCompatActivity){
        this.isShowProgress = isShowProgress;
        needShowPbActivity = BaseAppCompatActivity;
        return this;
    }

    public HttpHelper<T> showProgress(boolean isShowProgress){
        this.isShowProgress = isShowProgress;
        needShowPbActivity = null;
        return this;
    }

    public  void remove(){
        isRemove = true;
    }

    public HttpHelper<T> setConnectTime(int second){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //获取sd卡的缓存文件夹
        final File cacheDir;
        if(mContext!=null){
            cacheDir = mContext.getExternalCacheDir();
        }
        specialOkhttpClient = builder
//                .sslSocketFactory(HttpsTrustManager.createSSLSocketFactory())//设置https请求
//                .hostnameVerifier(new HttpsTrustManager.TrustAllHostnameVerifier())
//                .cookieJar(new CookieJar() {
//
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        String host = url.host();
////                        DataHelper.getCookieStore().put(host, cookies);
//                    }
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        String host = url.host();
////                        List<Cookie> cookies = DataHelper.getCookieStore().get(host);
////                                    if(url.toString().contains("handleGetAdcpgList_toCheck.php")){//模拟cookies 过期
////                                        Cookie cookie = cookies.get(0);
////                                        if(cookie!=null){
////                                            //(String name, String value, long expiresAt, String domain, String path,
////                                            //      boolean secure, boolean httpOnly, boolean hostOnly, boolean persistent)
////                                            Cookie cookieNew = new Cookie.Builder().name(cookie.name())
////                                                    .domain(cookie.domain())
////                                                    .value(cookie.value()+"12")
////                                                    .expiresAt(cookie.expiresAt())
////                                                    .hostOnlyDomain(cookie.domain())
////                                                    .path(cookie.path())
////                                                    .build();
////                                            cookies =new ArrayList<>();
////                                            cookies.add(cookieNew);
////                                        }
////                                        DataHelper.getCookieStore().put(host, cookies);
////                                    }
////                        return cookies != null ? cookies : new ArrayList<Cookie>();
//                        return null;
//                    }
//                })
                .connectTimeout(second, TimeUnit.SECONDS)
                .writeTimeout(second,TimeUnit.SECONDS)
                .readTimeout(second,TimeUnit.SECONDS)
//                .cache(new Cache(cacheDir,10*(1<<20)))//设置缓存位置和缓存大小
                .build();
        return this;
    }




    public HttpHelper<T> execute(final OnCompleteListener<T> onCompleteListener){
        this.mOnCompleteListener = onCompleteListener;
        if(!onCompleteListener.onNetworkStatus(getNetWorkStatus(mContext))){//onNetworkStatus返回flase表示取消本次请求
            String errMsg = "当前无网络连接";
            if(doInBackground){
                mOnCompleteListener.onError(NET_ERROR_CODE, errMsg);
            }
            Message message = Message.obtain();
            message.what  = FAIL;
            message.obj =  errMsg;
            message.arg1 = NET_ERROR_CODE;
            mHandler.sendMessage(message);
            return this;
        }
        mHandler.sendEmptyMessage(RESULT_START);
        Request request;
        requestBuilder.addHeader("Content-Typ","application/json; charset=utf-8;text/html;text/json;text/plain");
        if(fromBodyBuilder!=null){
            request =  requestBuilder.post(fromBodyBuilder.build()).build();
        }else {
            request =  requestBuilder.build();
        }
        Call call;
        if(specialOkhttpClient!=null){//这是有特定设置的client
            call = specialOkhttpClient.newCall(request);
        }else {
            call = mOkHttpClient.newCall(request);
        }
        if(needShowPbActivity!=null&&isShowProgress){
            needShowPbActivity.onRequestStart(HttpHelper.this);
        }
        call.enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e){
                if(isRemove){
                    return;
                }
                if(mContext!=null&&mContext instanceof BaseAppCompatActivity){
                    if (!((BaseAppCompatActivity)mContext).isAlive){//如果activity被销毁就不执行下面的回调
                        return;
                    }
                }
                String errMsg = "";
                if(e instanceof SocketTimeoutException){
                    errMsg = "网络连接失败";
                }else {
                    errMsg = "未知异常";
                }
                if(doInBackground){
                    mOnCompleteListener.onError(NET_ERROR_CODE, errMsg);
                }
                Message message = Message.obtain();
                message.what  = FAIL;
                message.obj =  errMsg;
                message.arg1 = NET_ERROR_CODE;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
//                    if(mContext instanceof BaseAppCompatActivity){
//                        if(((BaseAppCompatActivity)mContext).isCancleRequest()){//如果bar被
//                            return;
//                        }
//                    }
                    if(isRemove){
                        return;
                    }
                    if(mContext!=null&&mContext instanceof BaseAppCompatActivity){
                        if (!((BaseAppCompatActivity)mContext).isAlive){//如果activity被销毁就不执行下面的回调
                            return;
                        }
                    }
                    int httpCode = response.code();
                    String string = response.body().string();
//                    if(mUrl.contains("handleGetAdacList_toPrecheck")){
//                        string = "{\"r\":0,\"d\":{\"adac\":[[\"cn.15.436\",\"cn.15.200973377\",2,\"2019-01-03 17:57\",\"cn.15.201281131\",null,3,\"612731199003171411\",\"黄康\",\"sxleader.cn\",null,\"黄康\",\"17791342852\",null,\"sxleader@yeah.net\",null,null]],\"pdt\":{\"cn.15.436\":{\"3\":[\"cn.15.213\",2,\"2019-01-03 17:57\",\"cn.15.201281131\",null,null,null]}},\"qlf\":{\"cn.15.436\":{\"7\":[[\"cn.15.119\",1,null,null,null,null]]}},\"cl\":{\"cn.15.200973377\":[\"雷锋\"]},\"mpUs\":{\"cn.15.201281131\":[\"雷锋\"]}},\"_TT\":\"ELeXiQRwY4SL1KE2IRsG480s1pcQNBXp\"}";
//                    }

//                    if(mUrl.contains("handleGetAdcpgList_toCheck")){
//                        string = "{\"r\":0,\"d\":{\"adcpg\":[[\"cn.14.1\",\"cn.14.699\",3,\"牛大福科技招募代理\",2,null,null,\"http:\\/\\/m.niudafu88.com\\/col.jsp?id=112\",\"2019-01-10\",\"2019-01-10\",1,{\"1\":[1944],\"2\":[23,45],\"4\":null,\"5\":[1,4,9,10,11],\"6\":[3,4,5],\"7\":null},218.4,\"cn.14.6\",\"cn.14.201357041\"]],\"adscpg\":{\"cn.14.1\":[[\"cn.14.1\",3,9,31.2,\"2019-01-10\",\"2019-01-10\",{\"_SUPT\":{\"1\":1,\"2\":1,\"3\":1,\"4\":1,\"6\":1,\"5\":1,\"7\":1}},{\"p\":{\"n_txt_t\":1,\"txt_t_1\":{\"ic\":0,\"rg\":[1,15]},\"n_txt_d\":1,\"txt_d_1\":{\"ic\":0,\"rg\":[1,15]}},\"c\":{\"txt_t_1\":{\"ifg\":1,\"v\":\"牛大福科技招募建站服务销售代理\"},\"txt_d_1\":{\"ifg\":1,\"v\":\"网站、商城、小程序、H5游戏等\"}}},7,1,7,218.4,1.36,null]]},\"fr\":null,\"adac\":{\"cn.14.699\":[\"cn.14.699\",\"cn.14.201047366\",7,1,\"91440101MA59KJ8P06\",\"广州牛大福科技有限公司\",\"niudafu88.com\",\"粤ICP备17058651号-2\",\"杨生\",\"18122495739\",null,\"niudafu88@126.com\",null,1944,{\"171\":{\"274\":null}}]},\"pdt\":{\"cn.14.699\":{\"1\":[\"cn.14.197\",7,null,{\"_qqNo\":\"2303223610\",\"_plfContact\":\"服务商\"}]}},\"qlf\":{\"cn.14.699\":{\"5\":[[\"cn.14.78\",5,{\"_FN\":\"KgMiFwukhDFj9Qw8b28Soqvs3F6eC0Y2\",\"_FRN\":\"广告开通资质证明-营业执照-web\",\"_FEXT\":\"jpg\",\"_FS\":1083493}]],\"7\":[[\"cn.14.79\",5,{\"_FN\":\"sKCd301GLAUNAK9fllxCSCwnP0QHrERA\",\"_FRN\":\"TIM截图20181211173654\",\"_FEXT\":\"png\",\"_FS\":24270}]],\"6\":[[\"cn.14.82\",4,{\"_FN\":\"N3bLGA4qpf1RJYRX2j3txSqwL4ExwFKW\",\"_FRN\":\"腾讯社交广告开户对公账户\",\"_FEXT\":\"jpg\",\"_FS\":2331021}]],\"109\":[[\"cn.14.83\",5,{\"_FN\":\"McnRjWFdWPk1imRqL4WgtkRff1ZvVgIQ\",\"_FRN\":\"正面-web\",\"_FEXT\":\"jpg\",\"_FS\":518952}]],\"110\":[[\"cn.14.84\",5,{\"_FN\":\"GzkocpflrNkG6XP4sde9nzrIPety16P6\",\"_FRN\":\"反面-web\",\"_FEXT\":\"jpg\",\"_FS\":624058}]],\"113\":[[\"cn.14.85\",5,{\"_FN\":\"R4oBTAs2nuUf6kEeKTOYCWCpz6vrHn0w\",\"_FRN\":\"niudafu88.comICP备案信息截图（已盖章）\",\"_FEXT\":\"jpg\",\"_FS\":4978138}]]}},\"cl\":{\"cn.14.201047366\":[\"广州牛大福科技有限公司\"]},\"plfRecharge\":null,\"mpAdprd\":{\"mp\":{\"9\":[2,1,\"TSA-GDT-004\",\"App资源 | 白银Banner-B\",\"腾讯社交广告>网页广告>移动平台>移动banner 15字+15字文字链\",0.175,{\"n_txt_t\":1,\"txt_t_1\":{\"ic\":0,\"rg\":[1,15]},\"n_txt_d\":1,\"txt_d_1\":{\"ic\":0,\"rg\":[1,15]}}]},\"rlAdprdctg\":{\"9\":[1,2,3,4,5,6,7,8,9,10,11,12]}},\"mpUs\":{\"cn.14.201357041\":[\"牛大福科技\"]},\"mpAind\":{\"171\":\"商务服务\",\"274\":\"建站服务\"},\"mpAg\":{\"1944\":[\"广州市\",1943]},\"mpl\":{\"1944\":[\"广州市\",\"中国 广东省 广州市\"]}},\"_TT\":\"IA13GqZMpia7UuMvfSs2o8Dq4GIzhi0X\"}";
//                    }
                    LogUtils.e("httpHelper",string);
                    //回调result方法，用作处理特殊请求\


                    if(doInBackground){
                        mOnCompleteListener.onResult(string);
                    }else {
                        Message message = Message.obtain();
                        message.what = RESULT;
                        message.obj=string;
                        mHandler.sendMessage(message);
                    }

//                    if(httpCode==404){
//                        if(doInBackground){
//                            mOnCompleteListener.onError(404,"url未找到");
//                        }
//                        Message message = Message.obtain();
//                        message.what = FAIL;
//                        message.arg1 = 404;
//                        message.obj = "url未找到";
//                        mHandler.sendMessage(message);
//                        return;
//                    }

                    Gson gson = new Gson();
                    Type realType1 = mOnCompleteListener.getRealType();

                    Type type = type(BaseResult.class,realType1);
                    BaseResult<T> baseResult = gson.fromJson(string,type);
                    int code = baseResult.getCode();

                    if(code == 200 || code == 404){
                        if(doInBackground){//先回调处理，在让弹窗消失。因为回调处理可能会执行耗时操作，如果想要在回调里面做静默耗时操作。就新开线程
                            mOnCompleteListener.onSuccess(httpCode,code,baseResult);
                        }
                        Message message = Message.obtain();
                        message.what=SUCCESS;
                        message.arg1 = httpCode;
                        message.arg2 = code;
                        message.obj = baseResult;
                        mHandler.sendMessage(message);
                    }else {
                        String errStr = baseResult.getMsg();
                        if(doInBackground){
                            mOnCompleteListener.onError(code,errStr);
                        }
                        Message message = Message.obtain();
                        message.what=FAIL;
                        message.arg1 = code;
                        message.obj = errStr;
                        mHandler.sendMessage(message);
                    }
                }catch (SocketTimeoutException|JsonParseException|NullPointerException e){
                    e.printStackTrace();
                    String errMsg = "";
                    if(e instanceof SocketTimeoutException){
                        errMsg = "网络连接失败";
                    }else if(e instanceof JsonSyntaxException || e instanceof JsonParseException){
                        errMsg = "解析失败";
                    }else {
                        errMsg = "未知异常";
                    }
                    if(doInBackground){
                        mOnCompleteListener.onError(NET_ERROR_CODE,errMsg);
                    }
                    Message message = Message.obtain();
                    message.what = FAIL;
                    message.arg1 = NET_ERROR_CODE;
                    message.obj = errMsg;
                    mHandler.sendMessage(message);
                }
            }
        });

        return this;
    }




    //初始化hanler处理器
    private void initHandler(Context context) {
        mHandler = new Handler(context.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(isRemove){
                    return;
                }
                if(mContext!=null&&mContext instanceof BaseAppCompatActivity){
                    if (!((BaseAppCompatActivity)mContext).isAlive){//如果activity被销毁就不执行下面的回调
                        return;
                    }
                }
                if(msg.what==RESULT_START) {//表示请求刚开始
                    mOnCompleteListener.onStart();
                    return;
                }
                mOnCompleteListener.onEnd();//调用监听生命周期方法
                if(needShowPbActivity!=null){//代表开启了监听
                    if(isShowProgress){
                        needShowPbActivity.onRequestEnd(HttpHelper.this);
                    }
                }

                if(msg.what==FAIL){//请求失败,回调统一处理
                    if(mContext instanceof BaseAppCompatActivity){
                        ((BaseAppCompatActivity)mContext).onRequestError(msg.arg1,msg.obj.toString());
                    }
                }
                if(!doInBackground){
                    switch (msg.what){
                        case FAIL://请求失败
                            mOnCompleteListener.onError(msg.arg1,msg.obj.toString());
                            break;
                        case SUCCESS:
                            mOnCompleteListener.onSuccess(msg.arg1,msg.arg2, (BaseResult<T>) msg.obj);
                            break;
                        case RESULT:
                            mOnCompleteListener.onResult(msg.obj.toString());
                            break;
                    }
                }
            }
        };


    }


    public static void release(){
        if(mOkHttpClient!=null){
            mOkHttpClient.dispatcher().cancelAll();
            mOkHttpClient = null;
        }
    }

    public  interface HttpProgressListener{

        void onRequestStart(HttpHelper tag);//网络请求开始

        void onRequestEnd(HttpHelper tag);//网络请求结束,

        void onRequestError(int erroeCode, String errorMsg);//网络请求错误

    }

    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

    public static abstract class OnCompleteListener<T>{

        /*** 由doInBackground控制是否回调在主线程
         *
         *
         * */
        protected abstract void onSuccess(int httpCode, int code, BaseResult<T> result);

        /*** 由doInBackground控制是否回调在主线程
         *
         *
         *
         * */
        protected abstract void onError(int code,String errorMsg);

        /*** 由doInBackground控制是否回调在主线程
         *
         *  */
        protected void onResult(String result){

        }

        /*** 回调在主线程
         *
         * */
        protected void onStart(){

        }


        /*** 回调在主线程
         *
         * */
        protected void onEnd(){

        }
        /*** 执行在当前线程
         *
         * */
        protected boolean onNetworkStatus(int networkStatus){//当发起网络请求时调用，返回 true 发起网络请求，返回flase 取消网络请求
            if(networkStatus==NETWORK_NO){
                return false;
            }
            return true;
        }

        // 使用反射技术得到T的真实类型
        private Type getRealType(){
            // 获取当前new的对象的泛型的父类类型
            ParameterizedType pt = null;

            Type genericSuperclass = this.getClass().getGenericSuperclass();
            if(genericSuperclass instanceof ParameterizedType){
                pt = (ParameterizedType) genericSuperclass;
                // 获取第一个类型参数的真实类型
                Type type =  pt.getActualTypeArguments()[0];
                return type;
            }
            return genericSuperclass;
        }
    }


    public static int getNetWorkStatus(Context context){
        if(context == null){
            return NETWORK_WIFI;
        }
        ConnectivityManager  manager = (ConnectivityManager ) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            if(!networkInfo.isConnected()){
                return NETWORK_NO;
            }
            int networkInfoType = networkInfo.getType();
            if (networkInfoType == ConnectivityManager.TYPE_WIFI || networkInfoType == ConnectivityManager.TYPE_ETHERNET) {

                return NETWORK_WIFI;
            }else {
                return NETWOR_FLOW;
            }

        }else {
            return NETWORK_NO;
        }
    }


}