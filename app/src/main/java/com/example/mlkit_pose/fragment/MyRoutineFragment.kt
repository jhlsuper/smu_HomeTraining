package com.example.mlkit_pose.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ExpandableListView.OnGroupExpandListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mlkit_pose.MyExpandableListAdapter
import com.example.mlkit_pose.R
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MyRoutineFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    var groupList: List<String>? = null
    var childList: List<String>? = null
    var mobileCollection: Map<String, List<String>>? = null
    var expandableListView: ExpandableListView? = null
    var expandableListAdapter: ExpandableListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    private fun createCollection() {
        val samsungModels = arrayOf("Samsung Galaxy M21", "Samsung Galaxy F41", "Samsung Galaxy M51")
        val googleModels = arrayOf("Pixel 4 XL", "Pixel 3a")
        val redmiModels = arrayOf("Pixel 4 XL", "Pixel 3a")
        val vivoModels = arrayOf("Pixel 4 XL", "Pixel 3a")
        val nokiaModels = arrayOf("Pixel 4 XL", "Pixel 3a")
        val motorolaModels = arrayOf("Pixel 4 XL", "Pixel 3a")
        val emptyModels = arrayOf("")
        mobileCollection = HashMap()
        for (group in groupList!!) {
            if (group == "Samsung") {
                loadChild(samsungModels)
            } else if (group == "Google") {
                loadChild(googleModels)
            } else if (group == "Redmi") {
                loadChild(redmiModels)
            } else if (group == "Vivo") {
                loadChild(vivoModels)
            } else if (group == "Nokia") {
                loadChild(nokiaModels)
            } else if (group == "Motorola") {
                loadChild(motorolaModels)
            }
            else loadChild(emptyModels)
            (mobileCollection as HashMap<String, List<String>>).put(group, childList!!)
        }
    }

    private fun loadChild(mobileModels: Array<String>) {
        childList = ArrayList()
        for (model in mobileModels) {
            (childList as ArrayList<String>).add(model)
        }

    }

    private fun createGroupList() {
        groupList = ArrayList()
        (groupList as ArrayList<String>).add("Samsung")
        (groupList as ArrayList<String>).add("Google")
        (groupList as ArrayList<String>).add("Redmi")
        (groupList as ArrayList<String>).add("Vivo")
        (groupList as ArrayList<String>).add("Nokia")
        (groupList as ArrayList<String>).add("Motorola")
        (groupList as ArrayList<String>).add("empty")

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

        createGroupList()
        createCollection()
//        expandableListView = findViewById<ExpandableListView>(R.id.elvMobiles)
        expandableListView = getView()?.findViewById(R.id.elvMobiles) // 제일 큰 뷰, 줄줄이 나열된 뷰
        expandableListAdapter = MyExpandableListAdapter(context, groupList, mobileCollection)
        expandableListView!!.setAdapter(expandableListAdapter)
        expandableListView!!.setOnGroupExpandListener(object : OnGroupExpandListener {
            var lastExpandedPosition = -1
            override fun onGroupExpand(groupPosition: Int) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    expandableListView!!.collapseGroup(lastExpandedPosition) // 접음
                }
                lastExpandedPosition = groupPosition
            }
        })
        expandableListView!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val selected = expandableListAdapter!!.getChild(groupPosition, childPosition).toString()
            Toast.makeText(context, "Selected: $selected", Toast.LENGTH_SHORT)
                .show()
            true
        }
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