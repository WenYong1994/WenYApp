package com.wheny.wenyapplication.mvvm.view.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.databinding.FragmentTest2Binding
import com.wheny.whenyannotationapilib.ViewModelInjector
import com.wheny.whenyannotationlib.InjectViewModel
import com.wheny.whenylibrary.mvvm.contract.VmContract

/**
 * A simple [Fragment] subclass.
 */
class TestFragment2 : Fragment(), VmContract {

    @InjectViewModel(dataBindFieldName = "testFraVm")
    var testFraVm:TestFraVm<TestFragment2>?=null
    @InjectViewModel
    var mainVm: MainVm<MainActivity>? = null




    var rootView :View?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var dt = ViewModelInjector.inject<FragmentTest2Binding>(this,activity as AppCompatActivity,inflater, R.layout.fragment_test_2,container)
        rootView = dt.root
        testFraVm?.test?.postValue("212112124")
        return rootView

    }

}
