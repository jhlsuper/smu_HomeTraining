package com.example.mlkit_pose.fragment.expre;

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by wlsdud.choi on 2016-03-29.
 */
public class CustomRecyclerView extends RecyclerView {

    private static final String TAG = "[Simple][CustomRecyclerView]";

    public CustomRecyclerView(Context context) {
        super(context);
        setItemAnimator(new MyItemAnimator());
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setItemAnimator(new MyItemAnimator());
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setItemAnimator(new MyItemAnimator());
    }

    @SuppressLint("LongLogTag")
    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        Log.i(TAG, "setAdapter");
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelperCallback((ItemAdapter)adapter));
        helper.attachToRecyclerView(this);
    }

}