package com.example.mlkit_pose.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.example.mlkit_pose.companion.TodayEx.Companion.nameArray
import com.example.mlkit_pose.R

class PagerRecyclerAdapter(private val bgColors: ArrayList<Int>) :
    RecyclerView.Adapter<PagerRecyclerAdapter.PagerViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

    private var listener: OnItemClickListener? = null

    // (3) 외부에서 클릭 시 이벤트 설정
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    //     (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener: OnItemClickListener

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        private val pageImage: ImageView = itemView.findViewById(R.id.imgView_ex)
        private val pageText :TextView = itemView.findViewById(R.id.textView_ex)
//        val nameArray = mutableListOf<String>("오늘의 운동","와이드 스쿼트","덤벨 숄더 프레스","런지")


        @SuppressLint("SetTextI18n")
        fun bind(@SuppressLint("SupportAnnotationUsage") @ColorRes bgColor: String, position: Int) {


//            pageImage.setImageResource(bgColor)
            pageText.text = nameArray[position]
            itemView.setOnClickListener {
                listener?.onItemClick(itemView, position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.ex_list_item,
            parent,
            false
        )
        view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
//                Log.d("ViewPager", "12345")
//                PageActivity().change("와이드 스쿼트")
            }
        })
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {

        holder.bind(bgColors[position].toString(), position)


    }


    override fun getItemCount(): Int = bgColors.size
}
