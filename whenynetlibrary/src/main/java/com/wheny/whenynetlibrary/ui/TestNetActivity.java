package com.wheny.whenynetlibrary.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chenenyu.router.annotation.InjectParam;
import com.chenenyu.router.annotation.Route;
import com.wheny.whenylibrary.TestAni;
import com.wheny.whenynetlibrary.R;

@TestAni(moduleName = "net")
@Route({"TestNetActivity", "TestNetActivity1"})
public class TestNetActivity extends AppCompatActivity {

    @InjectParam(key = "testP1")
    String testP1 = "";

    @InjectParam(key = "testP2")
    boolean testP2 = false;

    @InjectParam(key = "testP3")
    int testP3 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_net);
    }
}