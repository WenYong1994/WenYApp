package com.example.commonlibrary.annotation_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import com.example.whenyannotationlib.AnnotationConstant;

public class ViewModelInjector {
    public static void inject(AppCompatActivity activity, ViewDataBinding viewDataBinding){
        ViewModelInjectorImp viewModelInjector = null;
        String className = AnnotationConstant.VIEW_MODEL_PACKNAME+"."+activity.getClass().getSimpleName()+ AnnotationConstant.VIEW_MODEL_SUFFIX;
        try {
            Class<?> aClass = Class.forName(className);
            viewModelInjector = (ViewModelInjectorImp) aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if(viewModelInjector!=null){
            viewModelInjector.injectViewModels(activity,viewDataBinding);
        }
    }

}
