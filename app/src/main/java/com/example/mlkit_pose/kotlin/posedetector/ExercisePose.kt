package com.example.mlkit_pose.kotlin.posedetector

class ExercisePose{
    // Hip angle
    private var rightHipAngleS : Double
    private var rightHipAngleD : Double
    private var leftHipAngleS : Double
    private var leftHipAngleD : Double
    // Shoulder angle
    private var rightShoulderAngleS : Double
    private var rightShoulderAngleD : Double
    private var leftShoulderAngleS : Double
    private var leftShoulderAngleD : Double
    // Elbow Angle
    private var rightElbowAngleS : Double
    private var rightElbowAngleD : Double
    private var leftElbowAngleS : Double
    private var leftElbowAngleD : Double
    // Knee Angle
    private var rightKneeAngleS : Double
    private var rightKneeAngleD : Double
    private var leftKneeAngleS : Double
    private var leftKneeAngleD : Double

    constructor(rhs : Double,rhd: Double,lhs: Double,lhd: Double,rss: Double
                ,rsd: Double,lss: Double,lsd: Double,res: Double,red: Double,
                les: Double,led: Double,rks: Double,rkd: Double,lks: Double,lkd: Double){
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
    }
    public fun isAngle_rh(rh : Double) : Boolean{
        if (rightHipAngleS <= rh && rightHipAngleD >= rh){
            return true
        }
        return false
    }
    public fun isAngle_lh(lh : Double) : Boolean{
        if (leftHipAngleS <= lh && leftHipAngleD >= lh){
            return true
        }
        return false
    }
    public fun isAngle_rs(rs : Double) : Boolean{
        if (rightShoulderAngleS <= rs && rightShoulderAngleD >= rs){
            return true
        }
        return false
    }
    public fun isAngle_ls(ls : Double) : Boolean{
        if (leftShoulderAngleS <= ls && leftShoulderAngleD >= ls){
            return true
        }
        return false
    }
    public fun isAngle_re(re : Double) : Boolean{
        if (rightElbowAngleS <= re && rightElbowAngleD >= re){
            return true
        }
        return false
    }
    public fun isAngle_le(le : Double) : Boolean{
        if (leftElbowAngleS <= le && leftElbowAngleD >= le){
            return true
        }
        return false
    }
    public fun isAngle_rk(rk : Double) : Boolean{
        if (rightKneeAngleS <= rk && rightKneeAngleD >= rk){
            return true
        }
        return false
    }
    public fun isAngle_lk(lk : Double) : Boolean{
        if (leftKneeAngleS <= lk && leftKneeAngleD >= lk){
            return true
        }
        return false
    }
}