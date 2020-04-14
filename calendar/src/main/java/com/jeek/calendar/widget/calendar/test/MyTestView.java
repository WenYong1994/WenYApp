package com.jeek.calendar.widget.calendar.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.jeek.calendar.library.R;

public class MyTestView extends View {

    public MyTestView(Context context) {
        super(context);
//        setBackgroundColor(context.getResources().getColor(R.color.test_color));
    }

    public MyTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setBackgroundColor(context.getResources().getColor(R.color.test_color));
        init(context,attrs);
    }

    public MyTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setBackgroundColor(context.getResources().getColor(R.color.test_color));
        init(context,attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        // 获取属性集合 TypedArray
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTestView);
        int color = typedArray.getColor(R.styleable.MyTestView_test_background, Color.BLACK);
        setBackgroundColor(color);
        typedArray.recycle();
    }





}
