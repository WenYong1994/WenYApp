package com.wheny.wenyapplication.mvvm.view.TestFragment.f1


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.base.BaseVmContract
import com.wheny.whenyannotationapilib.ViewModelInjector
import com.wheny.whenyannotationapilib.ViewModelInjector.*
import com.wheny.whenyannotationlib.InjectViewModel


/**
 * A simple [Fragment] subclass.
 */
class TestF1Fragment : Fragment(),BaseVmContract {

    @InjectViewModel
    var testF1Vm:TestF1Vm?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val inject = ViewModelInjector.inject<ViewDataBinding>(this, activity, inflater, R.layout.fragment_test_f1, container)
//        return inflater.inflate(, container, false)
        return inject.root
    }


}
