package com.example.mlkit_pose.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.MainActivity
import com.example.mlkit_pose.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class LoginFragment : Fragment(),View.OnClickListener{

    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val let = arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_login,container,false)

        return view
    }


    companion object {

        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {

//        val queue = Volley.newRequestQueue(requireContext())
//        when (v.id) {
//            R.id.btn_prac->{
//                Toast.makeText(activity,"프래그먼트 기능실행",Toast.LENGTH_SHORT).show()
//            }
//        }


    }
}