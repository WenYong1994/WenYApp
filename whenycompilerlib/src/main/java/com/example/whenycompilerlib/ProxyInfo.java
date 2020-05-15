package com.example.whenycompilerlib;

import com.example.whenyannotationlib.TestAn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

/**
 * @author dhht
 */
public class ProxyInfo {

    /**
     * 所在包名
     */
    private String packageName;

    /**
     * 生成的类名
     */
    private String proxyClassName;
    /**
     * 外部类
     */
    private TypeElement typeElement;

    /**
     * 保存类里面的所有注解
     */
    public List<Element> mElementList = new ArrayList<>();


    public static final String PROXY = "ViewInject";

    public ProxyInfo(Elements elementUtils, TypeElement classElement) {
        this.typeElement = classElement;
        PackageElement packageElement = elementUtils.getPackageOf(classElement);
        String packageName = packageElement.getQualifiedName().toString();
        String className = ClassValidator.getClassName(classElement, packageName);
        this.packageName = packageName;
        this.proxyClassName = className + "$$" + PROXY;
//        System.out.println(proxyClassName+"-------------------------------------------------");
    }

    /**
     * 生成代码
     *
     * @return
     */
    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("// Generated code. Do not modify!\n");
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import android.view.View;\n");
        builder.append("import com.dhht.annotation.*;\n");
        builder.append("import android.widget.Switch;\n");
        builder.append("import android.widget.CheckBox;\n");

        builder.append("import android.support.v4.widget.SwipeRefreshLayout;\n");
        builder.append("import android.util.Log;\n");
        builder.append("import android.support.v7.widget.RecyclerView;\n");
        builder.append("import com.dhht.annotationlibrary.view.RecyclerViewScrollListener;\n");
        builder.append("import com.dhht.annotationlibrary.view.AvoidShake;\n");

        builder.append("import com.dhht.annotationlibrary.view.AvoidShakeClickHelper;\n");
        builder.append("import com.dhht.annotationlibrary.view.AvoidShakeListener;\n");


        builder.append("import ").append(getLibrayPath(packageName)).append(".R;\n");
        builder.append("import com.dhht.annotationlibrary.*;\n");
        builder.append('\n');
        builder.append("public class ").append(proxyClassName).append(" implements " + ProxyInfo.PROXY + "<" + typeElement.getQualifiedName() + ">");
        builder.append(" {\n");

        //生成方法
//        generateMethods(builder);


        builder.append('\n');
        builder.append("}\n");
        return builder.toString();
    }


    /**
     * 生成根据注解去生成代码
     *
     * @param builder
     */
    private void generateMethods(StringBuilder builder) {
        builder.append("@Override\n ");
        builder.append("public void testAn(" + typeElement.getQualifiedName() + " host, Object source ) {\n");
        builder.append("initTestAn(host,source);\n ");
        builder.append("  }\n");


        //生成 initTestAn 方法
        builder.append("public void initTestAn(" + typeElement.getQualifiedName() + " host, Object source ) {\n");
        Iterator<Element> iterator = mElementList.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            TestAn annotation = element.getAnnotation(TestAn.class);
            if (annotation != null) {
                VariableElement variableElement = (VariableElement) element;
                generateTestAn(variableElement, builder);
                iterator.remove();
            }
        }
        builder.append("}");


    }

    /**
     * 生成TestAn方法
     */
    private void generateTestAn(VariableElement variableElement, StringBuilder builder) {
        //获取注解值
        String string = variableElement.getAnnotation(TestAn.class).value();
        //获取变量类型
        String type = variableElement.asType().toString();
        //获取变量名字
        String name = variableElement.getSimpleName().toString();

        builder.append(" if(source instanceof android.app.Activity){\n");
        builder.append("Log.e(\"ViewInjector\",\"==============================================string:\");");
        builder.append("}\n");
    }


    /**
     * 获取全名
     *
     * @return
     */
    public String getProxyClassFullName() {
        return packageName + "." + proxyClassName;
    }

    /**
     * 获取TypeElement
     *
     * @return
     */
    public TypeElement getTypeElement() {
        return typeElement;
    }


    /**
     * 获取包名
     *
     * @param packageName
     * @return
     */
    private String getLibrayPath(String packageName) {
        try {
            return packageName.substring(0, ordinalIndexOf(packageName, ".", 3));
        } catch (Exception e) {
            return packageName;
        }
    }


    private int ordinalIndexOf(String str, String substr, int n) {
        int pos = str.indexOf(substr);
        while (--n > 0 && pos != -1) {
            pos = str.indexOf(substr, pos + 1);
        }
        return pos;
    }
}