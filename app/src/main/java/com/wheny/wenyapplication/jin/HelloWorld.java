package com.wheny.wenyapplication.jin;

/**
 * 类名：HelloWorld
 * 包名：com.wheny.wenyapplication.jin
 * 创建时间：2023/5/17 16:19
 * 创建人： WhenYoung
 * 描述：
 **/
public class HelloWorld {

    public static native String sayHello(String name); 	// 1.声明这是一个native函数，由本地代码实现

    public static void main(String[] args) {
        String text = sayHello("wenyong");	// 3.调用本地函数
        System.out.println(text);
    }

    static {
        System.loadLibrary("HelloWorld");	// 2.加载实现了native函数的动态库，只需要写动态库的名字
    }

}
