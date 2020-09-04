package com.wheny.wenyapplication.test_coroutines;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class TestRxJava {
    public static void main(String[] args) {
        Flowable.just(1,3,4,5)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });
    }
}
