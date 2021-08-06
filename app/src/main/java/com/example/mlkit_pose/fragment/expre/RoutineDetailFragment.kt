package com.example.mlkit_pose.fragment.expre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.JSP
import com.example.mlkit_pose.R
import kotlinx.android.synthetic.main.fragment_guide_click.*
import kotlinx.android.synthetic.main.fragment_guide_click.sport_name
import kotlinx.android.synthetic.main.fragment_routine_detail.*

class RoutineDetailFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var routine_name : String? = null

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
        return inflater.inflate(R.layout.fragment_routine_detail, container, false)
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
}