package com.example.mlkit_pose.fragment.expre;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mlkit_pose.JSP;
import com.example.mlkit_pose.R;
import com.example.mlkit_pose.fragment.expre.model.ChildItem;
import com.example.mlkit_pose.fragment.expre.model.Item;
import com.example.mlkit_pose.fragment.expre.model.ParentItem;
import com.example.mlkit_pose.fragment.expre.model.SharedViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wlsdud.choi on 2016-04-01.
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperListener{

    private SharedViewModel sharedViewModel;
    private final String TAG = "[Simple][NewItemAdaptr]";
    private final int PARENT_ITEM_VIEW = 0;
    private final int CHILD_ITEM_VIEW = 1;
    private Context context;
    private Context lastContext;
    private String inputId;
    FragmentManager mFragmentManager;

    public Context getContext() {
        return context;
    }

    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> visibleItems = new ArrayList<>();

    private Map<String, ArrayList<String>> routineItems = new HashMap<String, ArrayList<String>>();

    public ItemAdapter(String inputId, Context lastContext,FragmentManager fm) {
        this.inputId = inputId;
        this.lastContext = lastContext;
        this.mFragmentManager = fm;
        setItemData(inputId);
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public ArrayList<Item> getVisibleItems() {
        return visibleItems;
    }
    public Map<String, ArrayList<String>> getRoutineItems() {
        return routineItems;
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
                    Item parent = new ParentItem(key, PARENT_ITEM_VIEW,null);
                    Log.d("ROUTINE_LIST", key);
                    items.add(parent);
                    ArrayList<Item> childList = new ArrayList<Item>();
                    for (String value : routineItems.get(key)) {
                        Item child = new ChildItem(value, CHILD_ITEM_VIEW,key);
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
        Item parent = new ParentItem(parentName, PARENT_ITEM_VIEW,null);
        items.add(parent);
        ArrayList<Item> childList = new ArrayList<Item>();
        for (String value : childItems) {
            Item child = new ChildItem(value, CHILD_ITEM_VIEW,parentName);
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
        for (Item i : visibleItems){
            if(i instanceof ParentItem){
                if (i.name.equals(parent)){
                    Log.d("ROUTINE_SET","Parent : "+i.name);
                    return true;
                }
            }
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
                    String routine_name = (String) parentItemVH.name.getText();
                    Log.d("ONCLICK_PARENT", routine_name);
                    RoutineDetailFragment rtd = new RoutineDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("routine_Name",routine_name);
                    rtd.setArguments(bundle);
                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    ft.add(R.id.frameLayout,rtd,"routine_detail");
                    ft.show(rtd);
                    ft.addToBackStack(null);
                    ft.commit();
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
        return false;
    }

    @Override
    public void onItemRemove(int position, boolean isDelete) {
        Log.i(TAG, "onItemRemove. item : " + visibleItems.get(position).name);
        if (isDelete) {
            switch (visibleItems.get(position).viewType) {
                case PARENT_ITEM_VIEW:
                    new AlertDialog.Builder(lastContext)
                            .setMessage("루틴을 삭제하면 하위 운동들도 전부 삭제됩니다.\n삭제하시겠습니까?")
                            .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int childItemSize = getVisibleChildItemSize(position);
                                    String deleteItem_Parent = visibleItems.get(position).name;
                                    // Volley Here
                                    RequestQueue queue_pr = Volley.newRequestQueue(lastContext);
                                    String url_pr = JSP.Companion.deleteRoutine(inputId,visibleItems.get(position).name);
                                    StringRequest stringRequest_pr = new StringRequest(Request.Method.GET, url_pr, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(lastContext, "Removed Success :"+deleteItem_Parent, Toast.LENGTH_SHORT).show();
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(lastContext, "sever error", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    queue_pr.add(stringRequest_pr);

                                    for (int i = 0; i <= childItemSize; i++) {
                                        visibleItems.remove(position);
                                    }

                                    Log.d("ROUTINE_DELETE","Delete Position :"+position);
                                    items.remove(deleteItem_Parent);
                                    notifyItemRangeRemoved(position, childItemSize + 1);

                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    notifyDataSetChanged();
                                }
                            }).create().show();
                    break;
                case CHILD_ITEM_VIEW:
                    ChildItem sel_ch_item = (ChildItem)visibleItems.get(position);
                    String deleteItem_ch = sel_ch_item.name;
                    Log.d("ROUTINE_DELETE",sel_ch_item.name);
                    // Volley Here
                    RequestQueue queue_ch = Volley.newRequestQueue(lastContext);
                    String url_ch = JSP.Companion.deleteRoutineExc(inputId,sel_ch_item.parent_Name,sel_ch_item.name);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url_ch, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(lastContext, "Removed Success :"+deleteItem_ch, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(lastContext, "sever error", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue_ch.add(stringRequest);

                    //삭제 후에 삭제한 것의 부모가 child를 가지지 않으면 부모도 삭제함 getVisibleChildItemSize
                    Log.d("ROUTINE_DELETE","Delete Position :"+position);
                    visibleItems.remove(position);
                    if (getItemViewType(position-1) == PARENT_ITEM_VIEW && getVisibleChildItemSize(position-1) == 0){ //부모인데, 자식이 없다면 삭제
                        visibleItems.remove(position-1);
                    }
//                    notifyItemRemoved(position);
                    notifyDataSetChanged();
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