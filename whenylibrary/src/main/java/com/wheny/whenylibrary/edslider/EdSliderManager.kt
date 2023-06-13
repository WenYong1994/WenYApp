package com.wheny.whenylibrary.edslider

import android.view.MotionEvent
import android.view.ViewGroup


/**
 * 类名：EdSliderManager
 * 包名：com.wheny.whenylibrary.edslider
 * 创建时间：2023/6/9 10:17
 * 创建人： WhenYoung
 * 描述：
 **/
class EdSliderManager(listener: OnSliderSelectedListener?) {
    private var layout: ViewGroup? = null
    private var view: EdSliderView? = null
    private var listener: OnSliderSelectedListener?
    private var showing = false

    init {
        this.listener = listener
    }

    fun dispatched(event: MotionEvent?): Boolean {
        // 这里因为 EdSliderManager.showing 会执行动画延迟关闭。所以两个showing状态不同步。这里使用内部的 showing来判断是否 响应事件
        if (view?.showing?.get() != true) return false
        view?.dispatchTouchEvent(event!!)
        return true
    }

    operator fun set(layout: ViewGroup?, view: EdSliderView?) {
        this.layout = layout
        this.view = view
    }

    fun show() {
        if (layout != null) if (view != null) {
            if (!showing) {
                showing = true
                view!!.show()
                layout!!.addView(view)
            }
        }
    }

    fun dismiss() {
        var index = -1
        if (layout != null) if (view != null) {
            index = view!!.getSelectedIndex()
            layout!!.removeView(view)
        }
        showing = false
        if (listener != null) listener!!.OnSelected(index)
    }

    /**
     * Register a listener to get event callback when selected an icon
     * @param listener event callback
     */
    fun setOnSliderSelectedListener(listener: OnSliderSelectedListener?) {
        this.listener = listener
    }
}