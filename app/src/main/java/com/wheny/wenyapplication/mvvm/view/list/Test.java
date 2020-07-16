package com.wheny.wenyapplication.mvvm.view.list;

import java.lang.reflect.Type;

public class Test {
    public static void main(String[] args) {
        C c = new C();
        Type[] genericInterfaces = c.getClass().getGenericInterfaces();

    }

}

class A<B,D>{

}
class C extends A<B,D>{

}

class B{

}
class D{

}
