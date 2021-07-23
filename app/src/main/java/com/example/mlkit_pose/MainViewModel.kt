package com.example.mlkit_pose

import android.annotation.SuppressLint
import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private val sharedManager: SharedManager by lazy {
        SharedManager(context)
    }

    val currentUser = sharedManager.getCurrentUser()
    val height: MutableLiveData<String> = MutableLiveData()
    val weight: MutableLiveData<String> = MutableLiveData()
    val belong: MutableLiveData<String> = MutableLiveData()
    fun init() {
        height.value = currentUser.height
        weight.value = currentUser.weight
        belong.value = currentUser.belong

    }

    fun editUser(input_height: String, input_weight: String, input_belong: String) {
        height.value = input_height
        weight.value = input_weight
        belong.value = input_belong
//        currentUser.height  = input_height
//        currentUser.weight = input_weight
//        currentUser.belong = input_belong
        sharedManager.setUserInfo(
            currentUser,
            weight.value.toString(),
            height.value.toString(),
            belong.value.toString()
        )
        Log.d("userinfo", "${currentUser.height},${currentUser.weight}")

    }

    override fun onCleared() {
        Log.d("userinfo", "edited info ${currentUser.height},${currentUser.weight}")
        super.onCleared()

    }

}