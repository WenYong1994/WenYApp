package com.example.commonlibrary.annotation_api;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

public interface ViewModelInjectorImp {
    void injectViewModels(AppCompatActivity activity, ViewDataBinding viewDataBinding);
}
