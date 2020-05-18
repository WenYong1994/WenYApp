package com.example.whenycompilerlib;

import com.example.whenyannotationlib.Route;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)//当前注解处理器能够处理的注解 代替 getSupportedAnnotationTypes函数
@SupportedSourceVersion(SourceVersion.RELEASE_8)//java版本 代替 getSupportedAnnotationTypes 函数
public class HubinProvessor extends AbstractProcessor {

    private Messager messager;
    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        messager.printMessage(Diagnostic.Kind.WARNING, "HubinProvessor----init-------------------------------------------------------------!");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //支持的注解也可以用@SupportedAnnotationTypes({"com.hubin.librouter.Route"})代替
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Route.class.getCanonicalName());
        return annotations;
    }


    /**
     * 注解处理
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.WARNING,"HubinProvessor----process,start process");
        for (TypeElement typeElement : set) {
            messager.printMessage(Diagnostic.Kind.WARNING,"HubinProvessor----process");
            //创建一个main函数
            MethodSpec main = MethodSpec.methodBuilder("main")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC) //public static
                    .returns(void.class)  //返回值  voild
                    .addParameter(String[].class, "args") //添加参数 String[] arrgs
                    .addStatement("$T.out.println($S)", System.class, "Hello, hubin!") //添加内容： System.out.println("Hello, JavaPoet!");
                    .build();

            //创建一个类 HelloWorld
            TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addMethod(main) //将main函数添加到类中
                    .build();

            //将helloWorld类添加到 包com.hubin.helloworld中
            JavaFile javaFile = JavaFile.builder("com.hubin.helloworld", helloWorld)
                    .build();

            try {
                javaFile.writeTo(filer); //执行写入
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

}
