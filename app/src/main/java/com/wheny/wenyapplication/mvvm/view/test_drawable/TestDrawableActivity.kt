package com.wheny.wenyapplication.mvvm.view.test_drawable

import android.animation.*
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.*
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.application.App
import com.wheny.whenylibrary.edslider.Align
import com.wheny.whenylibrary.edslider.EdSliderBuilder
import com.wheny.whenylibrary.edslider.EdSliderManager
import com.wheny.whenylibrary.edslider.OnSliderSelectedListener


/**
 * 类名：TestDrawableActivity1
 * 包名：com.wheny.wenyapplication.mvvm.view.test_drawable
 * 创建时间：2023/6/8 10:44
 * 创建人： WhenYoung
 * 描述：
 **/
class TestDrawableActivity : AppCompatActivity() {
    private val edslider by lazy {
        EdSliderBuilder(this)
            .set(manager)
            .on(findViewById(R.id.button))
            .setAlignment(Align.CENTER, Align.TOP)
            .setIconSize(30.dp)
            .setPadding(30.dp, 60.dp, 30.dp, 20.dp)
            .setDeterminePadding(40.dp, 20.dp)
            .setIconMargin(5.dp, 5.dp)
            .setChoseMargin(false)
            .addIcon(R.drawable.ic_android)
            .addIcon(R.drawable.ic_heart)
            .addIcon(R.drawable.ic_android)
            .addIcon(R.drawable.ic_camera)
            .addIcon(R.drawable.ic_android)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_drawable)
        val tvBg = findViewById<View>(R.id.tv_bg)
//        PressedAnimatorHelper.bindPressedAnimator(
//            tvBg,
//            0.9f,
//            1f,
//            Color.parseColor("#FFFFFF"),
//            Color.parseColor("#4DFFFFFF")
//        )
        tvBg.setOnClickListener {
            finish()
        }
        val button = findViewById<View>(R.id.button)
        button.setOnLongClickListener {
            edslider.show()
            true
        }
    }

    val manager = EdSliderManager(object : OnSliderSelectedListener {
        override fun OnSelected(index: Int) {
            Toast.makeText(this@TestDrawableActivity, "selected: $index", Toast.LENGTH_SHORT)
                .show()
        }
    })

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        return if (manager.dispatched(event)) false else super.dispatchTouchEvent(event)
    }

}

val Int.dp: Float
    get() {
        val scale = App.getApplication().resources.displayMetrics.density
        return (this * scale + 0.5f)
    }




