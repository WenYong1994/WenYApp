package com.wheny.whenylibrary.utils;

import java.text.DecimalFormat;

public class FileUtils {

    public static String getNameByPath(String path){
        try {
            String fileName = path.substring(path.lastIndexOf("/")+1);
            return fileName;
        }catch (Exception e){

        }
        return null;
    }


    public static String getSuffixByPath(String path){
        try {
            String fileName = path.substring(path.lastIndexOf(".")+1);
            return fileName;
        }catch (Exception e){

        }
        return null;
    }


    public static String byte2GB(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.00");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append(" G");
        } else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append(" M");
        }
        else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append(" K");
        }
        else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            }
            else {
                bytes.append((int) size).append(" B");
            }
        }
        return bytes.toString();
    }

}
