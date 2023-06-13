package com.wheny.whenylibrary.edslider

import android.content.Context
import android.graphics.Color
import android.graphics.PointF
import android.graphics.RectF
import android.text.Layout
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.core.view.size
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.wheny.whenylibrary.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.concurrent.atomic.AtomicBoolean


/**
 * 类名：EdSliderView
 * 包名：com.wheny.whenylibrary.edslider
 * 创建时间：2023/6/9 10:17
 * 创建人： WhenYoung
 * 描述：
 **/
class EdSliderView : ConstraintLayout {
    private var manager: EdSliderManager? = null
    private var boundary: RectF? = null
    private var index = 0
    private var flags: BooleanArray = booleanArrayOf()

    private var itemGroupLayout = LinearLayout(context).apply {
        gravity = Gravity.CENTER_VERTICAL
        id = R.id.item_group_layout
    }
    var itemLocationView = ConstraintLayout(context)
        .apply {
            id = R.id.item_location_layout
            clipChildren = false
        }
    var bgView = View(context)
    var groupLocation = IntArray(2)
    private var isReversed = false
    private var choseMargin = false
    var iconMarginHorizontal = 0f
    var iconMarginVertical = 0f
    var showing = AtomicBoolean(false)


    /**
     * 增加上下判定范围
     * */
    var determinePaddingTop = 0f

    /**
     * 增加上下判定范围
     * */
    var determinePaddingBottom = 0f

    /**
     * 记录上一次选择的Index 来判断三秒以上
     * */
    var lastIndex = -1

    /**
     * 记录一致Index开始没变化的时间
     * */
    var lastConstantIndexTime = 0L

    /**
     * 记录上一次长按选中的Index
     * */
    var lastLongSelectedIndex = -1

    /**
     * 选中时间 单位毫秒
     */
    var selectedTime = 1000 * 3L

    var timerJob: Job? = null


    constructor(context: Context) : super(context) {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        clipChildren = false
        clipToPadding = false
        index = -1
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (showing.get()) {
            when (event.action) {
                MotionEvent.ACTION_MOVE -> process(event.x, event.y)
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    dismiss()
                }
            }
        }
        return super.dispatchTouchEvent(event)

    }

    /**
     * Generate the view
     * @param builder the configs
     */
    fun build(builder: EdSliderBuilder) {
        layoutParams = ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        itemGroupLayout.orientation = LinearLayout.HORIZONTAL
        clipChildren = false
        clipToPadding = false
        itemGroupLayout.clipChildren = false
        itemGroupLayout.clipToPadding = false
        isReversed = builder.isReversed
        iconMarginHorizontal = builder.iconMarginHorizontal
        iconMarginVertical = builder.iconMarginVertical
        determinePaddingTop = builder.determinePaddingTop
        determinePaddingBottom = builder.determinePaddingBottom
        for (item in builder.list!!) {
            val itemView = EdSliderItem(
                context,
                builder.size,
                builder.iconMarginHorizontal,
                builder.iconMarginVertical,
                item,
                isReversed
            )
            itemGroupLayout.addView(itemView)
        }


        itemLocationView.layoutParams =
            LayoutParams(builder.getItemLayoutWidth(), builder.getItemLayoutHeight()).apply {
                startToStart = LayoutParams.PARENT_ID
                topToTop = LayoutParams.PARENT_ID
                endToEnd = LayoutParams.PARENT_ID
                bottomToBottom = LayoutParams.PARENT_ID

                marginStart = builder.paddingStart.toInt()
                marginEnd = builder.paddingEnd.toInt()
                topMargin = builder.paddingTop.toInt()
                bottomMargin = builder.paddingBottom.toInt()
            }

        bgView.setBackgroundResource(builder.backgroundResId)
        bgView.layoutParams = LayoutParams(
            0,
            (builder.size + iconMarginVertical + iconMarginVertical).toInt()
        ).apply {
            startToStart = itemGroupLayout.id
            endToEnd = itemGroupLayout.id
            topToTop = itemGroupLayout.id
            bottomToBottom = itemGroupLayout.id
        }

        itemGroupLayout.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {

            when (builder.aligns[0]) {
                Align.LEFT -> {
                    startToStart = itemLocationView.id
                    topToTop = itemLocationView.id
                    bottomToBottom = itemLocationView.id
                }
                Align.CENTER -> {
                    startToStart = itemLocationView.id
                    endToEnd = itemLocationView.id
                    topToTop = itemLocationView.id
                    bottomToBottom = itemLocationView.id
                }
                Align.RIGHT -> {
                    endToEnd = itemLocationView.id
                    topToTop = itemLocationView.id
                    bottomToBottom = itemLocationView.id
                }
            }
        }

        addView(itemLocationView)
        addView(bgView)
        addView(itemGroupLayout)
        manager = builder.manager
        flags = BooleanArray(builder.list!!.size)
        boundary = builder.determineBoundary
        choseMargin = builder.choseMargin
        selectedTime = builder.selectedTime
    }

    /**
     * Animate the views too look more lively
     */
    fun show() {
        showing.set(true)
        initTimer()
        bgView.scaleY = 0f
        bgView.animate().scaleY(1f).setDuration(150).start()
        for (i in 0 until itemGroupLayout.childCount) {
            (itemGroupLayout.getChildAt(i) as? EdSliderItemSliderListener)?.apply {
                onAppear(i)
            }
        }
    }

    /**
     * Animate the views too look more lively
     */
    fun dismiss() {
        showing.set(false)
        for (i in 0 until itemGroupLayout.childCount) {
            (itemGroupLayout.getChildAt(i) as? EdSliderItemSliderListener)?.apply {
                onDisAppear(i)
            }
        }
        // hide view when finish animating
        postDelayed({
            bgView.animate().scaleY(0f).setDuration(150).start()
            postDelayed({
                manager?.instantDismiss()
            }, 150)
        }, (150 * itemGroupLayout.childCount).toLong())
        timerJob?.cancel()
    }

    /**
     * Animate when touch move
     * @param eventX touch x
     * @param eventY touch y
     */
    private fun process(eventX: Float, eventY: Float) {
        var x = eventX
        var y = eventY
        // 转换为edsliderView父布局 内部滑动的事件 xy  相对布 的xy
        x -= groupLocation[0]
        y -= groupLocation[1]
        index = -1
        // 转换为edsliderView内部的 x y  相对布 的xy
        x -= getX()
        y -= getY()
        // 转换为itemGroupLayout内部的 x y  相对布 的xy
        x -= itemGroupLayout.x
        y -= itemGroupLayout.y

        index = -1
        index = checkPointPosition(x, y)
//        if (boundary?.contains(x, y) == true) {
//            index = Math.floor((x / (boundary!!.width() / flags.size)).toDouble()).toInt()
//        }
        if (index >= 0 && index < flags.size) {
            // enlarge
            if (!flags[index]) {
                // avoid duplicate
                flags[index] = true
                (itemGroupLayout.getChildAt(index) as? EdSliderItemSliderListener)?.apply {
                    onSelectedChange(index, true)
                }
            }
        }

        // reduce any enlarged icon
        for (i in flags.indices) {
            // don't reduce the current icon
            if (i == index) continue
            if (flags[i]) {
                flags[i] = false
                (itemGroupLayout.getChildAt(i) as? EdSliderItemSliderListener)?.apply {
                    onSelectedChange(i, false)
                }
            }
        }


    }

    private fun checkPointPosition(x: Float, y: Float): Int {
        var index = -1
        for (i in 0 until itemGroupLayout.childCount) {
            val rectF = RectF()
            val child = itemGroupLayout.getChildAt(i)
            // 这里需要加margin不
            rectF.set(
                child.left + if (choseMargin) 0f else iconMarginHorizontal,
                child.top - determinePaddingTop,
                child.right - if (choseMargin) 0f else iconMarginHorizontal,
                child.bottom + determinePaddingBottom,
            )
            if (rectF.contains(x, y)) {
                index = i
            }
            // 执行监听
            (child as EdSliderItemSliderListener).onSlider(i, rectF, PointF(x, y))
        }
        return index
    }


    fun getSelectedIndex(): Int {
        return index
    }


    private fun initTimer() {
        lastIndex = -1
        lastConstantIndexTime = -1
        lastLongSelectedIndex = -1
        (context as? FragmentActivity)?.lifecycleScope?.launch {
            while (showing.get()) {
                delay(100)
                Log.e("initTimer","lastIndex${lastIndex},index:${index},lastConstantIndexTime:${lastConstantIndexTime},lastLongSelectedIndex:${lastLongSelectedIndex}")
                val current = System.currentTimeMillis()
                //检查选中 index
                if (index == -1 || index != lastIndex){
                    lastIndex = index
                    lastConstantIndexTime = current
                    lastLongSelectedIndex = -1
                    continue
                }
                if(index == lastLongSelectedIndex ){
                    lastConstantIndexTime = current
                    continue
                }
                //选中变化了
                if (current - lastConstantIndexTime > selectedTime) {
                    manager?.onIndexLongSelected(index)
                    lastLongSelectedIndex = index
                }
            }
        }?.start()
    }

}