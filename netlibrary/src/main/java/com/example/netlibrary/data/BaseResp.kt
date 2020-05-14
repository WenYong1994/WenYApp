package com.example.netlibrary.data

class BaseResp<T> {
    var code:Int = 0
    var msg :String =""
    var `data`: T? = null

}