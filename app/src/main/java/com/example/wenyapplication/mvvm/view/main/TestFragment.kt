package com.example.wenyapplication.mvvm.view.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.wenyapplication.R
import com.example.wenyapplication.databinding.FragmentTestBinding
import com.example.whenyannotationapilib.ViewModelInjector
import com.example.whenyannotationlib.InjectViewModel

/**
 * A simple [Fragment] subclass.
 */
class TestFragment : Fragment() {

    @InjectViewModel(dataBindFieldName = "testFraVm")
    var testFraVm:TestFraVm?=null
    @InjectViewModel
    var mainVm: MainVm? = null




    var rootView :View?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var dt = ViewModelInjector.inject<FragmentTestBinding>(this,activity as AppCompatActivity,inflater,R.layout.fragment_test,container)
        rootView = dt.root
        testFraVm?.test?.postValue("212112124")
        return rootView
    }

    override fun onPause() {
        super.onPause()
    }





}
