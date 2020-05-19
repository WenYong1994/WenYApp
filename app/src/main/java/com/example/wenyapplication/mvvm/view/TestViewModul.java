package com.example.wenyapplication.mvvm.view;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.wenyapplication.BR;
import com.example.wenyapplication.mvvm.view_model.LoginVm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import kotlin.reflect.KClass;

public class TestViewModul {
    private static Map<String,Field> fieldMap = new HashMap<>();


    public static <T extends ViewModel> T injectViewModel(AppCompatActivity context,Class clazz){
        T viewModel= (T) ViewModelProviders.of(context).get(clazz);
        return viewModel;
    }

    public static <T extends ViewModel> T handViewModel(AppCompatActivity activity,ViewDataBinding viewDataBinding, String dataBindingFieldName,String outFieldName,Class clazz) {
        T viewModel = injectViewModel(activity,clazz);
        try {
            Field field = fieldMap.get(dataBindingFieldName);
            if(field == null){
                field  = BR.class.getField(dataBindingFieldName);
                fieldMap.put(dataBindingFieldName,field);
            }
            field.setAccessible(true);
            int anInt = field.getInt(BR.class);
            viewDataBinding.setVariable(anInt,viewModel);

            String className = activity.getClass().getName();
            Field outFiled = fieldMap.get(className);
            if(outFiled == null){
                outFiled = activity.getClass().getField(outFieldName);
                fieldMap.put(className,outFiled);
            }
            outFiled.setAccessible(true);
            outFiled.set(activity,viewModel);


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return viewModel;
    }


    public static void init(AppCompatActivity activity,ViewDataBinding viewDataBinding){
        handViewModel(activity,viewDataBinding,"","",LoginVm.class);

    }


}
