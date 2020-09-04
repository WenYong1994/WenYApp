package com.wheny.wenyapplication.mvvm.view.test_delegation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wheny.wenyapplication.R
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class TestDeleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dele)
    }
}



interface  Subject{
    fun request1()
    fun request2()

}


class RealSubject : Subject{
    override fun request1() {
        println("ReaSubject request1")
    }

    override fun request2() {
        println("ReaSubject request2")
    }
}

class Proxy(var subject: Subject) : Subject{

    override fun request1() {
        subject.request1()
    }

    override fun request2() {
        subject.request2()
    }

}

interface Base {
    fun print()
}

class BaseImpl(val a: Int) : Base {
    override fun print() { print(a) }
}

class Derived(b: Base) : Base by b{
    var x :Int by MyDele()


}


class MyDele{
    var aa:Int = 0

    operator fun getValue(derived: Derived, property: KProperty<*>): Int {
        return  aa
    }

    operator fun setValue(derived: Derived, property: KProperty<*>, i: Int) {
        aa = i
    }

}

val lazeValue :String by lazy {
    println("执行lazy")
    return@lazy "hello"
}




fun main() {
//    val proxy = Proxy(RealSubject())
//    proxy.request1()
    val b = BaseImpl(10)
    val derived = Derived(b)
    derived.x =101
    println(derived.x)


}

