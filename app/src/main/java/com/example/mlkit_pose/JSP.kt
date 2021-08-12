package com.example.mlkit_pose

class JSP {
    companion object {
        private val Jsp_Url = "http://13.125.123.238:8080/"  //git pull 받으면 이거 고쳐서 쓸것!

        fun getLoginURL(inputId:String, inputPw:String):String{
            val url = Jsp_Url+"Login.jsp?userId=${inputId}&userPwd=${inputPw}"
            return url
        }

        fun getSignInURL(inputName:String, inputId:String,inputPw: String,inputTeam:String,inputAge:String,inputGender:String,inputHeight:String,inputWeight:String):String{
            val url = Jsp_Url+"Sign.jsp?userName=$inputName&userId=$inputId&userPwd=$inputPw&userTeam=$inputTeam&userAge=$inputAge&userGender=$inputGender&userHeight=$inputHeight&userWeight=$inputWeight"
            return url
        }
        fun getEveryRank(inputTeam: String):String{
            val url = Jsp_Url+"Ranking.jsp?userTeam=$inputTeam"
            return url
        }
        fun getUserRank(inputId:String) :String{
            val url = Jsp_Url+"MainRanking.jsp?userId=$inputId"
            return url
        }

        fun getSportsName(inputParts:String):String{
            val url = Jsp_Url+"Part.jsp?part=$inputParts"
            return url
        }

        fun getSportsDetail(inputSports:String):String{
            val url = Jsp_Url+"Guide.jsp?guidename=$inputSports"
            return url
        }
        fun getSportsPic(inputSports: String):String{

            val url = Jsp_Url+"img/$inputSports"+".png"
            return url
        }
        fun getUserPointSet(inputId:String ,points:String):String{
            val url = Jsp_Url+"InsertScore.jsp?userId=$inputId&userScore=$points"
            return url
        }
        fun setUserInfoEdit(inputHeight: String,inputWeight: String,inputTeam: String,inputId: String):String{
            val url = Jsp_Url+"MypageUpdate.jsp?height=$inputHeight&weight=$inputWeight&team=$inputTeam&userId=$inputId"
            return url
        }
        // Routine Insert Part
        fun setRoutineIns(inputId: String,routineName:String,execName:String,engExcName:String):String{
//            val url = Jsp_Url+"RoutineInsert.jsp?userId=$inputId&routine_name=$routineName&exc_name=$execName&engexc_name=$engExcName"
            val url = Jsp_Url+"insert.jsp?userId=$inputId&routine_name=$routineName&exc_name=$execName&engexc_name=$engExcName"
            return url
        }
        fun getRoutineList(inputId: String) : String{
            val url = Jsp_Url+"RoutineListCheck.jsp?UserId=${inputId}"
            return url
        }
        fun getRoutineCheck(inputId: String) : String {
            val url = Jsp_Url+"RoutineCheck.jsp?UserId=${inputId}"
            return url
        }
        fun getRoutineNameList(inputId: String?, routineName: String) : String{
            val url = Jsp_Url+"RoutineExcCheck.jsp?UserId=$inputId&RoutineName=$routineName"
            return url
        }
    }


}