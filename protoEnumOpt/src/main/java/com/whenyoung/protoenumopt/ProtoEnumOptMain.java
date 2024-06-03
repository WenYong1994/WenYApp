package com.whenyoung.protoenumopt;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;


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
        String projectRootPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
        System.out.println("ProtoEnumOptMain projectRootPath:" + projectRootPath);
        String codePath = projectRootPath + "/build/generated/source/proto/release";
        System.out.println("ProtoEnumOptMain codePath:" + codePath);

        // 递归找到这个文件下面所有的.java文件
        File codeFileRoot = new File(codePath);
        ArrayList<File> pbFiles = new ArrayList<File>();
        findAllPb(pbFiles,codeFileRoot);
        for (File pbFile : pbFiles) {
            String absName = pbFile.getAbsoluteFile()+"/"+pbFile.getName();
            System.out.println("ProtoEnumOptMain pbFile:" +absName);
            removePbEnumGetter(absName);
        }

        System.out.println("ProtoEnumOptMain -------------end");
    }

    public static void removePbEnumGetter(String pbFileName){
        ClassPool classPool = ClassPool.getDefault();
        try {
            CtClass ctClass = classPool.get(pbFileName);
            for (CtMethod method : ctClass.getMethods()) {

            }

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void findAllPb(ArrayList<File> files,File file){
        if(file==null || !file.exists()){
            return;
        }
        if(file.isDirectory()){
            for (File listFile : file.listFiles()) {
                findAllPb(files,listFile);
            }
        }else {
            if(file.getName().endsWith(".java")){
                files.add(file);
            }
        }
    }
}
