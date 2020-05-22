package com.example.whenyannotationlib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //该注解的作用范围接口、类、枚举
@Retention(RetentionPolicy.CLASS) //默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
public @interface Route {

    /**
     * 定义：路由的路径，标识一个路由节点
     * @return
     */
    String path();


    /**
     *  将路由节点进行分组，可以实现按组动态加载
     * @return
     */
    String group() default "";
}