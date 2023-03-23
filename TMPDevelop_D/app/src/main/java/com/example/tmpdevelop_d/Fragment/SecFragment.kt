package com.example.tmpdevelop_d.Fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmpdevelop_d.Adapter.RecyclerViewAdapter
import com.example.tmpdevelop_d.R
import com.example.tmpdevelop_d.Users.Users
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SecFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sec, container, false)
        recyclerView = view.findViewById(R.id.recycle_friends)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore

        // Get the collection reference
        val usersRef = db.collection("users")

        // Create an empty ArrayList to hold user data
        val userList = arrayListOf<Users>()

        // Retrieve data from Firestore and populate ArrayList
        usersRef.get().addOnSuccessListener { result ->
            val userList = arrayListOf<Users>()
            for (document in result) {
                val user = document.toObject(Users::class.java)
                userList.add(user)
            }
            recyclerView.adapter = RecyclerViewAdapter(userList)
        }.addOnFailureListener { exception ->
            Log.d(TAG, "Error getting documents: ", exception)
        }


        return view

    }
}