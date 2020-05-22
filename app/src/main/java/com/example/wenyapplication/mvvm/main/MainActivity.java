package com.example.wenyapplication.mvvm.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;

import com.example.wenyapplication.R;
import com.example.wenyapplication.databind.TestDataBindActivity;
import com.example.wenyapplication.mvvm.view.LoginActivity;
import com.example.whenyannotationapilib.ViewModelInjector;
import com.example.whenyannotationlib.InjectViewModel;

public class MainActivity extends AppCompatActivity {

    @InjectViewModel(dataBindFieldName = "mainVm")
    MainVm mainVm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewDataBinding inject = ViewModelInjector.inject(this, R.layout.activity_main);

        TestFragment testFragment = new TestFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_fra,testFragment).commit();
    }


    public void setMainVm(MainVm mainVm) {
        this.mainVm = mainVm;
    }
}
