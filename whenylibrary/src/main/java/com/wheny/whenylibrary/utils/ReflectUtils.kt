package com.wheny.whenylibrary.utils

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

// 使用反射技术得到T的真实类型
fun getRealType(any: Any): Type? {
    var type = any::class.java
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