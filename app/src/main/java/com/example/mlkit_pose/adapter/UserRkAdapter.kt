package com.example.mlkit_pose.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mlkit_pose.R
import com.example.mlkit_pose.dao.User
import com.example.mlkit_pose.dao.userRank


class UserRkAdapter(val context: Context):
        RecyclerView.Adapter<UserRkAdapter.ViewHolder>() {

    var datas = mutableListOf<userRank>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ranking_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtId: TextView = itemView.findViewById(R.id.txt_ranking_id)
        private val txtPoint: TextView = itemView.findViewById(R.id.txt_ranking_point)
        private val imgProfile: ImageView = itemView.findViewById(R.id.img_ranking_profile)
        private val background: ConstraintLayout = itemView.findViewById(R.id.ranking_layout)
        fun bind(item: userRank) {
            if (item.rank == "1"){
                background.setBackgroundColor(Color.DKGRAY)
            }else if (item.rank == "2"){
                background.setBackgroundColor(Color.BLUE)
            }else if (item.rank == "3"){
                background.setBackgroundColor(Color.GREEN)
            }
            txtId.text = item.user!!.id
            txtPoint.text = item.user!!.points.toString()
//            Glide.with(itemView).load(item.img).into(imgProfile)

        }
    }


}