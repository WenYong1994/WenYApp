package com.wheny.whenylibrary.edslider

import android.content.Context
import android.graphics.Color
import android.graphics.PointF
import android.graphics.RectF
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.wheny.whenylibrary.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.max
import kotlin.math.min


/**
 * 类名：EdSliderView
 * 包名：com.wheny.whenylibrary.edslider
 * 创建时间：2023/6/9 10:17
 * 创建人： WhenYoung
 * 描述：
 **/
class EdSliderView : ConstraintLayout {
    private var manager: EdSliderManager? = null
    private var selectedIndex = 0
    private var flags: BooleanArray = booleanArrayOf()

    private var itemGroupLayout = LinearLayout(context).apply {
        gravity = Gravity.CENTER_VERTICAL
        id = R.id.item_group_layout
    }
    private var itemLocationView = View(context).apply {
        id = R.id.item_location_layout
        clipChildren = false
    }

    private var itemScrollView = EnableHorizontalScrollView(context).apply {
        id = R.id.item_scroll_layout
        clipChildren = false
        isEnabled = false
        enableScroll = false
        isHorizontalScrollBarEnabled = false
        overScrollMode = OVER_SCROLL_NEVER
    }


    var hintTxt = TextView(context).apply {
        id = R.id.slider_view_hint_txt
    }

    var bgView = View(context)
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
     * 记录上一次停留在某个action 没变化的开始时间
     * */
    var lastScrollActionTime = 0L

    /**
     * 选中时间 单位毫秒
     */
    var selectedTime = 1000 * 3L


    var scrollAction = ScrollAction.NONE

    /**
     * 是否限制 最大个数
     * */
    var limitMax = false

    var builder: EdSliderBuilder? = null

    var timerJob: Job? = null

    private val mHandler = Handler(Looper.getMainLooper())


    private val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    var currentPage = 0

    constructor(context: Context) : super(context) {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        clipChildren = false
        clipToPadding = false
        selectedIndex = -1
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
        this.builder = builder
        limitMax = builder.limitMax
        layoutParams = ViewGroup.LayoutParams(builder.getViewWidth(), builder.getViewHeight())
        itemGroupLayout.orientation = LinearLayout.HORIZONTAL
        itemGroupLayout.setPadding(
            builder.bgPaddingStart.toInt(), 0, builder.bgPaddingEnd.toInt(), 0
        )

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

        hintTxt.layoutParams =
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                startToStart = itemLocationView.id
                endToEnd = itemLocationView.id
                bottomToTop = itemLocationView.id
                // todo 换算成 dp来计算
                bottomMargin = 60
            }
        hintTxt.setText("滑动选择")

        itemScrollView.layoutParams =
            LayoutParams(builder.getItemLayoutWidth(), LayoutParams.WRAP_CONTENT).apply {
                startToStart = itemLocationView.id
                topToTop = itemLocationView.id
                endToEnd = itemLocationView.id
                bottomToBottom = itemLocationView.id
            }


        bgView.setBackgroundResource(builder.backgroundResId)
        bgView.layoutParams = LayoutParams(
            0, (builder.size + iconMarginVertical + iconMarginVertical).toInt()
        ).apply {
            val id = if (builder.bgChange && !limitMax) {
                itemGroupLayout.id
            } else {
                itemLocationView.id
            }
            startToStart = id
            endToEnd = id
            topToTop = id
            bottomToBottom = id
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
//        bgView.setBackgroundColor(Color.parseColor("#88000000"))
//        setBackgroundColor(Color.parseColor("#8800ff00"))
//        itemScrollView.setBackgroundColor(Color.parseColor("#88000000"))
//        itemGroupLayout.setBackgroundColor(Color.parseColor("#88000000"))
        addView(itemLocationView)
        addView(bgView)
        addView(hintTxt)
        if (limitMax) {
            addView(itemScrollView)
            itemScrollView.addView(itemGroupLayout)
        } else {
            addView(itemGroupLayout)
        }
        manager = builder.manager
        flags = BooleanArray(builder.list!!.size)
        choseMargin = builder.choseMargin
        selectedTime = builder.selectedTime
    }

    /**
     * Animate the views too look more lively
     */
    fun show() {
        if (showing.get()) {
            return
        }
        builder?.adjustPosition()
        showing.set(true)
        initTimer()
        bgView.scaleY = 0f
        bgView.animate().scaleY(1f).setDuration(150).start()

        val maxIcons = (builder?.maxIcons ?: 0)
        val aniStartIndex = max(getCurrentPageFirstIndex() - 1, 0)
        val aniEndIndex =
            min(getCurrentPageLastIndex() + 1, itemGroupLayout.childCount - 1)
        for (i in 0 until itemGroupLayout.childCount) {
            if (limitMax && itemGroupLayout.childCount > maxIcons) {
                (itemGroupLayout.getChildAt(i) as? EdSliderItemSliderListener)?.apply {
                    if (i in aniStartIndex..aniEndIndex) {
                        onAppear(i, i - aniStartIndex, true)
                    } else {
                        onAppear(i, -1, false)
                    }
                }
            } else {
                (itemGroupLayout.getChildAt(i) as? EdSliderItemSliderListener)?.apply {
                    onAppear(i, i, true)
                }
            }
        }
    }

    /**
     * Animate the views too look more lively
     */
    fun dismiss() {
        if (!showing.get()) {
            return
        }
        showing.set(false)
        var aniSize = itemGroupLayout.childCount
        val maxIcons = (builder?.maxIcons ?: 0)
        if (limitMax && itemGroupLayout.childCount > maxIcons) {
            val aniStartIndex = max(getCurrentPageFirstIndex() - 1, 0)
            val aniEndIndex =
                min(getCurrentPageLastIndex() + 1, itemGroupLayout.childCount - 1)
            aniSize = aniEndIndex - aniStartIndex + 1
            for (i in 0 until itemGroupLayout.childCount) {
                (itemGroupLayout.getChildAt(i) as? EdSliderItemSliderListener)?.apply {
                    // 显示中的需要执行 动画
                    if (i in aniStartIndex..aniEndIndex) {
                        onDisAppear(i, i - aniStartIndex, true)
                    } else {
                        // 没显示中的 不需要执行动画
                        (itemGroupLayout.getChildAt(i) as? EdSliderItemSliderListener)?.apply {
                            onDisAppear(i, -1, false)
                        }
                    }
                }
            }
        } else {
            for (i in 0 until itemGroupLayout.childCount) {
                (itemGroupLayout.getChildAt(i) as? EdSliderItemSliderListener)?.apply {
                    onDisAppear(i, i, true)
                }
            }
        }
        mHandler.postDelayed(
            {
                bgView.animate().scaleY(0f).setDuration(150).start()
                mHandler.postDelayed({
                    manager?.instantDismiss()
                }, 150)
            }, (150 * aniSize).toLong()
        )
        timerJob?.cancel()

    }

    /**
     * Animate when touch move
     * @param eventX touch x
     * @param eventY touch y
     */
    private fun process(eventX: Float, eventY: Float) {
        if (itemScrollView.isScrolling()) {
            return
        }
        val groupLocation = IntArray(2)
        // 转化为相对布局
        itemGroupLayout.getLocationOnScreen(groupLocation)
        val x = eventX - groupLocation[0]
        val y = eventY - groupLocation[1]
        val oldSelected = selectedIndex
        selectedIndex = checkPointPosition(x, y)
        if (selectedIndex >= 0 && selectedIndex < flags.size) {
            // enlarge
            if (!flags[selectedIndex]) {
                // avoid duplicate
                flags[selectedIndex] = true
                vibrator()
                (itemGroupLayout.getChildAt(selectedIndex) as? EdSliderItemSliderListener)?.apply {
                    onSelectedChange(selectedIndex, true)
                }
                manager?.onSelectedChange(oldSelected, selectedIndex)
            }
        }

        // reduce any enlarged icon
        for (i in flags.indices) {
            // don't reduce the current icon
            if (i == selectedIndex) continue
            if (flags[i]) {
                flags[i] = false
                (itemGroupLayout.getChildAt(i) as? EdSliderItemSliderListener)?.apply {
                    onSelectedChange(i, false)
                }
            }
        }
        checkHintTxt()
        checkScrollAction(x, y)
    }

    private fun vibrator() {
        val pattern = longArrayOf(0L, 30L)
        vibrator.vibrate(pattern, -1)
    }

    private fun checkPointPosition(x: Float, y: Float): Int {
        var index = -1
        val rectF = RectF()
        // 先判断是否在可选中范围外了
        for (i in 0 until itemGroupLayout.childCount) {
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
        // 如果限制最大个数， 超过这一个最大个数的都不触发选中，只触发当前页的
        if (limitMax) {
            // 超出当前页了
            if (index > getCurrentPageLastIndex()) {
                index = -1
            }
            // 小于当前页了
            if (index < getCurrentPageFirstIndex()) {
                index = -1
            }
        }
        return index
    }

    fun getSelectedIndex(): Int {
        return selectedIndex
    }

    private fun checkHintTxt() {
        if (selectedIndex == -1) {
            hintTxt.setText("松手取消")
        } else {
            //判断不同状态
            if (lastLongSelectedIndex != -1) {
                hintTxt.setText("松手发送，左右滑动选择其他")
            } else {
                hintTxt.setText("滑动选择")
            }
        }
    }

    private fun checkScrollAction(x: Float, y: Float) {
        var newAction = ScrollAction.NONE
        val current = System.currentTimeMillis()
        if (selectedIndex != -1) {
            scrollAction = newAction
            lastScrollActionTime = current
            return
        }
        val realX = x - itemScrollView.scrollX
        if (realX < 0) {
            newAction = ScrollAction.LEFT
        }
        if (realX > itemLocationView.width) {
            newAction = ScrollAction.RIGHT
        }
        if (newAction != scrollAction) {
            scrollAction = newAction
            lastScrollActionTime = current
        }
    }

    private fun initTimer() {
        lastIndex = -1
        lastConstantIndexTime = -1
        lastLongSelectedIndex = -1
        (context as? FragmentActivity)?.lifecycleScope?.launch {
            while (showing.get()) {
                delay(10)
                val current = System.currentTimeMillis()

                //检测需要下一页不
                if (current - lastScrollActionTime > (builder?.scrollActionThresholdTime
                        ?: 100000L)
                ) {
                    if (scrollAction == ScrollAction.RIGHT || scrollAction == ScrollAction.LEFT) {
                        lastScrollActionTime = current
                        scrollPage()
                    }
                }
                //检查选中 index
                if (selectedIndex == -1 || selectedIndex != lastIndex) {
                    lastIndex = selectedIndex
                    lastConstantIndexTime = current
                    lastLongSelectedIndex = -1
                    continue
                }
                if (selectedIndex == lastLongSelectedIndex) {
                    lastConstantIndexTime = current
                    continue
                }
                //选中变化了
                if (current - lastConstantIndexTime > selectedTime) {
                    manager?.onIndexLongSelected(selectedIndex)
                    lastLongSelectedIndex = selectedIndex
                    checkHintTxt()
                }

            }
        }?.start()
    }

    private fun scrollPage() {
        var targetPage = currentPage
        val maxPage = (builder!!.list!!.size + (builder!!.maxIcons - 1)) / builder!!.maxIcons - 1
        val minPage = 0
        when (scrollAction) {
            ScrollAction.RIGHT -> {
                targetPage = min(currentPage + 1, maxPage)
            }
            ScrollAction.LEFT -> {
                targetPage = max(currentPage - 1, minPage)
            }
        }
        if (targetPage == currentPage) {
            return
        }
        val targetX = targetPage * (builder!!.getItemSize() * builder!!.maxIcons)
        vibrator()
        itemScrollView.smoothScrollTo(targetX.toInt(), 0)
        currentPage = targetPage
    }


    /**
     * 获取当前page 的第一个index
     * */
    private fun getCurrentPageFirstIndex(): Int {
        return getCurrentPageLastIndex() - ((builder?.maxIcons ?: 0) - 1)
    }

    /**
     * 获取当前page 最后一个的index
     * */
    private fun getCurrentPageLastIndex(): Int {
        val lastIndex = (currentPage + 1) * (builder?.maxIcons ?: 0)
        if (lastIndex > (builder?.list?.size ?: 0)) {
            return (builder?.list?.size ?: 0) - 1
        }
        return lastIndex - 1
    }

}