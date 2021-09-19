package com.example.mlkit_pose.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mlkit_pose.*
import com.example.mlkit_pose.adapter.MainViewModel
import com.example.mlkit_pose.adapter.PagerRecyclerAdapter
import com.example.mlkit_pose.fragment.expre.RoutineDetailFragment
import kotlinx.android.synthetic.main.ex_list_item.view.*
import kotlinx.android.synthetic.main.fragment_guide_click.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


@Suppress("DEPRECATION")
class HomeFragment : Fragment(), View.OnClickListener {

    private var nickname: String? = null
    private var point: String? = null

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nickname = it.getString("name")
            point = it.getString("points")

//            Toast.makeText(activity, "$nickname,$point", Toast.LENGTH_SHORT).show()

        }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.point.observe(this, {
            btn_home_ranking.text = it.toString()
        })


    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container:
        ViewGroup?, savedInstanceState: Bundle?
    ): View {


        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        view.viewPager.setOnClickListener(this)
        view.btn_home_ranking.setOnClickListener(this)
        view.btn_home_ranking.text = "유저 포인트\n $point"
        view.btn_home_mypage.setOnClickListener(this)
        view.btn_home_guide.setOnClickListener(this)
        view.viewPager.setOnClickListener(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.setOnClickListener {
            Log.d("ViewPager", "fragment 기능 구현")
//            Toast.makeText(activity, "ohh", Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        var pos: Int = 0
//        val bgColors = ArrayList<Int>()
//        bgColors.add(R.drawable.todayexercise)
//        bgColors.add(R.drawable.dumbell)
//        bgColors.add(R.drawable.penguin)
//        viewPager.adapter = PagerRecyclerAdapter(bgColors)
//        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                Log.d("ViewPager", "$position 번째")
//                pos = position
//            }
//
//        })
//
//
//
//    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_home_ranking -> {
                (activity as PageActivity).setDataAtFragment(
                    RankingMainFragment(),
                    PageActivity.TAG_RANK_FRAGMENT
                )
            }
            R.id.btn_home_guide -> {
                (activity as PageActivity).setDataAtFragment(
                    GuideMainFragment(),
                    PageActivity.TAG_GUIDE_FRAGMENT
                )
            }
            R.id.btn_home_routine -> {
                TODO("routine button impl")
//                (activty as PageActivity).
            }
            R.id.btn_home_mypage -> {
                (activity as PageActivity).setDataAtFragment(
                    MyPageFragment(),
                    PageActivity.TAG_MYPAGE_FRAGMENT
                )
            }


        }
    }

//    fun toGuideFragment(position: Int) {
//        when (position) {
//            0 -> {
//                setFragmentResult("requestKey2", bundleOf("bundleKey" to "와이드 스쿼트"))
//                PageActivity().change()
//            }
//            1 -> {
//                setFragmentResult("requestKey2", bundleOf("bundleKey" to "덤벨 숄더 프레스"))
////                fragmentChange("덤벨 숄더 프레스")
//                PageActivity().change()
//            }
//            2 -> {
//                setFragmentResult("requestKey2", bundleOf("bundleKey" to "런지"))
////                fragmentChange()
//            }
//        }
//
//    }

//    fun fragmentChange(exName: String) {
//        val fragmentManager :FragmentManager =
//        Log.d("result", exName)
//        setFragmentResult("requestKey2", bundleOf("bundleKey" to exName))
//        parentFragmentManager.beginTransaction()
//            .add(R.id.frameLayout, GuideSportsFragment(), "guide_sport")
//            .show(GuideSportsFragment())
//            .hide(HomeFragment())
////            .replace(R.id.frameLayout, GuideClickFragment())
//            .addToBackStack(null)
//
//            .commit()
//    }

}
