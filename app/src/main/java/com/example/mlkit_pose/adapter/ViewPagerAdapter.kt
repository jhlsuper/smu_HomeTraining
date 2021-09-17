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
import com.example.mlkit_pose.PageActivity
import com.example.mlkit_pose.R
import com.example.mlkit_pose.fragment.GuideMainFragment
import kotlinx.android.synthetic.main.ex_list_item.view.*

class PagerRecyclerAdapter(private val bgColors: ArrayList<Int>) : RecyclerView.Adapter<PagerRecyclerAdapter.PagerViewHolder>() {


    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val pageName: ImageView = itemView.findViewById(R.id.imgView_ex)
//        private val pageName: ImageView = itemView.findViewById(R.id.viewPager)


        fun bind(@ColorRes bgColor: Int, position: Int) {
//            pageName. ="Page ${position +1}"
//            pageName.setBackgroundResource(R.drawable.todayexercise)
            pageName.setImageResource(bgColor)
            itemView.setOnClickListener{
                Log.d("ViewPager","$position")

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.ex_list_item,
            parent,
            false
        )
        return PagerViewHolder(view)
    }
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(bgColors[position], position)


    }
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener


    override fun getItemCount(): Int = bgColors.size
}
