package com.wheny.wenyapplication.view

import android.animation.ArgbEvaluator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import com.wheny.wenyapplication.application.App
import kotlin.math.min


/**
 * 类名：MixColorRoundProgress
 * 包名：com.wheny.wenyapplication.view
 * 创建时间：2023/6/5 09:54
 * 创建人： WhenYoung
 * 描述：
 **/
class MixColorRoundProgress : View {

    val attributeSet: AttributeSet?

    // 边框宽度
    var strokeWidth = 0f

    // 使用角度   X轴正数 为0度 顺时针旋转
    var mStartAngle = 0f

    // 0 -> 1旋转方向是否 逆时针
    var inverseClock = false

    // 颜色列表
    var colors = intArrayOf(Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN)

    // 进度 0 - 100
    var progress = 0f

    // 进度条背景色
    var bgColor = Color.parseColor("#ECE6D3")

    var cursorColor = Color.RED

    val paint = Paint().apply {
        //抗锯齿
        isAntiAlias = true
    }

    var padding = 5.dp


    private val cursorPaint = Paint().apply {
        //抗锯齿
        isAntiAlias = true
        maskFilter = BlurMaskFilter(2.dp, BlurMaskFilter.Blur.NORMAL)
    }

    val drawWidth: Float
        get() {
            return width - (padding * 2)
        }

    val drawHeight: Float
        get() {
            return height - (padding * 2)
        }

    private val rectF = RectF()

    var colorStyle = ColorStyle.ProgressColor

    private var progressAnimation: ProgressAnimation? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        attributeSet = attrs
        mStartAngle = 90f
        strokeWidth = 4.dp
        progress = 100f
        inverseClock = false
        colors = intArrayOf(
            // 红色
            Color.parseColor("#FFEB814E"),
            // 黄色
            Color.parseColor("#FFFCE14B"),
            //绿色
            Color.parseColor("#FF57E387"),
        )
        cursorColor = Color.WHITE
        padding = 5.dp
        colorStyle = ColorStyle.MixColor
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(c: Canvas?) {
        super.onDraw(c)
        val canvas = c ?: return
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth

        val radius = (min(drawWidth, drawHeight) - strokeWidth) / 2
        val cx = radius + strokeWidth / 2 + padding
        val cy = radius + strokeWidth / 2 + padding
        //先画 背景
        paint.shader = null
        paint.color = bgColor
        canvas.drawCircle(cx, cy, radius, paint)
        //画 进度
        paint.color = Color.RED
        rectF.set(
            strokeWidth / 2 + padding,
            strokeWidth / 2 + padding,
            width - (strokeWidth / 2) - padding,
            height - (strokeWidth / 2) - padding,
        )
        val sweepAngle = (progress / 100f) * 360
        val startAngle = if (inverseClock) {
            // 逆时针
            this.mStartAngle - sweepAngle
        } else {
            // 顺时针
            this.mStartAngle
        }
        when (colorStyle) {
            ColorStyle.MixColor -> {
                val sweepGradient =
                    SweepGradient(drawWidth / 2f + padding, drawHeight / 2f + padding, colors, null)
                val matrix = Matrix()
                matrix.setRotate(mStartAngle, drawWidth / 2f + padding, drawHeight / 2f + padding)
                sweepGradient.setLocalMatrix(matrix)
                paint.shader = sweepGradient
            }
            ColorStyle.ProgressColor -> {
                if (colors.isEmpty()) {
                    return
                } else if (colors.size < 2) {
                    paint.color = colors[0]
                } else {
                    // 根据进度来计算颜色
                    val stageSpan = (100f / (colors.size - 1))
                    val startColorIndex = (progress / stageSpan)
                    val endColorIndex = startColorIndex + 1
                    val colorStagePercent = (progress % stageSpan) / stageSpan
                    val startColor = if (startColorIndex < colors.size) {
                        colors[startColorIndex.toInt()]
                    } else {
                        colors[colors.size - 1]
                    }
                    val endColor = if (endColorIndex < colors.size) {
                        colors[endColorIndex.toInt()]
                    } else {
                        colors[colors.size - 1]
                    }
                    val currentColor =
                        ArgbEvaluator().evaluate(colorStagePercent, startColor, endColor) as Int
                    paint.color = currentColor
                }
            }
        }
        canvas.drawArc(
            rectF, startAngle, sweepAngle, false, paint
        )
        // 画一个圆
        cursorPaint.color = cursorColor
        val relAngle = if (inverseClock) {
            sweepAngle - mStartAngle
        } else {
            360 - (sweepAngle + startAngle)
        }
        val cursorRadius = (drawWidth / 2) - (strokeWidth / 2)
        Log.e("cursorRadius", relAngle.toString())
        val rx = cursorRadius * Math.cos(relAngle.toDouble() / 180 * Math.PI)
        val ry = cursorRadius * Math.sin(relAngle.toDouble() / 180 * Math.PI)
        val x = convertCoordinateX(rx.toFloat())
        val y = convertCoordinateY(ry.toFloat())
        canvas.drawCircle(x, y, 4.dp, cursorPaint)
    }

    // 将 以(width/2 height/2)为（0，0）原点的（x,y）坐标转换为在 View中的坐标以 左上为 （0，0）的坐标
    private fun convertCoordinateX(x: Float): Float {
        return x + (drawWidth / 2) + padding
    }

    // 将 以(width/2 drawHeight/2)为（0，0）原点的（x,y）坐标转换为在 View中的坐标以 左上为 （0，0）的坐标
    private fun convertCoordinateY(y: Float): Float {
        return (drawHeight / 2) - y + padding
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec))
    }

    fun measureSize(measureSpec: Int): Int {
        when (MeasureSpec.getMode(measureSpec)) {
            // 父布局不限制子布局大小，可以随便宽高 ，此时随意返回一个值就ok  这个很少很少，例如在竖直方向的 scrollView中 高度的测量
            MeasureSpec.UNSPECIFIED -> {
                return 96.dp.toInt()
            }
            // 精准模式 父布局 告诉子布局就这么大 对应设置 layout_width="12dp" layout_width="match_parent"
            MeasureSpec.EXACTLY -> {
                return MeasureSpec.getSize(measureSpec)
            }
            // 父布局 告诉子布局一个最大 size 对应设置 layout_width="warp_content"
            MeasureSpec.AT_MOST -> {
                return 96.dp.toInt()
            }
        }
        return MeasureSpec.getSize(measureSpec)
    }


    fun startProgressAnimation(fromProgress: Float, toProgress: Float, duration: Int) {
        progressAnimation?.let {
            it.cancel()
            clearAnimation()
        }
        progressAnimation = ProgressAnimation(fromProgress, toProgress)
        progressAnimation?.duration = duration.toLong()
        startAnimation(progressAnimation)
        progressAnimation?.start()
    }

    inner class ProgressAnimation(val fromProgress: Float, val toProgress: Float) : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            super.applyTransformation(interpolatedTime, t)
            val offset = toProgress - fromProgress
            val currentProgress = fromProgress + offset * interpolatedTime
            progress = currentProgress
            postInvalidate()
        }
    }

    enum class ColorStyle {
        // 混合渐变，同一时间有多个颜色
        MixColor,

        // 进度渐变，同一时间只有一个颜色
        ProgressColor
    }

}


val Int.dp: Float
    get() {
        val scale = App.getApplication().resources.displayMetrics.density
        return (this * scale + 0.5f)
    }
