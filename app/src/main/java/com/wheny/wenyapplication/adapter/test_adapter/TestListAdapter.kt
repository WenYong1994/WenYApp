package com.wheny.wenyapplication.adapter.test_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.data.TestListBean
import com.wheny.wenyapplication.databinding.TestListItemLayoutBinding

class TestListAdapter : RecyclerView.Adapter<TestListAdapter.TestViewHolder> {

    var data:List<TestListBean>
    var activity : AppCompatActivity

    constructor(data: List<TestListBean>, activity : AppCompatActivity) : super() {
        this.data = data
        this.activity = activity
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val inflate = DataBindingUtil.inflate<TestListItemLayoutBinding>(LayoutInflater.from(activity), R.layout.test_list_item_layout, parent, false)
        return TestViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.testDataBinding.testListVm?.testListBean = data[position]
//        holder.testDataBinding.testListVm?.txt2 = data[position].txt
    }

    open class TestViewHolder : RecyclerView.ViewHolder{

        var testDataBinding :TestListItemLayoutBinding

        constructor(dataBinding : TestListItemLayoutBinding) : super(dataBinding.root) {
            testDataBinding = dataBinding
            testDataBinding.testListVm = TestListVm()
        }

    }
}