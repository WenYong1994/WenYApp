package com.wheny.wenyapplication.mvvm.view.TestFragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.mvvm.view.TestFragment.f1.TestF1Fragment
import com.wheny.wenyapplication.mvvm.view.TestFragment.f1.TestF1Vm
import com.wheny.wenyapplication.mvvm.view.TestFragment.f2.TestF2Fragment
import com.wheny.wenyapplication.mvvm.view.TestFragment.f3.TestF3Fragment
import kotlinx.android.synthetic.main.activity_test_fragment.*

class TestFragmentActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fragment)
        initView()
    }

    private fun initView() {
        var list = arrayListOf<Fragment>(TestF1Fragment(),TestF2Fragment(),TestF3Fragment())
        val commAdapter = CommAdapter(supportFragmentManager, list)
        vp.offscreenPageLimit=0
        vp.adapter=commAdapter
    }



}




class CommAdapter : FragmentPagerAdapter {

    private var fragments: List<Fragment>? = null

    constructor(fm: FragmentManager, fragments: List<Fragment>?) :super(fm){
        this.fragments = fragments
    }

    override fun getItem(position: Int): Fragment {
        return fragments!![position]
    }

    override fun getCount(): Int {
        return if (fragments == null) 0 else fragments!!.size
    }
}
