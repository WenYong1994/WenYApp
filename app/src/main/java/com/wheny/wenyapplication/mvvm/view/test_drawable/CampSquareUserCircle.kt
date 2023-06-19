package com.wheny.wenyapplication.mvvm.view.test_drawable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.databinding.CampSquareUserCircleLayoutBinding


/**
 * 类名：CampSquarUserCircle
 * 包名：com.wheny.wenyapplication.mvvm.view.test_drawable
 * 创建时间：2023/6/16 10:44
 * 创建人： WhenYoung
 * 描述：
 **/
class CampSquareUserCircle(context: Context, attributeSet: AttributeSet?) :
    FrameLayout(context, attributeSet) {
    val binding =
        CampSquareUserCircleLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        clipChildren = false
    }


    fun setItem(item1: CircleItem, item2: CircleItem, item3: CircleItem) {
        binding.avatar1.setImageResource(item1.imageUrl)
        binding.tag1.text = item1.title

        binding.avatar2.setImageResource(item2.imageUrl)
        binding.tag2.text = item2.title

        binding.avatar3.setImageResource(item3.imageUrl)
        binding.tag3.text = item3.title
    }


    fun startAni() {
        appear(binding.itemLayout3, 500,0)
        appear(binding.itemLayout2, 500,500)
        appear(binding.itemLayout1, 500,2 * 500)
        appear(binding.helloLayout, 500,3 * 500)
    }

    private fun appear(view: View, duration: Long, delay: Long) {
        view.scaleX = 0f
        view.scaleY = 0f
        view.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setStartDelay(delay)
            .setDuration(duration)
            .start()
    }


    fun hideAll() {
        binding.itemLayout1.visibility = View.GONE
        binding.itemLayout2.visibility = View.GONE
        binding.itemLayout3.visibility = View.GONE
    }


}


data class CircleItem(@DrawableRes var imageUrl: Int, val title: String)
