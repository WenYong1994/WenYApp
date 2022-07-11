package com.wheny.wenyapplication.mvvm.view.test_delegation

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.chenenyu.router.annotation.InjectParam
import com.chenenyu.router.annotation.Route
import com.wheny.wenyapplication.R
import kotlinx.android.synthetic.main.activity_test_dele.*
import org.libpag.PAGView
import kotlin.reflect.KProperty

@Route("TestDeleActivity", "TestDeleActivity2")
class TestDeleActivity : AppCompatActivity() {

    @InjectParam(key = "testP1")
    public var testP1 = ""

    @InjectParam(key = "testP2")
    public var testP2 = false

    @InjectParam(key = "testP3")
    public var testP3 = 0
    val webView by lazy {
        WebView(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dele)
        findViewById<ViewGroup>(R.id.web_group).addView(webView)
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE,null)
        val group = findViewById<View>(R.id.web_group);
//        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
        val iv = findViewById<ImageView>(R.id.img)
        val pagView = findViewById<PAGView>(R.id.pag_view)
        pagView.path ="assets://wish_list_guide_profile.pag"
        pagView.play()
        pagView.setRepeatCount(1)
        findViewById<View>(R.id.btn1).setOnClickListener {
            webView.loadUrl("https://yari-demos.prod.mdn.mozit.cloud/en-US/docs/Web/API/Canvas_API/Tutorial/Basic_usage/_sample_.a_simple_example.html")
        }
        findViewById<View>(R.id.btn).setOnClickListener {
            val rootView = findViewById<NestedScrollView>(R.id.scroll_view).getChildAt(0)
            val drawingCache = Bitmap.createBitmap(webView.width,window.decorView.height,Bitmap.Config.ARGB_8888)
            val canvas = Canvas(drawingCache)
            rootView.draw(canvas)
            val mergeBitmap = Bitmap.createBitmap(rootView.width,rootView.height,Bitmap.Config.ARGB_8888)
            val mergeCanvas = Canvas(mergeBitmap)
            val paint = Paint()
            mergeCanvas.drawBitmap(drawingCache,0f,0f,paint)
            val pagBitMap = pagView.getBitmap(pagView.width,pagView.height)

            val pagLocation = IntArray(2)
            val rootGroupLocation = IntArray(2)
            pagView.getLocationOnScreen(pagLocation)
            rootView.getLocationOnScreen(rootGroupLocation)
            val x = pagLocation[0] - rootGroupLocation[0]
            val y = pagLocation[1] - rootGroupLocation[1]
            mergeCanvas.drawBitmap(pagBitMap,x.toFloat(),y.toFloat(),paint)
            pagBitMap.recycle()
            drawingCache.recycle()
            iv.setImageBitmap(mergeBitmap)
        }
    }
}


interface Subject {
    fun request1()
    fun request2()

}


class RealSubject : Subject {
    override fun request1() {
        println("ReaSubject request1")
    }

    override fun request2() {
        println("ReaSubject request2")
    }
}

class Proxy(var subject: Subject) : Subject {

    override fun request1() {
        subject.request1()
    }

    override fun request2() {
        subject.request2()
    }

}

interface Base {
    fun print()
}

class BaseImpl(val a: Int) : Base {
    override fun print() {
        print(a)
    }
}

class Derived(b: Base) : Base by b {
    var x: Int by MyDele()


}


class MyDele {
    var aa: Int = 0

    operator fun getValue(derived: Derived, property: KProperty<*>): Int {
        return aa
    }

    operator fun setValue(derived: Derived, property: KProperty<*>, i: Int) {
        aa = i
    }

}

val lazeValue: String by lazy {
    println("执行lazy")
    return@lazy "hello"
}


fun main() {
//    val proxy = Proxy(RealSubject())
//    proxy.request1()
    val b = BaseImpl(10)
    val derived = Derived(b)
    derived.x = 101
    println(derived.x)


}

