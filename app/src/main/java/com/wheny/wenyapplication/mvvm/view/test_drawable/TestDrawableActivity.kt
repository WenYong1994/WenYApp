package com.wheny.wenyapplication.mvvm.view.test_drawable

import android.animation.*
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.*
import androidx.room.Index
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.application.App
import com.wheny.whenylibrary.edslider.*


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
            .setAlignment(Align.CENTER, Align.CENTER)
            .setIconSize(46.dp)
            .setPadding(46.dp, 92.dp, 46.dp, 46.dp)
            .setDeterminePadding(40.dp, 40.dp)
            .setIconMargin(14.dp, 5.dp)
            .setBgPadding((14).dp, (14).dp)
            .setMargin(0f, 0f, 0f, 0f)
            .setSelectedOffset(46.dp / 8)
            .setSelectedItemScale(8f / 5)
            .setChoseMargin(true)
            .setClipChildren(true)
            .setItemDynamic(false)
            .setSelectedTime(1000 * 2)
            .setScrollActionThresholdTime(1000)
            .setMaxIcons(4)
            .setLimitMax(true)
            .setBgChange(false)
            .setSliderBackground(R.drawable.ed_slider_background)
            .addIcon(EdIcon(R.drawable.ganup_emo_1, ""))
            .addIcon(EdIcon(R.drawable.ganup_emo_2, ""))
            .addIcon(EdIcon(R.drawable.ganup_emo_3, ""))
            .addIcon(EdIcon(R.drawable.ganup_emo_4, ""))
            .addIcon(EdIcon(R.drawable.ganup_emo_5, ""))
            .addIcon(EdIcon(R.drawable.ganup_emo_5, ""))
            .addIcon(EdIcon(R.drawable.ganup_emo_5, ""))
            .addIcon(EdIcon(R.drawable.ganup_emo_5, ""))
            .addIcon(EdIcon(R.drawable.ganup_emo_5, ""))
            .addIcon(EdIcon(R.drawable.ganup_emo_5, ""))
            .addIcon(EdIcon(R.drawable.ganup_emo_5, ""))
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

        val button = findViewById<View>(R.id.button)
        button.setOnLongClickListener {
            edslider.show()
            button.visibility = View.GONE
            true
        }



        manager = EdSliderManager(object : OnSliderSelectedListener {
            override fun onDismiss(index: Int, longSelected: Int) {
                button.visibility = View.VISIBLE
                Toast.makeText(this@TestDrawableActivity, "selected: $index", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onSelectedChange(oldIndex: Int, newIndex: Int) {
                Log.e("onSelectedChange", "old:${oldIndex},newIndex:${newIndex}")
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

        edslider.dismiss()

        val circle = findViewById<CampSquareUserCircle>(R.id.camp_square_user_circle)
        circle.setItem(
            item1 = CircleItem(R.drawable.gan_up_square_avatar_test1, "国服打野"),
            item2 = CircleItem(R.drawable.gan_up_square_avatar_test2, "V10"),
            item3 = CircleItem(R.drawable.gan_up_square_avatar_test3, "省级诸葛亮")
        )


        tvBg.setOnClickListener {
//            finish()
            circle.startAni()
        }
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




