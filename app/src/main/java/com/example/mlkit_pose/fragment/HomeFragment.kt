package com.example.mlkit_pose.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mlkit_pose.*
import com.example.mlkit_pose.adapter.MainViewModel
import com.example.mlkit_pose.fragment.expre.RoutineFragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.ArrayList


@Suppress("DEPRECATION")
class HomeFragment : Fragment(), View.OnClickListener {
    val bgColors = ArrayList<Int>()
    var pos: Int = 0
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
        view.btn_home_ranking.text = "유저포인트\n $point"
        view.btn_home_mypage.setOnClickListener(this)
        view.btn_home_guide.setOnClickListener(this)
        view.btn_home_routine.setOnClickListener(this)
        view.btn_home_app_routine.setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as PageActivity).initViewPager()
//        viewPager.setOnClickListener {
//            Log.d("ViewPager", "fragment 기능 구현")
//
//        }

    }


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
                (activity as PageActivity).setDataAtFragment(
                    RoutineFragment(),
                    PageActivity.TAG_ROUTINE_FRAGMENT
                )

            }
            R.id.btn_home_mypage -> {
                (activity as PageActivity).setDataAtFragment(
                    MyPageFragment(),
                    PageActivity.TAG_MYPAGE_FRAGMENT
                )
            }
            R.id.btn_home_app_routine->{
                (activity as PageActivity).openTodayRoutine()
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
