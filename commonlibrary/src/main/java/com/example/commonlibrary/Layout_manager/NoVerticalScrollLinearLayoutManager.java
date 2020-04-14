package com.example.commonlibrary.Layout_manager;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

public class NoVerticalScrollLinearLayoutManager extends LinearLayoutManager {
    public NoVerticalScrollLinearLayoutManager(Context context) {
        super(context);
    }

    public NoVerticalScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
