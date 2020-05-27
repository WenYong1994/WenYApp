package com.example.commonlibrary.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.Spanned;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.example.commonlibrary.utils.ListAdapterUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * author：Vinsmoke
 * e-mail：vinsmokeleigh@gmail.com
 *
 * WhenY
 *
 */
public class ViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private T viewDataBinding;
    private int fade_duration = 500;
    private float sizeMultiplier = 0.3f;

    public ViewHolder(View itemView, boolean isAutoView) {
        super(itemView);
//        if (isAutoView) AutoSizeCompat.autoConvertDensityOfGlobal(itemView.getResources());
        mViews = new SparseArray<View>();
    }

    public ViewHolder(View itemView, boolean isAutoView,T viewDataBinding) {
        super(itemView);
//        if (isAutoView) AutoSizeCompat.autoConvertDensityOfGlobal(itemView.getResources());
        mViews = new SparseArray<View>();
        this.viewDataBinding = viewDataBinding;
    }


    public T getViewDataBinding() {
        return viewDataBinding;
    }


    public static ViewHolder create(View itemView, boolean isAutoView) {
        return new ViewHolder(itemView, isAutoView);
    }

    public static <T extends ViewDataBinding> ViewHolder<T> create(ViewGroup parent, @LayoutRes int layoutId, boolean isAutoView) {
        //这里兼容使用DataBinding
        T t = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId,parent, false);
        return new ViewHolder<T>(t.getRoot(),isAutoView,t);
    }

    private List<OnViewRecycledListener> mListeners;


    public interface OnViewRecycledListener {
        void onViewRecycled(ViewHolder holder);
    }

    protected void onViewRecycled(ViewHolder holder) {
        if (!ListAdapterUtils.isEmpty(mListeners)) {
            int size = mListeners.size();
            for (int i = size - 1; i >= 0; i--) {
                mListeners.get(i).onViewRecycled(holder);
            }
        }
    }

    public void addOnViewRecycledListener(@NonNull OnViewRecycledListener onViewRecycled) {
        if (onViewRecycled == null) {
            return;
        }
        if (mListeners == null) {
            mListeners = new ArrayList<>();
        }
        mListeners.add(onViewRecycled);
    }

    public void removeOnViewRecycledListener(@NonNull OnViewRecycledListener onViewRecycled) {
        if (onViewRecycled == null) {
            return;
        }
        if (mListeners == null) {
            // This can happen if this method is called before the first call to addOnViewRecycled
            return;
        }
        mListeners.remove(onViewRecycled);
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getContentView() {
        return itemView;
    }

    public ViewHolder setText(@IdRes int viewId, String text) {
        ((TextView) getView(viewId)).setText(text);
        return this;
    }

    public ViewHolder setText(@IdRes int viewId, Spanned text) {
        ((TextView) getView(viewId)).setText(text);
        return this;
    }

    public ViewHolder setTextColor(@IdRes int viewId, @ColorInt int color) {
        ((TextView) getView(viewId)).setTextColor(color);
        return this;
    }

    public ViewHolder setTextSize(@IdRes int viewId, float size) {
        ((TextView) getView(viewId)).setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        return this;
    }

    public ViewHolder setTextSize(@IdRes int viewId, int unit, float size) {
        ((TextView) getView(viewId)).setTextSize(unit, size);
        return this;
    }

    public ViewHolder setChecked(@IdRes int viewId, boolean checked) {
        ((CheckBox) getView(viewId)).setChecked(checked);
        return this;
    }

    public ViewHolder setButtonDrawable(@IdRes int viewId, @DrawableRes int resId) {
        final Drawable d;
        if (resId != 0) {
            d = getView(viewId).getContext().getResources().getDrawable(resId);
        } else {
            d = null;
        }
        return setButtonDrawable(viewId, d);
    }

    public ViewHolder setButtonDrawable(@IdRes int viewId, @Nullable Drawable drawable) {
        ((CheckBox) getView(viewId)).setButtonDrawable(drawable);
        return this;
    }

    public ViewHolder setImageRes(@IdRes int viewId, @DrawableRes int resId) {
        ((ImageView) getView(viewId)).setImageResource(resId);
        return this;
    }


    public ViewHolder setImageRes(Activity activity, @IdRes int viewId, @DrawableRes int resId) {
        Glide
                .with(activity)
                .asDrawable()
                .load(resId)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageRes(Fragment fragment, @IdRes int viewId, @DrawableRes int resId) {
        Glide
                .with(fragment)
                .asDrawable()
                .load(resId)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageDrawable(Activity activity, @IdRes int viewId, Drawable drawable) {
        Glide
                .with(activity)
                .asDrawable()
                .load(drawable)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageDrawable(Fragment fragment, @IdRes int viewId, Drawable drawable) {
        Glide
                .with(fragment)
                .asDrawable()
                .load(drawable)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByFile(Activity activity, @IdRes int viewId, File file) {
        Glide
                .with(activity)
                .asDrawable()
                .load(file)
                .centerCrop()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByFile(Fragment fragment, @IdRes int viewId, File file) {
        Glide
                .with(fragment)
                .asDrawable()
                .load(file)
                .centerCrop()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByUri(Activity activity, @IdRes int viewId, Uri portraitUri, @DrawableRes int resId) {
        Glide
                .with(activity)
                .asDrawable()
                .load(portraitUri)
                .centerCrop()
                .placeholder(resId)
                .error(resId)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }


    public ViewHolder setImageByUri(Fragment fragment, @IdRes int viewId, Uri portraitUri, @DrawableRes int resId) {
        Glide
                .with(fragment)
                .asDrawable()
                .load(portraitUri)
                .centerCrop()
                .placeholder(resId)
                .error(resId)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByUri_circleCrop(Activity activity, @IdRes int viewId, Uri portraitUri, @DrawableRes int resId) {
        Glide
                .with(activity)
                .asDrawable()
                .load(portraitUri)
                .circleCrop()
                .placeholder(resId)
                .error(resId)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByUri_circleCrop(Fragment fragment, @IdRes int viewId, Uri portraitUri, @DrawableRes int resId) {
        Glide
                .with(fragment)
                .asDrawable()
                .load(portraitUri)
                .circleCrop()
                .placeholder(resId)
                .error(resId)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByUrl(Activity activity, @IdRes int viewId, String url) {
        Glide
                .with(activity)
                .asDrawable()
                .load(url)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByUrl(Fragment fragment, @IdRes int viewId, String url) {
        Glide
                .with(fragment)
                .asDrawable()
                .load(url)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }




    public ViewHolder setImageByUrl_circleCrop(Fragment fragment, @IdRes int viewId, String url, @DrawableRes int defaultResID) {
        Glide
                .with(fragment)
                .asDrawable()
                .placeholder(defaultResID)
                .error(defaultResID)
                .load(url)
                .circleCrop()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByUrl_circleCrop(Activity activity, @IdRes int viewId, String url, @DrawableRes int defaultResID) {
        Glide
                .with(activity)
                .asDrawable()
                .placeholder(defaultResID)
                .error(defaultResID)
                .load(url)
                .circleCrop()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByUrl(Activity activity, @IdRes int viewId, String url, @DrawableRes int defaultResID, Transformation<Bitmap>... transformations) {
        Glide.with(activity)
                .asDrawable()
                .load(url)
                .placeholder(defaultResID)
                .error(defaultResID)
                .transform(transformations)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }
    public ViewHolder setImageByUrl(Context context, @IdRes int viewId, String url, @DrawableRes int defaultResID, Transformation<Bitmap>... transformations) {
        Glide.with(context)
                .asDrawable()
                .load(url)
                .placeholder(defaultResID)
                .error(defaultResID)
                .transform(transformations)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByUrl(Fragment fragment, @IdRes int viewId, String url, @DrawableRes int defaultResID, Transformation<Bitmap>... transformations) {
        Glide.with(fragment)
                .asDrawable()
                .load(url)
                .placeholder(defaultResID)
                .error(defaultResID)
                .transform(transformations)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByUrl(Activity activity, @IdRes int viewId, String url, Transformation<Bitmap>... transformations) {
        Glide.with(activity)
                .asDrawable()
                .load(url)
                .transform(transformations)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setImageByUrl(Fragment fragment, @IdRes int viewId, String url, Transformation<Bitmap>... transformations) {
        Glide.with(fragment)
                .asDrawable()
                .load(url)
                .transform(transformations)
                .dontAnimate()
                .into((ImageView) getView(viewId));
        return this;
    }

    public interface JudgeCallback {
        void callback(ViewHolder holder, boolean aBoolean);
    }

    public ViewHolder judge(boolean condiction, JudgeCallback judgeCallback) {
        if (judgeCallback != null) {
            judgeCallback.callback(this, condiction);
        }
        return this;
    }

    public interface ConditionCallBack {
        void callback(ViewHolder holder);
    }

    public ViewHolder condition(boolean condiction, ConditionCallBack callBack) {
        if (callBack != null && condiction) callBack.callback(this);
        return this;
    }

    public ViewHolder setVisible(@IdRes int viewId, int visible) {
        View view = getView(viewId);
        if(view!=null){
            view.setVisibility(visible);
        }
        return this;
    }

    public ViewHolder ifChildView(@IdRes int viewGroupId, JudgeCallback judgeCallback) {
        if (judgeCallback != null) {
            judgeCallback.callback(this, ((ViewGroup) getView(viewGroupId)).getChildCount() > 0);
        }
        return this;
    }

    public ViewHolder addView(@IdRes int viewGroupId, View childView) {
        ((ViewGroup) getView(viewGroupId)).addView(childView);
        return this;
    }

    public ViewHolder addView(@IdRes int viewGroupId, View childView, int index) {
        ((ViewGroup) getView(viewGroupId)).addView(childView, index);
        return this;
    }

    public ViewHolder addView(@IdRes int viewGroupId, View child, int width, int height) {
        ((ViewGroup) getView(viewGroupId)).addView(child, width, height);
        return this;
    }

    public ViewHolder addView(@IdRes int viewGroupId, View child, ViewGroup.LayoutParams params) {
        ((ViewGroup) getView(viewGroupId)).addView(child, params);
        return this;
    }

    public ViewHolder removeViewAt(@IdRes int viewGroupId, int index) {
        ((ViewGroup) getView(viewGroupId)).removeViewAt(index);
        return this;
    }

    public ViewHolder removeAllViews(@IdRes int viewGroupId) {
        ((ViewGroup) getView(viewGroupId)).removeAllViews();
        return this;
    }

    public ViewHolder removeView(@IdRes int viewGroupId, View view) {
        ((ViewGroup) getView(viewGroupId)).removeView(view);
        return this;
    }

    public ViewHolder setTag(@IdRes int viewId, Object tag) {
        getView(viewId).setTag(tag);
        return this;
    }

    public ViewHolder setTag(@IdRes int viewId, int key, Object tag) {
        getView(viewId).setTag(key, tag);
        return this;
    }

    public Object getTag(@IdRes int viewId) {
        return getView(viewId).getTag();
    }

    public Object getTag(@IdRes int viewId, int key) {
        return getView(viewId).getTag(key);
    }

    public ViewHolder setGradientDrawableColor(@IdRes int viewId, @ColorInt int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) getView(viewId).getBackground();
        try {
            gradientDrawable.setColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public ViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int resId) {
        return setBackgroundResource(getView(viewId), resId);
    }

    public ViewHolder setBackgroundResource(View view, @DrawableRes int resId) {
        view.setBackgroundResource(resId);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ViewHolder setBackground(@IdRes int viewId, Drawable background) {
        getView(viewId).setBackground(background);
        return this;
    }

    public ViewHolder setBackground(View view, Drawable background) {
        view.setBackground(background);
        return this;
    }

    public ViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        getView(viewId).setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroundColor(View view, @ColorInt int color) {
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setDrawingCacheBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        getView(viewId).setDrawingCacheBackgroundColor(color);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewHolder setBackgroundTintList(@IdRes int viewId, @Nullable ColorStateList tint) {
        getView(viewId).setBackgroundTintList(tint);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewHolder setBackgroundTintMode(@IdRes int viewId, @Nullable PorterDuff.Mode tintMode) {
        getView(viewId).setBackgroundTintMode(tintMode);
        return this;
    }

    public ViewHolder setAlpha(@IdRes int viewId, float alpha) {
        getView(viewId).setAlpha(alpha);
        return this;
    }

    public ViewHolder setEnable(@IdRes int viewId, boolean enable) {
        getView(viewId).setEnabled(enable);
        return this;
    }

    public ViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener onClickListener) {
        setOnClickListener(getView(viewId), onClickListener);
        return this;
    }

    public ViewHolder setOnClickListener(View view, View.OnClickListener onClickListener) {
        if(view!=null){
            view.setOnClickListener(onClickListener);
        }
        return this;
    }

    public ViewHolder setonTouchListener(@IdRes int viewId, View.OnTouchListener onTouchListener) {
        setonTouchListener(getView(viewId), onTouchListener);
        return this;
    }

    public ViewHolder setonTouchListener(View view, View.OnTouchListener onTouchListener) {
        view.setOnTouchListener(onTouchListener);
        return this;
    }

    public ViewHolder setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener onLongClickListener) {
        setOnLongClickListener(getView(viewId), onLongClickListener);
        return this;
    }

    public ViewHolder setOnLongClickListener(View view, View.OnLongClickListener onLongClickListener) {
        if(view != null){
            view.setOnLongClickListener(onLongClickListener);
        }
        return this;
    }
}
