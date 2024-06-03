package com.whenyoung.protoenumopt;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.whenyoung.protoenumopt.utils.ClassNameExtractor;
import com.whenyoung.protoenumopt.utils.DynamicCompiler;
import com.whenyoung.protoenumopt.utils.FileUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Consumer;


/**
 * 类名：ProtoEnumOptMain
 * 包名：com.whenyoung.protoenumopt
 * 创建时间：2024/5/30 17:10
 * 创建人： WhenYoung
 * 描述：
 **/
public class ProtoEnumOptMain {
    public static void main(String[] args) {
        FileUtils.println(Arrays.toString(args));
        String currentPath = System.getProperty("user.dir");
        FileUtils.println("ProtoEnumOptMain currentPath:" + currentPath);
        // 获取当前path
//        String projectRootPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
//        System.out.println("ProtoEnumOptMain projectRootPath:" + projectRootPath);
        String codePath = currentPath + "/build/generated/source/proto/release";
        FileUtils.println("ProtoEnumOptMain codePath:" + codePath);

        // 递归找到这个文件下面所有的.java文件
        File codeFileRoot = new File(codePath);
        ArrayList<File> pbFiles = new ArrayList<File>();
        findAllPb(pbFiles, codeFileRoot);
        for (File pbFile : pbFiles) {
            String absName = pbFile.getAbsoluteFile().getPath();
            FileUtils.println("ProtoEnumOptMain pbFile:" + absName);
            removePbEnumGetter(absName);
        }

        System.out.println("ProtoEnumOptMain -------------end");
    }

    public static void removePbEnumGetter(String pbFileName) {
        try {
            String compilerPath = FileUtils.getCompilerPath(pbFileName);
            FileUtils.println("ProtoEnumOptMain -------------end");

            DynamicCompiler.compile(pbFileName, compilerPath);
            Map<String, Map<String, Method>> wellRemoveMethodClazzs = new HashMap();

            URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(compilerPath).toURI().toURL()});
            List<String> allClassname = ClassNameExtractor.extractFullClassNames(pbFileName);
            for (String clazzName : allClassname) {
//                FileUtils.println("ProtoEnumOptMain clazzName:===============" + clazzName);
                try {
                    Class clazz = classLoader.loadClass(clazzName);
                    // 枚举内部的方法跳过
                    if (clazz.isEnum()) {
                        continue;
                    }
                    for (Method method : clazz.getMethods()) {
                        // 将返回值是枚举类型的方法干掉,只保留返回int类型的
                        if (method.getReturnType().isEnum()) {
                            Map<String, Method> map = wellRemoveMethodClazzs.get(clazzName);
                            if (map == null) {
                                map = new HashMap<>();
                                wellRemoveMethodClazzs.put(clazz.getName(), map);
                            }
//                            FileUtils.println("ProtoEnumOptMain well remove class:" + clazzName+" methodName:"+method.getName()) ;
                            map.put(method.getName(), method);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (wellRemoveMethodClazzs.size() > 0) {
                Path path = Paths.get(pbFileName);
                CompilationUnit outCu = StaticJavaParser.parse(path);
                for (ClassOrInterfaceDeclaration classOrInterface : outCu.findAll(ClassOrInterfaceDeclaration.class)) {
                    String fullClassName = ClassNameExtractor.getFullNameWithJavaParser(classOrInterface);
                    if (wellRemoveMethodClazzs.containsKey(fullClassName)) {
                        //这个类有被需要被移除的方法才处理
                        System.out.println("TProtoEnumOptMain  well remove class=====" + fullClassName);
                        Map<String, Method> wellRemoveMethods = wellRemoveMethodClazzs.get(fullClassName);
                        for (MethodDeclaration method : classOrInterface.getMethods()) {
                            if (wellRemoveMethods.containsKey(method.getNameAsString())){
                                method.getComment().ifPresent(Comment::remove);
                                String newMethodStr = method.toString().replaceAll("/\\*\\*","");
                                newMethodStr = newMethodStr.replaceAll("/\\*","");
                                newMethodStr = newMethodStr.replaceAll("\\*/","");
                                String commentedMethod =  newMethodStr ;
                                BlockComment blockComment = new BlockComment(commentedMethod);
                                System.out.println("TProtoEnumOptMain  well remove method:" + method.getNameAsString());
                                method.getParentNode().ifPresent(node -> {
                                    node.addOrphanComment(new BlockComment("this method is removed please use "+method.getNameAsString()+"Value()"));
                                    node.addOrphanComment(blockComment);
                                    method.remove();
                                });
                            }
                        }
                    }
                }

                Files.write(path, outCu.toString().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void findAllPb(ArrayList<File> files, File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File listFile : file.listFiles()) {
                findAllPb(files, listFile);
            }
        } else {
            if (file.getName().endsWith(".java")) {
                files.add(file);
            }
        }
    }
}
