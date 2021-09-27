package com.example.mlkit_pose.dao

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import com.example.mlkit_pose.dao.PreferenceHelper.set
import com.example.mlkit_pose.dao.PreferenceHelper.get

class SharedManager(context:Context) {
    private val prefs:SharedPreferences= PreferenceHelper.defaultPrefs(context)

    fun saveCurrentUser(user: User){
        prefs["name"]=user.name
        prefs["id"] =user.id
        prefs["password"]=user.password
        prefs["belong"] =user.belong
        prefs["age"]=user.age
        prefs["gender"]=user.gender
        prefs["points"]="0"
        prefs["height"]=user.height
        prefs["weight"]=user.weight
        prefs["recentDay"]=user.recentDay
        prefs["countDays"] = user.countDays
        prefs["img"] = user.img
    }
    fun getCurrentUser() : User {
        return User().apply {
            name = prefs["name",""]
            id= prefs["id",""]
            img =prefs["img",""]
            password = prefs["password",""]
            belong = prefs["belong",""]
            age = prefs["age",0]
            gender= prefs["gender",""]
            points = prefs["points",""]
            height =prefs["height",""]
            weight = prefs["weight",""]
            recentDay =prefs["recentDay",""]
            countDays = prefs["countDays",0]

        }
    }
    fun DeleteCurrentUser(user: User){
        prefs["name"]=null
        prefs["id"] = null
        prefs["password"]=null
        prefs["belong"] =null
        prefs["age"]=0
        prefs["gender"]=null
        prefs["points"] = null
        prefs["height"] =null
        prefs["weight"]=null
        prefs["recentDay"]=null
        prefs["countDays"]=0
        prefs["img"] =null

    }
    fun setUserPoint(user: User, points: String?){

        prefs["points"]=points.toString()

    }

    fun setUserInfo(user: User, weight:String, height:String, belong:String){
        prefs["weight"] =weight
        prefs["height"] = height
        prefs["belong"] = belong
    }
    fun setUserCountDays(recentDay:String,countDays:Int){
        prefs["recentDay"] = recentDay.trim()
        prefs["countDays"] = countDays
    }
    fun setUserImg(bitmapString: String){
        prefs["img"] = bitmapString
    }

}