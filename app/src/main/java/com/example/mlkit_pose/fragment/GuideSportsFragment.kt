package com.example.mlkit_pose.fragment



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
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener

import androidx.transition.FragmentTransitionSupport
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.JSP
import com.example.mlkit_pose.PageActivity
import com.example.mlkit_pose.R
import kotlinx.android.synthetic.main.fragment_bottom_menu.*
import kotlinx.android.synthetic.main.fragment_bottom_menu.btn_guide
import kotlinx.android.synthetic.main.fragment_bottom_menu.btn_home
import kotlinx.android.synthetic.main.fragment_bottom_menu.btn_mypage
import kotlinx.android.synthetic.main.fragment_bottom_menu.btn_routine
import kotlinx.android.synthetic.main.fragment_guide_click.*
import kotlinx.android.synthetic.main.fragment_guide_click.*
import kotlinx.android.synthetic.main.fragment_guide_sports.*
import kotlinx.android.synthetic.main.fragment_main_page_part.*
import kotlinx.android.synthetic.main.fragment_tool_bar.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class GuideSportsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param2: String? = null

    var bitmap: Bitmap? = null
    var eturl: String? = null
    var result2: String? = null

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
        btn_start_exercise.setOnClickListener{
            (activity as PageActivity).startExcercsie()
        }
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
                        sport_detail_text.setText(arr2[1])


                        // 운동 링크
//                    sports_link_check.setOnClickListener {
//                        fun onClick(v: View?) {
//                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(arr2[2]))
//                            startActivity(intent)
//                        }
//                    }
                        eturl = arr2[2]
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




}
