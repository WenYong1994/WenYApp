package com.wheny.wenyapplication.mvvm.view.test_drawable

import android.animation.*
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
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
            .on(findViewById(R.id.target_view))
            .setAlignment(Align.LEFT, Align.CENTER)
            .setIconSize(46.dp)
            .setPadding(23.dp, 92.dp, 23.dp, 46.dp)
            .setDeterminePadding(40.dp, 40.dp)
            .setIconMargin(14.dp, 5.dp)
            .setBgPadding((18).dp, (0).dp)
            .setMargin(0f, 0f, 0f, 0f)
            .setChoseMargin(true)
            .setSelectedTime(1000 * 2)
            .setScrollActionThresholdTime(1000)
            .setMaxIcons(3)
            .setLimitMax(true)
            .setBgChange(false)
            .setSliderBackground(R.drawable.ed_slider_background)
            .addIcon(R.drawable.ganup_emo_1)
            .addIcon(R.drawable.ganup_emo_2)
            .addIcon(R.drawable.ganup_emo_3)
            .addIcon(R.drawable.ganup_emo_1)
            .addIcon(R.drawable.ganup_emo_2)
            .addIcon(R.drawable.ganup_emo_3)
            .addIcon(R.drawable.ganup_emo_1)
            .addIcon(R.drawable.ganup_emo_2)
            .addIcon(R.drawable.ganup_emo_3)
            .addIcon(R.drawable.ganup_emo_1)
            .addIcon(R.drawable.ganup_emo_2)
            .addIcon(R.drawable.ganup_emo_3)
            .addIcon(R.drawable.ganup_emo_5)
            .build()
    }

    var manager: EdSliderManager? = null

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
            button.visibility = View.GONE
            true
        }


        manager = EdSliderManager(object : OnSliderSelectedListener {
            override fun onSelected(index: Int) {
                button.visibility = View.VISIBLE
                Toast.makeText(this@TestDrawableActivity, "selected: $index", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onLongSelected(index: Int) {
                Toast.makeText(this@TestDrawableActivity, "长按选中: $index", Toast.LENGTH_SHORT)
                    .show()
                val iv = findViewById<ImageView>(R.id.button)
                when (index) {
                    0 -> {
                        iv.setImageResource(R.drawable.ganup_emo_1)
                    }
                    1 -> {
                        iv.setImageResource(R.drawable.ganup_emo_2)
                    }
                    2 -> {
                        iv.setImageResource(R.drawable.ganup_emo_3)
                    }
                    3 -> {
                        iv.setImageResource(R.drawable.ganup_emo_4)
                    }
                }
//                manager?.dismiss()
            }
        })
    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        return if (manager?.dispatched(event) == true) false else super.dispatchTouchEvent(event)
    }

}

val Int.dp: Float
    get() {
        val scale = App.getApplication().resources.displayMetrics.density
        return (this * scale + 0.5f)
    }




