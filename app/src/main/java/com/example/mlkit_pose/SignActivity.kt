package com.example.mlkit_pose
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_login.*
import android.content.SharedPreferences
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
//import com.example.mlkit_pose.databinding.FragmentSignUpBinding

import com.example.mlkit_pose.fragment.MyPageFragment
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.belong_search.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class SignActivity : AppCompatActivity(){

    lateinit var navController: NavController
//    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val queue = Volley.newRequestQueue(this)

//        binding = FragmentSignUpBinding.inflate(layoutInflater)
//        val view = binding.root
        setContentView(R.layout.fragment_sign_up)
        val list = ArrayList<String>()
//        settingList(list)



        setContentView(R.layout.fragment_sign_up)
//        val belong_list =settingList()
//        val items = mutableListOf<String>()
//        for(item in belong_list){
//            items.add(item)
//        }

        btn_search_belong.setOnClickListener{
            //dialog 만들기

//            autoCompleteTextView.setAdapter(ArrayAdapter<String>(this,R.layout.belong_search,list))
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.belong_search,null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
//            val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,belong_list)
//            autoCompleteTextView.setAdapter(adapter)
            mBuilder.show()
        }
        btn_register_done.setOnClickListener {
            val userName = et_rg_name.text.toString().trim()
            val userId = et_rg_id.text.toString().trim()
            val userPwd = et_rg_password.text.toString().trim()
            val userRePwd = et_rg_passwordcheck.text.toString().trim()
            val userTeam = et_rg_passwordcheck.text.toString().trim()
            val userAge = et_rg_age.text.toString().trim()
            val userGender = et_rg_gender.text.toString().trim()
//
            val url_signup = JSP.getSignInURL(userName,userId,userPwd,userTeam,userAge,userGender)
            if (userPwd != userRePwd) {
                Toast.makeText(this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show()
            } else {
                val stringRequest2 = StringRequest(
                         Request.Method.GET, url_signup, { response ->
                    response.trim { it <= ' ' }
                    val details2 = response.split(",").toTypedArray()
//                    Toast.makeText(this, "${details2}", Toast.LENGTH_SHORT).show()
                    if (response.trim() == "success") {
                        Toast.makeText(this, "회원가입완료.", Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "아이디가 중복됩니다.", Toast.LENGTH_SHORT).show()

                    }
                }, {
                    Toast.makeText(this, "server error", Toast.LENGTH_SHORT).show()
                })
                queue.add(stringRequest2)
            }
        }

    }



}


