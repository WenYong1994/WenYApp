package com.wheny.wenyapplication.mvvm.view.testHanlder

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.wheny.wenyapplication.mvvm.view.testHanlder.Utils.miniAppPath
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.roundToInt

object Utils {
    private val defaultSet = setOf("iInfoId", "momentId", "postId")

    //使用 router 区分是战绩详情 还是战绩首页
    //战绩详情小程序分享的参数
    //https://camp.qq.com/h5/webdist/mp-redirect/index.html?title=银牌战士+14/7/17！我的曜，YYDS！&userId=1767314781&roleId=2312928006&router=battleDetail&gameSeq=1668593794&aCntCamp=1&relaySvrId=588776196&battleType=15&gameSvrId=382502&pvpType=5
    //战绩主页小程序分享
    //https://camp.qq.com/h5/webdist/mp-redirect/index.html?title=哎名字难啊啊的王者荣耀战绩主页&userId=1767314781&roleId=2312928006&router=battleHome
    private val battleSet = setOf(
        "title",
        "userId",
        "roleId",
        "router",
        "gameSeq",
        "aCntCamp",
        "relaySvrId",
        "battleType",
        "gameSvrId",
        "pvpType"
    )
    private val defaultMiniAppMap = mapOf(
        "iInfoId" to "iInfoId",
        "momentId" to "momentId",
        "postId" to "iInfoId"
    )
    private const val basePath = "subPackages/newsInfo/newsInfo"
    private const val baseBattleDetailPath = "/subPackages/record/info/info"
    private const val baseBattleHomePath = "/subPackages/record/guest/index"

    fun String.miniAppPath(): String? {
        try {
            val router = toUri().getQueryParameter("router")
            when (router) {
                "battleHome" -> {
                    val uri = baseBattleHomePath.toUri().buildUpon()
                    parseAllMiniAppParams()?.forEach { t ->
                        uri.appendQueryParameter(t.key, t.value)
                    }
                    return uri.toString()
                }
                "battleDetail" -> {
                    val uri = baseBattleDetailPath.toUri().buildUpon()
                    parseAllMiniAppParams()?.forEach { t ->
                        uri.appendQueryParameter(t.key, t.value)
                    }
                    return uri.toString()
                }
            }
            val params = parseMiniAppParams() ?: return null
            return "${basePath}?${defaultMiniAppMap[params.first]}=${params.second}"
        } catch (e: Exception) {

        }
        return null
    }

    private fun String.parseMiniAppParams(): Pair<String, String>? {
        try {
            val uri = toUri()
            for (name in defaultSet) {
                val value = uri.getQueryParameter(name)
                if (!value.isNullOrBlank()) {
                    return name to value
                }
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }

    private fun String.parseAllMiniAppParams(): Map<String, String>? {
        try {
            val map = mutableMapOf<String, String>()
            val uri = toUri()
            for (name in battleSet) {
                val value = uri.getQueryParameter(name)
                if (!value.isNullOrBlank()) {
                    map.put(name, value)
                }
            }
            return map
        } catch (e: Exception) {
            return null
        }
    }

    var callback: (suspend (Int, Int) -> Boolean)? = null

    fun callTest(callback: (suspend (Int, Int) -> Boolean)?) {
        this.callback = callback
    }


    suspend fun test2(x: Int, y: Int): Boolean {
        if(x>y){
            return true
        }
        return false
    }

}

abstract class TestCorou<in T> : kotlin.coroutines.Continuation<T>{

    abstract fun resume(t:T)

    abstract fun resumeWithException(exception: Throwable)

    override fun resumeWith(result: Result<T>) = result.fold(::resume,::resumeWithException)

}

public inline fun String.toUri(): Uri = Uri.parse(this)


