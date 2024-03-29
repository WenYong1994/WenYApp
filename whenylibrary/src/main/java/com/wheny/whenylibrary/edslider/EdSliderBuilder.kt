package com.wheny.whenylibrary.edslider

import android.app.Activity
import android.content.Context
import android.graphics.RectF
import android.view.View
import android.view.ViewGroup
import com.wheny.whenylibrary.R


/**
 * 类名：EdSliderBuilder
 * 包名：com.wheny.whenylibrary.edslider
 * 创建时间：2023/6/9 10:16
 * 创建人： WhenYoung
 * 描述：
 **/
class EdSliderBuilder {
    private var context: Context? = null
    var manager: EdSliderManager? = null

    var view: EdSliderView? = null
    var layout: ViewGroup? = null
    var target: View? = null
    var visionBoundary: RectF? = null

    var list: ArrayList<EdIcon> = arrayListOf()
    var maxIcons = Int.MAX_VALUE

    var limitMax = false

    var bgChange = true

    var backgroundResId = 0
    var size = 0f

    /**
     * icon 的margin
     * */
    var iconMarginHorizontal = 0f
    var iconMarginVertical = 0f
    var paddingBottom = 0f
    var paddingTop = 0f
    var paddingStart = 0f
    var paddingEnd = 0f
    var aligns: Array<Align> = arrayOf()

    var marginTop = 0f

    var marginBottom = 0f

    var marginStart = 0f

    var marginEnd = 0f

    /**
     * 是否反方向，上下反转
     * */
    var isReversed = false

    /**
     * 增加上下判定范围
     * */
    var determinePaddingTop = 0f

    /**
     * 增加上下判定范围
     * */
    var determinePaddingBottom = 0f

    /**
     * 当手指放到margin时 是否判断选中
     * */
    var choseMargin = false

    var bgPaddingStart = 0f

    var bgPaddingEnd = 0f

    /**
     * 选中时间 单位毫秒
     */
    var selectedTime = 1000 * 3L

    /**
     * 滑动切换page 单位毫秒
     */
    var scrollActionThresholdTime = 1000 * 1L

    var clipChildren = true

    var selectedItemScale = 1.5f

    var selectedOffset = 1.5f

    /**
     * item选中变化的时候，整个容器宽度 是否跟随一起变化
     *
     * true 会跟这一起变话
     * false 不会跟这变化，
     * */
    var itemDynamic = false

    constructor(context: Context) {
        this.context = context
        view = EdSliderView(context)
        backgroundResId = R.drawable.background
        size = Util.dimenToPx(context, 32f)
        iconMarginVertical = Util.dimenToPx(context, 4f)
        iconMarginHorizontal = Util.dimenToPx(context, 4f)
        // we use the icon size as padding
        paddingStart = size
        paddingEnd = size
        paddingTop = size * 2
        paddingBottom = size * 2
        determinePaddingBottom = size / 2
        determinePaddingTop = size / 2
        aligns = arrayOf(Align.LEFT, Align.TOP)
        isReversed = false
        layout = (context as Activity).findViewById<View>(android.R.id.content) as ViewGroup

    }

    fun set(manager: EdSliderManager?): EdSliderBuilder {
        this.manager = manager
        return this
    }

    fun on(target: View?): EdSliderBuilder {
        this.target = target
        return this
    }

    /**
     * Add icon to view
     * @param id drawable id
     * @return continue building
     */
    fun addIcon(item: EdIcon): EdSliderBuilder {
        list.add(item)
        return this
    }

    fun addIcons(items: Collection<EdIcon>): EdSliderBuilder {
        list.addAll(items)
        return this
    }

    fun maxIcon(maxIcons: Int): EdSliderBuilder {
        this.maxIcons = maxIcons
        return this
    }

    fun setClipChildren(clipChildren: Boolean): EdSliderBuilder {
        this.clipChildren = clipChildren
        return this
    }

    fun setItemDynamic(itemDynamic: Boolean): EdSliderBuilder {
        this.itemDynamic = itemDynamic
        return this
    }

    fun setSelectedItemScale(selectedItemScale:Float): EdSliderBuilder{
        this.selectedItemScale = selectedItemScale
        return this
    }

    fun setSelectedOffset(selectedOffset:Float): EdSliderBuilder{
        this.selectedOffset = selectedOffset
        return this
    }

    /**
     * Icon size, default 32dp
     * @param dimen dp
     * @return continue building
     */
    fun setIconSize(dimen: Float): EdSliderBuilder {
        size = dimen
        return this
    }

    /**
     * Margin around each icon, default 4dp
     * @param dimen dp
     * @return continue building
     */
    fun setIconMargin(horizontal: Float, vertical: Float): EdSliderBuilder {
        iconMarginHorizontal = horizontal
        iconMarginVertical = vertical
        return this
    }

    /**
     * 设置上下 判定范围的 padding
     *
     *  默认 determinePaddingTop = size determinePaddingBottom = size/2
     */
    fun setDeterminePadding(
        determinePaddingTop: Float,
        determinePaddingBottom: Float
    ): EdSliderBuilder {
        this.determinePaddingTop = determinePaddingTop
        this.determinePaddingBottom = determinePaddingBottom
        return this
    }

    /**
     * Provide a resource drawable for slider background
     * @param id resource id
     * @return continue building
     */
    fun setSliderBackground(id: Int): EdSliderBuilder {
        backgroundResId = id
        return this
    }

    /**
     * Align sliderview around the target. default LEFT, TOP
     * @param horizontal LEFT, RIGHT, CENTER
     * @param vertical TOP, BOTTOM
     * @return continue building
     */
    fun setAlignment(horizontal: Align, vertical: Align): EdSliderBuilder {
        aligns[0] = horizontal
        aligns[1] = vertical
        return this
    }

    /**
     */
    fun setPadding(start: Float, top: Float, end: Float, bottom: Float): EdSliderBuilder {
        paddingStart = start
        paddingEnd = end
        paddingTop = top
        paddingBottom = bottom
        return this
    }

    fun setBgPadding(start: Float, end: Float): EdSliderBuilder {
        bgPaddingStart = start
        bgPaddingEnd = end
        return this
    }

    fun setChoseMargin(choseMargin: Boolean): EdSliderBuilder {
        this.choseMargin = choseMargin
        return this
    }

    fun setMargin(start: Float, top: Float, end: Float, bottom: Float): EdSliderBuilder {
        marginStart = start
        marginTop = top
        marginEnd = end
        marginBottom = bottom
        return this
    }

    fun setSelectedTime(selectedTime: Long): EdSliderBuilder {
        this.selectedTime = selectedTime
        return this
    }

    fun setMaxIcons(max: Int): EdSliderBuilder {
        maxIcons = max
        return this
    }

    fun setLimitMax(limit: Boolean): EdSliderBuilder {
        limitMax = limit
        return this
    }

    fun setBgChange(change: Boolean): EdSliderBuilder {
        bgChange = change
        return this
    }

    fun setScrollActionThresholdTime(time: Long): EdSliderBuilder {
        scrollActionThresholdTime = time
        return this
    }


    /**
     * Finalize the builder
     * @return
     */
    fun build(): EdSliderManager {
        adjustPosition()
        view?.build(this)
        manager?.set(layout, view)
        return manager!!
    }

    /**
     * Adjust the view position around the target.
     */
    fun adjustPosition() {
        // set padding to view
        // so we need to calculate the size manually
        // 整个 buildView的 大小
        visionBoundary = RectF(
            0f,
            0f,
            getItemLayoutWidth() + paddingStart + paddingEnd,
            getItemLayoutHeight() + paddingTop + paddingBottom
        )

        // get the location of target
        val targetCoord = IntArray(2)
        val layoutCoord = IntArray(2)
        target!!.getLocationOnScreen(targetCoord)
        layout!!.getLocationOnScreen(layoutCoord)

        var x = 0f
        var y = 0f
        val relativeCoordX = targetCoord[0] - layoutCoord[0].toFloat()
        val relativeCoordY = targetCoord[1] - layoutCoord[1].toFloat()
        when (aligns[0]) {
            //左边对其
            Align.LEFT -> {
                x = relativeCoordX - paddingStart + marginStart
            }
            Align.RIGHT -> {
                x = relativeCoordX + (target?.width ?: 0f).toFloat() -
                        (visionBoundary?.width() ?: 0).toFloat() + paddingEnd - marginEnd
            }
            Align.CENTER -> {
                x = relativeCoordX + ((target?.width ?: 0) / 2) -
                        (visionBoundary?.width() ?: 0).toFloat() / 2
            }
        }
        when (aligns[1]) {
            //再目标view的上面
            Align.TOP -> {
                y = relativeCoordY - (visionBoundary?.height() ?: 0f) +
                        paddingBottom - marginBottom
            }
            Align.BOTTOM -> {
                y = relativeCoordY + (target?.height ?: 0) - paddingTop + marginTop
            }
            Align.CENTER -> {
                y = relativeCoordY - paddingTop +
                        ((target?.height?.toFloat() ?: 0f) - getItemLayoutHeight()) / 2
            }
        }
        view?.setX(x)
        view?.setY(y)
    }

    fun getItemLayoutHeight(): Int {
        val rectSize = size + iconMarginVertical + iconMarginVertical
        return rectSize.toInt()
    }

    fun getItemLayoutWidth(): Int {
        return if ((list.size ) <= maxIcons || !limitMax) {
            (getItemSize() * (list.size) + bgPaddingStart + bgPaddingEnd).toInt()
        } else {
            (getItemSize() * maxIcons + bgPaddingStart + bgPaddingEnd).toInt()
        }
    }

    fun getItemSize(): Float {
        return size + iconMarginHorizontal + iconMarginHorizontal
    }

    fun getItemShowingWidth(): Float {
        return if (limitMax) {
            getItemSize() * maxIcons
        } else {
            getItemSize() * (list.size)
        }
    }


    fun getViewWidth(): Int {
        return (getItemLayoutWidth() + paddingStart + paddingEnd).toInt()
    }

    fun getViewHeight(): Int {
        return (getItemLayoutHeight() + paddingTop + paddingBottom).toInt()
    }

}