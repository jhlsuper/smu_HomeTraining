package com.example.mlkit_pose.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mlkit_pose.*

import kotlinx.android.synthetic.main.fragment_bottom_menu.*
import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.view.*
import kotlinx.android.synthetic.main.fragment_tool_bar.*
import kotlinx.android.synthetic.main.popup_mypage_edit.*
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MyPageFragment : Fragment() ,View.OnClickListener{
    private var id:String? =null
    private var gender:String? = null
    private var belong:String? = null
    private var weight:String? =null
    private var height:String? =null

    lateinit var viewModel:MainViewModel
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")
            gender = it.getString("gender")
            belong = it.getString("belong")
            weight = it.getString("weight")
            height = it.getString("height")
        }
        viewModel =ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.weight.observe(this,Observer{
            et_mypage_weight.text =it.toString()
        })
        viewModel.height.observe(this,Observer{
            et_mypage_height.text =it.toString()
        })
        viewModel.belong.observe(this, Observer {
            et_mypage_belong.text =it.toString()
        })
        viewModel.init()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_my_page,container,false)
        view.et_mypage_gender.text = "$gender"
        view.et_mypage_id.text = "$id"
        view.et_mypage_belong.text = "$belong"
        view.et_mypage_height.text ="${height}cm"
        view.et_mypage_weight.text="${weight}kg"
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
                mypageEditPopup()
            }

        }
    }

    @SuppressLint("InflateParams")
    fun mypageEditPopup(){
        val dialog = AlertDialog.Builder(context).create()
        val edialog: LayoutInflater = LayoutInflater.from(context)
        val mView :View = edialog.inflate(R.layout.popup_mypage_edit,null)

        val weight :EditText = mView.findViewById<EditText>(R.id.et_mypage_edit_weight)
        val height :EditText = mView.findViewById<EditText>(R.id.et_mypage_edit_height)
        val belong :EditText = mView.findViewById<EditText>(R.id.et_mypage_edit_belong)

        val cancel :Button =mView.findViewById<Button>(R.id.btn_mypage_edit_cancel)
        val ok :Button =mView.findViewById<Button>(R.id.btn_mypage_edit_ok)

        cancel.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
        }
        ok.setOnClickListener {
            val user_weight = weight.text.toString()
            val user_height = height.text.toString()
            val user_belong = belong.text.toString()
            viewModel.editUser(user_height,user_weight,user_belong)
            Toast.makeText(context,"$user_weight, $user_height, $user_belong 서버에 적용해야됨",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialog.setView(mView)
        dialog.create()
        dialog.show()
    }


}