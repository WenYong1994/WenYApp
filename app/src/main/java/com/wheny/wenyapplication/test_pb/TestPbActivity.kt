package com.wheny.wenyapplication.test_pb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.pb.test.TestPb
import com.pb.test.TestPb.Sex
import com.wheny.wenyapplication.R
import com.google.protobuf.util.JsonFormat
import com.pb.test.TestPbMutile
import org.json.JSONObject

class TestPbActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_pb)


        findViewById<View>(R.id.test_pb).setOnClickListener {
            testPb()
            testPb2()
            testPb3()
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
        originTestBean.allFields
        val sexValue = originTestBean.sexValue
        val sex = Sex.forNumber(sexValue)
        // 获取枚举
        Log.e("testPb", "originTestBean:${originTestBean}")
        Log.e("testPb", "sexValue:${sexValue},sex:${sex}")
        // 和 bytes 互相转换
        val testBeanBytes = originTestBean.toByteArray()
        val newTestBean = TestPb.TestBean.newBuilder().mergeFrom(testBeanBytes)
        Log.e("testPb", "originTestBean:${newTestBean}")
        Log.e("testPb", "sexValue:${newTestBean.sex},sex:${newTestBean.sexValue}")
        // 和 json 互相转换
        val testBeanJson = JsonFormat.printer().print(originTestBean)
        val json = JSONObject(testBeanJson).apply {
            put("sex", 1)
        }.toString()
        Log.e("testPb", "json:${json}")
        val newJsonBuilder = TestPb.TestBean.newBuilder()
        JsonFormat.parser().merge(json, newJsonBuilder)
        val jsonTestBean = newJsonBuilder.build()
        Log.e("testPb", "jsonTestBean:${jsonTestBean}")
        Log.e("testPb", "sexValue:${jsonTestBean.sexValue}")


        val json1 = JSONObject(testBeanJson).apply {
        }.toString()
        val newJsonBuilder1 = TestPb.TestBean.newBuilder()
        JsonFormat.parser().merge(json1, newJsonBuilder1)
        val jsonTestBean1 = newJsonBuilder1.build()
        Log.e("testPb", "sexValue:${jsonTestBean1.sexValue}")
    }

    private fun testPb2() {
        val oriPb = TestPbMutile.CCCBean.newBuilder()
            .setId(1)
            .setName("mutileEnum")
//           .setB(1)
//           .setBB(2)
//           .setD(2)
//           .setDAaaCcc(3)
            .setB(TestPbMutile.BEnum.bb)
            .setBBValue(5)
            .setD(TestPbMutile.DEnum.dc)
            .setDAaaCccValue(4)
            .addJob("job1")
            .addJob("job2")
            .build()
        Log.e(
            "testPb2",
            "oriPb:${oriPb},enum-d:${oriPb.d},enum-b:${oriPb.b},enum-bb:${oriPb.bb},enum-dd:${oriPb.dAaaCcc}"
        )
        // 和 bytes 转换
        val bytes = oriPb.toByteArray()
        val bytesPb = TestPbMutile.CCCBean.newBuilder().mergeFrom(bytes).build()
        Log.e(
            "testPb2",
            "bytesPb:${bytesPb},enum-d:${bytesPb.d},enum-b:${bytesPb.b},enum-bb:${bytesPb.bb},enum-dd:${bytesPb.dAaaCcc}"
        )
        // 和 json 转换
        val json = JsonFormat.printer().print(oriPb)
        val jsonNew = JSONObject(json).apply {
            put("D", 5)
            put("DAaaCcc", 5)
        }.toString()
        val newJsonBuilder = TestPbMutile.CCCBean.newBuilder()
        JsonFormat.parser().merge(jsonNew, newJsonBuilder)
        val jsonPb = newJsonBuilder.build()
        Log.e(
            "testPb2",
            "jsonPb:${jsonPb},enum-d:${jsonPb.d},enum-b:${jsonPb.b},enum-bb:${jsonPb.bb},enum-dd:${jsonPb.dAaaCcc}"
        )
    }


    private fun testPb3(){
        val json = "{\"b\":3}"
        val newJsonBuilder = TestPbMutile.CCCBean.newBuilder()
        JsonFormat.parser().merge(json, newJsonBuilder)
        val jsonPb = newJsonBuilder.build()
        val value = jsonPb.bValue
        val b = jsonPb.b
        jsonPb.b.number
    }

}