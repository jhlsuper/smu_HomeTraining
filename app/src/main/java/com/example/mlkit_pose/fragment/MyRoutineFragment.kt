package com.example.mlkit_pose.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.l.expandable_cardview.ExpandableCardViewAdapter

import com.example.mlkit_pose.R
import com.example.mlkit_pose.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.cardview_child.*
import kotlinx.android.synthetic.main.fragment_my_routine.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class MyRoutineFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_routine, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.inflate(layoutInflater)
        val view = binding.root

        val cardView = recycler_cardview
        val itemList = mutableListOf<ExpandableCardViewAdapter.Item>()
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "P-Lang"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Kotlin"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Go"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "C++"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Java"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Clojure"))


        val item = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Android")
        item.children = listOf(
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Jelly Bean"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "KitKat"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Lollipop"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Marshmallow"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Nougat"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Oreo"))
        itemList.add(item)

        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "NoteBook"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "MS Surface Pro 4"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Google Pixel Book"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Apple MacBook"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Samsung Series 9"))

        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Smart Phone"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Apple iPhone X"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Google Pixel XL 2"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Samsung Galaxy Note 8"))
        itemList.add(ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "LG V30"))

        val nation = ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.PARENT, "Nation")
        nation.children = listOf(
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Republic Of Korea"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Canada"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "America"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Japan"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Denmark"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Britain"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "China"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Russia"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "France"),
                ExpandableCardViewAdapter.Item(ExpandableCardViewAdapter.CHILD, "Turkey"))
        itemList.add(nation)

//        this.activity?.recycler_cardview?.layoutManager = LinearLayoutManager(context)
//        this.activity?.recycler_cardview?.adapter= ExpandableCardViewAdapter(, )

        cardView.layoutManager = LinearLayoutManager(context)
        cardView.adapter = ExpandableCardViewAdapter(itemList, onClickDeleteIcon = {
            itemList.remove(it)
            cardView.adapter?.notifyDataSetChanged()
        })


    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyRoutineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }



}