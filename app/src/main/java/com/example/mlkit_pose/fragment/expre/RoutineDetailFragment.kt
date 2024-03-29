package com.example.mlkit_pose.fragment.expre

import GuideBookRecyclerAdapter
import GuideBookSportsList
import GuideSports
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.companion.JSP.Companion.getRoutineNameList
import com.example.mlkit_pose.PageActivity
import com.example.mlkit_pose.R
import kotlinx.android.synthetic.main.fragment_routine_detail.*


private const val ARG_PARAM1 = "param1"

class RoutineDetailFragment : Fragment() {
    var routine_name: String? = null
    private var id: String? = null
    var routine_name_list = arrayListOf<String>()
    val routine_Map : HashMap<String, String> = HashMap()
    var mFragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = (activity as PageActivity).findUserId()
        arguments?.let {
            routine_name = it.getString("routine_Name")
        }

    }

    public class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) :
        RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.bottom = verticalSpaceHeight
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_routine_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        routine_detail_name.text = routine_name
        Log.d("ROUTINE_DETAIL", routine_name.toString())

        val queue = Volley.newRequestQueue(context)
        val url = getRoutineNameList(id, routine_name!!)

        val Routine_detail_request = StringRequest(
            Request.Method.GET, url, { response ->
                response.trim { it <= ' ' }
                Log.d("response", response)
                val arr = response.trim().split("@")
                for (i:Int in 0 until arr.size-1){
                    val (krName,enName) = arr[i].split(",")
                    routine_name_list.add(krName)
                    routine_Map[krName] = enName
                    Log.d("Routine","$routine_name_list , $routine_Map")
                }
//
                val guideBookRecyclerAdapter = GuideBookRecyclerAdapter(
                    guidelist = createsportslist(),
                    inflater = LayoutInflater.from(context)
                )


                routine_recycler_view?.adapter = guideBookRecyclerAdapter
                routine_recycler_view?.layoutManager = LinearLayoutManager(context)

                val spaceDecoration = VerticalSpaceItemDecoration(10)
                routine_recycler_view.addItemDecoration(spaceDecoration)

                guideBookRecyclerAdapter.setOnItemClickListener(object : GuideBookRecyclerAdapter.OnItemClickListener{
                    override fun onItemClick(v: View, guidelist: GuideBookSportsList, pos: Int) {
                        val krName = guidelist.GuideList[pos].name
                        Log.d("ROUTINE_DETAIL", "KR : ${guidelist.GuideList[pos].name}, EN ${routine_Map[krName]}")
                        (activity as PageActivity).showTimeSettingPopup(routine_Map[krName],krName,context!!)
                    }
                })

            }, {})
        queue.add(Routine_detail_request)

//        val itemAdapter = ItemAdapter(id, context, mFragmentManager)
//        routine_recycler_view.adapter = itemAdapter
//        routine_recycler_view.layoutManager = LinearLayoutManager(context)


    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) {
            RoutineDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1);
                }
            }
        }

    }

    fun createsportslist(
        Number: Int = 30,
        guideBook: GuideBookSportsList = GuideBookSportsList()
    ): GuideBookSportsList {
        // guidebook을 넣어주지 않으면 기본으로 guideBook을 생성

        for (i in 0 until routine_name_list.size) {
            guideBook.addSports(
                GuideSports(routine_name_list[i])
            )
        }
        val num: Int = routine_name_list.size

//        for (i in num - 1 downTo (0)) {
//            routine_name_list.removeAt(i)
//        }

        return guideBook
    }

}