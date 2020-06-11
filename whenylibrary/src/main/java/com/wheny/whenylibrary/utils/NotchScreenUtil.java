package com.wheny.whenylibrary.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NotchScreenUtil {


    /**
     * 华为start
     */
    // 判断是否是华为刘海屏
    public static boolean hasNotchInScreenAtHuawei(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class<?> HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (Boolean) get.invoke(HwNotchSizeUtil);
            Log.d("NotchScreenUtil", "this Huawei device has notch in screen？"+ret);
        } catch (ClassNotFoundException e) {
            Log.e("NotchScreenUtil", "hasNotchInScreen ClassNotFoundException", e);
        } catch (NoSuchMethodException e) {
            Log.e("NotchScreenUtil", "hasNotchInScreen NoSuchMethodException", e);
        } catch (Exception e) {
            Log.e("NotchScreenUtil", "hasNotchInScreen Exception", e);
        }
        return ret;
    }

    /**
     * 获取华为刘海的高
     * @param context
     * @return
     */
    public static int getNotchSizeAtHuawei(Context context) {
        int[] ret = new int[] { 0, 0 };
        try {
            ClassLoader cl = context.getClassLoader();
            Class<?> HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);

        } catch (ClassNotFoundException e) {
            Log.e("NotchScreenUtil", "getNotchSize ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("NotchScreenUtil", "getNotchSize NoSuchMethodException");
        } catch (Exception e) {
            Log.e("NotchScreenUtil", "getNotchSize Exception");
        }
        return ret[1];
    }

    /**
     * 华为end
     */

    /**
     * Oppo start
     */
    public static boolean hasNotchInScreenAtOppo(Context context) {
        boolean hasNotch = context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        Log.d("NotchScreenUtil", "this OPPO device has notch in screen？"+hasNotch);
        return hasNotch;
    }

    public static int getNotchSizeAtOppo() {
        return 80;
    }

    /**
     * Oppo end
     */

    /**
     * vivo start
     */
    public static final int NOTCH_IN_SCREEN_VOIO = 0x00000020;// 是否有凹槽
    public static final int ROUNDED_IN_SCREEN_VOIO = 0x00000008;// 是否有圆角

    public static boolean hasNotchInScreenAtVivo(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class<?> FtFeature = cl.loadClass("com.util.FtFeature");
            Method get = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (Boolean) get.invoke(FtFeature, NOTCH_IN_SCREEN_VOIO);
            Log.d("NotchScreenUtil", "this VIVO device has notch in screen？" + ret);
        } catch (ClassNotFoundException e) {
            Log.e("NotchScreenUtil", "hasNotchInScreen ClassNotFoundException", e);
        } catch (NoSuchMethodException e) {
            Log.e("NotchScreenUtil", "hasNotchInScreen NoSuchMethodException", e);
        } catch (Exception e) {
            Log.e("NotchScreenUtil", "hasNotchInScreen Exception", e);
        }
        return ret;
    }

    public static int getNotchSizeAtVivo(Context context){
        return dp2px(context, 32);
    }

    /**
     * vivo end
     */


    /**
     * dp转px
     * @param context
     * @param dpValue
     * @return
     */
    private static int dp2px(Context context, int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,context.getResources().getDisplayMetrics());
    }


    /**
     * xiaomi start
     */

    public static boolean hasNotchInScreenAtXiaomi(Context context) {
        boolean ret = false;
        if(getXiaoMiInt("ro.miui.notch",context)==1){
            return true;
        }
        return false;
    }

    public static int getNotchSizeAtXiaomi(Context context){
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }



    /**
     * 小米刘海屏判断.
     * @return 0 if it is not notch ; return 1 means notch
     * @throws IllegalArgumentException if the key exceeds 32 characters
     */
    private static int getXiaoMiInt(String key,Context context) {
        int result = 0;
        try {
            ClassLoader classLoader = context.getClassLoader();
            @SuppressWarnings("rawtypes")
            Class SystemProperties = classLoader.loadClass("android.os.SystemProperties");
            //参数类型
            @SuppressWarnings("rawtypes")
            Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = int.class;
            Method getInt = SystemProperties.getMethod("getInt", paramTypes);
            //参数
            Object[] params = new Object[2];
            params[0] = new String(key);
            params[1] = new Integer(0);
            result = (Integer) getInt.invoke(SystemProperties, params);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * xiaomi end
     */


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */

    public final static int DEVICE_BRAND_OPPO = 0x0001;
    public final static int DEVICE_BRAND_HUAWEI = 0x0002;
    public final static int DEVICE_BRAND_VIVO = 0x0003;
    public final static int DEVICE_BRAND_XIAOMI = 0x0004;


    @SuppressLint("DefaultLocale")
    public static int getDeviceBrand() {
        String brand = Build.BRAND.trim().toUpperCase();
        if (brand.contains("HUAWEI")) {
            Log.d("device brand", "HUAWEI");
            return DEVICE_BRAND_HUAWEI;
        }else if (brand.contains("OPPO")) {
            Log.d("device brand", "OPPO");
            return DEVICE_BRAND_OPPO;
        }else if (brand.contains("XIAOMI")) {
            Log.d("device brand", "XIAOMI");
            return DEVICE_BRAND_XIAOMI;
        }else if (brand.contains("VIVO")) {
            Log.d("device brand", "VIVO");
            return DEVICE_BRAND_VIVO;
        }
        return 0;
    }


    public static void setNotechScreen(Activity context,int rootViewId){
        int offSet= getOffSet(context);
        View mkRootView = context.findViewById(rootViewId);
        if(mkRootView!=null){
            mkRootView.setPadding(0,offSet,0,0);
        }
    }

    /**
     * 获取应该位移多少（适配刘海屏）
     *
     */
    public static int getOffSet(Activity context) {
        int deviceBrand = NotchScreenUtil.getDeviceBrand();
        int notechHeight=0;
        int statuBarsHeight = NotchScreenUtil.getStatusBarHeight(context);
        switch (deviceBrand){
            case NotchScreenUtil.DEVICE_BRAND_HUAWEI:
                if(NotchScreenUtil.hasNotchInScreenAtHuawei(context)){
                    notechHeight = NotchScreenUtil.getNotchSizeAtHuawei(context);
                }
                break;
            case NotchScreenUtil.DEVICE_BRAND_OPPO:
                if(NotchScreenUtil.hasNotchInScreenAtOppo(context)){
                    notechHeight = NotchScreenUtil.getNotchSizeAtOppo();
                }
                break;
            case NotchScreenUtil.DEVICE_BRAND_VIVO:
                if(NotchScreenUtil.hasNotchInScreenAtVivo(context)){
                    notechHeight = NotchScreenUtil.getNotchSizeAtVivo(context);
                }
                break;
            case NotchScreenUtil.DEVICE_BRAND_XIAOMI:
                if(NotchScreenUtil.hasNotchInScreenAtXiaomi(context)){
                    notechHeight = NotchScreenUtil.getNotchSizeAtXiaomi(context);
                }
                break;
        }
        int totalHeight = 0;
        if(statuBarsHeight>=notechHeight){
            totalHeight=statuBarsHeight;
        }else {
            totalHeight=notechHeight;
        }
        return totalHeight;
    }


    /**
     * 获取屏幕高度
     *
     * */
    public static int getScreenHeight(Activity activity) {
        if (activity == null) {
            return 0;
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        int realHeight = 0;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            final DisplayMetrics metrics = new DisplayMetrics();
            display.getRealMetrics(metrics);
            realHeight = metrics.heightPixels;
        } else {
            try {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                realHeight = (Integer) mGetRawH.invoke(display);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return realHeight;
    }

    /**
     * 判断是否有导航栏
     *
     * */
    public static boolean checkDeviceHasNavigationBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            WindowManager windowManager = activity.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics realDisplayMetrics = new DisplayMetrics();
            display.getRealMetrics(realDisplayMetrics);
            int realHeight = realDisplayMetrics.heightPixels;
            int realWidth = realDisplayMetrics.widthPixels;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            int displayHeight = displayMetrics.heightPixels;
            int displayWidth = displayMetrics.widthPixels;
            return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
        } else {
            boolean hasNavigationBar = false;
            Resources resources = activity.getResources();
            int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (id > 0) {
                hasNavigationBar = resources.getBoolean(id);
            }
            try {
                Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
                Method m = systemPropertiesClass.getMethod("get", String.class);
                String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
                if ("1".equals(navBarOverride)) {
                    hasNavigationBar = false;
                } else if ("0".equals(navBarOverride)) {
                    hasNavigationBar = true;
                }
            } catch (Exception e) {
            }
            return hasNavigationBar;
        }
    }

    /**
     * 判断底部导航栏是否可见
     *
     * */
    public static boolean isNavigationBarVisible(Activity activity) {
        boolean show = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = activity.getWindow().getWindowManager().getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            View decorView = activity.getWindow().getDecorView();
            Configuration conf = activity.getResources().getConfiguration();
            if (Configuration.ORIENTATION_LANDSCAPE == conf.orientation) {
                View contentView = decorView.findViewById(android.R.id.content);
                show = (point.x != contentView.getWidth());
            } else {
                Rect rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                show = (rect.bottom != point.y);
            }
        }
        return show;
    }

    /**
     * 是否为竖屏
     *
     * @param context
     * @return
     */
    public static boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * 获取当前底部导航栏高度(隐藏后高度为0)
     *
     * @param activity
     * @return
     */
    public static int getCurrentNavigationBarHeight(Activity activity) {
        int navigationBarHeight = 0;
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier(isPortrait(activity) ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
        if (resourceId > 0 && checkDeviceHasNavigationBar(activity) && isNavigationBarVisible(activity)) {
            navigationBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
    }

    /**
     * 获取可用屏幕高度，排除虚拟键
     *
     * @param context 上下文
     * @return 返回高度
     */
    public static int getContentHeight(Activity context) {
        int contentHeight = getScreenHeight(context) - getCurrentNavigationBarHeight(context);
        return contentHeight;
    }

}
