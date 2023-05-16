package com.wheny.wenyapplication.mvvm.view.tf

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.adapter.test_adapter.CommonPagerAdapter

class C1Fragment : Fragment() {

    var rootView : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(rootView == null ){
            rootView = inflater.inflate(R.layout.fragment_c1, container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        rootView?.findViewById<ViewPager>(R.id.vp1)?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                onPageChoice(position)
            }
        })
        val vp1 = view?.findViewById<ViewPager>(R.id.vp1)
        var adapter = CommonPagerAdapter(childFragmentManager, listOf(C2Fragment(), C2Fragment()))
//        rootView?.findViewById<ViewPager>(R.id.vp1)?.adapter = adapter
        vp1?.adapter = adapter
    }



    fun onPageChoice(index:Int){
        rootView?.findViewById<TextView>(R.id.tv_11)?.setTextColor(if(index == 0) Color.parseColor("#22f2f2") else Color.parseColor("#000000") )
        rootView?.findViewById<TextView>(R.id.tv_22)?.setTextColor(if(index == 0) Color.parseColor("#000000") else Color.parseColor("#22f2f2"))
    }

}
