package com.wheny.wenyapplication.test_coroutines

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wheny.wenyapplication.R
import com.wheny.whenylibrary.utils.ToastUtils
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import kotlinx.coroutines.*
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
        testAsync2()
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

    fun oncl(view: View) {
        test1()
    }

    val myScope = CoroutineScope(Dispatchers.Default)
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("testCoroutine", "exceptionHandler-handler " + throwable.message)
    }
    private fun test1() {
        val list = listOf(1,2,3,4,5,6)
        kotlin.run {
            list.forEach {
                Log.e("TestFor","index:${it}")
                if (it == 3){
                    return@run
                }
            }
        }
        Log.e("TestFor","end")

        GlobalScope.launch(exceptionHandler) {
            launch {
                delay(2000)
                Log.e("TestCoroutineException", "start-job3 delay")
            }
            supervisorScope{
                launch {
                    Log.e("TestCoroutineException", "start job1 delay")
                    delay(1000)
                    Log.e("TestCoroutineException", "end job1 delay")
                }
                launch {
                    Log.e("TestCoroutineException", "job2 throw execption")
                    throw NullPointerException("Test Exception")
                }
            }
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