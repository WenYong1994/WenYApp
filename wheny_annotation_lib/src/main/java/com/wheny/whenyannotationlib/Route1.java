package com.wheny.whenyannotationlib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类名：Route
 * 包名：com.wheny.whenyannotationlib
 * 创建时间：2022/6/27 17:16
 * 创建人： WhenYoung
 * 描述：
 **/
@Target(ElementType.TYPE) //该注解的作用范围参数
@Retention(RetentionPolicy.CLASS)
public @interface Route1 {

    String[] value();

    /**
     * The interceptors' name.
     */
    String[] interceptors() default {};

}
