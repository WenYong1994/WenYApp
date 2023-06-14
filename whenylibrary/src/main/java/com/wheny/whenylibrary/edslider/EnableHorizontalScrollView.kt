package com.wheny.whenylibrary.edslider

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.MotionEvent
import android.widget.HorizontalScrollView
import android.widget.OverScroller


/**
 * 类名：EnableScrolleView
 * 包名：com.wheny.whenylibrary.edslider
 * 创建时间：2023/6/14 14:53
 * 创建人： WhenYoung
 * 描述：
 **/
class EnableHorizontalScrollView(context: Context) : HorizontalScrollView(context) {

    var enableScroll = true

    var lastScrollingTime = 0L

    var scrollListener: OnScrollChangeListener? = null

    var superScroller: OverScroller? = null

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (!enableScroll) {
            return true
        }
        return super.onTouchEvent(ev)
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                lastScrollingTime = System.currentTimeMillis()
                scrollListener?.onScrollChange(v, scrollX, scrollY, oldScrollX, oldScrollY)
            }
        }
        try {
            val field = HorizontalScrollView::class.java.getDeclaredField("mScroller")
            field.isAccessible = true
            superScroller = field.get(this) as? OverScroller
        }catch (e:java.lang.Exception){}

    }

    override fun setOnScrollChangeListener(l: OnScrollChangeListener?) {
        scrollListener = l
    }


    fun isScrolling(): Boolean {
        return superScroller?.isFinished == false
    }


}