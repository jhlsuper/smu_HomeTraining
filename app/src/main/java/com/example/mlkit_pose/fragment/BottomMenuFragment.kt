package com.example.mlkit_pose.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat

import com.example.mlkit_pose.R

import kotlinx.android.synthetic.main.fragment_bottom_menu.*
import kotlinx.android.synthetic.main.fragment_guide_click.*
import kotlinx.android.synthetic.main.fragment_tool_bar.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BottomMenuFragment : Fragment(),View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        btn_link_check.setOnClickListener(this)

        btn_guide.setOnClickListener(this)
        btn_mypage.setOnClickListener(this)
        btn_home.setOnClickListener(this)
        btn_routine.setOnClickListener(this)
        btn_drawer.setOnClickListener(this)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GuideClickFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
//            R.id.btn_link_check->{
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=IfJcq4LDXKE"))
//                startActivity(intent)
//            }

        }
    }
}


