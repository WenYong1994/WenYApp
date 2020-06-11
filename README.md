# WenYApp。一款MVVM的极简框架

## 怎么使用

在项目build.gradle文件中添加
```
allprojects {
    repositories {
      
        
        maven { url 'https://jitpack.io' }
    }
}

```
依赖所有模块
如果使用了kotlin项目，必须添加kapt 'com.github.WenYong1994.WenYApp:wheny_compiler_lib:1.1.0'	

```
dependencies {
	
	...
	
	implementation 'com.github.WenYong1994:WenYApp:1.1.0'
    annotationProcessor 'com.github.WenYong1994.WenYApp:wheny_compiler_lib:1.1.0'
	kapt 'com.github.WenYong1994.WenYApp:wheny_compiler_lib:1.1.0'	
	
	...

		
	//需要正常使用的第三方依赖	
		
	//添加lifeCycler
    implementation group: 'androidx.lifecycle', name: 'lifecycle-common-java8', version: '2.2.0'
    implementation "androidx.lifecycle:lifecycle-extensions:2.1.0"
    //添加ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0"
    //添加recyclerView
    implementation "com.android.support:recyclerview-v7:${recyclerview_libary_version}"
    //添加网络okhttp3 和RxJava
    implementation "com.squareup.okhttp3:okhttp:4.2.0"
    implementation "io.reactivex.rxjava2:rxjava:2.1.0" // 必要rxjava2依赖
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    implementation "io.reactivex.rxjava2:rxandroid:2.0.1" // 必要rxandrroid依赖，切线程时需要用到
    implementation 'com.squareup.retrofit2:retrofit:2.3.0' // 必要retrofit依赖
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0' // 必要依赖，解析json字符所用
    implementation 'com.squareup.okhttp3:logging-interceptor:3.8.1' //非必要依赖， log依赖，如果需要打印OkHttpLog需要添加
	//
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.3.0' // 必要依赖，和Rxjava结合必须用到，下面会提到	
	
}

```


###注意

如果想使用某一个部分功能请看下面的注释
```
{
	implementation 'com.github.WenYong1994:WenYApp:1.1.0'//依赖所有模块
//    implementation 'com.github.WenYong1994.WenYApp:whenynetlibrary:1.1.4'//网络相关模块
//
//    implementation 'com.github.WenYong1994.WenYApp:wheny_annotation_apilib:1.1.4'//MVVM相关注解初始化
//    implementation 'com.github.WenYong1994.WenYApp:wheny_annotation_lib:1.1.4'//MVVM相关注解初始化
//
//    implementation 'com.github.WenYong1994.WenYApp:whenydblibary:1.1.4'//数据库相关模块
//
//    implementation 'com.github.WenYong1994.WenYApp:whenylibrary:1.1.4'//工具类相关模块

	annotationProcessor 'com.github.WenYong1994.WenYApp:wheny_compiler_lib:1.1.0'//如果使用到MVVM注解初始化 必须添加	
}
```


MVVM初始化示例

```
class MainActivity : AppCompatActivity() {
	...
	@InjectViewModel(dataBindFieldName = "TestVm")
    var mTestVm:TestMainVm?=null
	...
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
		...
		ViewModelInjector.inject<ActivityMainBinding>(this,R.layout.activity_main)
		
		mTestVm?.txt?.postValue("测试数据绑定")
		...
	}

}



```



RxJava2+Retrofit2示例

```

class TestMainVm : BaseViewModel() {

    var txt = MutableLiveData<String>("2423")


    fun testClick(){
        var json = "{\"channel\":\"android\",\"data\":\"{\\\"password\\\":\\\"123456\\\",\\\"os\\\":\\\"android\\\",\\\"uid\\\":0,\\\"username\\\":\\\"kred001\\\"}\",\"salt\":\"064797\",\"service\":\"functionaryLoginService\",\"test\":true,\"time\":1590024509,\"version\":\"1.0\"}"

        val requestBody = json.toRequestBody("application/json; charset=utf-8;".toMediaTypeOrNull())
        val compose = RetrofitServiceManager.getInstance().create(ApiService::class.java)
            .login(requestBody)
            .flatMap {
                SystemClock.sleep(2000)
                Flowable.just(it.data.toString())
            }
            .compose(RxSchedulers.io_main())
            .subscribe({
                Log.e("doLogin","doLogindoLogindoLogindoLogindoLogindoLogindoLogin")

            },{
                Log.e("doLogin","error")
            })
        addDisposable(compose)
    }

}


```

万能Adapter示例

```
adapter.addItemViewDelegate(object : SimpleItemViewDelegate<String, TestListLayoutBinding>() {

                override fun getItemViewLayoutId(): Int {
                    return R.layout.test_list_layout
                }

                override fun injectViewModel(viewDataBinding: TestListLayoutBinding?) {
                    viewDataBinding?.testVm = TestVm()
                }

                override fun convert(holder: ViewHolder<TestListLayoutBinding>?, t: String?, position: Int) {
                    holder?.viewDataBinding?.testVm?.txt1 = t.toString()
//                    holder?.setText(R.id.txt,t)//或者
                }

                override fun isForViewType(item: String?, position: Int): Boolean {
                    return true
                }
            })
        rv_rlv.adapter = adapter
        rv_rlv.layoutManager = SafeLinearLayoutManager(this,SafeLinearLayoutManager.VERTICAL,false)
```


[更多使用方式请看详情文档](https://www.jianshu.com/p/1ad7663bc966)



























