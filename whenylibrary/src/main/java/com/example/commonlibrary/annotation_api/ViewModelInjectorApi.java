package com.example.commonlibrary.annotation_api;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

public interface ViewModelInjectorApi {

    <T extends ViewDataBinding> T injectViewModels(AppCompatActivity activity, int layoutId);

    void injectViewModelByFactory(AppCompatActivity activity, ViewModelProvider.NewInstanceFactory factory, ViewDataBinding viewDataBinding);

}
