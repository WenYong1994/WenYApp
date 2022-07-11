package com.wheny.whenycompilerlib;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.tools.javac.code.Symbol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

import jdk.nashorn.internal.parser.JSONParser;

/**
 * 类名：RouterProvessor
 * 包名：com.wheny.whenycompilerlib
 * 创建时间：2022/6/27 16:16
 * 创建人： WhenYoung
 * 描述：
 **/
@AutoService(Processor.class)//当前注解处理器能够处理的注解 代替 getSupportedAnnotationTypes函数
@SupportedSourceVersion(SourceVersion.RELEASE_8)//java版本 代替 getSupportedAnnotationTypes 函数
public class RouterProcessor extends AbstractProcessor {

    private static String AppCompatActivityName = "androidx.appcompat.app.AppCompatActivity";
    public static final String VIEW_MODEL_SUFFIX = "_ViewModelInjector";
    public static final String VIEW_MODEL_PACKNAME = "com.wheny.viewModel";

    private Messager messager;
    Filer filer;
    public static final String testAnnClassName = "com.wheny.whenylibrary.TestAni";
    public static Class testAnnClassClass;
    public static final String routeClazzName = "com.chenenyu.router.annotation.Route";
    Class routeClazz;
    public static final String injectParamsName = "com.chenenyu.router.annotation.InjectParam";
    Class injectParamsClazz;
    public static final String activityName = "androidx.appcompat.app.AppCompatActivity";
    Class activityClass;
    public static final String fragmentName = "androidx.fragment.app.Fragment";
    Class fragmentClass;

    {
        try {
            routeClazz = Class.forName(routeClazzName);
            injectParamsClazz = Class.forName(injectParamsName);
            activityClass = Class.forName(activityName);
            fragmentClass = Class.forName(fragmentName);
            testAnnClassClass = Class.forName(testAnnClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Map<String, RouteNoteEntity> routeNoteEntities = new HashMap();


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        messager.printMessage(Diagnostic.Kind.WARNING, "RouterProcessor----init-------------------------------------------------------------!");

    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //支持的注解也可以用@SupportedAnnotationTypes({"com.hubin.librouter.Route"})代替
        Set<String> annotations = new LinkedHashSet<>();
        messager.printMessage(Diagnostic.Kind.NOTE, "RouterProcessor----" + routeClazzName + "-------------------------------------------------------------!");
        annotations.add(routeClazzName);
        messager.printMessage(Diagnostic.Kind.NOTE, "RouterProcessor----" + injectParamsName + "-------------------------------------------------------------!");
        annotations.add(injectParamsName);
        annotations.add(testAnnClassName);
        return annotations;
    }


    /**
     * 注解处理
     *
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.WARNING, "RouterProcessor1----process,start process");
        //获取所有使用SimpleBindView的集合
        Set<? extends Element> routeElementSet = roundEnvironment.getElementsAnnotatedWith(routeClazz);
        Set<? extends Element> hookSet = roundEnvironment.getElementsAnnotatedWith(testAnnClassClass);
        String moduleName = "moduleName";
        if (hookSet.size() > 0) {
            Symbol.ClassSymbol hookClassSymbol = (Symbol.ClassSymbol) hookSet.iterator().next();
            Annotation hookAnn = hookClassSymbol.getAnnotation(testAnnClassClass);
            try {
                Method valueMethods = hookAnn.getClass().getMethod("moduleName");
                moduleName = (String) valueMethods.invoke(hookAnn);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
            if (routeElementSet.size() > 0) {
                //key是activity的名字
                //value是这个activity里面所有使用SimpleBindView注解的集合
                Map<String, Symbol.ClassSymbol> elementMap = handleRouteElementIntoMap(routeElementSet);
                //开始准备写XXActivity$ViewBinder内部类的java文件
                Iterator<String> iterator = elementMap.keySet().iterator();
                while (iterator.hasNext()) {
                    String outClassName = iterator.next();
                    //获取activity的所有元素
                    Symbol.ClassSymbol classSymbol = elementMap.get(outClassName);
                    //获取包名
                    //获取我们要写的内部类java文件名
                    Annotation routeAnnotation = classSymbol.getAnnotation(routeClazz);
                    String[] routeValue = {};
                    String classType = "";
                    try {
                        Method valueMethods = routeAnnotation.getClass().getMethod("value");
                        routeValue = (String[]) valueMethods.invoke(routeAnnotation);
                        String className = classSymbol.getQualifiedName().toString();

                        if (CompilerProcessorUtils.instanceofClass(classSymbol, activityClass)) {
                            //是否为Activity
                            classType = "Activity";
                        } else if (CompilerProcessorUtils.instanceofClass(classSymbol, fragmentClass)) {
                            //是否为fragment
                            classType = "Fragment";
                        } else {
                            //未知
                        }


                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    RouteNoteEntity entity = new RouteNoteEntity();
                    entity.className = outClassName;
                    entity.routes = Arrays.asList(routeValue);
                    entity.classType = classType;
                    routeNoteEntities.put(outClassName, entity);
                }
                Set<? extends Element> paramsElementSet = roundEnvironment.getElementsAnnotatedWith(injectParamsClazz);
                if (paramsElementSet.size() > 0) {
                    Map<String, List<VariableElement>> paramsElementMap = handleParamsElementIntoMap(paramsElementSet);
                }
                String jsonStr = new GsonBuilder().setPrettyPrinting().create().toJson(routeNoteEntities);
                File file = new File("routeJson-"+moduleName+".json");
                FileWriter fileWriter = null;
                PrintWriter printWriter = null;
                try {
                    fileWriter = new FileWriter(file);
                    printWriter = new PrintWriter(fileWriter);
                    printWriter.println(jsonStr);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fileWriter != null) {
                            fileWriter.flush();
                            fileWriter.close();
                        }
                        if (printWriter != null) {
                            printWriter.flush();
                            printWriter.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return true;
            }
            return false;
        }

        /**
         * 把所有的Element存放到map里
         */
        private Map<String, Symbol.ClassSymbol> handleRouteElementIntoMap (Set < ? extends
        Element > elementSet){
            Map<String, Symbol.ClassSymbol> elementMap = new HashMap<>();
            for (Element element : elementSet) {
                Symbol.ClassSymbol symbol = (Symbol.ClassSymbol) element;
                //获取activity,Fragment，dialog的名字
                String className = CompilerProcessorUtils.getEnclosingClassName(processingEnv, symbol);
                //通过activity名字，获取集合
                elementMap.put(className, symbol);
            }
            return elementMap;
        }

        /**
         * 把所有的Element存放到map里
         */
        private Map<String, List<VariableElement>> handleParamsElementIntoMap (Set < ? extends
        Element > elementSet){
            Map<String, List<VariableElement>> elementMap = new HashMap<>();
            for (Element element : elementSet) {
                VariableElement variableElement = (VariableElement) element;
                //获取activity,Fragment，dialog的名字
                String className = CompilerProcessorUtils.getEnclosingClassName(processingEnv, variableElement);
                //通过activity名字，获取集合
                List<VariableElement> elementList = elementMap.get(className);
                if (elementList == null) {
                    elementList = new ArrayList<>();
                    elementMap.put(className, elementList);
                }
                //处理将数据放到集合里面去
                RouteNoteEntity entity = routeNoteEntities.get(className);
                if (entity == null) {
                    continue;
                }
                if (entity.params == null) {
                    entity.params = new ArrayList<>();
                }
                RouteNoteEntity.RouteParams routeParams = new RouteNoteEntity.RouteParams();
                entity.params.add(routeParams);
                routeParams.key = "";
//            routeParams.required = false;
                Annotation injectParamsAnnotation = variableElement.getAnnotation(injectParamsClazz);
                String keyValue = "";
//            boolean requiredValue = false;
                try {
                    Method keyMethods = injectParamsAnnotation.getClass().getMethod("key");
//                Method requiredMethods = injectParamsAnnotation.getClass().getMethod("required");
                    keyValue = (String) keyMethods.invoke(injectParamsAnnotation);
//                requiredValue = (boolean) requiredMethods.invoke(injectParamsAnnotation);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                routeParams.key = keyValue;
                routeParams.type = variableElement.asType().toString();
            }
            return elementMap;
        }

        private String upCasuFirstChar (String s){
            if (s.length() > 0) {
                char[] chars = s.toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);
                return String.valueOf(chars);
            }
            return s;
        }


    }
