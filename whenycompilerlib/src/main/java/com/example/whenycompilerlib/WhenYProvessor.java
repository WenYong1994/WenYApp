package com.example.whenycompilerlib;

import com.example.whenyannotationlib.AnnotationConstant;
import com.example.whenyannotationlib.InjectViewModel;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;


import java.io.IOException;
import java.util.ArrayList;
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
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

@AutoService(Processor.class)//当前注解处理器能够处理的注解 代替 getSupportedAnnotationTypes函数
@SupportedSourceVersion(SourceVersion.RELEASE_8)//java版本 代替 getSupportedAnnotationTypes 函数
public class WhenYProvessor extends AbstractProcessor {



    private Messager messager;
    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        messager.printMessage(Diagnostic.Kind.WARNING, "WhenYProvessor----init-------------------------------------------------------------!");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //支持的注解也可以用@SupportedAnnotationTypes({"com.hubin.librouter.Route"})代替
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(InjectViewModel.class.getCanonicalName());
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
        messager.printMessage(Diagnostic.Kind.WARNING,"WhenYProvessor----process,start process");
        //获取所有使用SimpleBindView的集合
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(InjectViewModel.class);
        //key是activity的名字
        //value是这个activity里面所有使用SimpleBindView注解的集合
        Map<String, List<VariableElement>> elementMap = handleElementIntoMap(elementSet);
        //开始准备写XXActivity$ViewBinder内部类的java文件
        Iterator<String> iterator = elementMap.keySet().iterator();
        while (iterator.hasNext()) {
            String activityName = iterator.next();
            //获取activity的所有元素
            List<VariableElement> variableElements = elementMap.get(activityName);
            //获取包名
            String packageName = getPackageName(variableElements.get(0));
            //获取我们要写的内部类java文件名
            TypeElement typeElement = (TypeElement) variableElements.get(0).getEnclosingElement();
            String newActivityName = typeElement.getSimpleName().toString()+ AnnotationConstant.VIEW_MODEL_SUFFIX;
            Name qualifiedName = typeElement.getQualifiedName();
            messager.printMessage(Diagnostic.Kind.WARNING,"newActivityName-------"+newActivityName);
            //用流来写内部类的文件
            generateJavaCode (qualifiedName.toString(),newActivityName, variableElements);
        }
        return true;
    }

    private void generateJavaCode(String outClassName,String newActivityName, List<VariableElement> variableElements) {
        ClassName nameAppCompatActivity = ClassName.get("androidx.appcompat.app", "AppCompatActivity");
        ClassName nameRealActivity = ClassName.get(getPackNameByClassName(outClassName), getSimpleClassByClassName(outClassName));
        ClassName nameViewDataBinding = ClassName.get("androidx.databinding", "ViewDataBinding");
        ClassName nameViewModel = ClassName.get("androidx.lifecycle","ViewModel");
        ClassName nameViewModelProviders = ClassName.get("androidx.lifecycle","ViewModelProviders");
        ClassName nameField = ClassName.get("java.lang.reflect","Field");
        ClassName nameBR = ClassName.get("androidx.databinding.library.baseAdapters","BR");
        ClassName nameInjectViewModel = ClassName.get("com.example.whenyannotationlib","InjectViewModel");
        ClassName nameViewModelInjectorImp = ClassName.get("com.example.commonlibrary.annotation_api","ViewModelInjectorImp");
        TypeVariableName Type = TypeVariableName.get("T",nameViewModel);
        TypeVariableName mapType = TypeVariableName.get("HashMap<String,Field>");

        //创建一个变量 todo 反射可以优化一下。通过缓存的方式
        FieldSpec mapBuild = FieldSpec.builder(mapType,"fieldMap",Modifier.PUBLIC,Modifier.STATIC, Modifier.FINAL)
                .initializer("new $T<String,$T>()",HashMap.class,nameField)
                .build();



        //创建一个实例化 viewModel的方法
        MethodSpec.Builder initBulid = MethodSpec.methodBuilder("initViewModel")
                .addModifiers(Modifier.PUBLIC) //public static
                .returns(Type)  //返回值  Type
                .addTypeVariable(Type)
                .addParameter(nameAppCompatActivity,"activity") //添加参数 AppCompatActivity activity
                .addParameter(Class.class,"clazz")//添加参数 Class clazz
                .addStatement("$T.out.println(activity.getClass().getName())", System.class) //添加内容： System.out.println("Hello, JavaPoet!");
                .addStatement("T viewModel= (T) $T.of(activity).get(clazz)",nameViewModelProviders)
                .addStatement("return viewModel");

        //创建一个针对每个变量
        MethodSpec.Builder handBulid = MethodSpec.methodBuilder("handViewModel")
                .addModifiers(Modifier.PRIVATE) //public static
                .returns(Type)  //返回值  Type
                .addTypeVariable(Type)
                .addParameter(nameRealActivity,"activity") //添加参数 AppCompatActivity activity
                .addParameter(nameViewDataBinding,"viewDataBinding")//添加参数 ViewDataBinding viewDataBinding
                .addParameter(String.class,"outFieldName")//添加参数 String  fieldName
                .addParameter(String.class,"fieldName")//添加参数 String  fieldName
                .addParameter(Integer.class,"variableId")//添加参数 String  fieldName
                .addParameter(Class.class,"clazz")//添加参数 Class clazz
                .addCode("\n")
                .addCode(
                        "\n" +
                        "   T viewModel = initViewModel(activity,clazz);\n" +
                        "\n" +
                        "   viewDataBinding.setVariable(variableId,viewModel);\n" +
                        "   return viewModel;\n"
                        ,nameInjectViewModel,nameInjectViewModel,nameInjectViewModel,nameBR,nameBR)
                .addCode("\n")
                ;

        //创建一个初始化方法
        MethodSpec.Builder injectBulid = MethodSpec.methodBuilder("injectViewModels")
                .addModifiers(Modifier.PUBLIC) //public static
                .returns(void.class)  //返回值  Type
                .addParameter(nameAppCompatActivity,"activity") //添加参数 AppCompatActivity activity
                .addParameter(nameViewDataBinding,"viewDataBinding");//添加参数 ViewDataBinding viewDataBinding
        messager.printMessage(Diagnostic.Kind.WARNING,"variableElementsSize-------"+variableElements.size());

        for(VariableElement variableElement:variableElements){
            String simpleName = variableElement.getSimpleName().toString();
            InjectViewModel annotation = variableElement.getAnnotation(InjectViewModel.class);
            String fieldName = annotation.name();
            String name = variableElement.asType().toString();
            String className = name;
            messager.printMessage(Diagnostic.Kind.WARNING,"className++++++++++++++++++++++++++++++++"+className);
            String outFileName = simpleName;
            ClassName nameVm = ClassName.get(getPackNameByClassName(className),getSimpleClassByClassName(className));
            injectBulid.addStatement(getSimpleClassByClassName(outClassName)+" realActivity = ("+getSimpleClassByClassName(outClassName)+") activity");
            injectBulid.addStatement("int variableId = $T."+fieldName,nameBR);
            injectBulid.addStatement("ViewModel viewModel = handViewModel(realActivity,viewDataBinding,\""+outFileName+"\",\""+fieldName+"\","+"variableId,$T.class)",nameVm);
            injectBulid.addStatement("realActivity.set"+upCasuFirstChar(outFileName)+"((LoginVm) viewModel)");
        }



        //创建一个类
        TypeSpec viewModelInjector = TypeSpec.classBuilder(newActivityName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addSuperinterface(nameViewModelInjectorImp)
                .addField(mapBuild)
                .addMethod(initBulid.build())
                .addMethod(handBulid.build())
                .addMethod(injectBulid.build())
                .build();



        //将类添加到 包中
        JavaFile javaFile = JavaFile.builder(AnnotationConstant.VIEW_MODEL_PACKNAME, viewModelInjector)
                .build();

        try {
            javaFile.writeTo(filer); //执行写入
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把所有的Element存放到map里
     */
    private Map<String, List<VariableElement>> handleElementIntoMap(Set<? extends Element> elementSet) {
        Map<String, List<VariableElement>> elementMap = new HashMap<>();
        for (Element element : elementSet) {
            VariableElement variableElement = (VariableElement) element;
            //获取activity,Fragment，dialog的名字
            String activityName = getActivityName(variableElement);
            //通过activity名字，获取集合
            List<VariableElement> elementList = elementMap.get(activityName);
            if (elementList == null) {
                elementList = new ArrayList<>();
                elementMap.put(activityName, elementList);
            }
            elementList.add(variableElement);
        }
        return elementMap;
    }
    /**
     * 通过VariableElement获取包名
     */
    private String getPackageName(VariableElement element) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        return processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
    }

    private String upCasuFirstChar(String s){
        if(s.length()>0){
            char[] chars = s.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return String.valueOf(chars);
        }
        return s;
    }

    private String getPackNameByClassName(String className){
        String substring = className.substring(0, className.lastIndexOf("."));
        return substring;
    }

    private String getSimpleClassByClassName(String className){
        String substring = className.substring(className.lastIndexOf(".")+1);
        return substring;
    }


    /**
     * 通过VariableElement获取所在的activity名字
     */
    private String getActivityName(VariableElement element) {
        String packageName = getPackageName(element);
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        return packageName + "." + typeElement.getSimpleName().toString();
    }

}
