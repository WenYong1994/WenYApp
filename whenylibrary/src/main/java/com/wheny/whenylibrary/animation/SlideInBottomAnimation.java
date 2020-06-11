package com.wheny.whenylibrary.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;


/**
 * author：Vinsmoke
 * e-mail：vinsmokeleigh@gmail.com
 */
public class SlideInBottomAnimation implements BaseAnimation {



    @Override
    public Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0)
        };
    }
}
