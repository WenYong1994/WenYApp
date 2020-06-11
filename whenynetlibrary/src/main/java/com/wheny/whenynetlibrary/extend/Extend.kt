package com.wheny.whenynetlibrary.extend

import com.wheny.whenynetlibrary.data.BaseResp

/*数据解析扩展函数*/
fun <T> BaseResp<T>.dataConvert(): T? {
    if (code == 200) {
        return data
    } else {
        throw Exception(msg)
    }
}

/**
 * @description: 对RxKotlin的扩展
 * @author : yzq
 * @date   : 2018/7/9
 * @time   : 15:36
 *
 */

/**
* 对Observable进行线程调度和生命周期绑定
*
* */
//fun <T> Observable<T>.transform(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
//    return this.compose(RxSchedulers.io_main<>()).autoDisposable(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY))
//}




