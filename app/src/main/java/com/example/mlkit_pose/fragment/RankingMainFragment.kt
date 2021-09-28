package com.example.mlkit_pose.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mlkit_pose.*
import com.example.mlkit_pose.adapter.MainViewModel
import com.example.mlkit_pose.camera.convertBitMap
import com.example.mlkit_pose.dao.SharedManager
import com.example.mlkit_pose.dao.User

import kotlinx.android.synthetic.main.fragment_ranking_main.*
import kotlinx.android.synthetic.main.fragment_ranking_main.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var UserList = arrayListOf<User>()

class RankingMainFragment : Fragment(), View.OnClickListener {

    private var id: String? = null
    private var points: String? = null
    private var belong: String? = null
    private var nickname: String? = null
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")
            points = it.getString("points")
            belong = it.getString("belong")
            nickname = it.getString("name")
        }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.belong.observe(this, {
            (activity as PageActivity).rankingRefresh()
        })
//        viewModel.profileImg.observe(this,{
//            img_ranking_profile.setImageBitmap(convertBitMap().StringToBitmap(it))
//        })

//        viewModel.init()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val currentUser = SharedManager(requireContext()).getCurrentUser()
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_ranking_main, container, false)
        view.txt_ranking_my_id.text = "$nickname"
        view.txt_ranking_my_points.text = "$points"
        view.img_ranking_profile.setImageBitmap(convertBitMap().StringToBitmap(currentUser.img))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as PageActivity).initRecycler()
        (activity as PageActivity).setRankData()
        (activity as PageActivity).initRecycler()
        Log.d("viewCreated", "initRecyclerView")
        btn_ranking_refresh.setOnClickListener(this)
    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RankingMainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_ranking_refresh -> {
                (activity as PageActivity).rankingRefresh()
            }
        }
    }

}