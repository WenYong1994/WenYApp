package com.wheny.whenycompilerlib;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/**
 * 类名：CompilerProcessorUtils
 * 创建时间：2022/6/28 09:44
 * 创建人： WhenYoung
 * 描述：
 **/
public class CompilerProcessorUtils {

    /**
     * 通过VariableElement 获取所在的 class 名字
     */
    public static String getEnclosingClassName(ProcessingEnvironment processingEnv,VariableElement element) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        return typeElement.getQualifiedName().toString();
    }

    /**
     * 通过ClassSymbol 获取所在的 class 的名字
     */
    public static String getEnclosingClassName(ProcessingEnvironment processingEnv,Symbol.ClassSymbol symbol) {
        return symbol.getQualifiedName().toString();
    }

    /**
     * 通过VariableElement获取包名
     */
    public static String getPackageName(ProcessingEnvironment processingEnv, VariableElement element) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        return processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
    }

    /**
     * 通过VariableElement获取包名
     */
    public static String getPackageName(ProcessingEnvironment processingEnv, Symbol.ClassSymbol symbol) {
        return processingEnv.getElementUtils().getPackageOf(symbol).getQualifiedName().toString();
    }

    public static boolean instanceofClass(Symbol.ClassSymbol symbol,Class clazz){
        Type.ClassType type = (Type.ClassType) symbol.type;
        while (type!=null){
            if(type.toString().equals(clazz.getName())){
                return true;
            }
            Type t = type.supertype_field;
            if(t instanceof Type.ClassType){
                type = (Type.ClassType) t;
            }else {
                return false;
            }
        }
        return false;
    }


}
