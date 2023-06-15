package com.wheny.whenylibrary.edslider


/**
 * 类名：OnSliderSelectedListener
 * 包名：com.wheny.whenylibrary.edslider
 * 创建时间：2023/6/9 10:17
 * 创建人： WhenYoung
 * 描述：
 **/
interface OnSliderSelectedListener {

    fun onDismiss(currentSelectedIndex: Int, longSelectedIndex: Int)

    fun onSelectedChange(oldIndex: Int, newIndex: Int)

    fun onLongSelected(index: Int)

}