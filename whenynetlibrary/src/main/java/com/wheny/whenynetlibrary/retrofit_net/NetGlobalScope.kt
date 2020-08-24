package com.wheny.whenynetlibrary.retrofit_net

import android.app.Activity
import com.google.gson.JsonParseException
import com.wheny.whenylibrary.utils.ToastUtils
import kotlinx.coroutines.*
import java.net.ConnectException

object NetGlobalScope{
    //处理错误的地方
    private var handErrorFun : ((e:Exception)->Unit)? = null

    fun doNet(activity:Activity?,block:suspend   NetGlobalScope.()->Unit): Job = GlobalScope.launch {
        try {
            NetGlobalScope.block()
        }catch (e:Exception){
            withContext(Dispatchers.Main){
                handErrorFun?.let { it(e) }
            }
        }
    }

    fun doNet(block:suspend ()->Unit,handError:(e:Exception)->Unit): Job = GlobalScope.launch {
        try {
            block()
        }catch (e:Exception){
            withContext(Dispatchers.Main){
                handErrorFun?.let { it(e) }
                handError(e)
            }
        }
    }


    fun initHandErrorFun(handErrorFun:(e:Exception)->Unit){
        this.handErrorFun = handErrorFun
    }

    @Throws(ResultException::class)
    fun onNetError(code:Int,msg:String?){
        throw ResultException(code,msg)
    }

}


