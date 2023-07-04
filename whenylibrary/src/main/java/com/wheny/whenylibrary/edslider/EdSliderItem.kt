package com.wheny.whenylibrary.edslider

import android.content.Context
import android.graphics.Color
import android.graphics.PointF
import android.graphics.RectF
import android.util.Log
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.OvershootInterpolator
import android.view.animation.Transformation
import android.widget.FrameLayout
import android.widget.ImageView
import java.util.concurrent.Flow


/**
 * 类名：EdsliderItem
 * 包名：com.wheny.whenylibrary.edslider
 * 创建时间：2023/6/9 17:09
 * 创建人： WhenYoung
 * 描述：
 **/
class EdSliderItem(
    context: Context,
    private val size: Float,
    private val iconMarginHorizontal: Float,
    private val iconMarginVertical: Float,
    private val edIcon: EdIcon,
    var isReversed: Boolean = false,
    var itemDynamic: Boolean = true,
    var selectedScale: Float = 1.5f,
    var selectedOffset: Float = 0f,
) :
    FrameLayout(context), EdSliderItemSliderListener {

    private val image: ImageView
    private var currentScale = 1f

    init {
//        setBackgroundColor(Color.parseColor("#8800ff00"))
        layoutParams = if(itemDynamic){
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }else{
            LayoutParams((size + iconMarginHorizontal + iconMarginHorizontal).toInt(), (size + iconMarginVertical + iconMarginVertical).toInt())
        }
        val params = LayoutParams(size.toInt(), size.toInt())
        params.setMargins(
            iconMarginHorizontal.toInt(),
            iconMarginVertical.toInt(),
            iconMarginHorizontal.toInt(),
            iconMarginVertical.toInt()
        )
        params.gravity = Gravity.CENTER_HORIZONTAL.or(Gravity.BOTTOM)
        image = ImageView(getContext())
        image.layoutParams = params
        image.setImageResource(edIcon.drawableid)
        image.scaleType = ImageView.ScaleType.FIT_CENTER
        addView(image)
    }

    override fun onAppear(index: Int, showIndex: Int, needAni: Boolean) {
        val offsetY = if (isReversed) -height.toFloat() else height.toFloat()
        if (needAni){
            image.translationY = offsetY
            image.scaleX = 0f
            image.scaleY = 0f
            image.animate().scaleX(1f).scaleY(1f).setDuration(100)
                .translationY(0f)
                .setStartDelay((80 * showIndex + 150).toLong())
                .setInterpolator(OvershootInterpolator())
                .start()
        }else{
            image.scaleX = 1f
            image.scaleY = 1f
            image.translationY = 0f
        }

    }

    override fun onDisAppear(index: Int, showIndex: Int, needAni: Boolean) {
        if(needAni){
            image.scaleX = 1f
            image.scaleY = 1f
            image.animate().cancel()
            image.animate().translationY(if (isReversed) -height.toFloat() else height.toFloat())
                .scaleX(0f).scaleY(0f).setDuration(100)
                .setStartDelay((80 * showIndex).toLong())
                .setInterpolator(null)
                .start()
        }
    }

    override fun onSlider(index: Int, rectF: RectF, pointF: PointF) {


    }

    override fun onSelectedChange(index: Int, selected: Boolean) {
        if (selected) {
            val absTranslationY = selectedOffset
            image.clearAnimation()
            val animation = ItemAnimation(
                selectedScale,
                if (isReversed) absTranslationY else -absTranslationY
            )
                .apply {
                    duration = 150
                }
            image.startAnimation(animation)
        } else {
            image.clearAnimation()
            val animation = ItemAnimation(1f, 0f)
                .apply {
                    duration = 150
                }
            image.startAnimation(animation)
        }


    }


    inner class ItemAnimation(val toScale: Float, val toTranslationY: Float) : Animation() {
        val startScale = currentScale
        val startTranslationY = translationY

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            super.applyTransformation(interpolatedTime, t)
            val lp = image.layoutParams
            currentScale = (toScale - startScale) * interpolatedTime + startScale
            lp.width = (size * currentScale).toInt()
            lp.height = (size * currentScale).toInt()
            image.layoutParams = lp
            image.translationY =
                (toTranslationY - startTranslationY) * interpolatedTime + startTranslationY
        }
    }


}




