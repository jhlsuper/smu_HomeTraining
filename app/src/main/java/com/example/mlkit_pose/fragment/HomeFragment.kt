package com.example.mlkit_pose.fragment

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.mlkit_pose.MainActivity
import com.example.mlkit_pose.PageActivity
import com.example.mlkit_pose.R

import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment(),View.OnClickListener {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            Log.d("태그","fragment 기능 구현")
            Toast.makeText(activity,"프래그먼트 on",Toast.LENGTH_SHORT).show()

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view:View = inflater.inflate(R.layout.fragment_main,container,false)
        view.btn_home_daily.setOnClickListener(this)


//        return inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_home_daily.setOnClickListener{
            Log.d("태그","fragment 기능 구현")
            Toast.makeText(activity,"ohh",Toast.LENGTH_SHORT).show()
        }
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_home_daily->{

            }
        }
    }
}