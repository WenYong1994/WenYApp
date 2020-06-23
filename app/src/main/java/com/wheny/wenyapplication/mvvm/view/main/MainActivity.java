package com.wheny.wenyapplication.mvvm.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.wheny.wenyapplication.R;
import com.wheny.whenyannotationapilib.ViewModelInjector;
import com.wheny.whenyannotationlib.InjectViewModel;
import com.wheny.whenylibrary.mvvm.contract.VmContract;

public class MainActivity extends AppCompatActivity implements VmContract {



    @InjectViewModel(dataBindFieldName = "mainVm")
    MainVm mainVm;

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

    }

    public void setMainVm(MainVm mainVm) {
        this.mainVm = mainVm;
    }

//    public void setTestFraVm(TestFraVm testFraVm) {
//        this.testFraVm = testFraVm;
//    }

}
