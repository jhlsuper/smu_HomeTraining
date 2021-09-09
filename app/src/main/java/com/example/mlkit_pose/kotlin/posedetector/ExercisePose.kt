package com.example.mlkit_pose.kotlin.posedetector

import android.util.Log

class ExercisePose(
    rhs: List<Double>,
    rhd: List<Double>,
    rhm: List<Double>,
    lhs: List<Double>,
    lhd: List<Double>,
    lhm: List<Double>,
    rss: List<Double>,
    rsd: List<Double>,
    rsm: List<Double>,
    lss: List<Double>,
    lsd: List<Double>,
    lsm: List<Double>,
    res: List<Double>,
    red: List<Double>,
    rem: List<Double>,
    les: List<Double>,
    led: List<Double>,
    lem: List<Double>,
    rks: List<Double>,
    rkd: List<Double>,
    rkm: List<Double>,
    lks: List<Double>,
    lkd: List<Double>,
    lkm: List<Double>,
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
    var rightHipAngleM : List<Double> = rhm
        get(){
            return field
        }
    var leftHipAngleM : List<Double> = lhm
        get(){
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
    var rightShoulderAngleM : List<Double> = rsm
        get(){
            return field
        }
    var leftShoulderAngleM : List<Double> = lsm
        get(){
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
    var rightElbowAngleM : List<Double> = rem
        get(){
            return field
        }
    var leftElbowAngleM : List<Double> = lem
        get(){
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
    var rightKneeAngleM : List<Double> = rkm
        get(){
            return field
        }
    var leftKneeAngleM : List<Double> = lkm
        get(){
            return field
        }

    //S 는 Up인 상태 , D는 Down인 상태
    // list [start, end] 로 구성

    // Hip Angle
    public fun isAngle_rhS(rh : Double) : Boolean { //S 는 ?
        if (rightHipAngleS.get(0) <= rh && rightHipAngleS.get(1) >= rh){
            return true
        }
        return false
    }
    public fun isAngle_rhD(rh : Double) : Boolean {
        if (rightHipAngleD.get(0) <= rh && rightHipAngleD.get(1) >= rh){
            return true
        }
        return false
    }

    public fun isAngle_rhM(rh : Double) : Boolean {
        if (rightHipAngleM.get(0) <= rh && rightHipAngleM.get(1) >= rh){
            return true
        }
        return false
    }
    public fun isAngle_lhS(lh : Double) : Boolean {
        if (leftHipAngleS.get(0) <= lh && leftHipAngleS.get(1) >= lh){
            return true
        }
        return false
    }
    public fun isAngle_lhD(lh : Double) : Boolean {
        if (leftHipAngleD.get(0) <= lh && leftHipAngleD.get(1) >= lh){
            return true
        }
        return false
    }
    public fun isAngle_lhM(lh : Double) : Boolean {
        if (leftHipAngleM.get(0) <= lh && leftHipAngleM.get(1) >= lh){
            return true
        }
        return false
    }

    // Shoulder Angle
    public fun isAngle_rsS(rs : Double) : Boolean {
        if (rightShoulderAngleS.get(0) <= rs && rightShoulderAngleS.get(1) >= rs){
            return true
        }
        return false
    }
    public fun isAngle_rsD(rs : Double) : Boolean {
        if (rightShoulderAngleD.get(0) <= rs && rightShoulderAngleD.get(1) >= rs){
            return true
        }
        return false
    }
    public fun isAngle_rsM(rs : Double) : Boolean {
        if (rightShoulderAngleM.get(0) <= rs && rightShoulderAngleM.get(1) >= rs){
            return true
        }
        return false
    }
    public fun isAngle_lsS(ls : Double) : Boolean {
        if (leftShoulderAngleS.get(0) <= ls && leftShoulderAngleS.get(1) >= ls){
            return true
        }
        return false
    }
    public fun isAngle_lsD(ls : Double) : Boolean {
        if (leftShoulderAngleD.get(0) <= ls && leftShoulderAngleD.get(1) >= ls){
            return true
        }
        return false
    }
    public fun isAngle_lsM(ls : Double) : Boolean {
        if (leftShoulderAngleM.get(0) <= ls && leftShoulderAngleM.get(1) >= ls){
            return true
        }
        return false
    }

    // Elbow Angle
    public fun isAngle_reS(re : Double) : Boolean {
        if (rightElbowAngleS.get(0) <= re && rightElbowAngleS.get(1) >= re){
            return true
        }
        return false
    }
    public fun isAngle_reD(re : Double) : Boolean {
        if (rightElbowAngleD.get(0) <= re && rightElbowAngleD.get(1) >= re){
            return true
        }
        return false
    }
    public fun isAngle_reM(re : Double) : Boolean {
        if (rightElbowAngleM.get(0) <= re && rightElbowAngleM.get(1) >= re){
            return true
        }
        return false
    }
    public fun isAngle_leS(le : Double) : Boolean {
        if (leftElbowAngleS.get(0) <= le && leftElbowAngleS.get(1) >= le){
            return true
        }
        return false
    }
    public fun isAngle_leD(le : Double) : Boolean {
        if (leftElbowAngleD.get(0) <= le && leftElbowAngleD.get(1) >= le){
            return true
        }
        return false
    }
    public fun isAngle_leM(le : Double) : Boolean {
        if (leftElbowAngleM.get(0) <= le && leftElbowAngleM.get(1) >= le){
            return true
        }
        return false
    }

    // Knee Angle
    public fun isAngle_rkS(rk : Double) : Boolean {
        if (rightKneeAngleS.get(0) <= rk && rightKneeAngleS.get(1) >= rk){
            return true
        }
        return false
    }
    public fun isAngle_rkD(rk : Double) : Boolean {
        if (rightKneeAngleD.get(0) <= rk && rightKneeAngleD.get(1) >= rk){
            return true
        }
        return false
    }
    public fun isAngle_rkM(rk : Double) : Boolean {
        if (rightKneeAngleM.get(0) <= rk && rightKneeAngleM.get(1) >= rk){
            return true
        }
        return false
    }
    public fun isAngle_lkS(lk : Double) : Boolean {
        if (leftKneeAngleS.get(0) <= lk && leftKneeAngleS.get(1) >= lk){
            return true
        }
        return false
    }
    public fun isAngle_lkD(lk : Double) : Boolean {
        if (leftKneeAngleD.get(0) <= lk && leftKneeAngleD.get(1) >= lk){
            return true
        }
        return false
    }
    public fun isAngle_lkM(lk : Double) : Boolean {
        if (leftKneeAngleM.get(0) <= lk && leftKneeAngleM.get(1) >= lk){
            return true
        }
        return false
    }

    public fun getExName() : String?{
        return exName
    }
    public fun getEnable(anglesList : List<Double>) : Boolean {
        if (anglesList.get(2) > 0.0 ){
            return true
        }
        return false
    }
}