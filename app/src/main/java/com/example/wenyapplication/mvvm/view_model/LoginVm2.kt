package com.example.wenyapplication.mvvm.view_model

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wenyapplication.mvvm.data.LoginBean
import com.example.wenyapplication.mvvm.model.LoginModel
import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.TypeVariable
import java.util.*

class LoginVm2(string:String,app: Application) : AndroidViewModel(app){
    private var loginModel = LoginModel()

    var loginBean = MutableLiveData<LoginBean>()


    var account:String = ""
    var pwd : String =""
    var msg :String = string
        set(value) {
            field = value
        }

    fun doLogin(){
        Handler().postDelayed(object : Runnable {
            override fun run() {
                /*通知Activity刷新数据*/
                loginBean.value = loginModel.login(account)
                msg = loginBean.value?.message.toString()

            }
        }, 3000)
    }


    override fun onCleared() {
        super.onCleared()
    }

}

class LoginVm2Factory(private val string:String,private val app: Application) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginVm2(string,app) as T
    }
}


fun getType(type: Type): Type {
    when (type) {
        is ParameterizedType -> {
            return getGenericType(type)
        }
        is TypeVariable<*> -> {
            return getType(type.bounds[0])
        }
    }
    return type
}

fun getGenericType(type: ParameterizedType): Type {
    if (type.actualTypeArguments.isEmpty()) return type
    val actualType = type.actualTypeArguments[0]
    when (actualType) {
        is ParameterizedType -> {
            return actualType.rawType
        }
        is GenericArrayType -> {
            return actualType.genericComponentType
        }
        is TypeVariable<*> -> {
            return getType(actualType.bounds[0])
        }
    }
    return actualType
}

// 使用反射技术得到T的真实类型
fun getRealType(type: Class<*>): Type? {
    // 获取当前new的对象的泛型的父类类型
    var pt: ParameterizedType? = null
    val genericSuperclass: Type = type.genericSuperclass!!
    if (genericSuperclass is ParameterizedType) {
        pt = genericSuperclass
        // 获取第一个类型参数的真实类型
        return pt!!.actualTypeArguments[0]
    }
    return genericSuperclass
}


fun main(args: Array<String>) {
    val list =object :LinkedList<LoginVm2>(){}
    val type = getRealType(list::class.java)
    println(type)
}
