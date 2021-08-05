package com.example.mlkit_pose.fragment

import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.mlkit_pose.R
import kotlinx.android.extensions.LayoutContainer

class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView) ,
    LayoutContainer
{
    val chkBox  = containerView.findViewById(R.id.checkBox) as CheckBox
}