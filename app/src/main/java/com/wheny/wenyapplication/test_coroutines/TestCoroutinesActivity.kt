package com.wheny.wenyapplication.test_coroutines

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pb.test.TestPb
import com.test.wy.TestPbJava
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.view.MixColorRoundProgress
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import kotlinx.coroutines.*
import okhttp3.internal.toImmutableList
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.math.absoluteValue

class TestCoroutinesActivity : AppCompatActivity() {

    val TAG = "test_coroutines"

    val accounts = CopyOnWriteArrayList<A>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_coroutines)
        Log.e(TAG, "主线程id：${mainLooper.thread.id}")
        test()
        Log.e(TAG, "协程执行结束")
        val pb = TestPb.TestBean.newBuilder()
        val sex = pb.sexValue
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.clipChildren = false
        rv.adapter = Ada(this, 20)
        rv.layoutManager = LinearLayoutManager(this)
        val mixColorRoundProgress = findViewById<MixColorRoundProgress>(R.id.round_progress)
        val webvie = findViewById<WebView>(R.id.web_view)
        webvie.settings.javaScriptEnabled = true
        webvie.loadUrl("https://echarts.apache.org/examples/zh/editor.html?c=line-simple")
        val x: Int? = null
        accounts.add(A(1))
//        accounts.add(x)
        Log.e("testList", accounts.toString())
        val l1 = accounts.toImmutableList()
        Log.e("testList", l1.toString())
        val times = 1000
        GlobalScope.launch {
            repeat(times) { t ->
                delay(1)
                GlobalScope.launch {
                    delay(2)
                    val l = accounts
                    l.firstOrNull {
                        if(it == null){
                            Log.e("testList-null",l.toString())
                            Log.e("testList-null","null e is:${it.toString()}")
                        }
                        Log.e("testList", "indexA:${t}: ${it?.i}")
                        false
                    }
                }
            }
        }
        GlobalScope.launch {
            repeat(times) { t ->
                delay(1)
                GlobalScope.launch {
                    delay(1)
                    accounts.clear()
                }
            }
        }
        GlobalScope.launch {
            repeat(times) { t ->
                delay(1)
                GlobalScope.launch {
                    delay(2)
                    accounts.add(A(t))
                }
            }
        }
        GlobalScope.launch {
            repeat(times) { t ->
                delay(1)
                GlobalScope.launch {
                    delay(1)
                    val l = accounts
                    l.firstOrNull {
                        if(it == null){
                            Log.e("testList-null",l.toString())
                            Log.e("testList-null","null e is:${it.toString()}")
                        }
                        Log.e("testList", "indexD:${t}: ${it?.i}")
                        false
                    }
                }
            }
        }
    }
    var count = 0

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("TouchEvent", "Activity-onTouchEvent+++++++++++${event?.action}")
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("TouchEvent", "Activity-dispatchTouchEvent=======${ev?.action}")
        return super.dispatchTouchEvent(ev)
    }

    fun oncl(view: View) {
        lifecycleScope.launch {
            count++
            Log.e("TestCor", "1111")
            val result = if (count % 2 == 0) {
                SafeResult<Boolean>().apply {
                    success = true
                }
            } else {
                executeBlockSafe {
                    testRequest()
                }
            }
            Log.e("TestCor", result.toString())
        }
    }

    suspend inline fun getTestSuspend(block: () -> Boolean): Boolean {
        Log.e("TestCor", "4444")
        delay(300)
        Log.e("TestCor", "5555")
        return block()
    }


    suspend fun test4() {
        withContext(currentCoroutineContext()) {
            Log.d("test4", "out Async start")
            val outAsync = async {
                GlobalScope.launch {
                    async {
                        delay(400)
                        Log.d("test4", "inner Async 11111 end")
                    }
                }
                async {
                    delay(200)
                    Log.d("test4", "inner Async 22222 end")
                }.await()
            }
            outAsync.await()
            Log.d("test4", "out Async end")
        }
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


class SafeResult<T> {
    var data: T? = null
    var success: Boolean = false
    var error: Throwable? = null
}

suspend inline fun <T> executeBlockSafe(block: suspend () -> T): SafeResult<T> {
    var safeResult = SafeResult<T>()
    return try {
        val data = block.invoke()
        safeResult.data = data
        safeResult.success = true
        safeResult
    } catch (t: Throwable) {
        safeResult.error = t
        safeResult.success = false
        safeResult
    }
}

suspend fun testRequest(): Boolean {
    return suspendCancellableCoroutine<Boolean> {
        GlobalScope.launch {
            delay(300)
            it.resume(true)
        }
    }
}

// 这里是down 事件的整个穿透流程，目的主要是穿透到每一层判断那一层是事件最终 消费者
// 如果这一层没有消费。那么后续事件（move、down）不会传递到这一层来了
// 如果确定到某一层是消费者。那么后续事件（move、down）事件会传递到这一层来，并按照 这个顺序 dispatchTouchEvent -> onTouchEvent 走到最终消费者
// 如消费者是 onTouchEvent 那么事件就会走 dispatchTouchEvent ->onInterceptTouchEvent -> onTouchEvent
// 如果消费者是 dispatchTouchEvent 那么事件走到 当层的 dispatchTouchEvent 就截止了
// 在一次事件中不会更换消费的View。但是可以更换消费的方法
class TestTouchParentFramLayout(context: Context, attr: AttributeSet) : FrameLayout(context, attr) {
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("TouchEvent", "Parent-onTouchEvent+++++++++++${event?.action}")
        return true
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("TouchEvent", "Parent-dispatchTouchEvent=======${ev?.action}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d("TouchEvent", "Parent-onInterceptTouchEvent-------${ev?.action}")
        return false
    }
}

class TestTouchFramLayout(context: Context, attr: AttributeSet) : FrameLayout(context, attr) {

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i("TouchEvent", "inner-onTouchEvent+++++++++++${event?.action}")
        return true
    }

    var downX = 0f

    var ismax20 = false

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.i("TouchEvent", "inner-dispatchTouchEvent=======${ev?.action},${ismax20}")
        return false
//        return super.dispatchTouchEvent(ev)
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            downX = ev.x
            return super.dispatchTouchEvent(ev)
        }
        return false
        if (ev?.action == MotionEvent.ACTION_MOVE) {
            if ((ev.x - downX).absoluteValue > 20) {
                ismax20 = true
                return super.dispatchTouchEvent(ev)
            } else {
                ismax20 = false
            }
        }
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.i("TouchEvent", "inner-onInterceptTouchEvent-------${ev?.action}")
        return false
    }
}


// 基本条件。如果本层没有任何 消费。那么这一层 不会收到后续 move和up 事件
class TestTouchView(context: Context, attr: AttributeSet) : View(context, attr) {


    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.i("TouchEvent", "child-onTouchEvent>>>>>>>>>>>>>${event?.action}")
        return true
    }

    var downX = 0f

    var ismax20 = false

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.i("TouchEvent", "child-dispatchTouchEvent```````````${event?.action},max20${ismax20}")
        return super.dispatchTouchEvent(event)
    }

}


class A(val i: Int) {

}

class B {
    var c: C? = null
    fun getCC(): C? {
        return c
    }
}

class C {
    var i: Int? = null
}
