package com.jeek.calendar.widget.calendar.month;

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
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.commonlibrary.data.ScheduleDao;
import com.example.commonlibrary.utils.DensityUtils;
import com.jeek.calendar.library.R;
import com.jeek.calendar.widget.calendar.HintHelper;
import com.jeek.calendar.widget.calendar.base.CalendarBaseView;
import com.jeek.calendar.widget.calendar.CalendarUtils;
import com.jeek.calendar.widget.calendar.LunarCalendarUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jimmy on 2016/10/6 0006.
 */
public class MonthView extends CalendarBaseView {

    private static final int NUM_COLUMNS = 7;
    private static final int NUM_ROWS = 6;
    private Paint mPaint;
    private Paint mRingPaint;
    private Paint mLunarPaint;
    private int mNormalDayColor;
    private int mSelectDayColor;
    private int mSelectBGColor;
    private int mDirectBGColor;
    private int mDirectBorderColor;
    private int mSelectBGTodayColor;
    private int mCurrentDayColor;
    private int mHintCircleColor;
    private int mLunarTextColor;
    private int mHolidayTextColor;
    private int mLastOrNextMonthTextColor;
    private int mCurrYear, mCurrMonth, mCurrDay;
    private int mSelYear, mSelMonth, mSelDay;
    private int mDirectYear, mDirectMonth, mDirectDay;
    private int mColumnSize, mRowSize;
    private float mSelectCircleSize;
    private float mDaySize;
    private float mLunarTextSize;
    private float mSolarLunarSpeaceSize;//农历和国历之间的间隔
    private int mWeekRow; // 当前月份第几周
    private int mCircleRadius = 6;
    private int[][] mDaysText;
    private int[] mHolidays;
    private String[][] mHolidayOrLunarText;
    private boolean mIsShowLunar;
    private boolean mIsShowHint;
    private boolean mIsShowHolidayHint;
    private DisplayMetrics mDisplayMetrics;
    private OnMonthClickListener mDateClickListener;
    private GestureDetector mGestureDetector;
    private Bitmap mRestBitmap, mWorkBitmap;
    private int[] currentDayColRow;
    private boolean mIsShowWholeMonth;
    private boolean mIsShowWholeWeek;
    private int mWholeMonthTextColor;
    private int mWholeWeekTextColor;




    private String hintTag;

    public MonthView(Context context, int year, int month) {
        this(context, null, year, month);
    }

    public MonthView(Context context, TypedArray array, int year, int month) {
        this(context, array, null, year, month);
    }

    public MonthView(Context context, TypedArray array, AttributeSet attrs, int year, int month) {
        this(context, array, attrs, 0, year, month);
    }

    public MonthView(Context context, TypedArray array, AttributeSet attrs, int defStyleAttr, int year, int month) {
        super(context, attrs, defStyleAttr);
        initAttrs(array, year, month);
        initPaint();
        initMonth();
        initGestureDetector();
        initTaskHint();
    }

    private void initTaskHint() {
        if (mIsShowHint) {
            // 从数据库中获取圆点提示数据
            ScheduleDao dao = ScheduleDao.getInstance(getContext());
            CalendarUtils.getInstance(getContext()).addTaskHints(mSelYear, mSelMonth, dao.getTaskHintByMonth(mSelYear, mSelMonth));
        }
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

    private void initAttrs(TypedArray array, int year, int month) {
        if (array != null) {
            mSelectDayColor = array.getColor(R.styleable.MonthCalendarView_month_selected_text_color, Color.parseColor("#FFFFFF"));
            mSelectBGColor = array.getColor(R.styleable.MonthCalendarView_month_selected_circle_color, Color.parseColor("#E8E8E8"));
            mSelectBGTodayColor = array.getColor(R.styleable.MonthCalendarView_month_selected_circle_today_color, Color.parseColor("#FF8594"));
            mNormalDayColor = array.getColor(R.styleable.MonthCalendarView_month_normal_day_text_color, Color.parseColor("#575471"));
            mCurrentDayColor = array.getColor(R.styleable.MonthCalendarView_month_today_text_color, Color.parseColor("#FF8594"));
            mHintCircleColor = array.getColor(R.styleable.MonthCalendarView_month_hint_circle_color, Color.parseColor("#FE8595"));
            mLastOrNextMonthTextColor = array.getColor(R.styleable.MonthCalendarView_month_last_or_next_month_text_color, Color.parseColor("#ACA9BC"));
            mLunarTextColor = array.getColor(R.styleable.MonthCalendarView_month_lunar_normal_text_color, Color.parseColor("#C2C2C3"));
            mHolidayTextColor = array.getColor(R.styleable.MonthCalendarView_month_holiday_color, Color.parseColor("#A68BFF"));
            mDaySize = array.getDimension(R.styleable.MonthCalendarView_month_day_text_size, DensityUtils.dipToSp(getContext(),13));
            mLunarTextSize = array.getDimension(R.styleable.MonthCalendarView_month_day_lunar_text_size, DensityUtils.dipToSp(getContext(),8));
            mSolarLunarSpeaceSize = array.getDimension(R.styleable.MonthCalendarView_month_lunar_solar_text_space_size,DensityUtils.dipToSp(getContext(),1));
            mIsShowHint = array.getBoolean(R.styleable.MonthCalendarView_month_show_task_hint, true);
            mIsShowLunar = array.getBoolean(R.styleable.MonthCalendarView_month_show_lunar, true);
            mIsShowHolidayHint = array.getBoolean(R.styleable.MonthCalendarView_month_show_holiday_hint, true);
            mIsShowWholeMonth = array.getBoolean(R.styleable.MonthCalendarView_month_show_whole_month,false);
            mIsShowWholeWeek = array.getBoolean(R.styleable.MonthCalendarView_month_show_whole_week,false);


            mWholeMonthTextColor = array.getColor(R.styleable.MonthCalendarView_month_whole_month_text_color,Color.parseColor("#C2C2C3"));
            mWholeWeekTextColor = array.getColor(R.styleable.MonthCalendarView_month_whole_week_text_color,Color.parseColor("#C2C2C3"));

            mDirectBorderColor = Color.parseColor("#003CF5");
            mDirectBGColor = Color.parseColor("#7F9DFF");
        } else {
            mSelectDayColor = Color.parseColor("#FFFFFF");
            mSelectBGColor = Color.parseColor("#E8E8E8");
            mSelectBGTodayColor = Color.parseColor("#FF8594");
            mNormalDayColor = Color.parseColor("#575471");
            mCurrentDayColor = Color.parseColor("#FF8594");
            mHintCircleColor = Color.parseColor("#FE8595");
            mLunarTextColor = Color.parseColor("#C2C2C3");
            mLastOrNextMonthTextColor = Color.parseColor("#ACA9BC");
            mHolidayTextColor = Color.parseColor("#A68BFF");
            mDaySize = DensityUtils.dipToSp(getContext(),13);
            mLunarTextSize = DensityUtils.dipToSp(getContext(),8);
            mSolarLunarSpeaceSize = DensityUtils.dipToSp(getContext(),1);
            mIsShowHint = true;
            mIsShowLunar = true;
            mIsShowHolidayHint = true;
            mIsShowWholeMonth = false;
            mIsShowWholeWeek = false;
            mWholeMonthTextColor = Color.parseColor("#C2C2C3");
            mWholeWeekTextColor = Color.parseColor("#C2C2C3");

        }
        mSelYear = year;
        mSelMonth = month;
        mRestBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_rest_day);
        mWorkBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_work_day);
        mHolidays = CalendarUtils.getInstance(getContext()).getHolidays(mSelYear, mSelMonth + 1);
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

        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setStrokeWidth(DensityUtils.dipToSp(getContext(),1));

    }

    private void initMonth() {
        Calendar calendar = Calendar.getInstance();
        mCurrYear = calendar.get(Calendar.YEAR);
        mCurrMonth = calendar.get(Calendar.MONTH);
        mCurrDay = calendar.get(Calendar.DATE);
        if (mSelYear == mCurrYear && mSelMonth == mCurrMonth) {
            setSelectYearMonth(mSelYear, mSelMonth, mCurrDay);
        } else {
            setSelectYearMonth(mSelYear, mSelMonth, 1);
        }
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
        drawLastMonth(canvas);
        int selected[] = drawThisMonth(canvas);
        drawNextMonth(canvas);
        drawHintCircle(canvas);
        drawLunarText(canvas, selected);
        drawHoliday(canvas);
    }

    private void initSize() {
        mColumnSize = getWidth() / NUM_COLUMNS;
        mRowSize = getHeight() / NUM_ROWS;
        mSelectCircleSize =  (mColumnSize / 2f)*0.9f;
        while (mSelectCircleSize > mRowSize / 2) {
            mSelectCircleSize = (int) (mSelectCircleSize / 1.2);
        }
    }

    private void clearData() {
        mDaysText = new int[6][7];
        mHolidayOrLunarText = new String[6][7];
        currentDayColRow = new int[2];
    }

    private void drawLastMonth(Canvas canvas) {
        int lastYear, lastMonth;
        if (mSelMonth == 0) {
            lastYear = mSelYear - 1;
            lastMonth = 11;
        } else {
            lastYear = mSelYear;
            lastMonth = mSelMonth - 1;
        }

        int monthDays = CalendarUtils.getMonthDays(lastYear, lastMonth);
        int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
        for (int day = 0; day < weekNumber - 1; day++) {
            mDaysText[0][day] = monthDays - weekNumber + day + 2;
            String dayString = String.valueOf(mDaysText[0][day]);

            if(lastYear == mDirectYear &&lastMonth== mDirectMonth &&mDaysText[0][day] == mDirectDay){//绘制指向view
                int startRecX = mColumnSize * 0;
                int startRecY = mRowSize * day;
                int endRecX = startRecX + mColumnSize;
                int endRecY = startRecY + mRowSize;

                mPaint.setColor(mDirectBGColor);
                float cx = (startRecX + endRecX) / 2f;
                float cy =(startRecY + endRecY) / 2f;
                canvas.drawCircle(cx, cy, mSelectCircleSize-2, mPaint);
                RectF rectF =new RectF(cx-mSelectCircleSize,cy-mSelectCircleSize,cx+mSelectCircleSize,cy+mSelectCircleSize);

                mRingPaint.setStrokeWidth(4);
                mRingPaint.setColor(mDirectBorderColor);
                mRingPaint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(rectF,0,359.99f,false,mRingPaint);
            }

            mPaint.setColor(mLastOrNextMonthTextColor);
            int startX = (int) (mColumnSize * day + (mColumnSize - mPaint.measureText(dayString)) / 2);
            float  baseLineY = getSolarTextBaseLine(0);
            canvas.drawText(dayString, startX, baseLineY, mPaint);
            mHolidayOrLunarText[0][day] = CalendarUtils.getHolidayFromSolar(lastYear, lastMonth, mDaysText[0][day]);
        }
    }

    private int[] drawThisMonth(Canvas canvas) {
        String dayString;
        int selectedPoint[] = new int[2];
        int monthDays = CalendarUtils.getMonthDays(mSelYear, mSelMonth);
        int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
        for (int day = 0; day < monthDays; day++) {
            dayString = String.valueOf(day + 1);
            int relDay = day+1;
            //
            if(mSelYear == mCurrYear && mSelMonth == mCurrMonth && day+1 == mCurrDay){
                dayString = "今";
            }

            int col = (day + weekNumber - 1) % 7;
            int row = (day + weekNumber - 1) / 7;
            mDaysText[row][col] = day + 1;
            int startX = (int) (mColumnSize * col + (mColumnSize - mPaint.measureText(dayString)) / 2);
            float solarTextBaseLine = getSolarTextBaseLine(row);
            float startY = solarTextBaseLine;
            if (/*dayString.equals(String.valueOf(mSelDay))*/mSelDay == day+1) {
                int startRecX = mColumnSize * col;
                int startRecY = mRowSize * row;
                int endRecX = startRecX + mColumnSize;
                int endRecY = startRecY + mRowSize;

                if (mSelYear == mCurrYear && mCurrMonth == mSelMonth && relDay == mCurrDay) {
                    mPaint.setColor(mSelectBGTodayColor);
                } else {
                    mPaint.setColor(mSelectBGColor);
                }
                float cx = (startRecX + endRecX) / 2f;
                float cy =(startRecY + endRecY) / 2f;
                canvas.drawCircle(cx, cy, mSelectCircleSize, mPaint);
                mWeekRow = row + 1;
            }


            if(mSelYear == mDirectYear && mSelMonth == mDirectMonth &&relDay == mDirectDay){//绘制指向view
                int startRecX = mColumnSize * col;
                int startRecY = mRowSize * row;
                int endRecX = startRecX + mColumnSize;
                int endRecY = startRecY + mRowSize;

                mPaint.setColor(mDirectBGColor);
                float cx = (startRecX + endRecX) / 2f;
                float cy =(startRecY + endRecY) / 2f;
                canvas.drawCircle(cx, cy, mSelectCircleSize-2, mPaint);
                RectF rectF =new RectF(cx-mSelectCircleSize,cy-mSelectCircleSize,cx+mSelectCircleSize,cy+mSelectCircleSize);

                mRingPaint.setStrokeWidth(4);
                mRingPaint.setColor(mDirectBorderColor);
                mRingPaint.setStyle(Paint.Style.STROKE);
                if(mSelYear == mCurrDay){
                    mRingPaint.setColor(mSelectBGTodayColor);
                }
                canvas.drawArc(rectF,0,359.99f,false,mRingPaint);
            }


            if (mSelDay == day+1) {
                selectedPoint[0] = row;
                selectedPoint[1] = col;
                mPaint.setColor(mSelectDayColor);
            } else if ( day+1 == mCurrDay && mCurrDay != mSelDay && mCurrMonth == mSelMonth && mCurrYear == mSelYear) {
                // 记录今天的行列，用在在绘制农历的时候标红处理
                currentDayColRow[0]=col;
                currentDayColRow[1]=row;
                mPaint.setColor(mCurrentDayColor);
            } else {
                mPaint.setColor(mNormalDayColor);
            }

            canvas.drawText(dayString, startX, startY, mPaint);
            mHolidayOrLunarText[row][col] = CalendarUtils.getHolidayFromSolar(mSelYear, mSelMonth, mDaysText[row][col]);
        }
        return selectedPoint;
    }

    private void drawNextMonth(Canvas canvas) {

        int monthDays = CalendarUtils.getMonthDays(mSelYear, mSelMonth);
        int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
        int nextMonthDays = 42 - monthDays - weekNumber + 1;
        int nextMonth = mSelMonth + 1;
        int nextYear = mSelYear;
        if (nextMonth == 12) {
            nextMonth = 0;
            nextYear += 1;
        }
        for (int day = 0; day < nextMonthDays; day++) {
            int column = (monthDays + weekNumber - 1 + day) % 7;
            int row = 5 - (nextMonthDays - day - 1) / 7;
            try {
                mDaysText[row][column] = day + 1;
                mHolidayOrLunarText[row][column] = CalendarUtils.getHolidayFromSolar(nextYear, nextMonth, mDaysText[row][column]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String dayString = String.valueOf(mDaysText[row][column]);


            if(nextYear == mDirectYear && nextMonth == mDirectMonth &&mDaysText[row][column]== mDirectDay){//绘制指向view
                int startRecX = mColumnSize * column;
                int startRecY = mRowSize * row;
                int endRecX = startRecX + mColumnSize;
                int endRecY = startRecY + mRowSize;

                mPaint.setColor(mDirectBGColor);
                float cx = (startRecX + endRecX) / 2f;
                float cy =(startRecY + endRecY) / 2f;
                canvas.drawCircle(cx, cy, mSelectCircleSize-2, mPaint);
                RectF rectF =new RectF(cx-mSelectCircleSize,cy-mSelectCircleSize,cx+mSelectCircleSize,cy+mSelectCircleSize);

                mRingPaint.setStrokeWidth(4);
                mRingPaint.setColor(mDirectBorderColor);
                mRingPaint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(rectF,0,359.99f,false,mRingPaint);
            }

            mPaint.setColor(mLastOrNextMonthTextColor);
            int startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText(dayString)) / 2);
            float baseLineY = getSolarTextBaseLine(row);
            canvas.drawText(dayString, startX, baseLineY, mPaint);
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
     * 绘制农历
     *
     * @param canvas
     * @param selected
     */
    private void drawLunarText(Canvas canvas, int[] selected) {
        if (mIsShowLunar) {
            int firstYear, firstMonth, firstDay, monthDays;
            int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
            if (weekNumber == 1) {
                firstYear = mSelYear;
                firstMonth = mSelMonth + 1;
                firstDay = 1;
            } else {
                if (mSelMonth == 0) {
                    firstYear = mSelYear - 1;
                    firstMonth = 11;
                    monthDays = CalendarUtils.getMonthDays(firstYear, firstMonth);
                    firstMonth = 12;
                } else {
                    firstYear = mSelYear;
                    firstMonth = mSelMonth - 1;
                    monthDays = CalendarUtils.getMonthDays(firstYear, firstMonth);
                    firstMonth = mSelMonth;
                }
                firstDay = monthDays - weekNumber + 2;
            }
            LunarCalendarUtils.Solar solar = new LunarCalendarUtils.Solar(firstYear, firstMonth, firstDay);
            LunarCalendarUtils.Lunar lunar = LunarCalendarUtils.solarToLunar(solar);

            int solarDayCount = CalendarUtils.getMonthDays(solar.solarYear,solar.solarMonth-1);

            int days;
            int leapMonth = LunarCalendarUtils.leapMonth(lunar.lunarYear);
            days = LunarCalendarUtils.daysInMonth(lunar.lunarYear, lunar.lunarMonth, lunar.isLeap);
            for (int i = 0; i < 42; i++){
                int column = i % 7;
                int row = i / 7;
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


                if (lunar.lunarDay > days) {//判断农历当前是否属属于下一月
                    lunar.lunarDay = 1;
                    boolean isAdd = true;
                    if (lunar.lunarMonth == 12) {
                        lunar.lunarMonth = 1;
                        lunar.lunarYear = lunar.lunarYear + 1;
                        isAdd = false;
                    }
                    if (lunar.lunarMonth == leapMonth) {
                        days = LunarCalendarUtils.daysInMonth(lunar.lunarYear, lunar.lunarMonth, lunar.isLeap);
                    } else {
                        if (isAdd) {
                            lunar.lunarMonth++;
                            days = LunarCalendarUtils.daysInLunarMonth(lunar.lunarYear, lunar.lunarMonth);
                        }
                    }
                }


                if (row == 0 && mDaysText[row][column] >= 23 || row >= 4 && mDaysText[row][column] <= 14) {
                    mLunarPaint.setColor(mLunarTextColor);
                } else {
                    mLunarPaint.setColor(mHolidayTextColor);
                }



                //根据国历获取假期
                String dayString = mHolidayOrLunarText[row][column];




                if ("".equals(dayString)) {
                    dayString = LunarCalendarUtils.getLunarHoliday(lunar.lunarYear, lunar.lunarMonth, lunar.lunarDay);
                }

                //显示整周
                if(mIsShowWholeWeek&&"".equals(dayString)){
                    if(column==0){//




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
                    if ("初一".equals(dayString)) {//是显示当前是农历几月，还是初一
                        dayString = LunarCalendarUtils.getLunarFirstDayString(lunar.lunarMonth, lunar.isLeap);
                        mLunarPaint.setColor(mWholeMonthTextColor);
                    }
                }


                if (selected[0] == row && selected[1] == column) {
                    mLunarPaint.setColor(mSelectDayColor);
                }else if ((currentDayColRow[0]==column&&currentDayColRow[1]==row)&& mCurrDay != mSelDay && mCurrMonth == mSelMonth && mCurrYear == mSelYear) {
                    mLunarPaint.setColor(mCurrentDayColor);
                }


                int startX = (int) (mColumnSize * column + (mColumnSize - mLunarPaint.measureText(dayString)) / 2);
                float baseLineY = getLunarTextBaseLine(row);

//                int baseLineY = (int) (mRowSize * row + mRowSize * 0.72   - fontMetrics.top/2 - fontMetrics.bottom/2);//基线中间点的y轴计算公式
//                int startY = (int) (mRowSize * row + mRowSize * 0.72 - (mLunarPaint.ascent() + mLunarPaint.descent()) / 2);


                canvas.drawText(dayString, startX, baseLineY, mLunarPaint);
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
                int row = i / 7;
                if(column==0|column==6){
                    mHolidays[i] =1;
                }else {
                    mHolidays[i] =2;
                }

                rectF.set(mColumnSize * (column + 1) - mRestBitmap.getWidth() - distance, mRowSize * row + distance, mColumnSize * (column + 1) - distance, mRowSize * row + mRestBitmap.getHeight() + distance);

                if (mHolidays[i] == 1) {
                    canvas.drawBitmap(mRestBitmap, rect, rectF, null);
                } else if (mHolidays[i] == 2) {
                    canvas.drawBitmap(mWorkBitmap, rect, rectF, null);
                }
            }
        }
    }

    /**
     * 绘制圆点提示
     *
     * @param canvas
     */
    private void drawHintCircle(Canvas canvas) {
        if (mIsShowHint) {
//            List<Integer> hints = CalendarUtils.getInstance(getContext()).getTaskHints(mSelYear, mSelMonth);
            List<Integer> hints = HintHelper.getHintHelper(hintTag).getHintList(mSelYear, mSelMonth);
            if (hints.size() > 0) {
                mPaint.setColor(mHintCircleColor);
                int monthDays = CalendarUtils.getMonthDays(mSelYear, mSelMonth);
                int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
                for (int day = 0; day < monthDays; day++) {
                    int col = (day + weekNumber - 1) % 7;
                    int row = (day + weekNumber - 1) / 7;
                    if (!hints.contains(day + 1)) continue;
                    float circleX = (float) (mColumnSize * col + mColumnSize * 0.5);
                    float circleY = (float) (mRowSize * row + mRowSize * 0.80);
                    canvas.drawCircle(circleX, circleY, mCircleRadius, mPaint);
                }
            }
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    public void setSelectYearMonth(int year, int month, int day) {
        mSelYear = year;
        mSelMonth = month;
        mSelDay = day;
    }

    public void setDirectingYearMonthDay(int year,int month,int day){
        mDirectYear =year;
        mDirectMonth = month;
        mDirectDay = day;
    }



    private void doClickAction(int x, int y) {
        if (y > getHeight())
            return;
        int row = y / mRowSize;
        int column = x / mColumnSize;
        column = Math.min(column, 6);
        int clickYear = mSelYear, clickMonth = mSelMonth;
        if (row == 0) {
            int directing = mDaysText[row][column];
            if (directing >= 23) {
                if (mSelMonth == 0) {
                    clickYear = mSelYear - 1;
                    clickMonth = 11;
                } else {
                    clickYear = mSelYear;
                    clickMonth = mSelMonth - 1;
                }
                if (mDateClickListener != null) {
                    mDateClickListener.onClickLastMonth(clickYear, clickMonth, directing);
                }
            } else {
                clickThisMonth(clickYear, clickMonth, mDaysText[row][column]);
            }
        } else {
            int monthDays = CalendarUtils.getMonthDays(mSelYear, mSelMonth);
            int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
            int nextMonthDays = 42 - monthDays - weekNumber + 1;
            if (mDaysText[row][column] <= nextMonthDays && row >= 4) {
                if (mSelMonth == 11) {
                    clickYear = mSelYear + 1;
                    clickMonth = 0;
                } else {
                    clickYear = mSelYear;
                    clickMonth = mSelMonth + 1;
                }
                if (mDateClickListener != null) {
                    mDateClickListener.onClickNextMonth(clickYear, clickMonth, mDaysText[row][column]);
                }
            } else {
                clickThisMonth(clickYear, clickMonth, mDaysText[row][column]);
            }
        }
    }


    public void doDirectingAction(float x, float y){
        int[] location = new int[2];
        getLocationInWindow(location);
        x-=location[0];
        y-=location[1];

        if(x<0||y<0){
            directingMonth(0,0,0);
            return;
        }

        if (y > getHeight()){
            directingMonth(0,0,0);
            return;
        }
        int row = (int) (y / mRowSize);
        int column = (int) (x / mColumnSize);
        column = Math.min(column, 6);


        int directingYear = mSelYear, directingMonth = mSelMonth;
        if (row == 0) {
            if (mDaysText[row][column] >= 23) {
                if (mSelMonth == 0) {
                    directingYear = mSelYear - 1;
                    directingMonth = 11;
                } else {
                    directingYear = mSelYear;
                    directingMonth = mSelMonth - 1;
                }
                directingMonth(directingYear, directingMonth, mDaysText[row][column]);
            } else {
                directingMonth(directingYear, directingMonth, mDaysText[row][column]);
            }
        } else {
            int monthDays = CalendarUtils.getMonthDays(mSelYear, mSelMonth);
            int weekNumber = CalendarUtils.getFirstDayWeek(mSelYear, mSelMonth);
            int nextMonthDays = 42 - monthDays - weekNumber + 1;
            if (mDaysText[row][column] <= nextMonthDays && row >= 4) {
                if (mSelMonth == 11) {
                    directingYear = mSelYear + 1;
                    directingMonth = 0;
                } else {
                    directingYear = mSelYear;
                    directingMonth = mSelMonth + 1;
                }
                directingMonth(directingYear, directingMonth, mDaysText[row][column]);
            } else {
                directingMonth(directingYear, directingMonth, mDaysText[row][column]);
            }
        }

    }



    private void directingMonth(int directingYear,int directingMonth, int directingDay){
        setDirectingYearMonthDay(directingYear,directingMonth,directingDay);
        invalidate();
    }

    /**
     * 跳转到某日期
     *
     * @param year
     * @param month
     * @param day
     */
    public void clickThisMonth(int year, int month, int day) {
        if (mDateClickListener != null) {
            mDateClickListener.onClickThisMonth(year, month, day);
        }
        if(year==mSelYear&&month==mSelMonth&&day==mSelDay){
            return;
        }
        setSelectYearMonth(year, month, day);
        invalidate();
    }



    public void setmSelectBGColor(int color){
        mSelectBGColor = color;
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

    public int getRowSize() {
        return mRowSize;
    }

    public int getWeekRow() {
        return mWeekRow;
    }



    /**
     * 添加一个圆点提示
     *
     * @param day
     */
    public boolean addTaskHint(Integer day) {
        if (mIsShowHint) {
//            if (CalendarUtils.getInstance(getContext()).addTaskHint(mSelYear, mSelMonth, day)) {
            HintHelper.getHintHelper(hintTag).addHint(mSelYear, mSelMonth, day);
            invalidate();
            return true;
        }
        return false;
    }

    /**
     * 删除一个圆点提示
     *
     * @param day
     */
    public boolean removeTaskHint(Integer day) {
        if (mIsShowHint) {
            HintHelper.getHintHelper(hintTag).removeHint(mSelYear, mSelMonth, day);
            invalidate();
            return true;
        }
        return false;
    }

    /**
     * 删除所有圆点提示
     *
     */
    public boolean removeAllTaskHint() {
        if (mIsShowHint) {
            HintHelper.getHintHelper(hintTag).removeAllHint();
            invalidate();
            return true;
        }
        return false;
    }

    /**
     * 设置点击日期监听
     *
     * @param dateClickListener
     */
    public void setOnDateClickListener(OnMonthClickListener dateClickListener) {
        this.mDateClickListener = dateClickListener;
    }

    public boolean isDirected(){
        return mDirectYear!=0&&mDirectMonth!=0&&mDirectDay!=0;
    }

    public int getmDirectDay() {
        return mDirectDay;
    }

    public int getmDirectMonth(){
        return mDirectMonth;
    }

    public int getmDirectYear(){
        return mDirectYear;
    }

    public void setHintTag(String hintTag) {
        this.hintTag = hintTag;
        invalidate();
    }

    public void resetDirect(){
        mDirectDay = 0;
        mDirectMonth = 0;
        mDirectYear = 0;
        invalidate();
    }

}

