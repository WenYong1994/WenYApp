package com.wheny.wenyapplication.mvvm.view.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.databinding.ActivityTestDataBindingBinding

class TestDataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test_data_binding)
        val contentView = DataBindingUtil.setContentView<ActivityTestDataBindingBinding>(this, R.layout.activity_test_data_binding)
        contentView.testVm = TestVm()

    }
}
