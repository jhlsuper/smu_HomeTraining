package com.example.mlkit_pose.fragment


import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.JSP
import com.example.mlkit_pose.PageActivity
import com.example.mlkit_pose.R
import kotlinx.android.synthetic.main.fragment_guide_sports.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class GuideSportsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param2: String? = null

    var number: Int = 0

    var bitmap: Bitmap? = null
    var eturl: String? = null
    var result2: String? = null
    var exname: String? = null
    var exname_k: String? = null
    var id: String? = null
    var hasPermissions = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_guide_sports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        id = (activity as PageActivity).findUserId()

        setFragmentResultListener("requestKey2") { resultKey, bundle ->
            bundle.getString("bundleKey2")?.let {
                Log.d("result222", it)
                sport_detail_name.setText(it)
                result2 = it
                Log.d("start2", result2.equals(it).toString())
            }
            Log.d("start", result2.toString())

            val url = JSP.getSportsDetail(result2.toString())
            val queue = Volley.newRequestQueue(context)
//        val url = "Guide.jsp?guidename=브릿지"

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    response.trim { it <= ' ' }
                    val arr2 = response.split("$").toTypedArray()
                    if (response == "error") {
                        Log.d("error", "error")
                    } else {
//                    for (i in 0 until arr2.size) {
//                        Sport_guide.array_detail.add(arr2[i])
//                    }

                        // 운동 설명
                        sport_detail_text.setText(arr2[0])


                        // 운동 링크
//                    sports_link_check.setOnClickListener {
//                        fun onClick(v: View?) {
//                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(arr2[2]))
//                            startActivity(intent)
//                        }
//                    }
                        eturl = arr2[1]
                        // Setting Eng name
                        sport_detail_ename.text = arr2[2]
                        exname_k = sport_detail_name.text as String?
                        val testTEXT = arr2[2]
                        exname = arr2[2]
                    }
                },
                { })
            queue.add(stringRequest)

            val uThread: Thread = object : Thread() {
                override fun run() {
                    try {
                        val url = URL(JSP.getSportsPic(result2.toString()))
//                    Log.d("geturl",url.toString())

                        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
                        conn.setDoInput(true) //Server 통신에서 입력 가능한 상태로 만듦
                        conn.connect() //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
                        val `is`: InputStream = conn.getInputStream() //inputStream 값 가져오기
                        bitmap = BitmapFactory.decodeStream(`is`) // Bitmap으로 반환
                    } catch (e: MalformedURLException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            uThread.start() // 작업 Thread 실행

            try {
                uThread.join()
                pengsu.setImageBitmap(bitmap)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            sports_link_check.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(eturl))
                startActivity(intent)
            }

        }
        btn_start_exercise.setOnClickListener {
//            (activity as PageActivity).startExcercise(exname)
            var flag = false

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                Toast.makeText(requireContext(), "거부할 시 카메라 사용에 문제가 있을 수 있습니다.", Toast.LENGTH_LONG).show()
//                hasPermissions = (activity as PageActivity).requestPermission()
//                Log.d("user_permission",hasPermissions.toString())
//
//            } else {
//                Toast.makeText(requireContext(), "This library requires API 21>", Toast.LENGTH_SHORT).show();
//            }
//            if (hasPermissions) {
//                (activity as PageActivity).showTimeSettingPopup(exname,exname_k, requireContext())
//            }
            val cameraPermissionCheck = ContextCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.CAMERA
            )
            if (cameraPermissionCheck != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.RECORD_AUDIO,android.Manifest.permission.ACCESS_FINE_LOCATION), 1001)
                Toast.makeText(requireContext(), "거부할 시 카메라 사용에 문제가 있을 수 있습니다.", Toast.LENGTH_LONG).show()
            } else {
                flag = true
                (activity as PageActivity).showTimeSettingPopup(exname,exname_k, requireContext())
            }
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED && !flag
            ) {
                (activity as PageActivity).showTimeSettingPopup(exname,exname_k, requireContext())
            }

        }
        btn_add_routine.setOnClickListener {
            MyRoutinePopup()
        }
    }


//    fun showTimeSettingPopup(exEname:String?) {
//
//        val dialog = AlertDialog.Builder(context).create()
//
//        val edialog: LayoutInflater = LayoutInflater.from(context)
//        val mView: View = edialog.inflate(R.layout.popup_settime, null)
//
//        val minute: NumberPicker = mView.findViewById(R.id.numberPicker_min)
//        val second: NumberPicker = mView.findViewById(R.id.numberPicker_sec)
//
//        val cancel: Button = mView.findViewById<Button>(R.id.btn_settime_no)
//        val start: Button = mView.findViewById<Button>(R.id.btn_settime_ok)
//        // editText 설정해제
//        minute.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
//        second.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
//        //최소값 설정
//        minute.minValue = 0
//        second.minValue = 0
//
//        //최대값 설정
//        minute.maxValue = 30
//        second.maxValue = 59
//        //기본값 설정
//        minute.value = 1
//        second.value = 0
//
//        //보여질 값 설정
//
//
//        //취소버튼
//        cancel.setOnClickListener {
//            dialog.dismiss()
//            dialog.cancel()
//        }
//        start.setOnClickListener {
//            Toast.makeText(context, "${minute.value}분 ${second.value}초", Toast.LENGTH_SHORT).show()
//            (activity as PageActivity).startExcercise(exEname, minute.value, second.value)
//            dialog.dismiss()
//        }
//        dialog.setView(mView)
//        dialog.create()
//        dialog.show()
//        dialog.window!!.setLayout(750, WindowManager.LayoutParams.WRAP_CONTENT)
//
//    }

    fun MyRoutinePopup() {

        val list: MutableList<Model> by lazy {
            mutableListOf<Model>()
        }
        Log.d("userId","$id")
        val dialog = AlertDialog.Builder(context).create()
        val edialog: LayoutInflater = LayoutInflater.from(context)
        val mView: View = edialog.inflate(R.layout.popup_add_myroutine, null) //팝업창을 띄우는 코드
        val insert_button: Button = mView.findViewById<Button>(R.id.add_to_routine)
//        val checkbox:CheckBox =mView.findViewById<CheckBox>(R.id.checkBox)
        val cancel_button: Button = mView.findViewById<Button>(R.id.add_to_routine_cancel)
        val recyclerView: RecyclerView = mView.findViewById(R.id.recyclerView)
        val adapter = Adapter(list, R.layout.item_model, requireContext())
        recyclerView.adapter = adapter
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL, false
        )

        // 루틴 이름들 셋팅
        val queue = Volley.newRequestQueue(context)
        val url:String = JSP.getRoutineCheck(id!!)
        val stringRequest1 = StringRequest(Request.Method.GET, url, {  response ->
            response.trim {it <= ' '}
            val routineList = response.split(",")
            for ((i, item) in routineList.withIndex()){
                if (i==routineList.size-1){
                    break
                }
                Log.d("GuideClick","${item}, ${i}, ${routineList.size}")
                list.add(Model(item, i+1))
            }
            adapter.notifyDataSetChanged()
        },{Log.d("GuideClick","Volley Error") })
        queue.add(stringRequest1)

        // 루틴에 추가 버튼 누를 때 --> Volley 처리
        insert_button.setOnClickListener {
            Log.d("GuideClick","Insert to ${adapter.selectedItem}, item : ${exname}, ${sport_detail_name.text}")
            val queue = Volley.newRequestQueue(context)
            val urls:ArrayList<StringRequest> = ArrayList<StringRequest>()
            for (item:String in adapter.selectedItem){
                val url_item:String = JSP.setRoutineInsOne(id!!,item,exname_k!!,exname!!)
                val stringRequests = StringRequest(Request.Method.GET,url_item,{ response ->
                    response.trim{it<=' '}
                    Log.d("GuideClick_Insert", response)
                },{
                    Log.d("GuideClick","Volley Error")
                })
                urls.add(stringRequests)
            }
            for (requests:StringRequest in urls){
                queue.add(requests)
            }
            dialog.dismiss()
        }
        // 취소 버튼 누를 때
        cancel_button.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setView(mView)
        dialog.create()
        dialog.show()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            1001 ->
                if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    (activity as PageActivity).showTimeSettingPopup(exname,exname_k, requireContext())
                }
                //Alert 만들 것
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GuideSportsFragment().apply {
                arguments = Bundle().apply {
                    putString("id", param1)
                }
            }

        // var result: String? = null

    }
}




