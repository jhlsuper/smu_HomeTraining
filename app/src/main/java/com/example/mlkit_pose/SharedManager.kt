package com.example.mlkit_pose

import android.content.Context
import android.content.SharedPreferences
import com.example.mlkit_pose.PreferenceHelper.set
import com.example.mlkit_pose.PreferenceHelper.get
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
    }
    fun getCurrentUser() :User{
        return User().apply {
            name = prefs["name",""]
            id= prefs["id",""]
            password = prefs["password",""]
            belong = prefs["belong",""]
            age = prefs["age",0]
            gender= prefs["gender",""]
            points = prefs["points",""]
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

    }
    fun setUserPoint(user:User,points:Int){
        prefs["points"]=points.toString()
    }

}