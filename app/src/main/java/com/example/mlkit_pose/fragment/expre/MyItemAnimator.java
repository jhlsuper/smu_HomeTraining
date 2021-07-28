package com.example.mlkit_pose.fragment.expre;

import android.animation.ObjectAnimator;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by wlsdud.choi on 2016-04-05.
 */
public class MyItemAnimator extends DefaultItemAnimator {

    private final String TAG = "[Simple][MyItemAnimator]";

    @SuppressLint("LongLogTag")
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        Log.i(TAG, "animateAdd. holder : "+holder.getAdapterPosition()+", "+holder.getLayoutPosition());

        return super.animateAdd(holder);
    }

    @SuppressLint("LongLogTag")
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {

        Log.i(TAG, "animateRemove. holder : "+holder.getAdapterPosition()+", "+holder.getLayoutPosition());

        View view = holder.itemView;

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1.0f, 0.0f).setDuration(100);
        alphaAnimator.setInterpolator(new DecelerateInterpolator());
        alphaAnimator.start();

        return true;
    }

    @SuppressLint("LongLogTag")
    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        Log.i(TAG, "animateMove. fromX, fromY, toX, toY : "+fromX+", "+fromY+", "+toX+", "+toY);

        return super.animateMove(holder, fromX, fromY, toX, toY);
    }
}