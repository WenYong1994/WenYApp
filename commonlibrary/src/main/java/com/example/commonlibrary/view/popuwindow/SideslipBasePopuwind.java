package com.example.commonlibrary.view.popuwindow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.commonlibrary.R;
import com.example.commonlibrary.constant.EnumConstant;
import com.example.commonlibrary.utils.CommonUtils;
import com.example.commonlibrary.utils.DensityUtils;
import com.example.commonlibrary.utils.NotchScreenUtil;

import java.lang.reflect.Field;

public abstract class SideslipBasePopuwind  extends PopupWindow implements View.OnTouchListener {

    View mDecorView;
    View rootView;
    Context context;

    SideslipPopuwindowStateListener listener;


    public SideslipBasePopuwind(Context context) {
        this.context = context;
        rootView = LayoutInflater.from(context).inflate(getResId(), null, false);
        setContentView(rootView);
        setWidth(DensityUtils.dip2px(context, 290));//这里可以修改宽度
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        setBackgroundDrawable(new ColorDrawable());
        // 设置为true之后，PopupWindow内容区域 才可以响应点击事件
        setTouchable(true);
        setOutsideTouchable(false);
        // true时，点击返回键先消失 PopupWindow
        // 但是设置为true时setOutsideTouchable，setTouchable方法就失效了（点击外部不消失，内容区域也不响应事件）
        // false时PopupWindow不处理返回键
        setFocusable(false);
        setClippingEnabled(false);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置监听
        setAnimationStyle(R.style.popwin_anim_style);
        setTouchInterceptor(this);
        initView(rootView);
    }

    protected Context getContext(){
        return context;
    }

    protected abstract  int getResId();

    protected abstract void initView(View rootView);

    public void show(Activity activity) {
        //适配留海屏
        int yOffset = 0 ;
        // 7.0 以下或者高度为WRAP_CONTENT, 默认显示
        if (Build.VERSION.SDK_INT < 24 || getHeight() == ViewGroup.LayoutParams.WRAP_CONTENT) {
            showAtLocation(activity.getWindow().getDecorView(), Gravity.RIGHT|Gravity.TOP, 0, yOffset);
        } else {
            int screenHeight;
            // 获取屏幕真实高度, 减掉虚拟按键的高度
            screenHeight = NotchScreenUtil.getContentHeight(activity);
//            int[] location = new int[2];
//            // 获取控件在屏幕的位置
//            anchor.getLocationOnScreen(location);
//            // 算出popwindow最大高度
//            int maxHeight = screenHeight - location[1] - anchor.getHeight();
//            // popupwindow  有具体的高度值，但是小于anchor下边缘与屏幕底部的距离， 正常显示
//            if(getHeight() > 0 && getHeight() < maxHeight){
//                super.showAsDropDown(anchor, xoff, yoff, gravity);
//            }else {
//                // match_parent 或者 popwinddow的具体高度值大于anchor下边缘与屏幕底部的距离， 都设置为最大可用高度
//                setHeight(maxHeight);
//                showAtLocation(activity.getWindow().getDecorView(), Gravity.RIGHT|Gravity.TOP, 0, yOffset);
//            }
            setHeight(screenHeight);
            showAtLocation(activity.getWindow().getDecorView(), Gravity.RIGHT|Gravity.TOP, 0, yOffset);
        }
    }


    public static final int EVENT_INSIDE=0x001;//初始点在范围外
    public static final int EVENT_OUTSIDE=0x002;//初始点在范围内
    public static final int EVENT_END = 0x003;//结束
    public static final int SLIDE_TYPE_VERTICAL = 0x004;//垂直滑动
    public static final int SLIDE_TYPE_HORIZONTAL = 0x005;//水平滑动



    int eventStatu;
    int slideType;

    float eventStartX;
    float eventStartY;
    float viewStartX;
    float viewstartY;


    float damp = 0.7f;
    float slideTypeXThreshold = 5;//判断滑动方向的阈值
    float slideTypeYThreshold = 5;//判断滑动方向的阈值
    float actionThreshold = 0.25f;//判断当滑动整个弹窗的多少时关闭弹窗
    float distance;
    boolean isAnimation=false;
    int allAnimation = 500;


    //处理触摸事件
    private boolean onTouchEvent(MotionEvent ev){
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(isAnimation){
                    return true;
                }
                //判断按下的点是否在弹窗范围内，如果不是就不处理这个事件了
                float x = ev.getX();
                float y = ev.getY();
                eventStartX = x;
                eventStartY = y;

                //获取弹窗的范围
                float popuStartX = rootView.getX();
                float popuStartY = rootView.getY();
                viewStartX = popuStartX;
                viewstartY = popuStartY;


                float popuEndX = popuStartX+rootView.getWidth();
                float popuEndY = popuStartY+rootView.getHeight();

                if(x>=popuStartX&&x<=popuEndX&&y>=popuStartY&&y<=popuEndY){//按下的点在弹窗返回内需要处理本次触摸事件
                    eventStatu = EVENT_INSIDE;
                }else {
                    eventStatu = EVENT_OUTSIDE;
                }
                slideType = 0;//初始化滑动状态
                distance = 0;
                mDecorView = null;

                break;
            case MotionEvent.ACTION_UP:
                if(isAnimation){
                    return true;
                }
                eventStatu = EVENT_END;
                //判断是否需要关闭弹窗
                if(distance/actionThreshold/damp>=rootView.getWidth()){
                    setDismissAnimation();
                }else {//回弹效果
                    setSpringbackAnimation();
                }


                break;
            case MotionEvent.ACTION_MOVE:
                if(isAnimation){
                    return true;
                }
                if(eventStatu==EVENT_INSIDE){
                    //如果在竖直滑动之后不处理水平滑动了
                    float newX = ev.getX();
                    float relXDistance = newX - eventStartX;//真实位移
                    float newY = ev.getY();
                    float relYDistance = newY - eventStartY;//真实位移

                    if(slideType!=SLIDE_TYPE_HORIZONTAL&&Math.abs(relYDistance)> DensityUtils.dip2px(context,slideTypeYThreshold)){//如果大于阈值
                        slideType = SLIDE_TYPE_VERTICAL;
                    }

                    if(slideType!=SLIDE_TYPE_VERTICAL&&Math.abs(relXDistance)>DensityUtils.dip2px(context,slideTypeXThreshold)){//如果大于阈值
                        slideType = SLIDE_TYPE_HORIZONTAL;
                    }

                    if(slideType==SLIDE_TYPE_VERTICAL){//如果是竖直方向就继续处理了
                        return false;
                    }
                    if(slideType==SLIDE_TYPE_HORIZONTAL){
                        if(distance<0){
                            return true;
                        }

                        distance = relXDistance*damp;//乘以阻尼之后的距离
                        setMDecorView(viewStartX+distance);
                        return true;
                    }


                }
                break;
        }

        return false;
    }

    private void setSpringbackAnimation() {
        //改变view的位置
        if(mDecorView==null){
            mDecorView = getDecorView();
        }

        if(mDecorView==null){
            return;
        }
        float offSet= mDecorView.getX()-viewStartX;
        if(offSet<0){
            return;
        }
        int duration = (int) (allAnimation*(offSet/(mDecorView.getWidth()*actionThreshold)));
        ObjectAnimator animator = ObjectAnimator.ofFloat(mDecorView,"translationX",mDecorView.getX(),viewStartX);
        animator.setDuration(duration);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimation=false;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isAnimation=true;
            }
        });
    }

    private void setDismissAnimation() {
        //改变view的位置
        if(mDecorView==null){
            mDecorView = getDecorView();
        }

        if(mDecorView==null){
            return;
        }

        if (listener!=null) listener.onPopuDissmiss();

        float width = CommonUtils.getScreenWidth(context);
        float offSet= width-mDecorView.getX();
        if(offSet<0){
            return;
        }
        int duration = (int) (300*(offSet/(mDecorView.getWidth()*(1-actionThreshold))));
        ObjectAnimator animator = ObjectAnimator.ofFloat(mDecorView,"translationX",mDecorView.getX(),width);
        animator.setDuration(duration);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimation=false;
                dismiss();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isAnimation=true;

            }
        });
    }

    private View getDecorView() {//获取popudowindow 的根view
        try {
            Field field = PopupWindow.class.getDeclaredField("mDecorView");
            field.setAccessible(true);
            return  (View) field.get(this);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {//适配api 19
            Field field = PopupWindow.class.getDeclaredField("mPopupView");
            field.setAccessible(true);
            return  (View) field.get(this);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setMDecorView(float x) {
        //改变view的位置
        if(mDecorView==null){
            mDecorView = getDecorView();
        }

        if(mDecorView!=null){
            if(x>=viewStartX){//避免移动位置越过popuwindow的初始位置
                mDecorView.setX(x);
            }
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return onTouchEvent(event);//返回false 不消费
    }

    public void setListener(SideslipPopuwindowStateListener listener) {
        this.listener = listener;
    }


    public static interface SideslipPopuwindowStateListener {

        void onPopuDissmiss();

    }
}
