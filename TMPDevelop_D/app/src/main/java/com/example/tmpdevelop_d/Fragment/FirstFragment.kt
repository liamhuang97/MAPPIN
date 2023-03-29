package com.example.tmpdevelop_d.Fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmpdevelop_d.Adapter.GroupAdapter
import com.example.tmpdevelop_d.Dialog.CreateGroupDialog
import com.example.tmpdevelop_d.R
import com.example.tmpdevelop_d.Users.Group
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirstFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GroupAdapter
    private lateinit var fab: FloatingActionButton
    private val groups = arrayListOf<Group>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 綁定佈局
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        // 綁定 RecyclerView
        recyclerView = view.findViewById(R.id.group_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = GroupAdapter(groups)
        recyclerView.adapter = adapter

        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore

        // Get the collection reference
        val groupsRef = db.collection("Groups")

        // 綁定 FloatingActionButton
        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            // 點擊 FloatingActionButton 後顯示創建群組的 Dialog
            showCreateGroupDialog()
        }
        // Retrieve data from Firestore and populate ArrayList
        groupsRef.get().addOnSuccessListener { result ->
            groups.clear()
            for (document in result) {
                val group = document.toObject(Group::class.java)
                groups.add(group)
            }
            adapter.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            Log.d(TAG, "Error getting documents: ", exception)
        }

        return view
    }



    // 顯示創建群組的 Dialog
    private fun showCreateGroupDialog() {
        val createGroupDialog = CreateGroupDialog()
        createGroupDialog.show(childFragmentManager, "createGroupDialog")
    }
}