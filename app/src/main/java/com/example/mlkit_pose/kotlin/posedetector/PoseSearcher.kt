package com.example.mlkit_pose.kotlin.posedetector

class PoseSearcher {
    private val poseList:ArrayList<ExercisePose> = ArrayList()
    /*
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
     */
    init{ // 운동 목록 초기화
        val ShoulderPress:ExercisePose = ExercisePose(listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                    listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                    listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                    listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                    listOf(155.0,180.0,1.0),listOf(77.0,99.0,1.0),
                                                    listOf(155.0,180.0,1.0),listOf(77.0,99.0,1.0),
                                                    listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                    listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),"ShoulderPress")
        poseList.add(ShoulderPress)
    }

    public fun searchExByName(exname :String?) : ExercisePose?{
        if (exname == null){
            return null
        }
        for (ep in poseList){
            if (ep.getExName() == exname){
                return ep
            }
        }
        return null
    }

}