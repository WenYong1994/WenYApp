package com.whenyoung.protoenumopt.utils;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 类名：ClassNameExtractor
 * 包名：com.whenyoung.protoenumopt.utils
 * 创建时间：2024/6/3 14:53
 * 创建人： WhenYoung
 * 描述：
 **/
public class ClassNameExtractor {


    public static List<String> extractFullClassNames(CompilationUnit cu) throws IOException, ParseException {

        List<String> fullClassNames = new ArrayList<>();
        VoidVisitorAdapter<List<String>> classNameCollector = new VoidVisitorAdapter<List<String>>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, List<String> arg) {
                super.visit(n, arg);
                arg.add(getFullClassName(n));
            }

            @Override
            public void visit(EnumDeclaration n, List<String> arg) {
                super.visit(n, arg);
                arg.add(getFullClassName(n));

            }

            private String getFullClassName(TypeDeclaration<?> n) {
                String packageName = cu.getPackageDeclaration()
                        .map(PackageDeclaration::getNameAsString)
                        .orElse("");
                String oriClassName = n.getFullyQualifiedName()
                        .orElse(n.getNameAsString());
                oriClassName = oriClassName.replace(".", "$");
                if (!packageName.isEmpty()) {
                    oriClassName = packageName + "." + oriClassName.substring(packageName.length() + 1);
                }
                return oriClassName;
            }
        };

        classNameCollector.visit(cu, fullClassNames);

        return fullClassNames;
    }


    public static String getFullNameWithJavaParser(ClassOrInterfaceDeclaration classOrInterface){
        CompilationUnit cu = classOrInterface.findAncestor(CompilationUnit.class).orElse(null);
        String packageName = cu != null && cu.getPackageDeclaration().isPresent() ? cu.getPackageDeclaration().get().getNameAsString() : "";

        // 遍历所有嵌套的父类或接口
        Stack<String> classNames = new Stack<>();
        classNames.push(classOrInterface.getNameAsString());
        while (classOrInterface.getParentNode().isPresent() && classOrInterface.getParentNode().get() instanceof ClassOrInterfaceDeclaration) {
            classOrInterface = (ClassOrInterfaceDeclaration) classOrInterface.getParentNode().get();
            classNames.push(classOrInterface.getNameAsString());
        }

        // 拼接包名和类名
        StringBuilder fullClassName = new StringBuilder(packageName);
        if (!packageName.isEmpty()) {
            fullClassName.append(".");
        }
        while (!classNames.isEmpty()) {
            fullClassName.append(classNames.pop());
            if (!classNames.isEmpty()) {
                fullClassName.append("$");
            }
        }
        return fullClassName.toString();
    }




}