package com.example.mlkit_pose.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener

import com.example.mlkit_pose.R
//import com.example.mlkit_pose.databinding.ActivityMainBinding
//import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bottom_menu.*
import kotlinx.android.synthetic.main.fragment_guide_main.*
import kotlinx.android.synthetic.main.fragment_guide_main.view.*
import kotlinx.android.synthetic.main.fragment_tool_bar.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GuideMainFragment : Fragment(),View.OnClickListener {
//    lateinit var  binding: ActivityMainBinding
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
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_guide_main, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chest.setOnClickListener(this)
        back.setOnClickListener(this)
        shoulder.setOnClickListener(this)
        arm.setOnClickListener(this)
        abs.setOnClickListener(this)
        core.setOnClickListener(this)
        side.setOnClickListener(this)
        calf.setOnClickListener(this)
        thigh.setOnClickListener(this)
        pelvis.setOnClickListener(this)
        hip.setOnClickListener(this)

    }

    fun setPosition(positionName:String){
        val result = positionName
        Log.d("jieun", result)
        setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        parentFragmentManager.beginTransaction()
            .add(R.id.frameLayout,GuideClickFragment(),"guide_click")
            .show(GuideClickFragment())
            .hide(GuideMainFragment())
//            .replace(R.id.frameLayout, GuideClickFragment())
            .addToBackStack(null)

            .commit()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.chest->{
                setPosition("가슴")
                Log.d("jieun", "hello")
            }
            R.id.back->{
                setPosition("등")
            }
            R.id.shoulder->{
                setPosition("어깨")
            }
            R.id.arm ->{
                setPosition("팔")
            }
            R.id.abs->{
                setPosition("복근")
            }
            R.id.core->{
                setPosition("코어")
            }
            R.id.side->{
                setPosition("옆구리")
            }
            R.id.calf->{
                setPosition("종아리")
            }
            R.id.thigh->{
                setPosition("허벅지")
            }
            R.id.pelvis->{
                setPosition("골반")
            }
            R.id.hip->{
                setPosition("엉덩이")
            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                GuideMainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


}