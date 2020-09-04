package com.wheny.wenyapplication.test_coroutines

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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
    }

    fun test(){
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

    fun testAsync2(){
        Log.e(TAG,"------------1${Thread.currentThread().id}")
        GlobalScope.launch(EmptyCoroutineContext,CoroutineStart.UNDISPATCHED) {
            withContext(Dispatchers.Main){
                delay(10000)
                Log.e(TAG,"=============${Thread.currentThread().id}")
            }
        }
        repeat(2){
            Log.e(TAG,"------------${it}${Thread.currentThread().id}")
        }
    }
    fun oncl(view: View) {
        Log.e(TAG,"+++++++++++++++++++${Thread.currentThread().id}")
        ToastUtils.showShortToast(this,"sslslssls")
    }


    fun testAsync(){
        val job = GlobalScope.launch {
            delay(1000)
            val result1 = async {
                getResult1()
            }
            val result2 = async {
                getResult2()
            }
            Log.e(TAG,"start-------")
            val result = result1.await() + result2.await()
            Log.e(TAG,"result = $result")
        }


    }




    private suspend fun getResult1(): Int {
        delay(3000)
        Log.e(TAG,"getResult1")
        return 1
    }

    private suspend fun getResult2(): Int {
        Log.e(TAG,"getResult2")
        delay(4000)
        return 2
    }

    private fun testRunBlocking() = runBlocking {
        repeat(100){
            delay(if(it%2 == 0) 2000 else 1000)
            Log.e(TAG,"协程执行$it 线程id：${Thread.currentThread().id}")
        }

    }


    private fun testJob() : Job {
        return GlobalScope.launch (EmptyCoroutineContext,CoroutineStart.DEFAULT){
            delay(2000)
            Log.e(TAG, "协程执行结束 --11111线程id：${Thread.currentThread().id}")
        }
    }
    private fun testJob1() : Job {
        return GlobalScope.launch (EmptyCoroutineContext,CoroutineStart.DEFAULT){
            delay(1000)
            Log.e(TAG, "协程执行结束 -- 222222线程id：${Thread.currentThread().id}")
        }
    }

    fun testF(hh:String.(x:Int,y:String)->Int){
    }

    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.N)
    fun testRxJavaZip(){

        Flowable.just(1,2,3,4,5)
                .subscribe(object : Consumer<Int> {
                    override fun accept(t: Int) {

                    }
                })


    }


}


class sss{
    fun f(){
    }
}