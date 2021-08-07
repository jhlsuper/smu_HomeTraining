package com.example.mlkit_pose.fragment


import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.JSP
import com.example.mlkit_pose.PageActivity
import com.example.mlkit_pose.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_bottom_menu.*
import kotlinx.android.synthetic.main.fragment_guide_click.*
import kotlinx.android.synthetic.main.fragment_guide_sports.*
import kotlinx.android.synthetic.main.fragment_guide_sports.view.*
import kotlinx.android.synthetic.main.fragment_main_page_part.*
import kotlinx.android.synthetic.main.fragment_tool_bar.*
import kotlinx.android.synthetic.main.item_model.*
import kotlinx.android.synthetic.main.popup_add_myroutine.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GuideSportsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param2: String? = null
    var number :Int= 0
    val list :MutableList<Model> by lazy {
        mutableListOf<Model>()
    }
    var bitmap: Bitmap? = null
    var eturl: String? = null
    var result2: String? = null
    var exname: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            result = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }

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
                        val testTEXT = arr2[2]
                        Log.d("EnameCHECK", "Ename $testTEXT")
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
            showTimeSettingPopup()

        }
        btn_add_routine.setOnClickListener{
            MyRoutinePopup()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GuideSportsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        // var result: String? = null

    }

    fun showTimeSettingPopup() {

        val dialog = AlertDialog.Builder(context).create()

        val edialog: LayoutInflater = LayoutInflater.from(context)
        val mView: View = edialog.inflate(R.layout.popup_settime, null)

        val minute: NumberPicker = mView.findViewById(R.id.numberPicker_min)
        val second: NumberPicker = mView.findViewById(R.id.numberPicker_sec)

        val cancel: Button = mView.findViewById<Button>(R.id.btn_settime_no)
        val start: Button = mView.findViewById<Button>(R.id.btn_settime_ok)
        // editText 설정해제
        minute.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        second.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        //최소값 설정
        minute.minValue = 0
        second.minValue = 0

        //최대값 설정
        minute.maxValue = 30
        second.maxValue = 59
        //기본값 설정
        minute.value = 1
        second.value = 0
        
        //보여질 값 설정


        //취소버튼
        cancel.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
        }
        start.setOnClickListener {
            Toast.makeText(context, "${minute.value}분 ${second.value}초", Toast.LENGTH_SHORT).show()
            (activity as PageActivity).startExcercise(exname,minute.value,second.value)
            dialog.dismiss()
        }
        dialog.setView(mView)
        dialog.create()
        dialog.show()
        dialog.window!!.setLayout(750,WindowManager.LayoutParams.WRAP_CONTENT)

    }

        fun MyRoutinePopup() {

        val dialog = AlertDialog.Builder(context).create()

        val edialog: LayoutInflater = LayoutInflater.from(context)
        val mView: View = edialog.inflate(R.layout.popup_add_myroutine, null) //팝업창을 띄우는 코드

        val insert: Button = mView.findViewById<Button>(R.id.bt_insert)
        val button: Button = mView.findViewById<Button>(R.id.button)
        val recyclerView :RecyclerView = mView.findViewById(R.id.recyclerView)
        val adapter = Adapter(list,R.layout.item_model,requireContext())
        recyclerView.adapter = adapter
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            RecyclerView.VERTICAL,false   )
        list.add(Model("fistExc",1))


        insert.setOnClickListener{
            val name = editText.text.toString()
            if (adapter != null) {
                number = adapter.itemCount
            }
            list.add(Model(name,number++))
            if (adapter != null) {
                adapter.notifyDataSetChanged()
            }
        }

        button.setOnClickListener{

            val chkBox : CheckBox =checkBox
            if(chkBox.isChecked()){
                Toast.makeText(context,"루틴에 추가되었습니다",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.setView(mView)
        dialog.create()
        dialog.show()

    }


}




