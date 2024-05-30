package com.whenyoung.protoenumopt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * 类名：ProtoEnumOptMain
 * 包名：com.whenyoung.protoenumopt
 * 创建时间：2024/5/30 17:10
 * 创建人： WhenYoung
 * 描述：
 **/
public class ProtoEnumOptMain {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        String currentPath = System.getProperty("user.dir");
        // 获取当前path
        String projectRootPath = currentPath.substring(0,currentPath.lastIndexOf("/"));
        System.out.println("projectRootPath:"+projectRootPath);
        String codePath = projectRootPath+"/build/generated/source/proto/release";
        System.out.println("codePath:"+codePath);

        System.out.println("ProtoEnumOpt-------------start");

        System.out.println("ProtoEnumOpt-------------end");
    }
}
