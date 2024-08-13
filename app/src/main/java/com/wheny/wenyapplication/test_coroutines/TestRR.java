package com.wheny.wenyapplication.test_coroutines;

import android.util.Log;

/**
 * 类名：TestRR
 * 包名：com.wheny.wenyapplication.test_coroutines
 * 创建时间：2024/6/13 11:17
 * 创建人： WhenYoung
 * 描述：
 **/
public class TestRR {

    int i = 0;

    public void test() {
        int x = 0;
        for (int j = 0; j < 5; j++) {
            int finalX = x;
            RR r1 = new RR(){
                @Override
                void r() {
                    int xxx = finalX;
                    Log.e("TestRR","========:"+i);

                }
            };
            x = 1;
            r1.r();
        }
    }

    RR r =new  RR(){

        @Override
        void r() {
            Log.e("TestRR","------:"+i);
        }
    };

}

abstract class RR {
    abstract void r();
}

