package com.example.mlkit_pose.fragment

//import com.example.hometrain.GuideBookRecyclerAdapter.Companion.array_name

import GuideBookRecyclerAdapter
import GuideBookRecyclerAdapter.Companion.array_name
import GuideBookSportsList
import GuideSports
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.JSP.Companion.getSportsName
import com.example.mlkit_pose.R
import kotlinx.android.synthetic.main.fragment_guide_click.*


val sportsdatas = mutableListOf<String>()
private val ARG_PARAM1:String = "ARG_PARAM1"
private val ARG_PARAM2:String = "ARG_PARAM2"

class GuideClickFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var sports_names: String? = null
    private var id: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sports_names = it.getString("result")
            id = it.getString("id")
        }

        setFragmentResultListener("requestKey") { resultKey, bundle ->
            val result = bundle.getString("bundleKey")
            Log.d("result", result.toString())

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_guide_click, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener("requestKey") { resultKey, bundle ->
            val result = bundle.getString("bundleKey")
            Log.d("result", result.toString())
            sport_name.setText("${result}")
            val queue = Volley.newRequestQueue(context)
            val url = getSportsName(result.toString()).toString()


            val Sports_name_request = StringRequest(
                Request.Method.GET, url, { response ->
                    response.trim { it <= ' ' }
                    Log.d("err", response)
                    val arr = (response.trim().split("/")).toTypedArray()

                    if (response == "error") {
                        Log.d("error", "error")
                    } else {
                        for (i in 0 until arr.size-1) {
                            array_name.add(arr[i])
                        }
//                        Log.d("successs", array_name[0])
//                        Log.d("successs", array_name[1])
                    }


                    val guideBookRecyclerAdapter = GuideBookRecyclerAdapter(
                        guidelist = createsportslist(),
                        inflater = LayoutInflater.from(context)
                    )
                    sports_recycler_view?.adapter = guideBookRecyclerAdapter
                    sports_recycler_view?.layoutManager = LinearLayoutManager(context)
                    guideBookRecyclerAdapter.setOnItemClickListener(object :
                        GuideBookRecyclerAdapter.OnItemClickListener {
                        override fun onItemClick(
                            v: View,
                            guidelist: GuideBookSportsList,
                            pos: Int
                        ) {
                            val result = guidelist.GuideList.get(pos).name
                            Log.d("result", result)
                            setFragmentResult("requestKey2", bundleOf("bundleKey2" to result))
                            val transaction = parentFragmentManager.beginTransaction()
                            transaction.add(R.id.frameLayout,GuideSportsFragment(),"guide_sport")
                            transaction.show(GuideSportsFragment())
                            transaction.addToBackStack(null)
                            transaction.commit()
                        }
                    })
                }, {})
            queue.add(Sports_name_request)


        }
        btn_guide_back.setOnClickListener(this)


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GuideClickFragment().apply {
                arguments = Bundle().apply {
                    putString("id", id)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_guide_back -> {

                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, GuideMainFragment())
                    .commit()
            }
        }
    }



    fun createsportslist(
        Number: Int = 30,
        guideBook: GuideBookSportsList = GuideBookSportsList()
    ): GuideBookSportsList {
        // guidebook을 넣어주지 않으면 기본으로 guideBook을 생성

        for (i in 0 until array_name.size) {
            guideBook.addSports(
                GuideSports(array_name[i])
            )
        }
        val num: Int = array_name.size

        for (i in num - 1 downTo (0)) {
            array_name.removeAt(i)
        }

        return guideBook
    }


}