package com.example.mlkit_pose.kotlin.posedetector

class PoseSearcher {
    private val poseList:ArrayList<ExercisePose> = ArrayList()
    /*
    rightHipAngleS = rhs        rightHipAngleD = rhd
    leftHipAngleS = lhs         leftHipAngleD = lhd
    rightShoulderAngleS = rss   rightShoulderAngleD = rsd
    leftShoulderAngleS = lss    leftShoulderAngleD = lsd
    rightElbowAngleS = res      rightElbowAngleD = red
    leftElbowAngleS = les       leftElbowAngleD = led
    rightKneeAngleS = rks       rightKneeAngleD = rkd
    leftKneeAngleS = lks        leftKneeAngleD = lkd    excerciseName
     */
    /*
    PushUp    FullPlank    BackLift    Superman    *ShoulderPressDB    UpDownPlank    CrossoverExerciseDB
    LegRaise    SeatedKneeup    BirdDog    DeadBug    SitUp   ElbowPlank    SideBandDB    ForwardBand
    StepOnOneFoot    WideSquat    SideLegRaise    Lunge    Bridge    BandSideStep
     */

    init{ // 운동 목록 초기화 # S (Raisd) D (Down)
        val ShoulderPressDB:ExercisePose = ExercisePose(listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                    listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                    listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                    listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                    listOf(135.0,180.0,1.0),listOf(73.0,99.0,1.0),
                                                    listOf(135.0,180.0,1.0),listOf(73.0,99.0,1.0),
                                                    listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                    listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),"ShoulderPressDB")
        poseList.add(ShoulderPressDB)
        val WideSquat:ExercisePose = ExercisePose(listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),
                                                listOf(0.0,180.0,-1.0),listOf(0.0,180.0,-1.0),"WideSquat")
        poseList.add(WideSquat)
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