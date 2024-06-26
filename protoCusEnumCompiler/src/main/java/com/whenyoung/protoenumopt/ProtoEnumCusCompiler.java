package com.whenyoung.protoenumopt;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.whenyoung.protoenumopt.utils.ClassNameExtractor;
import com.whenyoung.protoenumopt.utils.DynamicCompiler;
import com.whenyoung.protoenumopt.utils.FileUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
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

    static NodeList<Statement> needAddStatement;
    static Class enumLiteClass = com.google.protobuf.Internal.EnumLite.class;

    /**
     * 脚本一共做了以下几件事情
     *
     * 1、调用 findAllPb 收集所有pb类
     * 2、遍历所有pb类
     *
     * 3、 a、判断 action 如果是 remove 调用 removePbEnumGetter 方法移除 getEnum方法
     *
     *    b、判断 action 是 replace 调用 setPbEnumToInt 方法
     *          以下步骤解释 cation为replace的过程
     *
     *          1、调用setReturnTypeToInt 方法 内部 先将 getEnum 方法返回值改成int 并添加注释
     *
     *          2、调用setReturnTypeToInt 方法 内部 然后
     *             为$Builder类添加 setEnum(int value) 方法，因为兼容我门把EnumField 改成了int，否则pb框架会报错
     *
     *          3、调用changeFiedEnumTypeToInt 方法 在 pb生成的最外层外部类的static代码块
     *             插入代码，插入代码逻辑是使用反射遍历所有filed 把 enum的类型改为int，并且把defaultValue改成0
     *
     * */


    static {
        String fixCodeStr =
                "public class DummyClass{\n" +
                        "    public void dummyMethod(){\n" +
                        "    System.out.println(\"-----------------\"+descriptorData);" +
                        "        for (Descriptors.Descriptor messageType : getDescriptor().getMessageTypes()) {\n" +
                        "            for (Descriptors.FieldDescriptor field : messageType.getFields()) {\n" +
                        "                if (field.getType() == Descriptors.FieldDescriptor.Type.ENUM) {\n" +
                        "                    Class fieldClass = field.getClass();\n" +
                        "                    try {\n" +
                        "                        Field typeField = fieldClass.getDeclaredField(\"type\");\n" +
                        "                        typeField.setAccessible(true);\n" + "             " +
                        "                        typeField.set(field, Descriptors.FieldDescriptor.Type.INT32);\n" +
                        "                        Field defaultValueField = fieldClass.getDeclaredField(\"defaultValue\");\n" +
                        "                        defaultValueField.setAccessible(true);\n" +
                        "                        defaultValueField.set(field,0);\n" +
                        "                    } catch (NoSuchFieldException e) {\n" +
                        "                        e.printStackTrace();\n" +
                        "                    } catch (IllegalAccessException e) {\n" +
                        "                        e.printStackTrace();\n" +
                        "                    }\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";
        CompilationUnit compilationUnit = StaticJavaParser.parse(fixCodeStr);
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = compilationUnit.findFirst(ClassOrInterfaceDeclaration.class).orElse(null);
        needAddStatement = classOrInterfaceDeclaration.getMethods().get(0).getBody().get().getStatements();
    }

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
        System.out.println(Arrays.toString(args));

        String currentPath = System.getProperty("user.dir");
        System.out.println("ProtoEnumCusCompiler currentPath:" + currentPath);
        // 获取当前path
//        String projectRootPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
//        System.out.println("ProtoEnumCusCompiler projectRootPath:" + projectRootPath);
        String codePathRelease = currentPath + "/build/generated/source/proto/release";
        String codePathDebug = currentPath + "/build/generated/source/proto/debug";
        System.out.println("ProtoEnumCusCompiler codePath:" + codePathDebug);
        System.out.println("ProtoEnumCusCompiler codePath:" + codePathRelease);

        // 递归找到这个文件下面所有的.java文件
        File codeFileRootRelease = new File(codePathRelease);
        File codeFileRootDebug = new File(codePathDebug);
        ArrayList<File> pbFiles = new ArrayList<File>();
        findAllPb(pbFiles, codeFileRootDebug);
        findAllPb(pbFiles, codeFileRootRelease);
        for (File pbFile : pbFiles) {
            String absName = pbFile.getAbsoluteFile().getPath();
            if (action.equals("replace")) {
                System.out.println("ProtoEnumCusCompiler replace pbFile:" + absName);
                setPbEnumToInt(absName);
            }
        }
    }

    public static void setPbEnumToInt(String pbFileName) {
        String compilerPath = FileUtils.getCompilerPath(pbFileName);
        try {
            DynamicCompiler.compile(pbFileName, compilerPath);
            Map<String, Map<String, Method>> wellRemoveMethodClazzs = new HashMap();

            Path path = Paths.get(pbFileName);
            CompilationUnit outCu = StaticJavaParser.parse(path);

            URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(compilerPath).toURI().toURL()});
            List<String> allClassname = ClassNameExtractor.extractFullClassNames(outCu);
            for (String clazzName : allClassname) {
                try {
                    Class clazz = classLoader.loadClass(clazzName);
                    // 枚举内部的方法跳过
                    if (clazz.isEnum()) {
                        continue;
                    }
                    for (Method method : clazz.getMethods()) {
                        // 将返回值是枚举类型的方法并且是getXXXEnum方法 改变返回值为int
                        if (method.getReturnType().isEnum() && method.getName().startsWith("get")) {
                            // 如果 实现的是 com.google.protobuf.ProtocolMessageEnum 才是枚举
                            // 如果 实现的是 com.google.protobuf.Internal.EnumLite 就是oneOf生成的 XXXCase ,这种不处理
                            boolean needJump = false;
                            for (Class<?> anInterface : method.getReturnType().getInterfaces()) {
                                if(enumLiteClass.getName().equals(anInterface.getName())){
                                    needJump = true;
                                    break;
                                }
                            }
                            if (needJump){
                                continue;
                            }
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

                // 遍历找出 返回枚举的方法改成返回 int
                setReturnTypeToInt(wellRemoveMethodClazzs, outCu);
                // 这里是需要修改描述文件里面 对字段描述类型为 enum 的 改成 int32
                changeFiedEnumTypeToInt(outCu);
                // 覆盖写入文件
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

    private static void changeFiedEnumTypeToInt(CompilationUnit outCu) {
        ClassOrInterfaceDeclaration outerClass = outCu.findFirst(ClassOrInterfaceDeclaration.class).orElse(null);
        if (outerClass != null) {
            // 查找最外层类的static代码块
            outerClass.getChildNodes().stream().filter(node -> node instanceof InitializerDeclaration).map(node -> (InitializerDeclaration) node).filter(InitializerDeclaration::isStatic).findFirst().ifPresent(staticBlock -> {
                if (needAddStatement != null && staticBlock.getBody() != null && staticBlock.getBody().getStatements() != null) {
                    // 添加到倒数第二行
                    int insertIndex = 0;
                    for (int i = 0; i < staticBlock.getBody().getStatements().size(); i++) {
                        Statement statement = staticBlock.getBody().getStatements().get(i);
                        if (statement.toString().trim().startsWith("com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom")) {
                            insertIndex = i + 1;
                        }
                    }
                    staticBlock.getBody().getStatements().addAll(insertIndex, needAddStatement);

                }
            });
            outCu.addImport("com.google.protobuf.Descriptors");
            outCu.addImport("java.lang.reflect.Field");


        }
    }

    private static void setReturnTypeToInt(Map<String, Map<String, Method>> wellRemoveMethodClazzs, CompilationUnit outCu) {
//        Map<String,Boolean> enumIsOneOf = new HashMap<>();
        for (ClassOrInterfaceDeclaration classOrInterface : outCu.findAll(ClassOrInterfaceDeclaration.class)) {
            String fullClassName = ClassNameExtractor.getFullNameWithJavaParser(classOrInterface);
            if (wellRemoveMethodClazzs.containsKey(fullClassName)) {
                //这个类有被需要被移除的方法才处理
                Map<String, Method> wellRemoveMethods = wellRemoveMethodClazzs.get(fullClassName);
                for (MethodDeclaration method : classOrInterface.getMethods()) {
                    if (wellRemoveMethods.containsKey(method.getNameAsString())) {
                        // 被处理的枚举
                        Type currentEnumType = method.getType();
                        String currentEnumTypeName = method.getType().asString();

                        // 获取变量名，例如 setXxxx 那么变量名就是 xxxx    这里获取的是首字母大写！！！！！
                        // 注意这里proto3 默认会把变量名转成首字母小写，所以不用考虑原变量名为大写的情况，全是首字母小写
                        String upperFiledName = method.getNameAsString().substring(3);
                        String lowerFiledName = upperFiledName.substring(0, 1).toLowerCase();
                        if (upperFiledName.length() > 1) {
                            lowerFiledName = lowerFiledName + upperFiledName.substring(1);
                        }
                        // 将返回枚举的方法。变成返回int
                        method.setType(int.class);
                        BlockStmt oldBlockStmt = method.getBody().orElse(null);
                        if (oldBlockStmt != null && oldBlockStmt.getStatements() != null && oldBlockStmt.getStatements().size() > 0) {
                            method.removeBody();
                            Statement statement = StaticJavaParser.parseStatement("return " + lowerFiledName + "_;");
                            BlockStmt blockStmt = new BlockStmt();
                            blockStmt.addStatement(statement);
                            method.setBody(blockStmt);
                            String oldCommentStr = "";
                            // 处理注释
                            Comment oldComment = method.getComment().orElse(null);
                            if (oldComment != null) {
                                oldCommentStr = oldComment.toString()
                                        .replaceAll("/\\*\\*", "")
                                        .replaceAll("/\\*", "")
                                        .replaceAll("\\*", "")
                                        .replaceAll("/", "")
                                        .replaceAll("\\n", "")
                                        .replaceAll("\\*/", "");
                            }
                            method.setBlockComment("* \n\t\t* " + oldCommentStr + " \n\t\t* old return type is " + currentEnumTypeName + " now change return type to int \n\t\t");
                        }

//                         为build方法添加setEnum(int value) 方法。因为get方法返回值改成了 int
                        if (fullClassName.endsWith("$Builder")) {
                            String finalLowerFiledName = lowerFiledName;
                            method.findAncestor(ClassOrInterfaceDeclaration.class).ifPresent(classOrInterfaceDeclaration -> {
                                // 将变量名首字母小写
                                // 为该类添加新方法
                                MethodDeclaration newMethod = classOrInterfaceDeclaration.addMethod("set" + upperFiledName, Modifier.Keyword.PUBLIC);
                                newMethod.setType("Builder");
                                newMethod.addParameter(int.class, "value");
                                String setEnumFiledMethod = "{\n" + finalLowerFiledName + "_ = value;\n" + "                onChanged();\n" + "                return this;" + "\n}";
                                newMethod.setBody(StaticJavaParser.parseBlock(setEnumFiledMethod));
                                newMethod.setBlockComment("为了兼容 将getEnum方法返回的enum的类型 改成了int，所以需要在build方法中添加对应的set方法");
                            });
                        }
                    }
                    //
                }
            }
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
