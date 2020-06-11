package com.wheny.whenylibrary.utils;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * author：Vinsmoke
 * e-mail：vinsmokeleigh@gmail.com
 */

public class ViewAdapterUtils {


    public static int visibleGone(boolean aBoolean){
        return aBoolean? VISIBLE: GONE;
    }

    public static int visibleInVisible(boolean aBoolean){
        return aBoolean? VISIBLE: INVISIBLE;
    }


}