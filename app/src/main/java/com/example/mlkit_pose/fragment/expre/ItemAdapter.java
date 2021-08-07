package com.example.mlkit_pose.fragment.expre;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.mlkit_pose.PageActivity;
import com.example.mlkit_pose.R;
import com.example.mlkit_pose.fragment.expre.model.ChildItem;
import com.example.mlkit_pose.fragment.expre.model.Item;
import com.example.mlkit_pose.fragment.expre.model.ParentItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.example.mlkit_pose.JSP;
import com.example.mlkit_pose.fragment.expre.model.SharedViewModel;

import static androidx.core.os.BundleKt.bundleOf;
import static androidx.fragment.app.FragmentKt.setFragmentResult;


/**
 * Created by wlsdud.choi on 2016-04-01.
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperListener, OnPersonItemClickListener {

    private SharedViewModel sharedViewModel;
    private final String TAG = "[Simple][NewItemAdaptr]";
    private final int PARENT_ITEM_VIEW = 0;
    private final int CHILD_ITEM_VIEW = 1;
    private Context context;
    private Context lastContext;
    OnPersonItemClickListener listener;

    public Context getContext() {
        return context;
    }

    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> visibleItems = new ArrayList<>();

    private Map<String, ArrayList<String>> routineItems = new HashMap<String, ArrayList<String>>();

    // 여기부터 아래까지 새로운 프래그먼트 열기 위함
    public void setOnItemClickListener(OnPersonItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView, final OnPersonItemClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.item_name);
            textView2 = itemView.findViewById(R.id.subitem_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }
    } // 여기까지

    public ItemAdapter(String inputId, Context lastContext) {

        this.lastContext = lastContext;
        setItemData(inputId);

//
//        char alphabet = 'A';
//        for(int i = 0; i < 5; i++) {
//            Item item1 = new ParentItem((char) (alphabet + i) + "", PARENT_ITEM_VIEW);
//            Item item2 = new ChildItem((char) (alphabet + i) + "-1", CHILD_ITEM_VIEW);
//            Item item3 = new ChildItem((char) (alphabet + i) + "-2", CHILD_ITEM_VIEW);
//            Item item4 = new ChildItem((char) (alphabet + i) + "-3", CHILD_ITEM_VIEW);
//
//            items.add(item1);
//            items.add(item2);
//            items.add(item3);
//            items.add(item4);
//
//            ParentItem pt = (ParentItem) item1;
//            pt.visibilityOfChildItems = false;
//            visibleItems.add(item1);
//            pt.unvisibleChildItems.add((ChildItem) item2);
//            pt.unvisibleChildItems.add((ChildItem) item3);
//            pt.unvisibleChildItems.add((ChildItem) item4);
//        }
    }

    public void setItemData(String inputId) {
        Log.d("ROUTINE_LIST", "setItemData " + inputId);
        RequestQueue queue = Volley.newRequestQueue(lastContext);
        String url = JSP.Companion.getRoutineList(inputId);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] routineList = response.split("@");
                // Divide Parent and Child

                for (int i = 0; i < routineList.length - 1; i++) {
                    String[] detail = routineList[i].split(",");
                    /* detail[0] // Parent    detail[1] // Child    detail[2] // Child Eng */
                    ArrayList<String> childList = new ArrayList<String>();
                    if (routineItems.containsKey(detail[0])) {
                        childList = routineItems.get(detail[0]);
                        childList.add(detail[1]);
                    } else {
                        childList.add(detail[1]);
                    }
                    routineItems.put(detail[0], childList);
                }
                Log.d("ROUTINE_LIST", routineItems.toString());
                for (String key : routineItems.keySet()) {
                    Item parent = new ParentItem(key, PARENT_ITEM_VIEW);
                    Log.d("ROUTINE_LIST", key);
                    items.add(parent);
                    ArrayList<Item> childList = new ArrayList<Item>();
                    for (String value : routineItems.get(key)) {
                        Item child = new ChildItem(value, CHILD_ITEM_VIEW);
                        items.add(child);
                        childList.add(child);
                    }
                    ParentItem pt = (ParentItem) parent;
                    pt.visibilityOfChildItems = false;
                    visibleItems.add(parent);
                    for (Item child : childList) {
                        pt.unvisibleChildItems.add((ChildItem) child);
                    }
                }
                notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ROUTINE_LIST_ERROR", error.toString());
                Toast.makeText(lastContext, "sever error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);

    }

    public void addItems(List<String> childItems, String parentName) {
        Item parent = new ParentItem(parentName, PARENT_ITEM_VIEW);
        items.add(parent);
        ArrayList<Item> childList = new ArrayList<Item>();
        for (String value : childItems) {
            Item child = new ChildItem(value, CHILD_ITEM_VIEW);
            items.add(child);
            childList.add(child);
        }
        ParentItem pt = (ParentItem) parent;
        pt.visibilityOfChildItems = false;
        visibleItems.add(parent);
        for (Item child : childList) {
            pt.unvisibleChildItems.add((ChildItem) child);
        }
        Log.d("ROUTINE_LIST", routineItems.toString());
        notifyDataSetChanged();
    }

    public boolean checkRoutineName(String parent) {
        if (routineItems.containsKey(parent)) {
            return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount. count : " + visibleItems.size());

        return visibleItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.i(TAG, "getItemViewType. position : " + position + ", viewType : " + visibleItems.get(position).viewType + ", item : " + visibleItems.get(position).name);
        return visibleItems.get(position).viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder. viewType : " + viewType);
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case PARENT_ITEM_VIEW:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_item, parent, false);
                viewHolder = new ParentItemVH(view);
                break;
            case CHILD_ITEM_VIEW:
                View subview = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_subitem, parent, false);
                viewHolder = new ChildItemVH(subview);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder. position : " + position);
        if (holder instanceof ParentItemVH) {
            Log.i(TAG, "onBindViewHolder. parentItem. " + visibleItems.get(position).name);
            ParentItemVH parentItemVH = (ParentItemVH) holder;

            parentItemVH.name.setText(visibleItems.get(position).name);
            parentItemVH.arrow.setTag(holder);

            parentItemVH.arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int holderPosition = ((ParentItemVH) v.getTag()).getAdapterPosition();
                    if (((ParentItem) visibleItems.get(holderPosition)).visibilityOfChildItems) {
                        collapseChildItems(holderPosition); // 접는거
                    } else {
                        expandChildItems(holderPosition); // 열리는거
                    }
                }
            });
            parentItemVH.name.setOnClickListener(new View.OnClickListener() { //이름 클릭했을 때
                @Override
                public void onClick(View v) {
                    Log.d("ONCLICK_PARENT", "ONCLICK TEXT");

                    String routine_name = (String) parentItemVH.name.getText();
                    Log.d("message", routine_name);

//                    Intent intent = new Intent(getContext(), RoutineDetailFragment.class);
//                    intent.putExtra("routine_name", routine_name);

                    FragmentTransaction var10000 = ((PageActivity)PageActivity.context_main).getSupportFragmentManager().beginTransaction();
                    RoutineDetailFragment routineDetailfragment = new RoutineDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("Routine_detail_name", routine_name); // 이름 번들에 넣음
                    routineDetailfragment.setArguments(bundle);
                    FragmentTransaction transaction = var10000;

//                    sharedViewModel.setLiveData(routine_name);

                    transaction.replace(R.id.frameLayout, new RoutineDetailFragment()); // 변경
//                    transaction.add(R.id.frameLayout, new RoutineDetailFragment(), "Routine_Detail");
                    transaction.commit(); // 저장

                }


            });

//            parentItemVH.itemView.setTag(holder);
//            if(parentItemVH.getItemViewType() == PARENT_ITEM_VIEW) {
//                parentItemVH.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        int holderPosition = ((ParentItemVH)v.getTag()).getAdapterPosition();
//                        if(((ParentItem)visibleItems.get(holderPosition)).visibilityOfChildItems){
//                            collapseChildItems(holderPosition);
//                        }
//                        return true;
//                    }
//                });
//            }

        } else if (holder instanceof ChildItemVH) {
            Log.i(TAG, "onBindViewHolder. sub item. " + visibleItems.get(position).name);

            ((ChildItemVH) holder).name.setText(visibleItems.get(position).name);
        }
    }

    private void collapseChildItems(int position) {
        Log.i(TAG, "collapseChildItems");
        ParentItem parentItem = (ParentItem) visibleItems.get(position);
        parentItem.visibilityOfChildItems = false;

        int subItemSize = getVisibleChildItemSize(position);
        for (int i = 0; i < subItemSize; i++) {
            parentItem.unvisibleChildItems.add((ChildItem) visibleItems.get(position + 1));
            visibleItems.remove(position + 1);
        }
        notifyItemRangeRemoved(position + 1, subItemSize);
    }

    private int getVisibleChildItemSize(int parentPosition) {
        int count = 0;
        parentPosition++;
        while (true) {
            if (parentPosition == visibleItems.size() || visibleItems.get(parentPosition).viewType == PARENT_ITEM_VIEW) {
                break;
            } else {
                parentPosition++;
                count++;
            }
        }
        return count;
    }

    private void expandChildItems(int position) {
        Log.i(TAG, "expandChildItems");

        ParentItem parentItem = (ParentItem) visibleItems.get(position);
        parentItem.visibilityOfChildItems = true;
        int childSize = parentItem.unvisibleChildItems.size();

        for (int i = childSize - 1; i >= 0; i--) {
            visibleItems.add(position + 1, parentItem.unvisibleChildItems.get(i));
        }
        parentItem.unvisibleChildItems.clear();

        notifyItemRangeInserted(position + 1, childSize);
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
//
//        Log.i(TAG, "onItemMove. fromPosition : "+fromPosition+", toPosition : "+toPosition);
//        Log.i(TAG, "onItemMove. fromItem : "+visibleItems.get(fromPosition).name+", toItem : "+visibleItems.get(toPosition).name);
//
//        if(fromPosition < 0 || fromPosition >= visibleItems.size() || toPosition < 0 || toPosition >= visibleItems.size()){
//            return false;
//        }
//
//        Item fromItem = visibleItems.get(fromPosition);
//
//        if(visibleItems.get(fromPosition).viewType == CHILD_ITEM_VIEW){
//            if(fromPosition <= 0 || toPosition <= 0){
//                return false;
//            }
//            Log.i(TAG, "onItemMove. remove add");
//
//            visibleItems.remove(fromPosition);
//            visibleItems.add(toPosition, fromItem);
//
//            notifyItemMoved(fromPosition, toPosition);
//
//        }else{
//            if(visibleItems.get(fromPosition).viewType == visibleItems.get(toPosition).viewType) {
//                if(fromPosition > toPosition){
//                    Log.i(TAG, "onItemMove. remove add");
//
//                    visibleItems.remove(fromPosition);
//                    visibleItems.add(toPosition, fromItem);
//
//                    notifyItemMoved(fromPosition, toPosition);
//                }else{
//                    int toParentPosition = getParentPosition(toPosition);
//                    int toLastchildSize = getVisibleChildItemSize(toParentPosition);
//                    Log.i(TAG, "onItemMove. lastChild : "+toLastchildSize);
//                    if(toLastchildSize == 0){
//                        Log.i(TAG, "onItemMove. remove add");
//
//                        visibleItems.remove(fromPosition);
//                        visibleItems.add(toPosition, fromItem);
//
//                        notifyItemMoved(fromPosition, toPosition);
//                    }
//
//                }
//            }else{
//                if(fromPosition < toPosition){
//                    int toParentPosition = getParentPosition(toPosition);
//                    int toLastchildPosition = getVisibleChildItemSize(toParentPosition) + toParentPosition;
//                    Log.i(TAG, "onItemMove. lastChild : "+toLastchildPosition);
//
//                    if(toLastchildPosition == toPosition) {
//                        Log.i(TAG, "onItemMove. remove add");
//
//                        visibleItems.remove(fromPosition);
//                        visibleItems.add(toPosition, fromItem);
//
//                        notifyItemMoved(fromPosition, toPosition);
//                    }
//                }
//            }
//        }
//
//        for(int i = 0; i < visibleItems.size(); i++){
//        Log.i(TAG, "onItemMove : "+visibleItems.get(i).name);
//    }

        return false;
    }

    @Override
    public void onItemRemove(int position, boolean isDelete) {
        Log.i(TAG, "onItemRemove. item : " + visibleItems.get(position).name);
        if (isDelete) {
            switch (visibleItems.get(position).viewType) {
                case PARENT_ITEM_VIEW:
                    int childItemSize = getVisibleChildItemSize(position);

                    for (int i = 0; i <= childItemSize; i++) {
                        visibleItems.remove(position);
                    }
                    notifyItemRangeRemoved(position, childItemSize + 1);

                    break;
                case CHILD_ITEM_VIEW:
                    visibleItems.remove(position);
                    notifyItemRemoved(position);
                    break;
            }
        } else {
            notifyItemChanged(position);
        }
    }

    private int getParentPosition(int position) {
        while (true) {
            if (visibleItems.get(position).viewType == PARENT_ITEM_VIEW) {
                break;
            } else {
                position--;
            }
        }
        return position;
    }

    public class ParentItemVH extends RecyclerView.ViewHolder {

        TextView name;
        ImageButton arrow;

        public ParentItemVH(View itemView) {
            super(itemView);
            Log.i(TAG, "ParentItemVH");
            name = (TextView) itemView.findViewById(R.id.item_name);
            arrow = (ImageButton) itemView.findViewById(R.id.item_arrow);
        }
    }

    public class ChildItemVH extends RecyclerView.ViewHolder {

        TextView name;

        public ChildItemVH(View itemView) {
            super(itemView);
            Log.i(TAG, "ChildItemVH");
            name = (TextView) itemView.findViewById(R.id.subitem_name);
        }
    }
}