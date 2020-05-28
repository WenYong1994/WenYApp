package com.example.whenyannotationapilib;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.example.whenyannotationlib.AnnotationConstant;

import java.util.HashMap;
import java.util.Map;

public class ViewModelInjector {
    private static Map<String,ViewModelInjectorApi> map = new HashMap<>();
    public static <T extends ViewDataBinding> T inject(AppCompatActivity activity, int layoutId){
        ViewModelInjectorApi viewModelInjector = null;
        String className = AnnotationConstant.VIEW_MODEL_PACKNAME+"."+activity.getClass().getSimpleName()+ AnnotationConstant.VIEW_MODEL_SUFFIX;
        try {
            viewModelInjector = map.get(className);
            if(viewModelInjector==null){
                Class<?> aClass = Class.forName(className);
                viewModelInjector = (ViewModelInjectorApi) aClass.newInstance();
                map.put(className,viewModelInjector);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if(viewModelInjector!=null){
            return viewModelInjector.injectViewModels(activity,activity,null,layoutId,null);//里面没有强引用Activity 所以不用担心内存泄漏
        }
        return null;
    }


    public static void injectByFactory(AppCompatActivity activity,ViewModelProvider.NewInstanceFactory factory, String fieldName,ViewDataBinding viewDataBinding){
        ViewModelInjectorApi viewModelInjector = null;
        String className = AnnotationConstant.VIEW_MODEL_PACKNAME+"."+activity.getClass().getSimpleName()+ AnnotationConstant.VIEW_MODEL_SUFFIX;
        try {
            viewModelInjector = map.get(className);
            if(viewModelInjector==null){
                Class<?> aClass = Class.forName(className);
                viewModelInjector = (ViewModelInjectorApi) aClass.newInstance();
                map.put(className,viewModelInjector);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if(viewModelInjector!=null){
            viewModelInjector.injectViewModelByFactory(activity,activity,factory,fieldName,viewDataBinding);//里面没有强引用Activity 所以不用担心内存泄漏
        }
    }


    public static <T extends ViewDataBinding> T inject(Object tag,AppCompatActivity activity,LayoutInflater inflater, int layoutId, ViewGroup group){
        ViewModelInjectorApi viewModelInjector = null;
        String className = AnnotationConstant.VIEW_MODEL_PACKNAME+"."+tag.getClass().getSimpleName()+ AnnotationConstant.VIEW_MODEL_SUFFIX;
        try {
            viewModelInjector = map.get(className);
            if(viewModelInjector==null){
                Class<?> aClass = Class.forName(className);
                viewModelInjector = (ViewModelInjectorApi) aClass.newInstance();
                map.put(className,viewModelInjector);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if(viewModelInjector!=null){
            return viewModelInjector.injectViewModels(tag,activity,inflater,layoutId,group);//里面没有强引用Activity 所以不用担心内存泄漏
        }
        return null;
    }


    public static void injectByFactory(Object tag,AppCompatActivity activity,ViewModelProvider.NewInstanceFactory factory, String fieldName,ViewDataBinding viewDataBinding){
        ViewModelInjectorApi viewModelInjector = null;
        String className = AnnotationConstant.VIEW_MODEL_PACKNAME+"."+tag.getClass().getSimpleName()+ AnnotationConstant.VIEW_MODEL_SUFFIX;
        try {
            viewModelInjector = map.get(className);
            if(viewModelInjector==null){
                Class<?> aClass = Class.forName(className);
                viewModelInjector = (ViewModelInjectorApi) aClass.newInstance();
                map.put(className,viewModelInjector);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if(viewModelInjector!=null){
            viewModelInjector.injectViewModelByFactory(tag,activity,factory,fieldName,viewDataBinding);//里面没有强引用Activity 所以不用担心内存泄漏
        }
    }



}
