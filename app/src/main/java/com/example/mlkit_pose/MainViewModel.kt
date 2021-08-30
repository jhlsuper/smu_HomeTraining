package com.example.mlkit_pose

import android.annotation.SuppressLint
import android.app.Application
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
    val point: MutableLiveData<String> = MutableLiveData()
    val recentDay: MutableLiveData<String> = MutableLiveData()
    val countDays: MutableLiveData<Int> = MutableLiveData()
    fun init() {
        height.value = currentUser.height
        weight.value = currentUser.weight
        belong.value = currentUser.belong
        point.value = currentUser.points
        recentDay.value = currentUser.recentDay
        countDays.value = currentUser.countDays
    }

    fun editUser(input_height: String, input_weight: String, input_belong: String) {
        height.value = input_height
        weight.value = input_weight
        belong.value = input_belong

        sharedManager.setUserInfo(
            currentUser,
            weight.value.toString(),
            height.value.toString(),
            belong.value.toString()
        )
        Log.d("userinfo", "${currentUser.height},${currentUser.weight}")

    }
    fun editPoint(input_point:String){
        point.value = input_point
        sharedManager.setUserPoint(currentUser,point.value.toString())
        Log.d("userPoint","${currentUser.points}")
    }

    fun editExerciseDay(input_recentDay:String,input_countDays:Int){
        recentDay.value = input_recentDay
        countDays.value = input_countDays
        sharedManager.setUserCountDays(input_recentDay,input_countDays)
    }


    override fun onCleared() {
        Log.d("userinfo", "edited info ${currentUser.height},${currentUser.weight}")
        super.onCleared()

    }


}