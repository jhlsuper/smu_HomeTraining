package com.example.mlkit_pose.kotlin.posedetector

import android.util.Log

class ExercisePose(
    rhs: List<Double>,
    rhd: List<Double>,
    lhs: List<Double>,
    lhd: List<Double>,
    rss: List<Double>,
    rsd: List<Double>,
    lss: List<Double>,
    lsd: List<Double>,
    res: List<Double>,
    red: List<Double>,
    les: List<Double>,
    led: List<Double>,
    rks: List<Double>,
    rkd: List<Double>,
    lks: List<Double>,
    lkd: List<Double>,
    exN: String
) {
    private val exName : String? = exN

    // Hip angle
    var rightHipAngleS : List<Double> = rhs
        get() {
            return field
        }
    var rightHipAngleD : List<Double> = rhd
        get() {
            return field
        }
    var leftHipAngleS : List<Double> = lhs
        get() {
            return field
        }
    var leftHipAngleD : List<Double> = lhd
        get() {
            return field
        }

    // Shoulder angle
    var rightShoulderAngleS : List<Double> = rss
        get() {
            return field
        }
    var rightShoulderAngleD : List<Double> = rsd
        get() {
            return field
        }
    var leftShoulderAngleS : List<Double> = lss
        get() {
            return field
        }
    var leftShoulderAngleD : List<Double> = lsd
        get() {
            return field
        }

    // Elbow Angle
    var rightElbowAngleS : List<Double> = res
        get() {
            return field
        }
    var rightElbowAngleD : List<Double> = red
        get() {
            return field
        }
    var leftElbowAngleS : List<Double> = les
        get() {
            return field
        }
    var leftElbowAngleD : List<Double> = led
        get() {
            return field
        }

    // Knee Angle
    var rightKneeAngleS : List<Double> = rks
        get() {
            return field
        }
    var rightKneeAngleD : List<Double> = rkd
        get() {
            return field
        }
    var leftKneeAngleS : List<Double> = lks
        get() {
            return field
        }
    var leftKneeAngleD : List<Double> = lkd
        get() {
            return field
        }

    //S 는 Up인 상태 , D는 Down인 상태
    // list [start, end] 로 구성

    // Hip Angle
    public fun isAngle_rhS(rh : Double) : Boolean { //S 는 ?
        if (rightHipAngleS.get(0) <= rh && rightHipAngleS.get(1) >= rh){
//            Log.d("PoseGraphic","PoseGraphic Angle Line1")
            return true
        }
        return false
    }
    public fun isAngle_rhD(rh : Double) : Boolean {
        if (rightHipAngleD.get(0) <= rh && rightHipAngleD.get(1) >= rh){
//            Log.d("PoseGraphic","PoseGraphic Angle Line2")
            return true
        }
        return false
    }
    public fun isAngle_lhS(lh : Double) : Boolean {
        if (leftHipAngleS.get(0) <= lh && leftHipAngleS.get(1) >= lh){
//            Log.d("PoseGraphic","PoseGraphic Angle Line3")
            return true
        }
        return false
    }
    public fun isAngle_lhD(lh : Double) : Boolean {
        if (leftHipAngleD.get(0) <= lh && leftHipAngleD.get(1) >= lh){
//            Log.d("PoseGraphic","PoseGraphic Angle Line4")
            return true
        }
        return false
    }

    // Shoulder Angle
    public fun isAngle_rsS(rs : Double) : Boolean {
        if (rightShoulderAngleS.get(0) <= rs && rightShoulderAngleS.get(1) >= rs){
//            Log.d("PoseGraphic","PoseGraphic Angle Line5")
            return true
        }
        return false
    }
    public fun isAngle_rsD(rs : Double) : Boolean {
        if (rightShoulderAngleD.get(0) <= rs && rightShoulderAngleD.get(1) >= rs){
//            Log.d("PoseGraphic","PoseGraphic Angle Line6")
            return true
        }
        return false
    }
    public fun isAngle_lsS(ls : Double) : Boolean {
        if (leftShoulderAngleS.get(0) <= ls && leftShoulderAngleS.get(1) >= ls){
//            Log.d("PoseGraphic","PoseGraphic Angle Line7")
            return true
        }
        return false
    }
    public fun isAngle_lsD(ls : Double) : Boolean {
        if (leftShoulderAngleD.get(0) <= ls && leftShoulderAngleD.get(1) >= ls){
//            Log.d("PoseGraphic","PoseGraphic Angle Line8")
            return true
        }
        return false
    }

    // Elbow Angle
    public fun isAngle_reS(re : Double) : Boolean {
        if (rightElbowAngleS.get(0) <= re && rightElbowAngleS.get(1) >= re){
//            Log.d("PoseGraphic","PoseGraphic Angle Line9")
            return true
        }
        return false
    }
    public fun isAngle_reD(re : Double) : Boolean {
        if (rightElbowAngleD.get(0) <= re && rightElbowAngleD.get(1) >= re){
//            Log.d("PoseGraphic","PoseGraphic Angle Line10")
            return true
        }
        return false
    }
    public fun isAngle_leS(le : Double) : Boolean {
        if (leftElbowAngleS.get(0) <= le && leftElbowAngleS.get(1) >= le){
//            Log.d("PoseGraphic","PoseGraphic Angle Line11")
            return true
        }
        return false
    }
    public fun isAngle_leD(le : Double) : Boolean {
        if (leftElbowAngleD.get(0) <= le && leftElbowAngleD.get(1) >= le){
//            Log.d("PoseGraphic","PoseGraphic Angle Line12")
            return true
        }
        return false
    }

    // Knee Angle
    public fun isAngle_rkS(rk : Double) : Boolean {
        if (rightKneeAngleS.get(0) <= rk && rightKneeAngleS.get(1) >= rk){
//            Log.d("PoseGraphic","PoseGraphic Angle Line13")
            return true
        }
        return false
    }
    public fun isAngle_rkD(rk : Double) : Boolean {
        if (rightKneeAngleD.get(0) <= rk && rightKneeAngleD.get(1) >= rk){
//            Log.d("PoseGraphic","PoseGraphic Angle Line14")
            return true
        }
        return false
    }
    public fun isAngle_lkS(lk : Double) : Boolean {
        if (leftKneeAngleS.get(0) <= lk && leftKneeAngleS.get(1) >= lk){
//            Log.d("PoseGraphic","PoseGraphic Angle Line15")
            return true
        }
        return false
    }
    public fun isAngle_lkD(lk : Double) : Boolean {
        if (leftKneeAngleD.get(0) <= lk && leftKneeAngleD.get(1) >= lk){
//            Log.d("PoseGraphic","PoseGraphic Angle Line16")
            return true
        }
        return false
    }

    public fun getExName() : String?{
        return exName
    }
    public fun getEnable(anglesList : List<Double>) : Boolean {
//        Log.d("PoseGraphic","Enable Line")
        if (anglesList.get(2) > 0.0 ){
            return true
        }
        return false
    }
}