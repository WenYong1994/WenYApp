package com.example.whenycompilerlib;

import com.example.whenyannotationlib.AnnotationConstant;
import com.example.whenyannotationlib.InjectViewModel;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
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

    private static String AppCompatActivityName = "androidx.appcompat.app.AppCompatActivity";

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
        if(elementSet.size()>0){
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
        return false;
    }

    private void generateJavaCode(String outClassName,String newActivityName, List<VariableElement> variableElements) {
        ClassName nameAppCompatActivity = ClassName.get("androidx.appcompat.app", "AppCompatActivity");
        ClassName nameRealTag = ClassName.get(getPackNameByClassName(outClassName), getSimpleClassByClassName(outClassName));
        ClassName nameViewDataBinding = ClassName.get("androidx.databinding", "ViewDataBinding");
        ClassName nameViewModel = ClassName.get("androidx.lifecycle","ViewModel");
        ClassName nameViewModelProviders = ClassName.get("androidx.lifecycle","ViewModelProviders");
        ClassName nameBR = ClassName.get("androidx.databinding.library.baseAdapters","BR");
        ClassName nameInjectViewModel = ClassName.get("com.example.whenyannotationlib","InjectViewModel");
        ClassName nameViewModelInjectorApi = ClassName.get("com.example.whenyannotationapilib","ViewModelInjectorApi");
        ClassName nameDataBindingUtils = ClassName.get("androidx.databinding","DataBindingUtil");
        TypeVariableName Type = TypeVariableName.get("T",nameViewModel);
        TypeVariableName TypeViewDataBinding = TypeVariableName.get("VDB",nameViewDataBinding);
        ClassName nameViewModelFactory = ClassName.get("androidx.lifecycle","ViewModelProvider","NewInstanceFactory");
        ClassName nameDefaultLifecycleObserver = ClassName.get("androidx.lifecycle","DefaultLifecycleObserver");
        ClassName nameLayoutInflater = ClassName.get("android.view","LayoutInflater");
        ClassName nameViewGoup = ClassName.get("android.view","ViewGroup");
        ClassName baseViewModel = ClassName.get("com.example.commonlibrary.mvvm.vm","BaseViewModel");
        ClassName baseAndroidViewModel = ClassName.get("com.example.commonlibrary.mvvm.vm","BaseAndroidViewModel");
        ClassName vmContract = ClassName.get("com.example.commonlibrary.mvvm.contract","VmContract");




        //创建一个实例化 viewModel的方法
        MethodSpec.Builder initBulid = MethodSpec.methodBuilder("initViewModel")
                .addModifiers(Modifier.PUBLIC) //public static
                .returns(Type)  //返回值  Type
                .addTypeVariable(Type)
                .addParameter(nameAppCompatActivity,"activity") //添加参数 AppCompatActivity activity
                .addParameter(Class.class,"clazz")//添加参数 Class clazz
                .addParameter(boolean.class,"needFactory")//添加参数 Class clazz
                .addStatement("$T.out.println(activity.getClass().getName())", System.class) //添加内容： System.out.println("Hello, JavaPoet!");
                .addCode(
                        "if(!needFactory){\n" +
                        "   T viewModel= (T) $T.of(activity).get(clazz);\n" +
                        "   return viewModel;\n" +
                        "}else {\n" +
                        "   return null;\n" +
                        "}",nameViewModelProviders);

        //创建一个针对每个变量
        MethodSpec.Builder handBulid = MethodSpec.methodBuilder("handViewModel")
                .addModifiers(Modifier.PRIVATE) //public static
                .returns(Type)  //返回值  Type
                .addTypeVariable(Type)
                .addParameter(nameAppCompatActivity,"activity") //添加参数 AppCompatActivity activity
                .addParameter(nameViewDataBinding,"viewDataBinding")//添加参数 ViewDataBinding viewDataBinding
                .addParameter(String.class,"outFieldName")//添加参数 String  fieldName
                .addParameter(String.class,"fieldName")//添加参数 String  fieldName
                .addParameter(Integer.class,"variableId")//添加参数 String  fieldName
                .addParameter(Class.class,"clazz")//添加参数 Class clazz
                .addParameter(boolean.class,"needFactory")//添加参数 Class clazz
                .addCode("\n")
                .addCode(
                        "\n" +
                        "   T viewModel = initViewModel(activity,clazz,needFactory);\n" +
                        "\n" +
                        "   if(viewModel instanceof $T){\n" +
                        "          (($T)viewModel).setContract(($T)activity);\n" +
                        "   }\n" +
                        "   if(viewModel instanceof $T){\n" +
                        "          (($T)viewModel).setContract(($T)activity);\n" +
                        "   }\n" +
                        "   if(viewDataBinding!=null) viewDataBinding.setVariable(variableId,viewModel);\n" +
                        "   return viewModel;\n"
                        ,baseViewModel,baseViewModel,vmContract,baseAndroidViewModel,baseAndroidViewModel,vmContract)
                .addCode("\n",nameInjectViewModel,nameInjectViewModel,nameInjectViewModel,nameBR,nameBR);


        //创建一个初始化方法,用在fragment 和自定义View 中的
        MethodSpec.Builder injectBulid = MethodSpec.methodBuilder("injectViewModels")
                .addModifiers(Modifier.PUBLIC) //public static
                .addTypeVariable(TypeViewDataBinding)
                .returns(TypeViewDataBinding)  //返回值  Type
                .addAnnotation(Override.class)
                .addParameter(Object.class,"tag") //添加参数 Object tag
                .addParameter(nameAppCompatActivity,"activity") //添加参数 AppCompatActivity activity
                .addParameter(nameLayoutInflater,"inflater") //添加参数 LayoutInflater inflater
                .addParameter(int.class,"layoutId")//添加参数 ViewDataBinding viewDataBinding
                .addParameter(nameViewGoup,"viewGroup"); //添加参数 ViewGroup viewGroup
        messager.printMessage(Diagnostic.Kind.WARNING,"variableElementsSize-------"+variableElements.size());
        injectBulid.addStatement("VDB viewDataBinding = null");



        //创建一个初始化方法用于fragment和自定义View中
        MethodSpec.Builder injectByFactory = MethodSpec.methodBuilder("injectViewModelByFactory")
                .addModifiers(Modifier.PUBLIC) //public static
                .returns(void.class)  //返回值  Type
                .addAnnotation(Override.class)
                .addParameter(Object.class,"tag") //添加参数 Object tag
                .addParameter(nameAppCompatActivity,"activity") //添加参数 AppCompatActivity activity
                .addParameter(nameViewModelFactory,"factory") //添加参数 ViewModelProvider.NewInstanceFactory factory
                .addParameter(String.class,"fieldName") //添加参数 ViewModelProvider.NewInstanceFactory factory
                .addParameter(nameViewDataBinding,"viewDataBinding"); //添加参数 ViewDataBinding viewDataBinding
        messager.printMessage(Diagnostic.Kind.WARNING,"variableElementsSize-------"+variableElements.size());


        for(int i=0;i<variableElements.size();i++){
            VariableElement variableElement = variableElements.get(i);
            String outFileName = variableElement.getSimpleName().toString();
            InjectViewModel annotation = variableElement.getAnnotation(InjectViewModel.class);
            String fieldName = annotation.dataBindFieldName();
            boolean needFactory = annotation.needFactory();
            String className = variableElement.asType().toString();
            messager.printMessage(Diagnostic.Kind.WARNING,"className++++++++++++++++++++++++++++++++"+className);
            className = removeGenericity(className);
            ClassName nameVm = ClassName.get(getPackNameByClassName(className),getSimpleClassByClassName(className));
            if(i==0){

                injectBulid.addCode(
                        "if(inflater!=null){\n" +
                        "   viewDataBinding = $T.inflate(inflater,layoutId,viewGroup,false);\n" +
                        "}else {\n" +
                        "   viewDataBinding = $T.setContentView(activity,layoutId);\n" +
                        "}\n",nameDataBindingUtils,nameDataBindingUtils);
                injectBulid.addStatement("if(viewDataBinding!=null) viewDataBinding.setLifecycleOwner(activity)");
                injectBulid.addStatement("$T realTag = ($T) tag",nameRealTag,nameRealTag);

                injectByFactory.addStatement("$T realTag = ($T) tag",nameRealTag,nameRealTag);
            }
            String format = "if(viewModel" + fieldName + i + "!=null && viewModel" + fieldName + i + " instanceof $T){\n" +
                    "   activity.getLifecycle().addObserver(($T)viewModel" + fieldName + i + ");\n" +
                    "\n" +
                    "   if(viewModel"+ fieldName + i + " instanceof $T){\n" +
                    "          (($T)viewModel"+fieldName + i +").setContract(($T)activity);\n" +
                    "   }\n" +
                    "   if(viewModel"+ fieldName + i + " instanceof $T){\n" +
                    "          (($T)viewModel"+fieldName + i +").setContract(($T)activity);\n" +
                    "   }\n" +
                    "}\n";

            if(!needFactory){
                if(fieldName.length()>0){

                    injectBulid.addStatement("int variableId"+fieldName+" = $T."+fieldName,nameBR);
                    injectBulid.addStatement("ViewModel viewModel"+fieldName+i+"= handViewModel(activity,viewDataBinding,\""+outFileName+"\",\""+fieldName+"\","+"variableId"+fieldName+",$T.class,"+needFactory+")",nameVm);

                }else {

                    injectBulid.addStatement("ViewModel viewModel"+fieldName+i+"= handViewModel(activity,viewDataBinding,\""+outFileName+"\",\""+fieldName+"\","+"-1,$T.class,"+needFactory+")",nameVm);
                }


                injectBulid.addCode(format,nameDefaultLifecycleObserver,nameDefaultLifecycleObserver,baseViewModel,baseViewModel,vmContract,baseAndroidViewModel,baseAndroidViewModel,vmContract);


                injectBulid.addStatement("realTag.set"+upCasuFirstChar(outFileName)+"(($T) viewModel"+fieldName+i+")",nameVm);

            }else {
                if(fieldName.length()>0){

                    injectByFactory.addStatement("int variableId"+fieldName+i+" = $T."+fieldName,nameBR);
                }


                injectByFactory.addCode("if(fieldName == \""+outFileName+"\"){\n");
                injectByFactory.addStatement("     ViewModel viewModel"+fieldName+i+" = factory.create($T.class)",nameVm);
                injectByFactory.addCode(format,nameDefaultLifecycleObserver,nameDefaultLifecycleObserver,baseViewModel,baseViewModel,vmContract,baseAndroidViewModel,baseAndroidViewModel,vmContract);

                if(fieldName.length()>0){

                    injectByFactory.addStatement("     if(viewDataBinding!=null) viewDataBinding.setVariable(variableId"+fieldName+i+", viewModel"+fieldName+i+");");
                }else {

                    injectByFactory.addStatement("     if(viewDataBinding!=null) viewDataBinding.setVariable(-1, viewModel"+fieldName+i+");");
                }

                injectByFactory.addStatement("     realTag.set"+upCasuFirstChar(outFileName)+"(($T) viewModel"+fieldName+i+")",nameVm);
                injectByFactory.addCode("}");
            }
        }

        injectBulid.addStatement("return viewDataBinding");



        //创建一个类
        TypeSpec.Builder viewModelInjector = TypeSpec.classBuilder(newActivityName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addSuperinterface(nameViewModelInjectorApi)
                .addMethod(initBulid.build())
                .addMethod(handBulid.build())
                .addMethod(injectBulid.build())
                .addMethod(injectByFactory.build());



        //将类添加到 包中
        JavaFile javaFile = JavaFile.builder(AnnotationConstant.VIEW_MODEL_PACKNAME, viewModelInjector.build())
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

    private String removeGenericity(String className){
        int i = className.indexOf("<");
        if(i>0){
            String substring = className.substring(0,i);
            return substring;
        }
        return className;
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
