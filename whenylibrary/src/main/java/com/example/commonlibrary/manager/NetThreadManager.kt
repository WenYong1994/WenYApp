package com.example.commonlibrary.manager

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

object NetThreadManager {
    const val THREAD_COUNT = 8

    val io_ThreadPool : Scheduler by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Schedulers.from(Executors.newFixedThreadPool(THREAD_COUNT))
    }


}