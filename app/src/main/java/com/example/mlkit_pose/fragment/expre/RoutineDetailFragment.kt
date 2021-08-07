package com.example.mlkit_pose.fragment.expre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.mlkit_pose.R
import com.example.mlkit_pose.fragment.expre.model.SharedViewModel
import kotlinx.android.synthetic.main.fragment_routine_detail.*
import java.util.*


class RoutineDetailFragment : Fragment(){
    var routine_name : String = ""
    private val sharedViewModel: SharedViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var routine_name : String? = null
        context_mainR = this;

//        arguments?.let {
//            routine_name = it.getString("result")
//        }
//
//        setFragmentResultListener("requestKey") { resultKey, bundle ->
//            val result = bundle.getString("bundleKey")
//            Log.d("result", result.toString())
//
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_routine_detail, container, false)

        Log.d("jieun", arguments.toString()) // 널로 찍힘
        if(arguments != null)
        {
            routine_name = arguments?.getString("Routine_detail_name").toString()
            Log.d("jieun", routine_name)
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setFragmentResultListener("requestKey") { resultKey, bundle ->
            val result = bundle.getString("bundleKey")
            Log.d("result", result.toString())
            routine_detail_name.setText("${result}")
//            val queue = Volley.newRequestQueue(context)
//            val url = JSP.getSportsName(result.toString()).toString()
        }


    }

    companion object {
        lateinit var context_mainR: Any

    }

}