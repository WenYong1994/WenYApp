package com.example.commonlibrary.enums

enum class LoadMoreState {
    STATE_LOADING,  //加载中
    STATE_END_FORONCE,  //单次加载完成
    STATE_END_FORALL,  //全部加载完成
    STATE_FAILED,  //加载失败
    STATE_HIDE,  //隐藏
    STATE_EMPTY
    //空白
}