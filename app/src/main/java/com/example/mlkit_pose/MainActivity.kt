package com.example.mlkit_pose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast


import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.fragment_login.*


class MainActivity : AppCompatActivity(), View.OnClickListener {


    private val sharedManager: SharedManager by lazy { SharedManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
//        navController = nav_host_fragment.findNavController()

        super.onCreate(savedInstanceState)
        val currentUser = sharedManager.getCurrentUser()
//        Toast.makeText(this, currentUser.id.toString(),Toast.LENGTH_SHORT).show()
        Log.d("idinfo", currentUser.id.toString())
        if (currentUser.id.toString() !== "") { //만약 로그인 정보가 있으면 그냥 메인화면으로
            // id == null로 하면안되고 === 3개를 이용해야된다!
            startActivity(Intent(this, PageActivity::class.java))
            finish()
        }
        setContentView(R.layout.fragment_login)
        btn_login.setOnClickListener(this)
        btn_signup.setOnClickListener(this)

    }


    override fun onClick(v: View?) {

        val queue = Volley.newRequestQueue(this)

        when (v?.id) {
            R.id.btn_login -> {
//                startActivity(Intent(this, SignActivity::class.java))
//                finish()
                val inputLogin = et_id.text.toString()
                val inputPassword = et_password.text.toString()
                val url = JSP.getLoginURL(inputLogin, inputPassword)

                val stringRequest = StringRequest(
                    Request.Method.GET, url, { response ->
                        response.trim { it <= ' ' }

                        val details = response.split(",").toTypedArray()
                        Toast.makeText(this, "${details[0]},${details[1]},${details[2]}", Toast.LENGTH_LONG).show()
                        if (response.trim() == "error") {
                            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT)
                                .show()
                            et_password.text = null


                        } else {
//                            Toast.makeText(this, "${details[0]}비밀먼호: ${details[1]}", Toast.LENGTH_SHORT).show()
//                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                            val currentUser = User().apply {
                                id = details[1]
                                password = details[2]
                                name = details[3]
                                belong = details[4]
                                age = details[5].toInt()
                                gender = details[6]
                                weight = details[8]
                                height = details[7]
                            }
                            Log.d("userinfo", "height : ${details[7]}  weight: ${details[8]}")
                            sharedManager.saveCurrentUser(currentUser)
                            getUserPoints(currentUser.id)
                            startActivity(Intent(this, PageActivity::class.java))

                            finish()
                        }
                    }, {
                        Toast.makeText(this, "서버 에러", Toast.LENGTH_SHORT).show()
                    }
                )
                queue.add(stringRequest)
            }
            R.id.btn_signup -> {
//                    Toast.makeText(this, "회원 가입버튼 눌림",Toast.LENGTH_SHORT).show()
//                    setContentView(R.layout.fragment_sign_up)
                startActivity(Intent(this, SignActivity::class.java))
//                    finish()
            }


        } //when 문 끝
    }// onClick 끝

    private fun getUserPoints(inputId: String?) {
        val currentUser = sharedManager.getCurrentUser()
        val queue = Volley.newRequestQueue(this)

        val userRankUrl = JSP.getUserRank(inputId.toString())
        val stringRequest2 = StringRequest(
            Request.Method.GET, userRankUrl, { response ->
                response.trim { it <= ' ' }
                val userPoint = response.split(",").toTypedArray()[2]
                sharedManager.setUserPoint(currentUser, userPoint.trim())
                Log.d("userPoint", userPoint)
            }, {
                Toast.makeText(this, "sever error", Toast.LENGTH_SHORT).show()
            })
        queue.add(stringRequest2)
//        return user_point
    }

}