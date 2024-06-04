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


/**
 * 类名：ProtoEnumCusCompiler
 * 包名：com.whenyoung.protoenumopt
 * 创建时间：2024/5/30 17:10
 * 创建人： WhenYoung
 * 描述：
 **/
public class ProtoEnumCusCompiler {
    public static void main(String[] args) {
        String action = "remove";
        boolean enableLog = false;
        for (String arg : args) {
            if (arg.startsWith("action")) {
                action = arg.replaceAll("action:", "").trim();
            }
            if (arg.startsWith("enableLog")) {
                try {
                    enableLog = Boolean.parseBoolean(arg.replaceAll("enableLog:", "false").trim());
                } catch (Exception e) {
                }
            }
        }
        FileUtils.enableLog = enableLog;
        FileUtils.println(Arrays.toString(args));

        String currentPath = System.getProperty("user.dir");
        FileUtils.println("ProtoEnumCusCompiler currentPath:" + currentPath);
        // 获取当前path
//        String projectRootPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
//        System.out.println("ProtoEnumCusCompiler projectRootPath:" + projectRootPath);
        String codePathRelease = currentPath + "/build/generated/source/proto/release";
        String codePathDebug = currentPath + "/build/generated/source/proto/debug";
        FileUtils.println("ProtoEnumCusCompiler codePath:" + codePathDebug);
        FileUtils.println("ProtoEnumCusCompiler codePath:" + codePathRelease);

        // 递归找到这个文件下面所有的.java文件
        File codeFileRootRelease = new File(codePathRelease);
        File codeFileRootDebug = new File(codePathDebug);
        ArrayList<File> pbFiles = new ArrayList<File>();
        findAllPb(pbFiles, codeFileRootDebug);
        findAllPb(pbFiles, codeFileRootRelease);
        for (File pbFile : pbFiles) {
            String absName = pbFile.getAbsoluteFile().getPath();
            FileUtils.println("ProtoEnumCusCompiler pbFile:" + absName);
            if (action.equals("replace")) {
                setPbEnumToInt(absName);
            } else {
                removePbEnumGetter(absName);
            }
        }


    }

    public static void removePbEnumGetter(String pbFileName) {
        String compilerPath = FileUtils.getCompilerPath(pbFileName);
        try {
            FileUtils.println("ProtoEnumCusCompiler -------------end");

            DynamicCompiler.compile(pbFileName, compilerPath);
            Map<String, Map<String, Method>> wellRemoveMethodClazzs = new HashMap();

            URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(compilerPath).toURI().toURL()});
            List<String> allClassname = ClassNameExtractor.extractFullClassNames(pbFileName);
            for (String clazzName : allClassname) {
//                FileUtils.println("ProtoEnumCusCompiler clazzName:===============" + clazzName);
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
//                            FileUtils.println("ProtoEnumCusCompiler well remove class:" + clazzName+" methodName:"+method.getName()) ;
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
                            if (wellRemoveMethods.containsKey(method.getNameAsString())) {
                                method.getComment().ifPresent(Comment::remove);
                                String newMethodStr = method.toString().replaceAll("/\\*\\*", "");
                                newMethodStr = newMethodStr.replaceAll("/\\*", "");
                                newMethodStr = newMethodStr.replaceAll("\\*/", "");
                                String commentedMethod = newMethodStr;
                                BlockComment blockComment = new BlockComment(commentedMethod);
                                System.out.println("TProtoEnumOptMain  well remove method:" + method.getNameAsString());
                                method.getParentNode().ifPresent(node -> {
                                    node.addOrphanComment(new BlockComment("this method is removed please use " + method.getNameAsString() + "Value()"));
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
        } finally {
            try {
                //删除生成的编译class文件
                File file = new File(compilerPath);
                deleteDirectory(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("ProtoEnumCusCompiler -------------end");
    }

    public static void setPbEnumToInt(String pbFileName) {
        String compilerPath = FileUtils.getCompilerPath(pbFileName);
        try {
            FileUtils.println("ProtoEnumCusCompiler -------------end");

            DynamicCompiler.compile(pbFileName, compilerPath);
            Map<String, Map<String, Method>> wellRemoveMethodClazzs = new HashMap();

            URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(compilerPath).toURI().toURL()});
            List<String> allClassname = ClassNameExtractor.extractFullClassNames(pbFileName);
            for (String clazzName : allClassname) {
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
                        Map<String, Method> wellRemoveMethods = wellRemoveMethodClazzs.get(fullClassName);
                        for (MethodDeclaration method : classOrInterface.getMethods()) {
                            if (wellRemoveMethods.containsKey(method.getNameAsString())) {
                                // 将返回枚举的方法。变成返回int
                                method.setType(int.class);
                                System.out.println("TProtoEnumOptMain  well remove class===== " + fullClassName );
                                BlockStmt oldBlockStmt = method.getBody().orElse(null);
                                if (oldBlockStmt != null && oldBlockStmt.getStatements() != null && oldBlockStmt.getStatements().size() > 0) {
                                    method.removeBody();
                                    Statement statement = StaticJavaParser.parseStatement("return " + method.getNameAsString() + "Value();");
                                    BlockStmt blockStmt = new BlockStmt();
                                    blockStmt.addStatement(statement);
                                    method.setBody(blockStmt);
                                }
                            }
                        }
                    }
                }

                Files.write(path, outCu.toString().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //删除生成的编译class文件
                File file = new File(compilerPath);
                deleteDirectory(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("ProtoEnumCusCompiler -------------end");
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

    private static void deleteDirectory(File directory) {
        // 确保文件或文件夹存在
        if (!directory.exists()) {
            return;
        }

        // 如果是文件夹，首先删除其中的文件和子文件夹
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                deleteDirectory(file);
            }
        }

        // 删除文件或空文件夹
        directory.delete();
    }


}
