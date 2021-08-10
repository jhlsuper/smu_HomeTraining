package com.example.mlkit_pose.fragment.expre

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.mlkit_pose.R
import com.example.mlkit_pose.fragment.expre.model.SharedViewModel
import kotlinx.android.synthetic.main.fragment_routine_detail.*


private const val ARG_PARAM1 = "param1"

class RoutineDetailFragment : Fragment(){
    private var routine_name : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            routine_name = it.getString("routine_Name")
        }
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
        Log.d("ROUTINE_DETAIL",routine_name!!)
        routine_detail_name.text = routine_name

    }


    companion object {
        @JvmStatic
        fun newInstance(param1:String){
            RoutineDetailFragment().apply{
                arguments = Bundle().apply{
                    putString(ARG_PARAM1,param1);
                }
            }
        }

    }

}