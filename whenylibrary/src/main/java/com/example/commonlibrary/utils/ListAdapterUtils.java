package com.example.commonlibrary.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * author：Vinsmoke
 * e-mail：vinsmokeleigh@gmail.com
 */
public class ListAdapterUtils {

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean isSize(List list, int size) {
        return !isEmpty(list) && list.size() >= size;
    }

    public static int size(List list) {
        return isEmpty(list) ? 0 : list.size();
    }

    public static String toStr(String space, List<String> list) {
        StringBuffer sb = new StringBuffer();
        if (!ListAdapterUtils.isEmpty(list)) {
            for (String str :
                    list) {
                sb.append(str).append(space);
            }
        }
        return sb.toString().isEmpty() ? "" : sb.substring(0, sb.length() - space.length());
    }

    public static <T> T safetyGet(List<T> list, int index, T defaultValue) {
        if (isSize(list, index + 1)) {
            return list.get(index);
        }
        return defaultValue;
    }

    public static <T> T safetyGet(List<T> list, int index) {
        return safetyGet(list, index, null);
    }


    /**
     * 等分一个list
     *
     * @param target 数据源
     * @param count  等分大小
     **/
    public static <T> List<List<T>> createList(List<T> target, int count) {
        List<List<T>> listArr = new ArrayList<List<T>>();
        //获取被拆分的数组个数
        int arrSize = target.size() % count == 0 ? target.size() / count : target.size() / count + 1;
        for (int i = 0; i < arrSize; i++) {
            List<T> sub = new ArrayList<T>();
            //把指定索引数据放入到list中
            for (int j = i * count; j <= count * (i + 1) - 1; j++) {
                if (j <= target.size() - 1) {
                    sub.add(target.get(j));
                }
            }
            listArr.add(sub);
        }
        return listArr;
    }

    /**
     * 深度拷贝list
     * 对象需要实现序列化接口Serializable
     **/
    @SuppressWarnings("unchecked")
    public static <T> List<T> deepCopyList(List<T> src) {
        List<T> dest = null;
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dest;
    }

    /**
     * 判断两个list是否相等
     **/
    public static boolean equals(List aList, List bList) {

        if (aList == bList)
            return true;

        if (size(aList) != size(bList))
            return false;

        int n = size(aList);
        int i = 0;
        while (n-- != 0) {
            if (!aList.get(i).equals(bList.get(i)))
                return false;
            i++;
        }
        return true;
    }
}
