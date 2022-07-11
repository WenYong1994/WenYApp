package com.wheny.wenyapplication.mvvm.view.main


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chenenyu.router.annotation.InjectParam
import com.chenenyu.router.annotation.Route
import com.wheny.wenyapplication.R
import com.wheny.wenyapplication.databinding.FragmentTestBinding
import com.wheny.whenyannotationapilib.ViewModelInjector
import com.wheny.whenyannotationlib.InjectViewModel
import com.wheny.whenylibrary.mvvm.contract.VmContract

/**
 * A simple [Fragment] subclass.
 */
@Route("testFragment", "testFragment2")
class TestFragment : Fragment() , VmContract {

    @InjectViewModel(dataBindFieldName = "testFraVm")
    var testFraVm:TestFraVm<TestFragment>?=null
    @InjectViewModel
    var mainVm: MainVm<MainActivity>? = null


    @InjectParam(key = "testP1")
    public var testP1 = ""

    @InjectParam(key = "testP2")
    public var testP2 = false

    @InjectParam(key = "testP3")
    public var testP3 = 0


    var rootView :View?= null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("ssss","${this.toString()}onAttach111111")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ssss","${this.toString()}onCreate111111111111")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var dt = ViewModelInjector.inject<FragmentTestBinding>(this,activity as AppCompatActivity,inflater, R.layout.fragment_test,container)
        rootView = dt.root
        Log.e("ssss","${this.toString()}onCreateView111111")
        return rootView
    }


    override fun onStart() {
        super.onStart()
        Log.e("ssss","${this.toString()}onStart111111")
    }

    override fun onResume() {
        super.onResume()
        Log.e("ssss","${this.toString()}onResume111111")
    }

    override fun onPause() {
        super.onPause()
        Log.e("ssss","${this.toString()}onPause111111")
    }

    override fun onStop() {
        super.onStop()
        Log.e("ssss","${this.toString()}onStop111111")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("ssss","${this.toString()}onDestroyView111111")
    }


}
