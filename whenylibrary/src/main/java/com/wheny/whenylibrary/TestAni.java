package com.wheny.whenylibrary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类名：TestAni
 * 包名：com.wheny.whenycompilerlib
 * 创建时间：2022/7/5 10:58
 * 创建人： WhenYoung
 * 描述：
 **/
@Target(ElementType.TYPE) //该注解的作用范围参数
@Retention(RetentionPolicy.CLASS)
public @interface TestAni {
    String moduleName() default "";
}
