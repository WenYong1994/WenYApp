package com.wheny.wenyapplication.mvvm.view.test_drawable

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.StateListAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnStart
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel
import com.wheny.wenyapplication.view.dp


/**
 * 类名：PressedAnimatorHelper
 * 包名：com.wheny.wenyapplication.mvvm.view.test_drawable
 * 创建时间：2023/6/8 16:15
 * 创建人： WhenYoung
 * 描述：
 **/
object PressedAnimatorHelper {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun bindPressedAnimator(
        targetView: View,
        pressedScale: Float,
        normalScale: Float,
        @ColorInt pressedColor: Int,
        @ColorInt normalColor: Int,
        drawable: MaterialShapeDrawable? = null
    ) {
        val stateListAnimator = StateListAnimator()
        val pressedAniSet = AnimatorSet()
            .apply {
                val animation = CampTextPressedAnimation(
                    targetView,
                    pressedScale,
                    pressedScale,
                    normalColor,
                    pressedColor,
                    drawable
                )
                doOnStart {
                    animation.checkStartState()
                }
                playTogether(ValueAnimator.ofFloat(0f, 1f).apply {
                    addUpdateListener {
                        animation.applyTransformation(it.animatedFraction)
                    }
                })
            }
        val pressedOutAniSet = AnimatorSet()
            .apply {
                val animation = CampTextPressedAnimation(
                    targetView,
                    normalScale,
                    normalScale,
                    pressedColor,
                    normalColor,
                    drawable
                )
                doOnStart {
                    animation.checkStartState()
                }
                playTogether(ValueAnimator.ofFloat(0f, 1f).apply {
                    addUpdateListener {
                        animation.applyTransformation(it.animatedFraction)
                    }
                })
            }
        stateListAnimator.addState(intArrayOf(android.R.attr.state_pressed), pressedAniSet)
        stateListAnimator.addState(intArrayOf(0), pressedOutAniSet)
        targetView.stateListAnimator = stateListAnimator
    }

}


class CampTextPressedAnimation(
    private val targetView: View,
    private val toWidthScale: Float,
    private val toHeightScale: Float,
    private val fromColor: Int,
    private val toColor: Int,
    drawable: MaterialShapeDrawable? = null
) {

    private val shapeAppearanceModel = ShapeAppearanceModel.builder().apply {
        setAllCorners(RoundedCornerTreatment())
        setTopLeftCornerSize(4.dp)
        setTopRightCornerSize(4.dp)
        setBottomRightCornerSize(4.dp)
        setBottomLeftCornerSize(4.dp)
    }.build()

    private val mDrawable: MaterialShapeDrawable

    init {
        mDrawable = drawable ?: MaterialShapeDrawable(shapeAppearanceModel).apply {
            setTint(Color.parseColor("#4DFFFFFF")) //填充颜色
        }
    }


    var currentColor = 0
    var startXScale = 1f
    var startYScale = 1f

    fun checkStartState() {
        startXScale = targetView.scaleX
        startYScale = targetView.scaleY
    }

    fun applyTransformation(interpolatedTime: Float) {
        val currentWidthScale = (toWidthScale - startXScale) * interpolatedTime + startXScale
        val currentHeightScale =
            (toHeightScale - startYScale) * interpolatedTime + startYScale
        targetView.scaleX = currentWidthScale
        targetView.scaleY = currentHeightScale
        currentColor = ArgbEvaluator().evaluate(interpolatedTime, fromColor, toColor) as Int
        mDrawable.apply {
            setTint(currentColor)
        }
        targetView.background = mDrawable
    }

}