package com.example.mlkit_pose.fragment.expre

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
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
    lateinit var adapter: ItemAdapter

        //체크된 내용 기억
    val checkedItemList = ArrayList<String>()  // 선택된 항목을 담는 리스트
    val RoutineList = ArrayList<String>()  // 선택된 항목을 담는 리스트
    val RoutinesportsList = ArrayList<String>()  // 선택된 항목을 담는 리스트


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
        val recyclerView: CustomRecyclerView =
            view.findViewById(R.id.recylcerview) as CustomRecyclerView
        adapter = ItemAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context))
        recyclerView.layoutManager = ItemLayoutManager(context)

        tv_message.setOnClickListener(View.OnClickListener {
//            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
//            val view: View = inflater.inflate(R.layout.fragment_ranking_main, container, false)
//            builder.setView(view)
            val checkedItems = booleanArrayOf(
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false
            )
            val builder = AlertDialog.Builder(context)
            builder.setTitle("NEW 루틴 생성")

            val popupView: View = layoutInflater.inflate(R.layout.edit_box, null)
            builder.setView(popupView)

            val itemList = arrayOf<String>(
                "푸쉬업", "풀 플랭크", "백 리프트", "슈퍼맨 운동", "덤벨 숄더 프레스", "덤벨 사이드 레터럴 레이즈",
                "업다운 플랭크", "덤벨 교차 운동", "레그레이즈", "시티드 니업", "버드독", "데드 버그",
                "V 싯업", "포워드 밴드", "한 발로 땅 짚기", "와이드 스쿼트", "사이드 레그레이즈", "밴드 사이드 스텝", "런지", "브릿지"
            )    // 항목 리스트

//            builder.setTitle("닉네임 변경").setMessage("변경할 닉네임을 입력하세요")
//                .setCancelable(false).setView(popupView).setPositiveButton("확인",
//                    DialogInterface.OnClickListener { dialog, id ->
//                        Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
//                    })



            var listener = DialogInterface.OnClickListener { p0, p1 ->
                var alert = p0 as AlertDialog
                var edit1: EditText? = alert.findViewById<EditText>(R.id.new_routine_name)
                // 입력 받는 칸


//                Push_up.setOnClickListener {
//
//                }
//
//                Full_plank.setOnCheckedChangeListener { buttonView, isChecked ->
//                    if (isChecked) RoutinesportsList.add("푸쉬업")
//                    else Toast.makeText(context, "안됨", Toast.LENGTH_SHORT).show()
//                    Log.d("test22", RoutinesportsList.toString())
//                }


                RoutineList.add("${edit1?.text}") // (루틴 이름을 받아오는쪽) 얘는 잘 됨!
                Toast.makeText(
                    context, "${edit1?.text} 루틴이 추가됨!",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("list", RoutineList.toString())

            }


            builder.setPositiveButton("확인", listener)
            builder.setNegativeButton("취소", null)

            // 항목 클릭 시 이벤트
//            builder.setMultiChoiceItems(itemList, checkedItems) { dialog, which, isChecked ->
//                // 체크 시 리스트에 추가
//                if (isChecked) {
//                    checkedItemList.add(itemList[which])
//                }
//                // 체크 해제 시 리스트에서 제거
//                else if (checkedItemList.contains(itemList[which])) {
//                    checkedItemList.remove(itemList[which])
//                }
//            }

//            builder.setPositiveButton("추가") { dialog, which ->
//                Log.d("test2222", checkedItemList.toString())
//                Toast.makeText(
//                    context, checkedItemList.joinToString(", ", "루틴이 추가됨: "),
//                    Toast.LENGTH_SHORT).show()
//            }

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

    inner class CheckedboxListener : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            when (p0?.id) {
                R.id.Push_up ->
                    if (p1) RoutinesportsList.add(Push_up.text.toString())
                    else Log.d("tes22", "안 찍힘")
                R.id.Full_plank ->
                    if (p1) RoutinesportsList.add("풀 플랭크")
                    else Log.d("tes22", "안 찍힘")
            }
        }
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