package com.wheny.wenyapplication.mvvm.view.tf

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.adapter.test_adapter.CommonPagerAdapter
import kotlinx.android.synthetic.main.activity_tf.*

class TFActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tf)
        vp.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                onPageChoice(position)
            }
        })
        var adapter = CommonPagerAdapter(supportFragmentManager, listOf(C1Fragment(), C1Fragment()))
        vp.adapter = adapter
    }

    fun onPageChoice(index:Int){
        tv_1.setTextColor(if(index == 0) Color.parseColor("#22f2f2") else Color.parseColor("#000000") )
        tv_2.setTextColor(if(index == 0) Color.parseColor("#000000") else Color.parseColor("#22f2f2"))
    }

}
