package com.wheny.wenyapplication.mvvm.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.wheny.wenyapplication.R;
import com.wheny.whenyannotationapilib.ViewModelInjector;
import com.wheny.whenyannotationlib.InjectViewModel;
import com.wheny.whenylibrary.mvvm.contract.VmContract;

public class MainActivity extends AppCompatActivity implements VmContract {



    @InjectViewModel(dataBindFieldName = "mainVm")
    MainVm mainVm;
    @InjectViewModel(dataBindFieldName = "testFraVm")
    TestFraVm testFraVm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewModelInjector.inject(this, R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.main_fra,new TestFragment()).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_fra_2,new TestFragment2()).commit();
    }

    public void setMainVm(MainVm mainVm) {
        this.mainVm = mainVm;
    }

    public void setTestFraVm(TestFraVm testFraVm) {
        this.testFraVm = testFraVm;
    }

}
