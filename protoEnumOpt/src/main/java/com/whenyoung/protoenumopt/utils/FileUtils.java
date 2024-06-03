package com.whenyoung.protoenumopt.utils;

import java.io.File;

/**
 * 类名：FileUtils
 * 包名：com.whenyoung.protoenumopt.utils
 * 创建时间：2024/5/30 17:34
 * 创建人： WhenYoung
 * 描述：
 **/
public  class FileUtils {

    public static boolean enableLog = true;

    public static String getCompilerPath(String path){
        File file = new File(path);
        String newFilePath = file.getParent()+"/compiler";
        File newFile = new File(newFilePath);
        if(!newFile.exists()){
            newFile.mkdirs();
        }
        return newFilePath;
    }


    public static void println(String message){
        if(enableLog){
            System.out.println(message);
        }
    }







}
