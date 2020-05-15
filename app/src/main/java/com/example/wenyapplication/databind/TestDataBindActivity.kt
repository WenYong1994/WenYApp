package com.example.wenyapplication.databind

import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.commonlibrary.rxjava.RxSchedulers
import com.example.wenyapplication.R
import com.example.wenyapplication.databinding.ActivityDataBindBinding
import com.example.wenyapplication.entity.UserEntity
import com.example.whenyannotationlib.TestAn
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.disposables.CompositeDisposable

public class TestDataBindActivity : AppCompatActivity() {
    @TestAn("ccccc")
    var s2=""

    var mCompositeDisposable = CompositeDisposable()
    var userEntity : UserEntity?= null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_bind)
        val subscribe = Flowable
                .create(FlowableOnSubscribe<UserEntity> { e ->
                    SystemClock.sleep(2000)
                    var userEntity = UserEntity()
                    userEntity.name="测试"
                    userEntity.age=12
                    Log.e("TestDataBindActivity","create"+Thread.currentThread().name)
                    e.onNext(userEntity)
                }, BackpressureStrategy.BUFFER)
                .compose(RxSchedulers.io_main())
                .subscribe {
                    Log.e("TestDataBindActivity","subscribe"+Thread.currentThread().name)
                    val appCompatActivity = DataBindingUtil.setContentView<ActivityDataBindBinding>(this, R.layout.activity_data_bind)
                    userEntity=it
                    appCompatActivity.userEntityViewModul = UserEntityViewModul(it)
                }
        mCompositeDisposable.add(subscribe)
    }


    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }

}