package com.wheny.wenyapplication.mvvm.view.test_delegation

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.PixelCopy
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.chenenyu.router.annotation.InjectParam
import com.chenenyu.router.annotation.Route
import com.wheny.wenyapplication.R
import org.libpag.PAGView
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
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

    val webViewPaint by lazy {
        Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dele)
        findViewById<ViewGroup>(R.id.web_group).addView(webView)
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, webViewPaint)
//        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, webViewPaint)
        val startTime = System.currentTimeMillis()
        webView.loadUrl("https://yari-demos.prod.mdn.mozit.cloud/en-US/docs/Web/API/Canvas_API/Tutorial/Basic_usage/_sample_.a_simple_example.html")
        webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                webView.setLayerType(View.LAYER_TYPE_HARDWARE,webViewPaint)
                super.onPageFinished(view, url)
                val time = System.currentTimeMillis() - startTime
                Log.e("onPageFinished","times:$time")
                Toast.makeText(this@TestDeleActivity,"sfafafafa",Toast.LENGTH_SHORT).show()
            }
        }
        webView.settings.javaScriptEnabled = true
        WebView.setWebContentsDebuggingEnabled(true)
        val iv = findViewById<ImageView>(R.id.img)
        val pagView = findViewById<PAGView>(R.id.pag_view)
        pagView.path = "assets://wish_list_guide_profile.pag"
        pagView.play()
        pagView.setRepeatCount(1)
        findViewById<View>(R.id.btn1).setOnClickListener {
            webView.loadUrl("https://yari-demos.prod.mdn.mozit.cloud/en-US/docs/Web/API/Canvas_API/Tutorial/Basic_usage/_sample_.a_simple_example.html")
        }
        findViewById<View>(R.id.btn).setOnClickListener {
//            val rootView = findViewById<NestedScrollView>(R.id.scroll_view).getChildAt(0)
            val rootView = window.decorView
            val drawingCache =
                Bitmap.createBitmap(webView.width, window.decorView.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(drawingCache)
            rootView.draw(canvas)

            val mergeBitmap =
                Bitmap.createBitmap(rootView.width, rootView.height, Bitmap.Config.ARGB_8888)
            val mergeCanvas = Canvas(mergeBitmap)
            val paint = Paint()
            mergeCanvas.drawBitmap(drawingCache, 0f, 0f, paint)
            val pagBitMap = pagView.getBitmap(pagView.width, pagView.height)

            val pagLocation = IntArray(2)
            val rootGroupLocation = IntArray(2)
            pagView.getLocationOnScreen(pagLocation)
            rootView.getLocationOnScreen(rootGroupLocation)
            val x = pagLocation[0] - rootGroupLocation[0]
            val y = pagLocation[1] - rootGroupLocation[1]
            mergeCanvas.drawBitmap(pagBitMap, x.toFloat(), y.toFloat(), paint)
            pagBitMap.recycle()
            drawingCache.recycle()
            iv.setImageBitmap(mergeBitmap)


        }
    }


    /**
     * Pixel copy to copy SurfaceView/VideoView into BitMap
     * Work with Surface View, Video View
     * Won't work on Normal View
     */
//    fun getBitMapFromSurfaceView(videoView: SurfaceView, callback: (Bitmap?) -> Unit) {
//        val bitmap: Bitmap = Bitmap.createBitmap(
//            videoView.width,
//            videoView.height,
//            Bitmap.Config.ARGB_8888
//        );
//        try {
//            // Create a handler thread to offload the processing of the image.
//            val handlerThread = HandlerThread("PixelCopier");
//            handlerThread.start();
//            PixelCopy.request(
//                videoView, bitmap,
//                PixelCopy.OnPixelCopyFinishedListener { copyResult ->
//                    if (copyResult == PixelCopy.SUCCESS) {
//                        callback(bitmap)
//                    }
//                    handlerThread.quitSafely();
//                },
//                Handler(handlerThread.looper)
//            )
//        } catch (e: IllegalArgumentException) {
//            callback(null)
//            // PixelCopy may throw IllegalArgumentException, make sure to handle it
//            e.printStackTrace()
//        }
//    }


}

private fun screenshotSystem(webview:View):Bitmap? {
    val surfaceClass: Class<*>
    var method: Method? = null
    var bitmap: Bitmap? = null
    try {
        surfaceClass = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Class.forName("android.view.SurfaceControl")
        } else {
            Class.forName("android.view.Surface")
        }
        method = surfaceClass.getDeclaredMethod("screenshot", Integer.TYPE, Integer.TYPE)
        method.setAccessible(true)
        bitmap = method.invoke(webview, webview.getWidth(), webview.getHeight()) as Bitmap
        return bitmap
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    } catch (e: InvocationTargetException) {
        e.printStackTrace()
    }
    return null
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

