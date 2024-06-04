package com.wheny.wenyapplication.test_pb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.pb.test.TestPb
import com.pb.test.TestPb.Sex
import com.wheny.wenyapplication.R
import com.google.protobuf.util.JsonFormat
import org.json.JSONObject

class TestPbActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_pb)


        findViewById<View>(R.id.test_pb).setOnClickListener {
            testPb()
        }
    }

    private fun testPb() {
        val originTestBean = TestPb.TestBean.newBuilder()
            .setSex(Sex.man)
            .setId(1)
            .setName("test1")
            .addJob("coder1")
            .addJob("coder2")
            .build()
//        originTestBean.allFields
        val sexValue = originTestBean.sexValue
        val sex = Sex.forNumber(sexValue)
        // 获取枚举
        Log.e("testPb","originTestBean:${originTestBean}")
        Log.e("testPb","sexValue:${sexValue},sex:${sex}")
        // 和 bytes 互相转换
        val testBeanBytes = originTestBean.toByteArray()
        val newTestBean = TestPb.TestBean.newBuilder().mergeFrom(testBeanBytes)
        Log.e("testPb","originTestBean:${originTestBean}")
        Log.e("testPb","sexValue:${sexValue},sex:${sex}")
        // 和 json 互相转换
        val testBeanJson = JsonFormat.printer().print(originTestBean)
        val json = JSONObject(testBeanJson).apply {
            put("sex",1)
        }.toString()
        Log.e("testPb","json:${json}")
        val newJsonBuilder = TestPb.TestBean.newBuilder()
        JsonFormat.parser().merge(json,newJsonBuilder)
        val jsonTestBean = newJsonBuilder.build()
//        Log.e("testPb","jsonTestBean:${jsonTestBean}")
        Log.e("testPb","sexValue:${jsonTestBean.sexValue}")
    }
}