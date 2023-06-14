package com.wheny.whenylibrary.edslider

import android.graphics.PointF
import android.graphics.RectF


/**
 * 类名：EdsliderItem
 * 包名：com.wheny.whenylibrary.edslider
 * 创建时间：2023/6/9 17:09
 * 创建人： WhenYoung
 * 描述：
 **/
interface EdSliderItemSliderListener {

    fun onAppear(index: Int, showIndex: Int, needAni: Boolean = false)

    fun onDisAppear(index: Int, showIndex: Int, needAni: Boolean = false)

    fun onSlider(index: Int, rectF: RectF, pointF: PointF)

    fun onSelectedChange(index: Int, selected: Boolean)

}