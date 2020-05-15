package com.example.wenyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.commonlibrary.annotation_api.ViewInject;
import com.example.commonlibrary.annotation_api.ViewInjector;
import com.example.commonlibrary.rxjava.RxSchedulers;
import com.example.netlibrary.api.ApiService;
import com.example.netlibrary.data.BaseResp;
import com.example.netlibrary.entity.UserInfo;
import com.example.netlibrary.manager.RetrofitServiceManager;
import com.example.wenyapplication.databind.TestDataBindActivity;
import com.example.wenyapplication.mvvm.view.LoginActivity;
import com.example.whenyannotationlib.TestAn;

import org.reactivestreams.Publisher;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @TestAn("哈哈哈")
    public String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewInjector.injectView(this);

        findViewById(R.id.btn_dataBinding)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, TestDataBindActivity.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.btn_mvvm)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.test_net)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testNet();
                    }
                });
    }

    private void testNet(){
        Log.e("main","start");
        ApiService apiService = RetrofitServiceManager.getInstance().create(ApiService.class);
        String json = "{\"channel\":\"android\",\"data\":\"{\\\"password\\\":\\\"123456\\\",\\\"os\\\":\\\"android\\\",\\\"uid\\\":0,\\\"username\\\":\\\"kred001\\\"}\",\"salt\":\"064797\",\"service\":\"functionaryLoginService\",\"test\":true,\"time\":1589357972,\"version\":\"1.0\"}";
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        Flowable<BaseResp<UserInfo>> login = apiService.login(body);
        Disposable subscribe = login
                .flatMap((Function<BaseResp<UserInfo>, Publisher<String>>) userInfoBaseResp -> Flowable.just(userInfoBaseResp.toString()))
                .compose(RxSchedulers.io_main())
                .subscribe(userInfoBaseResp -> {



                });
    }

}
