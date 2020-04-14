package com.example.commonlibrary.bean;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.commonlibrary.http.HttpHelper;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public abstract class BaseAppCompatActivity extends AppCompatActivity implements HttpHelper.HttpProgressListener {
    public boolean isAlive;
    Dialog mPd;
    protected Activity activity;
    ArrayList<HttpHelper> needShowPbHttpTagList;//用来记录目前有哪些需要显示progressDialog的请求列表


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        isAlive =true;
        needShowPbHttpTagList =new ArrayList<>();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isAlive =false;
        for(HttpHelper httpHelper:needShowPbHttpTagList){
            httpHelper.remove();
        }
        needShowPbHttpTagList.clear();
    }



    /**
     * 多种隐藏软件盘方法的其中一种
     *
     */
    protected void hideSoftInput() {
        View v = getCurrentFocus();
        if(v!=null){
            IBinder token = v.getWindowToken();
            if (token != null) {
                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(token,
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }



    //重新跳转登录页面鉴权
    public abstract void reLogin();

//    {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(activity,LoginActivity.class);
//                intent.putExtra(EnumConstant.INTENT_ACTION,EnumConstant.INTENT_ACTION_RE_LOGIN);
//                startActivity(intent);
//            }
//        });
//    }



    @Override
    public void onRequestStart(HttpHelper tag) {
        //显示
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                needShowPbHttpTagList.add(tag);
                showPb();
            }
        });
    }

    protected void showPb() {
        if(mPd != null){
            mPd.show();
        }
    }




    protected void dismissPb() {
        if(mPd!=null&&mPd.isShowing()){
            mPd.dismiss();
        }
    }

    @Override
    public void onRequestEnd(HttpHelper tag) {
        //停止显示
        runOnUiThread(() -> {
            needShowPbHttpTagList.remove(tag);
            if(needShowPbHttpTagList.size()==0){//没有需要显示pb的请求了才弹窗消失
                dismissPb();
            }
        });
    }

    @Override
    public void onRequestError(int erroeCode, String errorMsg) {
        if(erroeCode == -275){//登录过期
            reLogin();
        }
    }


    protected void setLoadingDialog(Dialog dialog){
        this.mPd = dialog;
        if(mPd!=null){
            mPd.setOnDismissListener(dialog1 -> {
                if(needShowPbHttpTagList!=null){
                    for(HttpHelper helper:needShowPbHttpTagList){
                        helper.remove();
                    }
                }
            });
        }
    }

    @SuppressWarnings("all")
    protected <T extends Activity> T getActivity(){
        return (T) activity;
    }
}
