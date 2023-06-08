package com.wheny.wenyapplication.mvvm.view.test_drawable

import android.animation.*
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.*
import androidx.core.content.ContextCompat
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.view.dp


/**
 * 类名：TestDrawableActivity1
 * 包名：com.wheny.wenyapplication.mvvm.view.test_drawable
 * 创建时间：2023/6/8 10:44
 * 创建人： WhenYoung
 * 描述：
 **/
class TestDrawableActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_drawable)
        val tvBg = findViewById<View>(R.id.tv_bg)
        PressedAnimatorHelper.bindPressedAnimator(
            tvBg,
            0.9f,
            1f,
            Color.parseColor("#FFFFFF"),
            Color.parseColor("#4DFFFFFF")
        )
        tvBg.setOnClickListener {
            finish()
        }
    }

}




