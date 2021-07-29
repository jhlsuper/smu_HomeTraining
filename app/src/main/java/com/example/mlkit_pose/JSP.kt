package com.example.mlkit_pose

import android.content.Intent
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_login.*

class JSP {
    companion object {
        private val Jsp_Url = "http://3.34.133.113:8080/"  //git pull 받으면 이거 고쳐서 쓸것!

        fun getLoginURL(inputId:String, inputPw:String):String{
            val url = Jsp_Url+"Login.jsp?userId=${inputId}&userPwd=${inputPw}"

            return url
        }

        fun getSignInURL(inputName:String, inputId:String,inputPw: String,inputTeam:String,inputAge:String,inputGender:String,inputHeight:String,inputWeight:String):String{
            val url = Jsp_Url+"Sign.jsp?userName=$inputName&userId=$inputId&userPwd=$inputPw&userTeam=$inputTeam&userAge=$inputAge&userGender=$inputGender&userHeight=$inputHeight&userWeight=$inputWeight"
            return url
        }
        fun getEveryRank(inputTeam: String):String{
            val url = Jsp_Url+"Ranking.jsp?userTeam=$inputTeam"
            return url
        }
        fun getUserRank(inputId:String) :String{
            val url = Jsp_Url+"MainRanking.jsp?userId=$inputId"
            return url
        }

        fun getSportsName(inputParts:String):String{
            val url = Jsp_Url+"Part.jsp?part=$inputParts"
            return url
        }

        fun getSportsDetail(inputSports:String):String{
            val url = Jsp_Url+"Guide.jsp?guidename=$inputSports"
            return url
        }
        fun getSportsPic(inputSports: String):String{

            val url = Jsp_Url+"img/$inputSports"+".png"
            return url
        }
        fun getUserPointSet(inputId:String ,points:String):String{
            val url = Jsp_Url+"InsertScore.jsp?userId=$inputId&userScore=$points"
            return url
        }
        fun setUserInfoEdit(inputHeight: String,inputWeight: String,inputTeam: String,inputId: String):String{
            val url = Jsp_Url+"MypageUpdate.jsp?height=$inputHeight&weight=$inputWeight&team=$inputTeam&userId=$inputId"
            return url
        }

    }


}