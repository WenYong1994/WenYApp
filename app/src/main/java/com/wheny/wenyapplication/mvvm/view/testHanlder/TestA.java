package com.wheny.wenyapplication.mvvm.view.testHanlder;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;

/**
 * 类名：TestA
 * 包名：com.wheny.wenyapplication.mvvm.view.testHanlder
 * 创建时间：2022/11/22 16:09
 * 创建人： WhenYoung
 * 描述：
 **/
public class TestA {

    public static void test(){
        Utils.INSTANCE.callTest((x,y,c)->{
            if(x>y){
                return true;
            }else {
                return false;
            }
        });

    }

    public static void test2(FragmentActivity activity){
        Utils.INSTANCE.test2(1, 2, new Continuation<Object>() {
            @NonNull
            @Override
            public CoroutineContext getContext() {
                return LifecycleOwnerKt.getLifecycleScope(activity).getCoroutineContext();
            }

            @Override
            public void resumeWith(@NonNull Object o) {
                Log.d("heheh",o.toString());
            }

        });
    }
}
