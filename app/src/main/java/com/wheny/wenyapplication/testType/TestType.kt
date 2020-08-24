package com.wheny.wenyapplication.testType

import io.reactivex.Flowable
import java.lang.reflect.ParameterizedType

class TestType {

}

open class A<T:B>{
}
open class D<T:B> : A<T>(){
    fun getType():Class<*>?{
        var  parameterizedType = this.javaClass.genericSuperclass as? ParameterizedType
        return  parameterizedType?.actualTypeArguments!![0] as? Class<*>
    }
}
open class B{
}

class C :B(){
}
class E< T:C>{
    constructor(){

    }
}




fun squrt(value:Double,accuracy:Int):Double{
    var max:Double =0.0
    var min:Double =0.0
    if(value == 1.0){
        return 1.0
    }
    if(value>0){
        max = value
        min = 1.0
    }else{
        max = 1.0
        min = 0.0
    }

    var currentValue : Double = 0.0
    var count = 0

    while(count<accuracy){
        currentValue = (max+min)/2
        if(currentValue*currentValue<value){//二分法 根号value > cur
            min = currentValue
        }else if(currentValue*currentValue>value){
            max = currentValue
        }else{
            return currentValue
        }
        count++
    }
    return currentValue
}

fun test(){
    var list:List<Int> = listOf<Int>(1,2,3,4,5)
    var list1 = listOf<Int>(1,2,3,4,5)
    var list3 = listOf<String>("a","b","c","d")

    for(i in list){
        if(i % 2 !=0){
            continue
        }
        for(j in list1){
            for(s in list3){
                print(""+i+j+s)
            }
        }
    }


    var dis = Flowable.just(1,2,3,4)
            .filter{
                if(it % 2 == 0){
                    return@filter true
                }
                return@filter false
            }

            .flatMap {
                return@flatMap Flowable.just("1$it","2$it","3$it","4$it")
            }

            .flatMap {
                return@flatMap Flowable.just("a$it","b$it","c$it","d$it")
            }

            .subscribe {
                print("$it ")
            }



}


fun main() {
    var a =object : D<C>(){}
//    var a = D<C>()
//    System.out.println("${a.getType()}")
//    System.out.println("${squrt(3.0,100)}")
    test()
}