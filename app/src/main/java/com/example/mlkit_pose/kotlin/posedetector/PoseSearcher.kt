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
        val DeadBug: ExercisePose = ExercisePose(
            listOf(166.0, 190.0, 1.0), listOf(70.0, 110.0, 1.0),
            listOf(166.0, 190.0, 1.0), listOf(70.0, 110.0, 1.0),
            listOf(160.0, 180.0, 1.0), listOf(70.0, 100.0, 1.0),
            listOf(160.0, 180.0, 1.0), listOf(70.0, 100.0, 1.0),
            listOf(152.0, 200.0, 1.0), listOf(152.0, 200.0, 1.0),
            listOf(152.0, 200.0, 1.0), listOf(152.0, 200.0, 1.0),
            listOf(160.0, 190.0, 1.0), listOf(55.0, 95.0, 1.0),
            listOf(160.0, 190.0, 1.0), listOf(55.0, 95.0, 1.0), "DeadBug"
        )
        poseList.add(DeadBug)
        val SitUp: ExercisePose = ExercisePose(
            listOf(50.0, 65.0, 1.0), listOf(170.0, 190.0, 1.0),
            listOf(50.0, 65.0, 1.0), listOf(170.0, 190.0, 1.0),
            listOf(70.0, 90.0, 1.0), listOf(160.0, 185.0, 1.0),
            listOf(70.0, 90.0, 1.0), listOf(160.0, 185.0, 1.0),
            listOf(155.0, 185.0, 1.0), listOf(155.0, 185.0, 1.0),
            listOf(155.0, 185.0, 1.0), listOf(155.0, 185.0, 1.0),
            listOf(140.0, 170.0, 1.0), listOf(160.0, 185.0, 1.0),
            listOf(140.0, 170.0, 1.0), listOf(160.0, 185.0, 1.0), "SitUp"
        )
        poseList.add(SitUp)
        val ElbowPlank: ExercisePose = ExercisePose(
            listOf(160.0, 180.0, 1.0), listOf(160.0, 180.0, 1.0),
            listOf(160.0, 180.0, 1.0), listOf(160.0, 180.0, 1.0),
            listOf(60.0, 90.0, 1.0), listOf(60.0, 90.0, 1.0),
            listOf(60.0, 90.0, 1.0), listOf(60.0, 90.0, 1.0),
            listOf(70.0, 90.0, 1.0), listOf(70.0, 90.0, 1.0),
            listOf(70.0, 90.0, 1.0), listOf(70.0, 90.0, 1.0),
            listOf(160.0, 180.0, 1.0), listOf(160.0, 180.0, 1.0),
            listOf(160.0, 180.0, 1.0), listOf(160.0, 180.0, 1.0), "ElbowPlank"
        )
        poseList.add(ElbowPlank)
        val SideBandDB: ExercisePose = ExercisePose(
            listOf(165.0, 180.0, 1.0), listOf(165.0, 180.0, 1.0),
            listOf(165.0, 180.0, 1.0), listOf(165.0, 180.0, 1.0),
            listOf(25.0, 40.0, 1.0), listOf(15.0, 30.0, 1.0),
            listOf(25.0, 40.0, 1.0), listOf(15.0, 30.0, 1.0),
            listOf(165.0, 180.0, 1.0), listOf(165.0, 180.0, 1.0),
            listOf(165.0, 180.0, 1.0), listOf(165.0, 180.0, 1.0),
            listOf(165.0, 180.0, 1.0), listOf(165.0, 180.0, 1.0),
            listOf(165.0, 180.0, 1.0), listOf(165.0, 180.0, 1.0), "SideLegRaise"
        )
        poseList.add(SideBandDB)
        val ForwardBand: ExercisePose = ExercisePose(
            listOf(40.0, 60.0, 1.0), listOf(165.0, 180.0, 1.0),
            listOf(40.0, 60.0, 1.0), listOf(165.0, 180.0, 1.0),
            listOf(105.0, 120.0, 1.0), listOf(0.0, 25.0, 1.0),
            listOf(105.0, 120.0, 1.0), listOf(0.0, 25.0, 1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(0.0, 180.0, -1.0), listOf(0.0, 180.0, -1.0),
            listOf(165.0, 180.0, 1.0), listOf(165.0, 180.0, 1.0),
            listOf(165.0, 180.0, 1.0), listOf(165.0, 180.0, 1.0), "SideLegRaise"
        )
        poseList.add(ForwardBand)
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