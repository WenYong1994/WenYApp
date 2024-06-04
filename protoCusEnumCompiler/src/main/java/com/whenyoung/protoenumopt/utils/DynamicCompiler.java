package com.whenyoung.protoenumopt.utils;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class DynamicCompiler {

    public static void compile(String sourceFilePath, String outputDirectoryPath) {
        File sourceFile = new File(sourceFilePath);
        File outputDirectory = new File(outputDirectoryPath);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        try {
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(outputDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);

        task.call();

        try {
            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}