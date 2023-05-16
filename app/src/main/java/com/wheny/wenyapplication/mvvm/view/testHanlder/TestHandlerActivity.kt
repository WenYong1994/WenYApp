package com.wheny.wenyapplication.mvvm.view.testHanlder

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.*
import android.text.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.View.MeasureSpec
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.mvvm.view.testHanlder.Utils.miniAppPath
import com.wheny.wenyapplication.mvvm.view.test_delegation.TestDeleActivity
import com.wheny.whenylibrary.utils.DensityUtils
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.math.max
import kotlin.math.min

val TAG = "TestHandlerActivity"

class TestHandlerActivity : AppCompatActivity() {

    val txt = "有标题有正文，和动态一致上文下图。标题最多两行，展示封面图，标题最多两行"
    var txtSpan: Spannable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_handler)
        val btn = findViewById<Button>(R.id.btn)
        val image = findViewById<ImageView>(R.id.img)
        Glide.with(this)
            .load(R.drawable.share_function_battle_create_reoprt_ing)
            .into(image)
        val myThread = MyThread()
        myThread.start()
        var i = 0
        TestA.test()
        btn.setOnClickListener {
            TestA.test2(this)
            val bitmap = getBitmap()
            val intent = Intent(this, TestDeleActivity::class.java)
            val test = TestData.newInstance("1",bitmap)
            intent.putExtra("sss", test)
            startActivity(intent)
            lifecycleScope.coroutineContext
            lifecycleScope.launch {
                val b = Utils.callback?.invoke(3,2)
                Log.d("testtest",b.toString())
            }

            val url =
                "https://camp.qq.com/h5/webdist/mp-redirect/index.html?title=银牌战士+14/7/17！我的曜，YYDS！&userId=1767314781&roleId=2312928006&router=battleDetail&gameSeq=1668593794&aCntCamp=1&relaySvrId=588776196&battleType=15&gameSvrId=382502&pvpType=5";
            val url1 =
                "https://camp.qq.com/h5/webdist/mp-redirect/index.html?title=哎名字难啊啊的王者荣耀战绩主页&userId=1767314781&roleId=2312928006&router=battleHome"
            val x = url.miniAppPath()
            val x1 = url.miniAppPath()
            myThread.hd.sendEmptyMessage(i++)
            testText()

            lifecycleScope.launch {
                try {
                    flow {
                        val s = testFlow(i)
                        emit(s)
                    }.catch {
                        Log.e("ssss", "catch")
                    }.collect {
                        Log.e("ssss", "collect")
                    }
                } catch (e: Exception) {
                    Log.e("ssss", "")
                }
            }

        }
        findViewById<View>(R.id.tag_layer).post {
            testText()
        }


    }

    fun getBitmap(): Bitmap {
        val tagView = findViewById<View>(R.id.tag_layer)
        val bitmap = Bitmap.createBitmap(tagView.width, tagView.height, Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        tagView.draw(canvas)
        return bitmap
    }


    suspend fun testFlow(i: Int) = suspendCancellableCoroutine<String> {
//        it.resumeWith(Result.success("$i"))
        it.resumeWith(Result.failure(Exception("hahahahah")))
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.no_anim, R.anim.bottom_out)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_anim, R.anim.bottom_out)
    }

    fun testText() {
        val txtSize = 16f
        val lineHeight = 24f
        val tv = findViewById<TextView>(R.id.text)
        tv.setTextSize(txtSize)
        tv.setTextColor(Color.parseColor("#0F2128"))
        tv.maxLines = 3
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            tv.lineHeight = DensityUtils.dipToSp(this, lineHeight)
        }
        val textSpan = SpannableStringBuilder()
        val maxLineHeight =
            tv.paint.fontMetricsInt.bottom - tv.paint.fontMetricsInt.top /*- tv.paint.fontMetricsInt.descent*/
        val bitmap = getBitmap(maxLineHeight)

        val imgSpan = CenterImageSpan(BitmapDrawable(resources, bitmap), this)
        val imgSpanStr = "Image"
        val index_ = txt.length
        val index = max(min(txt.length, index_), 0)
        val startStr = txt.substring(0, index)
        val endStr = txt.substring(index, txt.length)
        textSpan.append(startStr)
        textSpan.append(imgSpanStr)
        textSpan.append(endStr)
        textSpan.setSpan(
            imgSpan,
            startStr.length,
            startStr.length + imgSpanStr.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

//        val bgSpan = BackgroundColorSpan(Color.RED)
//        textSpan.setSpan(
//            bgSpan,
//            startStr.length - 3,
//            startStr.length,
//            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
//        )
        txtSpan = textSpan
        tv.ellipsize = TextUtils.TruncateAt.END

        tv.setText(textSpan)
        measureTv(tv)


        val tv1 = findViewById<TextView>(R.id.text1)
        tv1.setTextSize(txtSize)
        tv1.setTextColor(Color.parseColor("#0F2128"))
        tv1.maxLines = 3
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            tv1.lineHeight = DensityUtils.dipToSp(this, lineHeight)
        }
        tv1.setText(txt)


    }


    fun measureTv(tv: TextView) {
        val widthModel =
            MeasureSpec.makeMeasureSpec(DensityUtils.dip2px(this, 231f), MeasureSpec.AT_MOST)
        val heightModel = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        tv.measure(widthModel, heightModel)
        val vp = (tv.parent as View)
        vp.measure(
            MeasureSpec.makeMeasureSpec(tv.measuredWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(tv.measuredHeight, MeasureSpec.EXACTLY)
        )
        vp.layout(0, 0, vp.measuredWidth, vp.measuredHeight)
        tv.layout(0, 0, tv.measuredWidth, tv.measuredHeight)
    }

    fun getBitmap(maxHeight: Int): Bitmap {
        val tagView = findViewById<View>(R.id.tag_layer)
        val bitmap = Bitmap.createBitmap(tagView.width, tagView.height, Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        tagView.draw(canvas)
        if (bitmap.height > maxHeight) {
            val finalWidth = bitmap.width * (maxHeight.toFloat() / bitmap.height)
            return Bitmap.createScaledBitmap(bitmap, finalWidth.toInt(), maxHeight, false)
        }
        return bitmap
    }


}


@Parcelize
data class TestData(
    var x: String,
) : Parcelable {

    companion object{
        fun newInstance(x: String,bitmap_: Bitmap?) : TestData {
            return TestData(x).apply {
                this.bitmap = bitmap_
            }
        }
    }

    @kotlinx.parcelize.IgnoredOnParcel
    var bitmap: Bitmap? = null
}

class RichTextView(context: Context, att: AttributeSet) : FrameLayout(context, att) {

    override fun requestLayout() {
        super.requestLayout()
//        post {
//            layout(left, right, top, bottom)
//        }
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }


}

class NoLayoutView(context: Context, att: AttributeSet) : FrameLayout(context, att) {

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        super.onLayout(changed, left, top, right, bottom)
    }

//    override fun requestLayout() {
//        super.requestLayout()
//        super.onLayout(true,left,top,right,bottom)
//    }


}


class MyThread : Thread() {
    lateinit var hd: Handler
    override fun run() {
        super.run()
        Log.e(TAG, "11111111111111111111111111111111111111111111111111111111111")
        Looper.prepare()
        hd = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.e(TAG, "==========================================${msg.what}")
            }
        }
        Looper.loop()
        Log.e(TAG, "-----------------------------------------------------------")
    }
}



