package com.example.mlkit_pose.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import com.example.mlkit_pose.MainActivity
import com.example.mlkit_pose.PageActivity
import com.example.mlkit_pose.R
import com.example.mlkit_pose.fragment.GuideMainFragment
import com.example.mlkit_pose.fragment.HomeFragment
import kotlinx.android.synthetic.main.ex_list_item.view.*
import kotlin.math.log

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

        private val pageImage: ImageView = itemView.findViewById(R.id.imgView_ex)




        @SuppressLint("SetTextI18n")
        fun bind(@ColorRes bgColor: Int, position: Int) {
//            pageName. ="Page ${position +1}"
//            pageName.setBackgroundResource(R.drawable.todayexercise)
//            val PageActivity = (activity as MainActivity)

            pageImage.setImageResource(bgColor)
//            pageName.text = nameArray[position]
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
                Log.d("ViewPager", "12345")
                PageActivity().change("와이드 스쿼트")
            }
        })
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(bgColors[position], position)


    }


    override fun getItemCount(): Int = bgColors.size
}
