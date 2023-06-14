package com.wheny.whenylibrary.edslider

import android.content.Context
import android.graphics.PointF
import android.graphics.RectF
import android.util.Log
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
    var isReversed: Boolean = false
) :
    FrameLayout(context), EdSliderItemSliderListener {

    private val image: ImageView
    private var currentScale = 1f

    init {
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        val params = LayoutParams(size.toInt(), size.toInt())
        params.setMargins(
            iconMarginHorizontal.toInt(),
            iconMarginVertical.toInt(),
            iconMarginHorizontal.toInt(),
            iconMarginVertical.toInt()
        )
        image = ImageView(getContext())
        image.layoutParams = params
        image.setImageResource(edIcon.drawableid)
        image.scaleType = ImageView.ScaleType.FIT_CENTER
        addView(image)
    }

    override fun onAppear(index: Int, showIndex: Int, needAni: Boolean) {
        val offsetY = if (isReversed) -height.toFloat() else height.toFloat()
        if (needAni){
            translationY = offsetY
            scaleX = 0f
            scaleY = 0f
            animate().scaleX(1f).scaleY(1f).setDuration(100)
                .translationY(0f)
                .setStartDelay((80 * showIndex + 150).toLong())
                .setInterpolator(OvershootInterpolator())
                .start()
        }else{
            scaleX = 1f
            scaleY = 1f
            translationY = 0f
        }

    }

    override fun onDisAppear(index: Int, showIndex: Int, needAni: Boolean) {
        if(needAni){
            scaleX = 1f
            scaleY = 1f
            animate().cancel()
            animate().translationY(if (isReversed) -height.toFloat() else height.toFloat())
                .scaleX(0f).scaleY(0f).setDuration(100)
                .setStartDelay((80 * showIndex).toLong())
                .setInterpolator(null)
                .start()
        }
    }

    override fun onSlider(index: Int, rectF: RectF, pointF: PointF) {


    }

    override fun onSelectedChange(index: Int, selected: Boolean) {
        Log.e("onSelectedChange", "index:${index},selected:${selected}")
        if (selected) {
            val absTranslationY = height.toFloat() / 2
            clearAnimation()
            val animation = ItemAnimation(
                2f,
                if (isReversed) absTranslationY else -absTranslationY
            )
                .apply {
                    duration = 150
                }
            startAnimation(animation)
        } else {
            clearAnimation()
            val animation = ItemAnimation(1f, 0f)
                .apply {
                    duration = 150
                }
            startAnimation(animation)
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
            translationY =
                (toTranslationY - startTranslationY) * interpolatedTime + startTranslationY
        }
    }


}




