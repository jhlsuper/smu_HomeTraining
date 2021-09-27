package com.example.mlkit_pose.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.*
import com.example.mlkit_pose.adapter.MainViewModel


import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MyPageFragment : Fragment(), View.OnClickListener {
    private var id: String? = null
    private var nickname: String? = null
    private var gender: String? = null
    private var belong: String? = null
    private var weight: String? = null
    private var height: String? = null
    private var point: String? = null
    private var recentDay: String? = null
    private var countDays: String? = null
    lateinit var year: String
    lateinit var month: String
    lateinit var day: String
    lateinit var viewModel: MainViewModel

    @SuppressLint("InflateParams", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")
            nickname = it.getString("name")
            gender = it.getString("gender")
            belong = it.getString("belong")
            weight = it.getString("weight")
            height = it.getString("height")
            point = it.getString("points")
            recentDay = it.getString("recentDay")
            countDays = it.getString("countDays")
        }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.weight.observe(this, {
            et_mypage_weight.text = it.toString()
        })
        viewModel.height.observe(this, {
            et_mypage_height.text = it.toString()
        })
        viewModel.belong.observe(this, {
            et_mypage_belong.text = it.toString()
        })
//        viewModel.recentDay.observe(this, {
//            year = it.subSequence(0, 4).toString()
//            month = it.subSequence(4, 6).toString()
//            day = it.subSequence(6, 8).toString()
//            et_mypage_belong.text = "${year}년 ${month}월 ${day}일"
//        })
//        viewModel.countDays.observe(this, {
//            et_mypage_exercisedays.text = it.toString() + "일"
//        })


//        viewModel.init()
        Log.d("userinfo","$recentDay")
        if (recentDay.toString()=="0"){
            year ="없음"
            month = ""
            day = ""
        }
        else{
            year = recentDay?.substring(0 until 4).toString().trim()+"년"
            month = recentDay?.subSequence(4 until  6).toString()+"월"
            day =recentDay?.subSequence(6 until  8).toString()+"일"
        }
        Log.d("userinfo","ymd ymd $year,$month,$day")
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_my_page, container, false)
        view.et_mypage_gender.text = "$gender"
        view.et_mypage_id.text = "$id"
        view.et_mypage_name.text = "$nickname"
        view.et_mypage_belong.text = "$belong"
        view.et_mypage_height.text = "${height}cm"
        view.et_mypage_weight.text = "${weight}kg"
        view.et_mypage_point.text = "$point"
        view.et_mypage_recent_day.text = "$year $month $day"
        view.et_mypage_exercisedays.text = "$countDays"

//        view.et_mypage_recent_day.text = "$r"
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
        when (v?.id) {
            R.id.btn_mypage_edit -> {
                mypageEditPopup()
            }

        }
    }

    @SuppressLint("InflateParams")
    fun mypageEditPopup() {
        val dialog = AlertDialog.Builder(context).create()
        val edialog: LayoutInflater = LayoutInflater.from(context)
        val mView: View = edialog.inflate(R.layout.popup_mypage_edit, null)

        val weight: EditText = mView.findViewById<EditText>(R.id.et_mypage_edit_weight)
        val height: EditText = mView.findViewById<EditText>(R.id.et_mypage_edit_height)
        val belong: EditText = mView.findViewById<EditText>(R.id.et_mypage_edit_belong)

        val cancel: Button = mView.findViewById<Button>(R.id.btn_mypage_edit_cancel)
        val ok: Button = mView.findViewById<Button>(R.id.btn_mypage_edit_ok)

        cancel.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
        }
        ok.setOnClickListener {
            val user_weight = weight.text.toString()
            val user_height = height.text.toString()
            val user_belong = belong.text.toString()
            viewModel.editUser(user_height, user_weight, user_belong)
//            Toast.makeText(
//                context,
//                "$user_weight, $user_height, $user_belong 서버에 적용해야됨",
//                Toast.LENGTH_SHORT
//            ).show()
            editUserInfoDB(user_weight, user_height, user_belong, this.id.toString())
            dialog.dismiss()
        }
        dialog.setView(mView)
        dialog.create()
        dialog.show()
    }

    fun editUserInfoDB(
        input_weight: String,
        input_height: String,
        input_belong: String,
        input_id: String
    ) {
        val queue = Volley.newRequestQueue(context)
        val url_setUserInfoDB =
            JSP.setUserInfoEdit(input_height, input_weight, input_belong, input_id)
        val StringRequest = StringRequest(
            Request.Method.GET, url_setUserInfoDB, { response ->
                response.trim { it <= ' ' }
                Toast.makeText(context, "변경 되었습니다", Toast.LENGTH_SHORT).show()

            }, {
                Toast.makeText(context, "sever error", Toast.LENGTH_SHORT).show()
            })
        queue.add(StringRequest)
    }

}