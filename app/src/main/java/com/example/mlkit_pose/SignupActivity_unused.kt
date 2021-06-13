package com.example.mlkit_pose

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_tool_bar.*

class SignupActivity_unused :AppCompatActivity(),View.OnClickListener{

//    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.fragment_sign_up)


//        setContentView(R.layout.fragment_main_page_part)
//        setSupportActionBar(main_layout_toolbar)
//        transaction.add(R.id.frameLayout,SignUpFragment())
//        transaction.commit()

        btn_register_done.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val queue = Volley.newRequestQueue(this)

        val transaction = supportFragmentManager.beginTransaction()
        when(v?.id){
            R.id.btn_register_done->{

//


            }
        }
    }

}