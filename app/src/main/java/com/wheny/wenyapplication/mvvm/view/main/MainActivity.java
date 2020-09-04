package com.wheny.wenyapplication.mvvm.view.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wheny.wenyapplication.R;
import com.wheny.wenyapplication.mvvm.view.TestFragment.TestFragmentActivity;
import com.wheny.wenyapplication.mvvm.view.testHanlder.TestHandlerActivity;
import com.wheny.wenyapplication.mvvm.view.test_delegation.TestDeleActivity;
import com.wheny.wenyapplication.mvvm.view.tf.TFActivity;
import com.wheny.wenyapplication.test_coroutines.TestCoroutinesActivity;
import com.wheny.whenyannotationapilib.ViewModelInjector;
import com.wheny.whenyannotationlib.InjectViewModel;
import com.wheny.whenylibrary.mvvm.contract.VmContract;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class MainActivity extends AppCompatActivity implements VmContract {



    @InjectViewModel(dataBindFieldName = "mainVm")
    MainVm<MainActivity> mainVm;

    boolean isAdded = true;
    boolean show = true;

    TestFragment testFragment;
    TestFragment2 testFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewModelInjector.inject(this, R.layout.activity_main);
        testFragment = new TestFragment();
        testFragment2 = new TestFragment2();
        findViewById(R.id.test_004).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestFragmentActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.test_005).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isAdded){
                    isAdded=true;
                    getSupportFragmentManager().beginTransaction().add(R.id.main_fra,testFragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.main_fra_2,testFragment2).commit();
                }else {
                    show= !show;
                    if(show){
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fra,testFragment).commit();
                    }else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fra,testFragment2).commit();
                    }
                }
            }
        });
        findViewById(R.id.test_007).setOnClickListener(v -> {
        });
        findViewById(R.id.test_008).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TestCoroutinesActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.test_009).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TestHandlerActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.test_010).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TFActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.test_011).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TestDeleActivity.class);
            startActivity(intent);
        });


    }

    public void setMainVm(MainVm mainVm) {
        this.mainVm = mainVm;
    }

//    public void setTestFraVm(TestFraVm testFraVm) {
//        this.testFraVm = testFraVm;
//    }

}
