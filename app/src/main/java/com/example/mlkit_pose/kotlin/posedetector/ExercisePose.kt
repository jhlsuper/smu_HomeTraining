package com.example.mlkit_pose.kotlin.posedetector

class ExercisePose{
    private val exName : String
    // Hip angle
    var rightHipAngleS : List<Double>
        get() {
            return rightHipAngleS
        }
    var rightHipAngleD : List<Double>
        get() {
            return rightHipAngleD
        }
    var leftHipAngleS : List<Double>
        get() {
            return leftHipAngleS
        }
    var leftHipAngleD : List<Double>
        get() {
            return leftHipAngleD
        }

    // Shoulder angle
    var rightShoulderAngleS : List<Double>
        get() {
            return rightShoulderAngleS
        }
    var rightShoulderAngleD : List<Double>
        get() {
            return rightShoulderAngleD
        }
    var leftShoulderAngleS : List<Double>
        get() {
            return leftShoulderAngleS
        }
    var leftShoulderAngleD : List<Double>
        get() {
            return leftShoulderAngleD
        }

    // Elbow Angle
    var rightElbowAngleS : List<Double>
        get() {
            return rightElbowAngleS
        }
    var rightElbowAngleD : List<Double>
        get() {
            return rightElbowAngleD
        }
    var leftElbowAngleS : List<Double>
        get() {
            return leftElbowAngleS
        }
    var leftElbowAngleD : List<Double>
        get() {
            return leftElbowAngleD
        }

    // Knee Angle
    var rightKneeAngleS : List<Double>
        get() {
            return rightKneeAngleS
        }
    var rightKneeAngleD : List<Double>
        get() {
            return rightKneeAngleD
        }
    var leftKneeAngleS : List<Double>
        get() {
            return leftKneeAngleS
        }
    var leftKneeAngleD : List<Double>
        get() {
            return leftKneeAngleD
        }

    constructor(rhs : List<Double>,rhd: List<Double>,lhs: List<Double>,lhd: List<Double>,rss: List<Double>
                ,rsd: List<Double>,lss: List<Double>,lsd: List<Double>,res: List<Double>,red: List<Double>,
                les: List<Double>,led: List<Double>,rks: List<Double>,rkd: List<Double>,lks: List<Double>,lkd: List<Double>,exN : String){
        rightHipAngleS = rhs
        rightHipAngleD = rhd
        leftHipAngleS = lhs
        leftHipAngleD = lhd
        rightShoulderAngleS = rss
        rightShoulderAngleD = rsd
        leftShoulderAngleS = lss
        leftShoulderAngleD = lsd
        rightElbowAngleS = res
        rightElbowAngleD = red
        leftElbowAngleS = les
        leftElbowAngleD = led
        rightKneeAngleS = rks
        rightKneeAngleD = rkd
        leftKneeAngleS = lks
        leftKneeAngleD = lkd
        exName = exN
    }
    //S 는 Up인 상태 , D는 Down인 상태
    // list [start, end] 로 구성

    // Hip Angle
    public fun isAngle_rhS(rh : Double) : Boolean {
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

    // Knee Angle
    public fun isAngle_rkS(rk : Double) : Boolean {
        if (rightKneeAngleS.get(0) <= rk && rightKneeAngleS.get(1) >= rk){
            return true
        }
        return false
    }
    public fun isAngle_rKD(rk : Double) : Boolean {
        if (rightKneeAngleD.get(0) <= rk && rightKneeAngleD.get(1) >= rk){
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

    public fun getExName() : String{
        return exName
    }
    public fun getEnable(anglesList : List<Double>) : Boolean {
        if (anglesList.get(2) > 0.0 ){
            return true
        }
        return false
    }
}