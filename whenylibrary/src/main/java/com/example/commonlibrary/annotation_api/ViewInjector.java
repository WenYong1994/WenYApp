package com.example.commonlibrary.annotation_api;

import android.app.Activity;

import android.view.View;

/**
 * Created by zhy on 16/4/22.
 */
public class ViewInjector {
    private static final String SUFFIX = "$$ViewInject";

    public static void injectView(Activity activity) {
        ViewInject testAn = findProxyActivity(activity);
        if (testAn == null) {
            return;
        }
        testAn.testAn(activity, activity);

    }

    public static void injectView(Object object, View view) {
        ViewInject testAn = findProxyActivity(object);
        if (testAn == null) {
            return;
        }
        testAn.testAn(object, view);
    }

    /**
     * 根据使用注解的类和约定的命名规则，反射获取注解生成的类
     *
     * @param object
     * @return
     */
    private static ViewInject findProxyActivity(Object object) {
        try {
            Class clazz = object.getClass();
            Class injectorClazz = Class.forName(clazz.getName() + SUFFIX);
            return (ViewInject) injectorClazz.newInstance();
        } catch (Exception e) {
            return null;
        }

    }
}