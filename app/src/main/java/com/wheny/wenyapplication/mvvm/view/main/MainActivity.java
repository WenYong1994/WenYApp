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

    boolean isAdded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewModelInjector.inject(this, R.layout.activity_main);
        findViewById(R.id.test_004).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAdded){
                    return;
                }
                isAdded=true;
                getSupportFragmentManager().beginTransaction().add(R.id.main_fra,new TestFragment()).commit();
                getSupportFragmentManager().beginTransaction().add(R.id.main_fra_2,new TestFragment2()).commit();
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
