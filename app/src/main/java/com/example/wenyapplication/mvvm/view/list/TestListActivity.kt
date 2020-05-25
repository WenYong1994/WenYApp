package com.example.wenyapplication.mvvm.view.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wenyapplication.R
import com.example.wenyapplication.adapter.test_adapter.TestListAdapter
import com.example.wenyapplication.data.TestListBean
import kotlinx.android.synthetic.main.activity_test_list.*

class TestListActivity : AppCompatActivity() {

    lateinit var testListAdapter:TestListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_list)
        var list = arrayListOf(TestListBean("1"),TestListBean("1"),TestListBean("1"),
                TestListBean("1"),TestListBean("1"),TestListBean("1"),TestListBean("1"),TestListBean("1"),
                TestListBean("1"),TestListBean("1"),TestListBean("1"),TestListBean("1"),TestListBean("1"),
                TestListBean("1"),TestListBean("1"),TestListBean("1"),TestListBean("1"),TestListBean("1"),
                TestListBean("1"),TestListBean("1"),TestListBean("1"),TestListBean("1"),TestListBean("1"),
                TestListBean("1"),TestListBean("1"),TestListBean("1"),TestListBean("1"),TestListBean("1"))
        testListAdapter = TestListAdapter(list, this)
        rv.adapter = testListAdapter
        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        testListAdapter.notifyDataSetChanged()
    }


    override fun onPause() {
        super.onPause()
    }



}
