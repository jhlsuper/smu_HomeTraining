package com.example.mlkit_pose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.mlkit_pose.companion.JSP
//import com.example.mlkit_pose.databinding.FragmentSignUpBinding

//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.belong_search.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignActivity : AppCompatActivity() {


//    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sign_up)
        val queue = Volley.newRequestQueue(this)

        val list = resources.getString(R.string.university_name)
        val university = list.split(" ")
        for (i in university) {
            Log.d("university", i)
        }
        val arrayAdapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, university)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.et_rg_belong)
        autoCompleteTextView.setAdapter(arrayAdapter)


//        et_belong.setAdapter(adapter)

        btn_register_done.setOnClickListener {
            val userName = et_rg_name.text.toString().trim()
            val userId = et_rg_id.text.toString().trim()
            val userPwd = et_rg_password.text.toString().trim()
            val userRePwd = et_rg_passwordcheck.text.toString().trim()
            val userTeam = et_rg_belong.text.toString().trim()
            val userAge = et_rg_age.text.toString().trim()
            val userGender = et_rg_gender.text.toString().trim()
            val userHeight = et_rg_height.text.toString().trim()
            val userWeight = et_rg_weight.text.toString().trim()

            val url_signup =
                JSP.getSignInURL(userName, userId, userPwd, userTeam, userAge, userGender,userHeight,userWeight)
            Log.d("signin", url_signup)
            if (userPwd != userRePwd) {
                Toast.makeText(this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show()
            } else {
                val stringRequest2 = StringRequest(
                    Request.Method.GET, url_signup, { response ->
                        response.trim { it <= ' ' }
                        val details2 = response.split(",").toTypedArray()
//                    Toast.makeText(this, "${details2}", Toast.LENGTH_SHORT).show()
                        if (response.trim() == "success") {
                            Toast.makeText(this, "회원이 되신 걸 환영합니다!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
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

//        btn_search_belong.setOnClickListener {
//
//        }




    }


}


