package com.example.mlkit_pose

import android.content.Context
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class UserRkAdapter(val context: Context):
        RecyclerView.Adapter<UserRkAdapter.ViewHolder>() {

    var datas = mutableListOf<User>()
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

        fun bind(item: User) {
            txtId.text = item.id
            txtPoint.text = item.points.toString()
//            Glide.with(itemView).load(item.img).into(imgProfile)

        }
    }


}