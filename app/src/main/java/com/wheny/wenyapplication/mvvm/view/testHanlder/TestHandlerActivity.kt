package com.wheny.wenyapplication.mvvm.view.testHanlder

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

val TAG = "TestHandlerActivity"

class TestHandlerActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test_handler)
        val btn = Button(this)
        val lp = AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        btn.layoutParams = lp
        btn.text="sfafa"
        setContentView(btn)
        val myThread = MyThread()
        myThread.start()
        var i=0
        btn.setOnClickListener {
            myThread.hd.sendEmptyMessage(i++)
        }

    }


}
class MyThread: Thread() {
    lateinit var hd : Handler
    override fun run() {
        super.run()
        Log.e(TAG,"11111111111111111111111111111111111111111111111111111111111")
        Looper.prepare()
        hd = @SuppressLint("HandlerLeak")
        object : Handler(){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.e(TAG,"==========================================${msg.what}")
            }
        }
        Looper.loop()
        Log.e(TAG,"-----------------------------------------------------------")
    }
}


