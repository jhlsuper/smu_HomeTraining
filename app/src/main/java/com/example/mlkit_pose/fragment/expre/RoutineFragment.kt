package com.example.mlkit_pose.fragment.expre

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mlkit_pose.R
import kotlinx.android.synthetic.main.fragment_my_routine.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RoutineFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var adapter : ItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_routine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : CustomRecyclerView = view.findViewById(R.id.recylcerview) as CustomRecyclerView
        adapter = ItemAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context))
        recyclerView.layoutManager = ItemLayoutManager(context)

//        tv_message.setOnClickListener(View.OnClickListener {
//            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
//            val view: View = LayoutInflater.from(context).inflate(R.layout.edit_box, null, false)
//            builder.setView(view)
//
//
//
//
//        })



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoutineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }


}