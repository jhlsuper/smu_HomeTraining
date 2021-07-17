package com.example.mlkit_pose.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.GravityCompat

import com.example.mlkit_pose.PageActivity
import com.example.mlkit_pose.R
import com.example.mlkit_pose.SharedManager
import com.example.mlkit_pose.User

import kotlinx.android.synthetic.main.fragment_bottom_menu.*
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.view.*
import kotlinx.android.synthetic.main.fragment_tool_bar.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MyPageFragment : Fragment() ,View.OnClickListener{
    private var id:String? =null
    private var gender:String? = null
    private var belong:String? = null


    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")
            gender = it.getString("gender")
            belong = it.getString("belong")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_my_page,container,false)
        view.et_mypage_gender.setText("${gender}")
        view.et_mypage_id.setText("${id}")
        view.et_mypage_belong.setText("${belong}")
        view.btn_mypage_edit.setOnClickListener(this)

//        return inflater.inflate(R.layout.fragment_my_page, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        btn_mypage_logout.setOnClickListener(this)
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MyPageFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_mypage_edit->{

            }

        }
    }


}