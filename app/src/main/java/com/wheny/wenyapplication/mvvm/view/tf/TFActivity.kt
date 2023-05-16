package com.wheny.wenyapplication.mvvm.view.tf

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.adapter.test_adapter.CommonPagerAdapter

class TFActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tf)
        val vp = findViewById<ViewPager>(R.id.vp)
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
    }

}
