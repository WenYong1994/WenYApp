package com.wheny.wenyapplication.mvvm.view.main

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.wheny.wenyapplication.mvvm.view.databinding.TestDataBindingActivity
import com.wheny.wenyapplication.mvvm.view.list.TestListActivity
import com.wheny.wenyapplication.mvvm.view.login.LoginActivity
import com.wheny.whenylibrary.mvvm.contract.VmContract
import com.wheny.whenylibrary.mvvm.vm.BaseViewModel
import kotlinx.coroutines.delay

class MainVm<T : VmContract> : BaseViewModel<T>() {
    var dataBindingBtnTxt = MutableLiveData("数据绑定")
    var loginVmTxt = MutableLiveData("loginVm")
    var testNetTxt = MutableLiveData("测试网络")
    var testListTxt = MutableLiveData("测试列表")

    fun testDataBinding(view: View) {
        view.context.startActivity(Intent(view.context, TestDataBindingActivity::class.java))
    }

    fun testLogin(view: View) {
        view.context.startActivity(Intent(view.context, LoginActivity::class.java))
    }

    inline fun testList(view: View) {
        view.context.startActivity(Intent(view.context, TestListActivity::class.java))
    }

}


open class Parent {
    open fun print() {
        println("Parent print method")
    }
}

class Child : Parent() {
    override fun print() {
        println("Child print method")
    }
}


object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            // 创建一个Child对象
            val child = Child()

            // 获取Child类的Class对象
            val childClass: Class<*> = child.javaClass

            // 获取Child父类的Class对象
            val parentClass = childClass.superclass

            // 获取Parent类的print方法
            val parentPrintMethod = parentClass.getDeclaredMethod("print")

            // 调用Parent类的print方法
            // 在子类实例上调用父类方法
            parentPrintMethod.invoke(child)

            try {
                var child1: Child? = null
                child1!!.print()
            } catch (e: Exception) {
                e.printStackTrace()
            }


        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}




