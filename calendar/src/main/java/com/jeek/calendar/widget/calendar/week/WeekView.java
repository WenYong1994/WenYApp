package com.jeek.calendar.widget.calendar.week;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.commonlibrary.data.ScheduleDao;
import com.example.commonlibrary.utils.DensityUtils;
import com.jeek.calendar.library.R;
import com.jeek.calendar.widget.calendar.CalendarUtils;
import com.jeek.calendar.widget.calendar.HintHelper;
import com.jeek.calendar.widget.calendar.LunarCalendarUtils;
import com.jeek.calendar.widget.calendar.base.CalendarBaseView;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Jimmy on 2016/10/7 0007.
 */
public class WeekView extends CalendarBaseView {

    private static final int NUM_COLUMNS = 7;
    private Paint mPaint;
    private Paint mLunarPaint;
    private int mNormalDayColor;
    private int mSelectDayColor;
    private int mSelectBGColor;
    private int mSelectBGTodayColor;
    private int mCurrentDayColor;
    private int mHintCircleColor;
    private int mLunarTextColor;
    private int mHolidayTextColor;
    private int mCurrYear, mCurrMonth, mCurrDay;
    private int mSelYear, mSelMonth, mSelDay;
    private int mColumnSize, mRowSize;
    private float mSelectCircleSize;
    private float mDaySize;
    private float mLunarTextSize;
    private float mSolarLunarSpeaceSize;//农历和国历之间的间隔
    private int mCircleRadius = 6;
    private int[] mHolidays;
    private String mHolidayOrLunarText[];
    private boolean mIsShowLunar;
    private boolean mIsShowHint;
    private boolean mIsShowHolidayHint;
    private DateTime mStartDate;
    private DisplayMetrics mDisplayMetrics;
    private OnWeekClickListener mOnWeekClickListener;
    private GestureDetector mGestureDetector;
    private Bitmap mRestBitmap, mWorkBitmap;
    private int mCurrentCol=-1;//今天是第几列
    private boolean mIsShowWholeWeek,mIsShowWholeMonth;
    private int mWholeMonthTextColor;
    private int mWholeWeekTextColor;


    private String hintTag;

    public WeekView(Context context, DateTime dateTime) {
        this(context, null, dateTime);
    }

    public WeekView(Context context, TypedArray array, DateTime dateTime) {
        this(context, array, null, dateTime);
    }

    public WeekView(Context context, TypedArray array, AttributeSet attrs, DateTime dateTime) {
        this(context, array, attrs, 0, dateTime);
    }

    public WeekView(Context context, TypedArray array, AttributeSet attrs, int defStyleAttr, DateTime dateTime) {
        super(context, attrs, defStyleAttr);
        initAttrs(array, dateTime);
        initPaint();
        initWeek();
        initGestureDetector();
    }

    private void initTaskHint(DateTime date) {
        if (mIsShowHint) {
            // 从数据库中获取圆点提示数据
            ScheduleDao dao = ScheduleDao.getInstance(getContext());
            if (CalendarUtils.getInstance(getContext()).getTaskHints(date.getYear(), date.getMonthOfYear() - 1).size() == 0)
                CalendarUtils.getInstance(getContext()).addTaskHints(date.getYear(), date.getMonthOfYear() - 1, dao.getTaskHintByMonth(mSelYear, mSelMonth));
        }
    }

    private void initAttrs(TypedArray array, DateTime dateTime) {
        if (array != null) {
            mSelectDayColor = array.getColor(R.styleable.WeekCalendarView_week_selected_text_color, Color.parseColor("#FFFFFF"));
            mSelectBGColor = array.getColor(R.styleable.WeekCalendarView_week_selected_circle_color, Color.parseColor("#E8E8E8"));
            mSelectBGTodayColor = array.getColor(R.styleable.WeekCalendarView_week_selected_circle_today_color, Color.parseColor("#FF8594"));
            mNormalDayColor = array.getColor(R.styleable.WeekCalendarView_week_normal_day_text_color, Color.parseColor("#575471"));
            mCurrentDayColor = array.getColor(R.styleable.WeekCalendarView_week_today_text_color, Color.parseColor("#FF8594"));
            mHintCircleColor = array.getColor(R.styleable.WeekCalendarView_week_hint_circle_color, Color.parseColor("#FE8595"));
            mLunarTextColor = array.getColor(R.styleable.WeekCalendarView_week_lunar_normal_text_color, Color.parseColor("#ACA9BC"));
            mHolidayTextColor = array.getColor(R.styleable.WeekCalendarView_week_holiday_color, Color.parseColor("#A68BFF"));
            mDaySize = array.getDimension(R.styleable.WeekCalendarView_week_day_text_size, DensityUtils.dipToSp(getContext(),13));
            mLunarTextSize = array.getDimension(R.styleable.WeekCalendarView_week_day_lunar_text_size, DensityUtils.dipToSp(getContext(),8));
            mSolarLunarSpeaceSize = array.getDimension(R.styleable.WeekCalendarView_week_lunar_solar_text_space_size,DensityUtils.dipToSp(getContext(),1));
            mIsShowHint = array.getBoolean(R.styleable.WeekCalendarView_week_show_task_hint, true);
            mIsShowLunar = array.getBoolean(R.styleable.WeekCalendarView_week_show_lunar, true);
            mIsShowHolidayHint = array.getBoolean(R.styleable.WeekCalendarView_week_show_holiday_hint, true);
            mIsShowWholeMonth = array.getBoolean(R.styleable.WeekCalendarView_week_show_whole_month,false);
            mIsShowWholeWeek = array.getBoolean(R.styleable.WeekCalendarView_week_show_whole_week,false);

            mWholeMonthTextColor = array.getColor(R.styleable.WeekCalendarView_week_whole_month_text_color,Color.parseColor("#C2C2C3"));
            mWholeWeekTextColor = array.getColor(R.styleable.WeekCalendarView_week_whole_week_text_color,Color.parseColor("#C2C2C3"));

        } else {
            mSelectDayColor = Color.parseColor("#FFFFFF");
            mSelectBGColor = Color.parseColor("#E8E8E8");
            mSelectBGTodayColor = Color.parseColor("#FF8594");
            mNormalDayColor = Color.parseColor("#575471");
            mCurrentDayColor = Color.parseColor("#FF8594");
            mHintCircleColor = Color.parseColor("#FE8595");
            mLunarTextColor = Color.parseColor("#ACA9BC");
            mHolidayTextColor = Color.parseColor("#A68BFF");
            mDaySize =  DensityUtils.dipToSp(getContext(),13);
            mDaySize =  DensityUtils.dipToSp(getContext(),8);
            mSolarLunarSpeaceSize =  DensityUtils.dipToSp(getContext(),2);
            mIsShowHint = true;
            mIsShowLunar = true;
            mIsShowHolidayHint = true;

            mIsShowWholeMonth = false;
            mIsShowWholeWeek = false;
            mWholeMonthTextColor = Color.parseColor("#C2C2C3");
            mWholeWeekTextColor = Color.parseColor("#C2C2C3");
        }
        mStartDate = dateTime;
        mRestBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_rest_day);
        mWorkBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_work_day);
        int holidays[] = CalendarUtils.getInstance(getContext()).getHolidays(mStartDate.getYear(), mStartDate.getMonthOfYear());
        int row = CalendarUtils.getWeekRow(mStartDate.getYear(), mStartDate.getMonthOfYear() - 1, mStartDate.getDayOfMonth());
        mHolidays = new int[7];
        System.arraycopy(holidays, row * 7, mHolidays, 0, mHolidays.length);
    }

    private void initPaint() {
        mDisplayMetrics = getResources().getDisplayMetrics();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mDaySize);

        mLunarPaint = new Paint();
        mLunarPaint.setAntiAlias(true);
        mLunarPaint.setTextSize(mLunarTextSize);
        mLunarPaint.setColor(mLunarTextColor);
    }

    private void initWeek() {
        Calendar calendar = Calendar.getInstance();
        mCurrYear = calendar.get(Calendar.YEAR);
        mCurrMonth = calendar.get(Calendar.MONTH);
        mCurrDay = calendar.get(Calendar.DATE);
        DateTime endDate = mStartDate.plusDays(7);
        if (mStartDate.getMillis() <= System.currentTimeMillis() && endDate.getMillis() > System.currentTimeMillis()) {
            if (mStartDate.getMonthOfYear() != endDate.getMonthOfYear()) {
                if (mCurrDay < mStartDate.getDayOfMonth()) {
                    setSelectYearMonth(mStartDate.getYear(), endDate.getMonthOfYear() - 1, mCurrDay);
                } else {
                    setSelectYearMonth(mStartDate.getYear(), mStartDate.getMonthOfYear() - 1, mCurrDay);
                }
            } else {
                setSelectYearMonth(mStartDate.getYear(), mStartDate.getMonthOfYear() - 1, mCurrDay);
            }
        } else {
            setSelectYearMonth(mStartDate.getYear(), mStartDate.getMonthOfYear() - 1, mStartDate.getDayOfMonth());
        }
        initTaskHint(mStartDate);
        initTaskHint(endDate);
    }

    private void initGestureDetector() {
        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                doClickAction((int) e.getX(), (int) e.getY());
                return true;
            }
        });
    }

    public void setSelectYearMonth(int year, int month, int day) {
        mSelYear = year;
        mSelMonth = month;
        mSelDay = day;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = mDisplayMetrics.densityDpi * 200;
        }
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = mDisplayMetrics.densityDpi * 300;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initSize();
        clearData();
        int selected = drawThisWeek(canvas);
        drawLunarText(canvas, selected);
        drawHintCircle(canvas);
        drawHoliday(canvas);
    }

    private void clearData() {
        mHolidayOrLunarText = new String[7];
    }

    private void initSize() {
        mColumnSize = getWidth() / NUM_COLUMNS;
        mRowSize = getHeight();

        mSelectCircleSize =  (mColumnSize / 2f)*0.9f;
        while (mSelectCircleSize > mRowSize / 2) {
            mSelectCircleSize = (int) (mSelectCircleSize / 1.2);
        }
    }

    private int drawThisWeek(Canvas canvas) {
        int selected = 0;
        for (int i = 0; i < 7; i++) {
            DateTime date = mStartDate.plusDays(i);
            int day = date.getDayOfMonth();
            String dayString = String.valueOf(day);
            if(date.getYear() == mCurrYear && date.getMonthOfYear() - 1 == mCurrMonth && day == mCurrDay){
                dayString = "今";
            }

            int startX = (int) (mColumnSize * i + (mColumnSize - mPaint.measureText(dayString)) / 2);
            float  baseLineY = getSolarTextBaseLine(0);
//            int startY = (int) (mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);

            if (day == mSelDay) {
                int startRecX = mColumnSize * i;
                int endRecX = startRecX + mColumnSize;
                if (date.getYear() == mCurrYear && date.getMonthOfYear() - 1 == mCurrMonth && day == mCurrDay) {
                    mPaint.setColor(mSelectBGTodayColor);
                } else {
                    mPaint.setColor(mSelectBGColor);
                }
                canvas.drawCircle((startRecX + endRecX) / 2f, mRowSize / 2f, mSelectCircleSize, mPaint);
            }
            if (day == mSelDay) {
                selected = i;
                mPaint.setColor(mSelectDayColor);
                //如果是选择今天，就显示 今

            } else if (date.getYear() == mCurrYear && date.getMonthOfYear() - 1 == mCurrMonth && day == mCurrDay && day != mSelDay && mCurrYear == mSelYear) {
                mPaint.setColor(mCurrentDayColor);
                mCurrentCol=i;
            } else {
                mPaint.setColor(mNormalDayColor);
            }


            canvas.drawText(dayString, startX, baseLineY, mPaint);
            mHolidayOrLunarText[i] = CalendarUtils.getHolidayFromSolar(date.getYear(), date.getMonthOfYear() - 1, day);
        }
        return selected;
    }

    /**
     * 绘制农历
     *
     * @param canvas
     * @param selected
     */
    private void drawLunarText(Canvas canvas, int selected) {
        if (mIsShowLunar) {
            LunarCalendarUtils.Solar solar = new LunarCalendarUtils.Solar(mStartDate.getYear(), mStartDate.getMonthOfYear(), mStartDate.getDayOfMonth());
            LunarCalendarUtils.Lunar lunar = LunarCalendarUtils.solarToLunar(solar);

            int leapMonth = LunarCalendarUtils.leapMonth(lunar.lunarYear);
            int lunarDayCount = LunarCalendarUtils.daysInMonth(lunar.lunarYear, lunar.lunarMonth, lunar.isLeap);

            int solarDayCount = CalendarUtils.getMonthDays(solar.solarYear,solar.solarMonth-1);

            for (int i = 0; i < 7; i++) {
                if(solar.solarDay>solarDayCount){
                    solar.solarDay = 1;
                    if (solar.solarMonth == 12) {
                        solar.solarMonth = 1;
                        solar.solarYear++;//年加一
                    }else {
                        solar.solarMonth++;
                    }
                    solarDayCount =CalendarUtils.getMonthDays(solar.solarYear,solar.solarMonth);
                }


                if (lunar.lunarDay > lunarDayCount) {//判断农历当前是否属属于下一月
                    lunar.lunarDay = 1;
                    boolean isAdd = true;
                    if (lunar.lunarMonth == 12) {
                        lunar.lunarMonth = 1;
                        lunar.lunarYear = lunar.lunarYear + 1;
                        isAdd = false;
                    }
                    if (lunar.lunarMonth == leapMonth) {
                        lunarDayCount = LunarCalendarUtils.daysInMonth(lunar.lunarYear, lunar.lunarMonth, lunar.isLeap);
                    } else {
                        if (isAdd) {
                            lunar.lunarMonth++;
                            lunarDayCount = LunarCalendarUtils.daysInLunarMonth(lunar.lunarYear, lunar.lunarMonth);
                        }
                    }
                }




                mLunarPaint.setColor(mHolidayTextColor);
                String dayString = mHolidayOrLunarText[i];
                if ("".equals(dayString)) {
                    dayString = LunarCalendarUtils.getLunarHoliday(lunar.lunarYear, lunar.lunarMonth, lunar.lunarDay);
                }

                //显示整周
                if(mIsShowWholeWeek&&"".equals(dayString)){
                    if(i==0){//
                        Calendar cal=Calendar.getInstance();
//                        LogUtils.e("testWen",solar.solarYear+","+solar.solarMonth+","+solar.solarDay);
                        cal.set(solar.solarYear,solar.solarMonth-1,solar.solarDay);

                        int weeksOfYear = cal.get(Calendar.WEEK_OF_YEAR);

                        mLunarPaint.setColor(mWholeWeekTextColor);
                        dayString = weeksOfYear + "周";
                    }
                }



                if ("".equals(dayString)) {
                    dayString = LunarCalendarUtils.getLunarDayString(lunar.lunarDay);
                    mLunarPaint.setColor(mLunarTextColor);
                }
                if(mIsShowWholeMonth){
                    if ("初一".equals(dayString)) {
                        dayString = LunarCalendarUtils.getLunarFirstDayString(lunar.lunarMonth, lunar.isLeap);
                        mLunarPaint.setColor(mWholeMonthTextColor);
                    }
                }
                DateTime date = mStartDate.plusDays(i);
                if (i == selected) {
                    mLunarPaint.setColor(mSelectDayColor);
                }else if (date.getYear() == mCurrYear && date.getMonthOfYear() - 1 == mCurrMonth && i == mCurrentCol && lunar.lunarDay != mSelDay && mCurrYear == mSelYear) {
                    mLunarPaint.setColor(mCurrentDayColor);
                }
                int startX = (int) (mColumnSize * i + (mColumnSize - mLunarPaint.measureText(dayString)) / 2);
                float lunarTextBaseLine = getLunarTextBaseLine(0);
                canvas.drawText(dayString, startX, lunarTextBaseLine, mLunarPaint);
                lunar.lunarDay++;
                solar.solarDay++;
            }
        }
    }

    private void drawHoliday(Canvas canvas) {
        if (mIsShowHolidayHint) {
            Rect rect = new Rect(0, 0, mRestBitmap.getWidth(), mRestBitmap.getHeight());
            RectF rectF = new RectF();
            float distance = (float) (((2-Math.sqrt(2))/2)*mSelectCircleSize);
            for (int i = 0; i < mHolidays.length; i++) {
                int column = i % 7;
                if(column==0|column==6){
                    mHolidays[i] =1;
                }else {
                    mHolidays[i] =2;
                }


                rectF.set(mColumnSize * (column + 1) - mRestBitmap.getWidth() - distance, distance, mColumnSize * (column + 1) - distance, mRestBitmap.getHeight() + distance);
                if (mHolidays[i] == 1) {
                    canvas.drawBitmap(mRestBitmap, rect, rectF, null);
                } else if (mHolidays[i] == 2) {
                    canvas.drawBitmap(mWorkBitmap, rect, rectF, null);
                }
            }
        }
    }






    /**返回国历的text的baseLine
     *
     * @param row
     * @return
     */
    private float getSolarTextBaseLine(int row) {
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLineY;
        if(mIsShowLunar){
            float solarTextHeight = -fontMetrics.ascent+fontMetrics.descent;
            Paint.FontMetrics lunarFontMetrics = mLunarPaint.getFontMetrics();
            float luanrTextHeight = -lunarFontMetrics.ascent+lunarFontMetrics.descent;
            float allTextViewHeight = solarTextHeight+mSolarLunarSpeaceSize+luanrTextHeight;//国历农历两个text的总高度
            float centerY = mRowSize * row + mRowSize / 2f;
            baseLineY = centerY-allTextViewHeight/2f+solarTextHeight/2f - fontMetrics.top/2 - fontMetrics.bottom/2;
        }else {
            baseLineY=  (mRowSize * row + mRowSize / 2f  - fontMetrics.top/2 - fontMetrics.bottom/2);
        }

        return baseLineY;
    }

    /**返回农历的text的baseLine
     *
     * @param row
     * @return
     */
    private float getLunarTextBaseLine(int row){
        float baseLineY;
        if(mIsShowLunar){
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            float solarTextHeight = -fontMetrics.ascent+fontMetrics.descent;
            Paint.FontMetrics lunarFontMetrics = mLunarPaint.getFontMetrics();
            float luanrTextHeight = -lunarFontMetrics.ascent+lunarFontMetrics.descent;
            float allTextViewHeight = solarTextHeight+mSolarLunarSpeaceSize+luanrTextHeight;//国历农历两个text的总高度
            float centerY = mRowSize * row + mRowSize / 2f;
            baseLineY = centerY+allTextViewHeight/2f-luanrTextHeight/2f - lunarFontMetrics.top/2 - lunarFontMetrics.bottom/2;
        }else {
            baseLineY= 0;
        }
        return baseLineY;
    }




    /**
     * 绘制圆点提示
     *
     * @param canvas
     */
    private void drawHintCircle(Canvas canvas) {
        if (mIsShowHint) {
            mPaint.setColor(mHintCircleColor);
            int startMonth = mStartDate.getMonthOfYear();
            int endMonth = mStartDate.plusDays(7).getMonthOfYear();
            int startDay = mStartDate.getDayOfMonth();
            if (startMonth == endMonth) {

//                List<Integer> hints = CalendarUtils.getInstance(getContext()).getTaskHints(mStartDate.getYear(), mStartDate.getMonthOfYear() - 1);
                List<Integer> hints = HintHelper.getHintHelper(hintTag).getHintList(mStartDate.getYear(), mStartDate.getMonthOfYear() - 1);
                for (int i = 0; i < 7; i++) {
                    drawHintCircle(hints, startDay + i, i, canvas);
                }
            } else {
                for (int i = 0; i < 7; i++) {
//                    List<Integer> hints = CalendarUtils.getInstance(getContext()).getTaskHints(mStartDate.getYear(), mStartDate.getMonthOfYear() - 1);
                    List<Integer> hints = HintHelper.getHintHelper(hintTag).getHintList(mStartDate.getYear(), mStartDate.getMonthOfYear() - 1);
//                    List<Integer> nextHints = CalendarUtils.getInstance(getContext()).getTaskHints(mStartDate.getYear(), mStartDate.getMonthOfYear());
                    List<Integer> nextHints = HintHelper.getHintHelper(hintTag).getHintList(mStartDate.getYear(), mStartDate.getMonthOfYear());
                    DateTime date = mStartDate.plusDays(i);
                    int month = date.getMonthOfYear();
                    if (month == startMonth) {
                        drawHintCircle(hints, date.getDayOfMonth(), i, canvas);
                    } else {
                        drawHintCircle(nextHints, date.getDayOfMonth(), i, canvas);
                    }
                }
            }
        }
    }


    private void drawHintCircle(List<Integer> hints, int day, int col, Canvas canvas) {
        if (!hints.contains(day)) return;
        float circleX = (float) (mColumnSize * col + mColumnSize * 0.5);
        float circleY = (float) (mRowSize * 0.80);
        canvas.drawCircle(circleX, circleY, mCircleRadius, mPaint);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private void doClickAction(int x, int y) {
        if (y > getHeight())
            return;
        int column = x / mColumnSize;
        column = Math.min(column, 6);
        DateTime date = mStartDate.plusDays(column);
        clickThisWeek(date.getYear(), date.getMonthOfYear() - 1, date.getDayOfMonth());
    }

    public void clickThisWeek(int year, int month, int day) {
        if (mOnWeekClickListener != null) {
            mOnWeekClickListener.onClickDate(year, month, day);
        }
        setSelectYearMonth(year, month, day);
        invalidate();
    }

    public void setOnWeekClickListener(OnWeekClickListener onWeekClickListener) {
        mOnWeekClickListener = onWeekClickListener;
    }

    public DateTime getStartDate() {
        return mStartDate;
    }

    public DateTime getEndDate() {
        return mStartDate.plusDays(6);
    }


    public void setmSelectBGColor(int color) {
        this.mSelectBGColor = color;
        invalidate();
    }

    public void setmSelectBGTodayColor(int color) {
        this.mSelectBGTodayColor = color;
        invalidate();
    }

    /**
     * 获取当前选择年
     *
     * @return
     */
    public int getSelectYear() {
        return mSelYear;
    }

    /**
     * 获取当前选择月
     *
     * @return
     */
    public int getSelectMonth() {
        return mSelMonth;
    }


    /**
     * 获取当前选择日
     *
     * @return
     */
    public int getSelectDay() {
        return this.mSelDay;
    }


    /**
     * 添加一个圆点提示
     *
     * @param day
     */
    public void addTaskHint(Integer day) {
        if (mIsShowHint) {
            HintHelper.getHintHelper(hintTag).addHint(mSelYear, mSelMonth, day);
            invalidate();
        }
    }

    /**
     * 删除一个圆点提示
     *
     * @param day
     */
    public void removeTaskHint(Integer day) {
        if (mIsShowHint) {
            HintHelper.getHintHelper(hintTag).removeHint(mSelYear, mSelMonth, day);
            invalidate();
        }
    }

    public void setHintTag(String hintTag) {
        this.hintTag = hintTag;
        invalidate();
    }

}
