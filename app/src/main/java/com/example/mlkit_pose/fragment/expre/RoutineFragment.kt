package com.example.mlkit_pose.fragment.expre

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mlkit_pose.R
import kotlinx.android.synthetic.main.edit_box.*
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

        tv_message.setOnClickListener(View.OnClickListener {
//            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
//            val view: View = inflater.inflate(R.layout.fragment_ranking_main, container, false)
//            builder.setView(view)

            val popupView: View = layoutInflater.inflate(R.layout.edit_box, null)
            val builder = AlertDialog.Builder(context)
            builder.setView(popupView)


            builder.setPositiveButton("추가") { dialog, which ->
                Toast.makeText(context, "만들어졌습니다.", Toast.LENGTH_SHORT).show()
            }

            val alertDialog = builder.create()
            alertDialog.show()
//
//            button_dialog_submit.setOnClickListener(View.OnClickListener { // 4. 사용자가 입력한 내용을 가져와서
//                val strID: String = new_routine_name.getText().toString()
//                Log.d("settt", strID)


//                // 5. ArrayList에 추가하고
//                val dict = Dictionary<Any, Any>(strID, strEnglish, strKorean)
//                mArrayList.add(0, dict) //첫번째 줄에 삽입됨
//                //mArrayList.add(dict); //마지막 줄에 삽입됨
//
//                // 6. 어댑터에서 RecyclerView에 반영하도록 합니다.
//                mAdapter.notifyItemInserted(0)
//                //mAdapter.notifyDataSetChanged();
//                dialog.dismiss()
            })
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