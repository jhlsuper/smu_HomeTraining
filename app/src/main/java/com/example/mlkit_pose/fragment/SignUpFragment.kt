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
import com.example.mlkit_pose.R
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SignUpFragment : Fragment(),View.OnClickListener {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            Toast.makeText(activity,"signup 프래그먼트 on",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View = inflater.inflate(R.layout.fragment_sign_up,container,false)
        view.btn_register_done.setOnClickListener(this)

        return view
    }


    override fun onClick(v: View?) {
        val queue = Volley.newRequestQueue(activity)
        when(v?.id){
            R.id.btn_register_done->{


            }
        }
    }
}