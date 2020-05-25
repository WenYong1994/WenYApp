package com.example.wenyapplication.mvvm.view.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wenyapplication.R;
import com.example.whenyannotationapilib.ViewModelInjector;
import com.example.whenyannotationlib.InjectViewModel;

public class MainActivity extends AppCompatActivity {

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
