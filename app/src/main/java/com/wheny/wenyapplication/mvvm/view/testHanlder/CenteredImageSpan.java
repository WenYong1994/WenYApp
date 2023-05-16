package com.wheny.wenyapplication.mvvm.view.testHanlder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Created by jevinfang on 2016/11/24.
 */
public class CenteredImageSpan extends ImageSpan {
    private WeakReference<Drawable> mDrawableRef;
    private int mLeftPadding;
    private int mRightPadding;

    public CenteredImageSpan(Drawable drawable) {
        super(drawable);
    }

    public CenteredImageSpan(Drawable drawable, int rightPadding) {
        super(drawable);
        mRightPadding = rightPadding;
    }

    public CenteredImageSpan(Context context, final int drawableRes) {
        super(context, drawableRes);
    }

    public CenteredImageSpan(Context context, final int drawableRes, int leftPadding, int rightPadding) {
        super(context, drawableRes);
        mLeftPadding = leftPadding;
        mRightPadding = rightPadding;
    }

    @Override
    public int getSize(Paint paint, CharSequence text,
                       int start, int end,
                       Paint.FontMetricsInt fm) {
        Drawable d = getCachedDrawable();
        if (d == null) {
            return 0;
        }
        Rect rect = d.getBounds();

        if (fm != null) {
            Paint.FontMetricsInt pfm = paint.getFontMetricsInt();
            // keep it the same as paint's fm
            fm.ascent = pfm.ascent;
            fm.descent = pfm.descent;
            fm.top = pfm.top;
            fm.bottom = pfm.bottom;
        }

        return rect.right + mLeftPadding + mRightPadding;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text,
                     int start, int end, float x,
                     int top, int y, int bottom, @NonNull Paint paint) {
        Drawable b = getCachedDrawable();
        if (b == null) {
            return;
        }
        canvas.save();

        int drawableHeight = b.getBounds().height();
        int fontAscent = paint.getFontMetricsInt().ascent;
        int fontDescent = paint.getFontMetricsInt().descent;
        int transY = bottom - b.getBounds().bottom +  // align bottom to bottom
                (drawableHeight - fontDescent + fontAscent) / 2;  // align center to center

        canvas.translate(x + mLeftPadding, transY);
        b.draw(canvas);
        canvas.restore();
    }

    // Redefined locally because it is a private member from DynamicDrawableSpan
    private Drawable getCachedDrawable() {
        WeakReference<Drawable> wr = mDrawableRef;
        Drawable d = null;

        if (wr != null)
            d = wr.get();

        if (d == null) {
            d = getDrawable();
            mDrawableRef = new WeakReference<>(d);
        }

        return d;
    }
}
