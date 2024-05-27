package com.wheny.whenycompilerlib;

/**
 * 类名：Test
 * 包名：com.wheny.whenylibrary.edslider
 * 创建时间：2023/9/6 09:39
 * 创建人： WhenYoung
 * 描述：
 **/
public class Test {
    public static void main(String[] args) {
        System.out.println("===================");
        System.out.println("===================");
        System.out.println("===================");
        if (0 == 1) {
            // these characters are magic:
            // \u000a\u007d\u007b
            System.out.println("true is false");
        }
        /**
         *   \u000a\u007d\u007b
         *   这一句编译有问题！！！！！！！！   \u000a 代表换行符， \u007d代表 }  \u007b代表 {
         *   所以代码会被编译成
         */
        if (0 == 1) {
            // these characters are magic:
        }
        {
            System.out.println("true is false");
        }


        System.out.println("-------------------");
        System.out.println("-------------------");
        System.out.println("-------------------");
    }
}
