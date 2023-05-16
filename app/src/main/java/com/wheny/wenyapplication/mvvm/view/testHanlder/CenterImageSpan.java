package com.wheny.wenyapplication.mvvm.view.testHanlder;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;



/**
 * Created by lionljwang on 2016/6/14.
 */
public class CenterImageSpan extends ImageSpan {
    private Context mContext;

    public CenterImageSpan(Drawable drawable) {
        super(drawable);
    }

    public CenterImageSpan(Drawable drawable, Context context) {
        super(drawable);
        mContext = context;
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
    }

    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return super.getSize(paint,text,start,end,fm);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end,
                     float x, int top, int y, int bottom, Paint paint) {
        try {
            Drawable b = getDrawable();
            if (b instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) b).getBitmap();
                if (bitmap == null || bitmap.isRecycled()) return;
            }
            if (b != null) {
                canvas.save();
                int transY = 0;
                int drawableHeight = b.getBounds().height();
                int txtHeight =  paint.getFontMetricsInt().bottom -paint.getFontMetricsInt().top;
                transY = (int) (bottom-(txtHeight/2f)-drawableHeight/2f) ;
                canvas.translate(x, transY);
                b.draw(canvas);
                canvas.restore();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


}
