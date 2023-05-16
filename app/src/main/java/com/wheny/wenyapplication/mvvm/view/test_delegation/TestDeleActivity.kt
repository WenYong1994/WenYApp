package com.wheny.wenyapplication.mvvm.view.test_delegation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.chenenyu.router.annotation.InjectParam
import com.chenenyu.router.annotation.Route
import com.google.android.material.appbar.AppBarLayout
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.adapter.test_adapter.TestListAdapter
import com.wheny.wenyapplication.data.TestListBean
import com.wheny.whenylibrary.utils.DensityUtils
import io.reactivex.Flowable
import java.lang.StringBuilder
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import kotlin.math.absoluteValue
import kotlin.reflect.KProperty


@Route("TestDeleActivity", "TestDeleActivity2")
class TestDeleActivity : AppCompatActivity() {

    @InjectParam(key = "testP1")
    public var testP1 = ""

    @InjectParam(key = "testP2")
    public var testP2 = false

    @InjectParam(key = "testP3")
    public var testP3 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dele)
        val rootView= findViewById<View>(R.id.root_view)


        val vp = findViewById<ViewPager>(R.id.view_pager)
        val adapter = HippyViewPagerAdapter(this,vp)
        val view1 = LayoutInflater.from(this).inflate(R.layout.scroll_layout,null,false)
        adapter.addView(view1,0)
        val view2 = LayoutInflater.from(this).inflate(R.layout.rv_layout,null,false)
        adapter.addView(view2,0)
        vp.adapter = adapter
        val rv = view2.findViewById<RecyclerView>(R.id.rv)
        val list = arrayListOf<TestListBean>()
        for (i in 0 .. 100){
            list.add(TestListBean("$i"))
        }
        val rvad1 = TestListAdapter(list,this)
        rv.adapter = rvad1
        rv.layoutManager = LinearLayoutManager(this)
//        vp.setPageTransformer(false, object : ViewPager.PageTransformer{
//            override fun transformPage(view: View, position: Float) {
//                val pageWidth: Int = view.getWidth()
//                val pageHeight: Int = view.getHeight()
//                var alpha = 0f
//                val maxOffSet =  pageWidth - pageWidth*(2f/3)
//                var relOffset = 0;
//                if (0 <= position && position <= 1) {
//                    alpha = 1 - position
//                } else if (-1 < position && position < 0) {
//                    alpha = position + 1
//                }
//                relOffset = (maxOffSet*position).toInt()
//                view.setAlpha(alpha)
//                view.setTranslationX(view.getWidth() * -position + relOffset)
//            }
//        })
        val  vpLayer = findViewById<View>(R.id.vp_layer)

        vp.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{

            val pagerHeights = mapOf<Int,Int>(0 to DensityUtils.dip2px(this@TestDeleActivity,100f),1 to DensityUtils.dip2px(this@TestDeleActivity,600f))

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                Log.d(
                    "onPageScrolled",
                    ",position:$position,positionOffset:$positionOffset,positionOffsetPixels:$positionOffsetPixels"
                )
                val currentHeight = if(positionOffset == 0f){
                    pagerHeights[position] ?:0
                }else{
                    val leftHeight = pagerHeights[position] ?:0
                    val rightHeight = pagerHeights[position+1] ?:0
                    (leftHeight + (rightHeight - leftHeight) * positionOffset).toInt()
                }
                vpLayer.apply {
                    layoutParams = layoutParams.apply {
                        height = currentHeight ?: 0
                    }
                }
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {
                Log.d(
                    "onPageScrolled",
                    "onPageScrollStateChanged:$state"
                )
            }

        })
        val testRv = findViewById<RecyclerView>(R.id.test_de_rv)
        testRv.layoutManager = LinearLayoutManager(this)
        testRv.adapter = MyAdapter(this)

        findViewById<AppBarLayout>(R.id.appbar).addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                Log.d(
                    "onOffsetChanged",
                    "verticalOffset:$verticalOffset"
                )
            }
        })

    }


}



@SuppressLint("CheckResult")
fun testRunApply(){
    var bool = true
    val s1 = StringBuilder()
        .append("")
        .apply {
            if(bool){
                append("true")
            }else{
                append("false")
            }
        }
        .toString()

    val s2 = StringBuilder()
        .append("")
        .run {
            return@run if(bool){
                append("true")
            }else{
                append("false")
            }
        }
        .toString()
    Log.e("testRunApply","test------------:${s1 == s2}")

    Flowable.just("start")
        .apply {
            if(bool){
                map {
                    return@map it+"true"
                }
            }else{
                map {
                    return@map it+"false"
                }
            }
        }
        .subscribe {
            Log.e("testRunApply","test---------apply ${it}")
        }
    Flowable.just("start")
        .run {
            return@run if(bool){
                map {
                    return@map it+"true"
                }
            }else{
                map {
                    return@map it+"false"
                }
            }
        }
        .subscribe {
            Log.e("testRunApply","test---------apply ${it}")
        }
}



class VH(view:View) : RecyclerView.ViewHolder(view){

}

class MyAdapter(val context:Context) : RecyclerView.Adapter<VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.my_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv).text = "------$position-------"
        holder.itemView.setOnLongClickListener {
            Toast.makeText(it.context, "长按了", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return 60
    }

}




class HippyViewPagerAdapter(
    context: Context,
    val mViewPager: ViewPager
) :
    PagerAdapter() {
    protected val mViews: MutableList<View?> = arrayListOf()
    private var mChildSize = 0
    private var mInitPageIndex = 0
    fun setChildSize(size: Int) {
        mChildSize = size
    }

    fun setInitPageIndex(index: Int) {
        mInitPageIndex = index
    }

    fun addView(view: View?, position: Int) {
        if (view != null && position >= 0) {
            if (position >= mViews.size) {
                mViews.add(view)
            } else {
                mViews.add(position, view)
            }
        }
    }

    fun removeViewAtIndex(postion: Int) {
        if (postion >= 0 && postion < mViews.size) {
            mViews.removeAt(postion)
        }
    }

    fun removeView(view: View) {
        val size = mViews.size
        var index = -1
        for (i in 0 until size) {
            val curr = getViewAt(i)
            if (curr === view) {
                index = i
                break
            }
        }
        if (index >= 0) {
            mViews.removeAt(index)
        }
    }

    protected fun getViewAt(index: Int): View? {
        return if (index >= 0 && index < mViews.size) mViews[index] else null
    }

    protected val itemViewSize: Int
        protected get() = mViews.size

    override fun getCount(): Int {
        return mViews.size
    }

    override fun getItemPosition(any: Any): Int {
        return if (mViews.isEmpty()) {
            -2
        } else {
            val index = mViews.indexOf(any as? View)
            if (index < 0) -2 else index
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var viewWrapper: View? = null
        if (position < mViews.size) {
            viewWrapper = mViews[position]
        }
        if (viewWrapper != null && viewWrapper.parent == null) {
            container.addView(viewWrapper, ViewPager.LayoutParams())
        } else {
            viewWrapper = null
        }
        return viewWrapper!!
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (`object` is View) {
            val view = `object`
            view.layout(0, 0, 0, 0)
            container.removeView(view)
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    companion object {
        private const val TAG = "HippyViewPagerAdapter"
    }
}


private fun screenshotSystem(webview: View): Bitmap? {
    val surfaceClass: Class<*>
    var method: Method? = null
    var bitmap: Bitmap? = null
    try {
        surfaceClass = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Class.forName("android.view.SurfaceControl")
        } else {
            Class.forName("android.view.Surface")
        }
        method = surfaceClass.getDeclaredMethod("screenshot", Integer.TYPE, Integer.TYPE)
        method.setAccessible(true)
        bitmap = method.invoke(webview, webview.getWidth(), webview.getHeight()) as Bitmap
        return bitmap
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    } catch (e: InvocationTargetException) {
        e.printStackTrace()
    }
    return null
}


interface Subject {
    fun request1()
    fun request2()

}


class RealSubject : Subject {
    override fun request1() {
        println("ReaSubject request1")
    }

    override fun request2() {
        println("ReaSubject request2")
    }
}

class Proxy(var subject: Subject) : Subject {

    override fun request1() {
        subject.request1()
    }

    override fun request2() {
        subject.request2()
    }

}

interface Base {
    fun print()
}

class BaseImpl(val a: Int) : Base {
    override fun print() {
        print(a)
    }
}

class Derived(b: Base) : Base by b {
    var x: Int by MyDele()


}


class MyDele {
    var aa: Int = 0

    operator fun getValue(derived: Derived, property: KProperty<*>): Int {
        return aa
    }

    operator fun setValue(derived: Derived, property: KProperty<*>, i: Int) {
        aa = i
    }

}

val lazeValue: String by lazy {
    println("执行lazy")
    return@lazy "hello"
}


fun main() {
//    val proxy = Proxy(RealSubject())
//    proxy.request1()
    val b = BaseImpl(10)
    val derived = Derived(b)
    derived.x = 101
    println(derived.x)
    testRunApply()

}

