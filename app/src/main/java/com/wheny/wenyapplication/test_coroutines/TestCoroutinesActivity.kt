package com.wheny.wenyapplication.test_coroutines

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.view.MixColorRoundProgress
import com.wheny.whenylibrary.utils.ToastUtils
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.EmptyCoroutineContext

class TestCoroutinesActivity : AppCompatActivity() {

    val TAG = "test_coroutines"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_coroutines)
        Log.e(TAG, "主线程id：${mainLooper.thread.id}")
        test()
        Log.e(TAG, "协程执行结束")


        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.clipChildren = false
        rv.adapter = Ada(this, 20)
        rv.layoutManager = LinearLayoutManager(this)
        val mixColorRoundProgress = findViewById<MixColorRoundProgress>(R.id.round_progress)
        val webvie= findViewById<WebView>(R.id.web_view)
        webvie.settings.javaScriptEnabled = true
        webvie.loadUrl("https://echarts.apache.org/examples/zh/editor.html?c=line-simple")
    }

    fun oncl(view: View) {
//        test1()
        val mixColorRoundProgress = findViewById<MixColorRoundProgress>(R.id.round_progress)
        mixColorRoundProgress.startProgressAnimation(100f,0f,5000)

//        val webView = findViewById<WebView>(R.id.web_view)
//        val bitmap = Bitmap.createBitmap(webView.width,webView.height,Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(bitmap)
//        webView.draw(canvas)
//        View::class.java.getDeclaredField("")
//        canvas.save()
//        findViewById<ImageView>(R.id.iv).setImageBitmap(bitmap)
//        Log.e("bit",bitmap.toString())
    }

    fun test() {
//        testRunBlocking()
//        testJob().invokeOnCompletion {
//            Log.e(TAG, "111111111111111协程执行结束 -- 线程id：${Thread.currentThread().id}")
//        }
//        testJob1().invokeOnCompletion {
//            Log.e(TAG, "2222222222222协程执行结束 -- 线程id：${Thread.currentThread().id}")
//        }
//        testAsync()
//        testAsync2()
    }

    val set = hashSetOf<Int>().apply {
        for (i in 0..1000) {
            add(i)
        }
    }
    val list = Collections.synchronizedSet(set)

    override fun onResume() {
        super.onResume()
//        testAsync3()
    }

    fun testAsync3() {
        lifecycleScope.launch {
            launch(Dispatchers.IO) {
                for (i in 0..1000) {
                    list.add(-i)
                    Log.e("testAsync3", Thread.currentThread().name + ":${-i}----------")
                    delay(1)
                }
            }
        }
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            Log.e("testAsync3", Thread.currentThread().name + ":${iterator.next()}++++++++++++++")
        }

    }

    fun testAsync2() {
        Log.e(TAG, "------------1${Thread.currentThread().id}")
        GlobalScope.launch(EmptyCoroutineContext, CoroutineStart.UNDISPATCHED) {
            withContext(Dispatchers.Main) {
                delay(10000)
                Log.e(TAG, "=============${Thread.currentThread().id}")
            }
        }
        repeat(2) {
            Log.e(TAG, "------------${it}${Thread.currentThread().id}")
        }
    }

    val hander = CoroutineExceptionHandler { c, t ->
        Log.e("testA", "44" + t.message ?: "")
    }



    val myScope = CoroutineScope(Dispatchers.Default)
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("testCoroutine", "exceptionHandler-handler " + throwable.message)
    }

    private fun test1() {
        val list = listOf(1, 2, 3, 4, 5, 6)
        kotlin.run {
            list.forEach {
                Log.e("TestFor", "index:${it}")
                if (it == 3) {
                    return@run
                }
            }
        }
        Log.e("TestFor", "end")

        GlobalScope.launch {
            try {
                myScope.async {
                    throw java.lang.Exception("TestCoroutineException")
                }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("testCoroutine", "捕获异常" + e.message)
            }
            Log.e("testCoroutine", "捕获异常之后的的代码")
        }
    }


    fun testAsync() {
        val job = GlobalScope.launch {
            delay(1000)
            val result1 = async {
                getResult1()
            }
            val result2 = async {
                getResult2()
            }
            Log.e(TAG, "start-------")
            val result = result1.await() + result2.await()
            Log.e(TAG, "result = $result")
        }


    }


    private suspend fun getResult1(): Int {
        delay(3000)
        Log.e(TAG, "getResult1")
        return 1
    }

    private suspend fun getResult2(): Int {
        Log.e(TAG, "getResult2")
        delay(4000)
        return 2
    }

    private fun testRunBlocking() = runBlocking {
        repeat(100) {
            delay(if (it % 2 == 0) 2000 else 1000)
            Log.e(TAG, "协程执行$it 线程id：${Thread.currentThread().id}")
        }

    }


    private fun testJob(): Job {
        return GlobalScope.launch(EmptyCoroutineContext, CoroutineStart.DEFAULT) {
            delay(2000)
            Log.e(TAG, "协程执行结束 --11111线程id：${Thread.currentThread().id}")
        }
    }

    private fun testJob1(): Job {
        return GlobalScope.launch(EmptyCoroutineContext, CoroutineStart.DEFAULT) {
            delay(1000)
            Log.e(TAG, "协程执行结束 -- 222222线程id：${Thread.currentThread().id}")
        }
    }

    fun testF(hh: String.(x: Int, y: String) -> Int) {
    }

    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.N)
    fun testRxJavaZip() {

        Flowable.just(1, 2, 3, 4, 5)
            .subscribe(object : Consumer<Int> {
                override fun accept(t: Int) {

                }
            })


    }


}

class VH(view: View) : ViewHolder(view) {

}

class Ada(val context: Context, val count: Int) : Adapter<VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.test_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
    }

    override fun getItemCount(): Int {
        return count
    }

}


class sss {
    fun f() {
    }
}