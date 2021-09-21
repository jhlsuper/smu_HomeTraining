package com.example. mlkit_pose.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*

import androidx.fragment.app.Fragment


import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.*
import com.example.mlkit_pose.adapter.MainViewModel
import com.example.mlkit_pose.dao.SharedManager


import kotlinx.android.synthetic.main.fragment_my_page.*
import kotlinx.android.synthetic.main.fragment_my_page.view.*
import kotlinx.android.synthetic.main.fragment_ranking_main.*
import kotlinx.android.synthetic.main.fragment_ranking_main.img_ranking_profile
import kotlinx.android.synthetic.main.main_drawer_header.*
import kotlinx.android.synthetic.main.ranking_item.*
import android.view.WindowManager
import android.widget.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@Suppress("DEPRECATION")
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
    private var profileImg: String? = null
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
            recentDay = it.getString("recentDay")?.trim()
            countDays = it.getString("countDays")
//            profileImg = it.getString("profile")
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
        viewModel.profileImg.observe(this, {
//            val bit:Bitmap = convertBitMap().StringToBitmap(it)!!
//            img_mypage_profile?.setImageBitmap(bit)
//            header_icon?.setImageBitmap(bit)
//
//            img_ranking_profile?.setImageBitmap(bit)
        })


//        viewModel.init()
        Log.d("userinfo", "$recentDay")
//        if((recentDay?.length)!! <6){
//            year ="없음"
//            month = ""
//            day = ""
//        }
        if (recentDay?.toInt()!!  == 0) {
            year = "없음"
            month = ""
            day = ""
        } else {
            year = recentDay?.substring(0 until 4).toString().trim() + "년"
            month = recentDay?.subSequence(4 until 6).toString() + "월"
            day = recentDay?.subSequence(6 until 8).toString() + "일"
        }
        Log.d("userinfo", "ymd ymd $year,$month,$day")
    }


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val sharedManager: SharedManager = SharedManager(requireContext())
        val currentUser = sharedManager.getCurrentUser()
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

        view.img_mypage_profile.setImageBitmap(convertBitMap().StringToBitmap(currentUser.img))
        view.btn_mypage_edit.setOnClickListener(this)
        view.img_mypage_profile.setOnClickListener(this)

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
            R.id.img_mypage_profile -> {
                (activity as PageActivity).selectGalley()
            }

        }
    }

    @SuppressLint("InflateParams", "CutPasteId")
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

            editUserInfoDB(user_weight, user_height, user_belong, this.id.toString())
            dialog.dismiss()
        }
        val params: WindowManager.LayoutParams = dialog.window!!.attributes
//        params.width = WindowManager.LayoutParams.MATCH_PARENT
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        params.width = 100
        params.height = 100
        val list = resources.getString(R.string.university_name)
        val university = list.split(" ")

        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, university)
        val autoCompleteTextView2 = mView.findViewById<AutoCompleteTextView>(R.id.et_mypage_edit_belong)
        autoCompleteTextView2.setAdapter(arrayAdapter)


        dialog.setView(mView)
        dialog.create()
        dialog.window!!.attributes = params
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
                Toast.makeText(context, "정보가 변경되었습니다", Toast.LENGTH_SHORT).show()

            }, {
                Toast.makeText(context, "sever error", Toast.LENGTH_SHORT).show()
            })
        queue.add(StringRequest)
    }


}