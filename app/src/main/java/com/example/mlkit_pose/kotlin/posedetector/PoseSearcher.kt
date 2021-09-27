package com.example.mlkit_pose.kotlin.posedetector

class PoseSearcher {
    private val poseList: ArrayList<ExercisePose> = ArrayList()
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
    PushUp    FullPlank    BackLift    Superman    -ShoulderPressDB-    UpDownPlank    CrossoverExerciseDB
    LegRaise    SeatedKneeup    BirdDog    DeadBug    SitUp   ElbowPlank    SideBandDB    ForwardBand
    StepOnOneFoot    WideSquat    SideLegRaise    Lunge    Bridge    BandSideStep
     */

    init { // 운동 목록 초기화 # S (Raisd) D (Down)
        val ShoulderPressDB: ExercisePose = ExercisePose(
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(145.0, 180.0, 1.0), listOf(77.0, 99.0, 1.0),
            listOf(145.0, 180.0, 1.0), listOf(77.0, 99.0, 1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0), "ShoulderPressDB"
        )
        poseList.add(ShoulderPressDB)
        val WideSquat: ExercisePose = ExercisePose(
            listOf(140.0, 165.0, 1.0), listOf(60.0, 90.0, 1.0),
            listOf(140.0, 165.0, 1.0), listOf(60.0, 90.0, 1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(150.0, 180.0, 1.0), listOf(50.0, 96.0, 1.0),
            listOf(150.0, 180.0, 1.0), listOf(50.0, 96.0, 1.0), "WideSquat"
        )
        poseList.add(WideSquat)
        val SideLegRaise: ExercisePose = ExercisePose(
            listOf(110.0, 144.5, 1.0), listOf(165.0, 180.0, 1.0),
            listOf(110.0, 144.5, 1.0), listOf(165.0, 180.0, 1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0), "SideLegRaise"
        )
        poseList.add(SideLegRaise)


    }

    public fun searchExByName(exname: String?): ExercisePose? {
        if (exname == null) {
            return null
        }
        for (ep in poseList) {
            if (ep.getExName() == exname) {
                return ep
            }
        }
        return null
    }

}