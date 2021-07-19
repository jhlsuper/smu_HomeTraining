package com.example.l.expandable_cardview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mlkit_pose.R
import com.example.mlkit_pose.databinding.CardviewChildBinding
import kotlinx.android.synthetic.main.cardview_parent.view.*



class ExpandableCardViewAdapter(var items: MutableList<Item>, val onClickDeleteIcon: (item: Item) -> Unit) : //2. delete button이 눌렸을때 onclickDeleteIcon을 실행하라는뜻, 0->Unit이기때문에 함수자체에 return없다는뜻
        RecyclerView.Adapter<ExpandableCardViewAdapter.TodoViewHolder>() {
    class TodoViewHolder(val binding: CardviewChildBinding) : RecyclerView.ViewHolder(binding.root) {
        val item_text: TextView
            get() {
                TODO()
            }
    }


    companion object {
        val PARENT = 0
        val CHILD = 1
        val OPEN = 0.0F
        val CLOSE = 180.0F
    }

    // ---------------------------------
    var mPosition = 0

    fun getPosition(): Int {
        return mPosition
    }

    fun setPosition(position: Int) {
        mPosition = position
    }

    fun removeItem(position: Int) {
        if (position > 0) {
            items.removeAt(position)
            notifyDataSetChanged()
        }
    }
    // ---------------------------------

    data class Item(
            val type: Int = 0,
            var text: String = "Default",
            var children: List<Item>? = null
    )

    inner class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textView = v.item_text
        val toggleImageView = v.arrow_button


    }


    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        val inflater = LayoutInflater.from(parent?.context)
        var view: View? = null

        when (viewType) {
            PARENT -> view = inflater.inflate(R.layout.cardview_parent, parent, false)
            CHILD -> view = inflater.inflate(R.layout.cardview_child, parent, false)
        }

        return TodoViewHolder(CardviewChildBinding.bind(view!!))
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val itemHolder = holder as? ItemHolder
        val item = items[position]


        itemHolder?.let {
            it.toggleImageView?.let {
                it.setImageResource(R.drawable.arrow_button)
                it.rotation = if (item.children == null) OPEN else CLOSE

                it.setOnClickListener { view ->
                    val start = items.indexOf(item) + 1
                    if (item.children == null) {
                        var count = 0
                        var nextHeader = items.indexOf(items.find {
                            (count++ >= start) && (it.type == item.type)
                        })

                        if (nextHeader == -1) nextHeader = items.size
                        item.children = items.slice(start..nextHeader - 1)

                        val end = item.children!!.size
                        if (end > 0) items.removeAll(item.children!!)

                        view.animate().rotation(CLOSE).start()
                        notifyItemRangeRemoved(start, end)
                    } else {
                        item.children?.let {
                            items.addAll(start, it)
                            view.animate().rotation(OPEN).start()
                            notifyItemRangeInserted(start, it.size)
                            item.children = null
                        }
                    }
                }
            }

            it.textView.text = item.text
        }

        val listposition = items[position]
        holder.binding.itemText.text = listposition.text
        holder.binding.sportDeleteButton.setOnClickListener {
            onClickDeleteIcon.invoke(listposition) // deleteimage가 눌렸을때 listposition를 전달하면서 onClickDeleteIcon함수를 실행한다.
        }
    }

}