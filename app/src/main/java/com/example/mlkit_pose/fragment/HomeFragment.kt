package com.example.mlkit_pose.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mlkit_pose.*
import com.example.mlkit_pose.adapter.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class HomeFragment : Fragment(), View.OnClickListener {

    private var nickname: String? = null
    private var point: String? = null

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nickname = it.getString("name")
            point = it.getString("points")
            Log.d("태그", "fragment 기능 구현")
            Toast.makeText(activity, "$nickname,$point", Toast.LENGTH_SHORT).show()

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
        view.btn_home_daily.setOnClickListener(this)
        view.btn_home_ranking.setOnClickListener(this)
        view.btn_home_ranking.text = "유저 포인트\n $point"
        view.btn_home_mypage.setOnClickListener(this)
        view.btn_home_guide.setOnClickListener(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_home_daily.setOnClickListener {
            Log.d("태그", "fragment 기능 구현")
            Toast.makeText(activity, "ohh", Toast.LENGTH_SHORT).show()
        }
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
                TODO("routine button impl")
            }
            R.id.btn_home_mypage -> {
                (activity as PageActivity).setDataAtFragment(
                    MyPageFragment(),
                    PageActivity.TAG_MYPAGE_FRAGMENT
                )
            }
        }
    }
}