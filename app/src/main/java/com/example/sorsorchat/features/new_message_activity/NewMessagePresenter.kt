package com.example.sorsorchat.features.new_message_activity

import android.util.Log
import com.example.sorsorchat.common.Model.User
import com.example.sorsorchat.common.ViewHolder.userItemModel
import com.example.sorsorchat.features.SignUp.BasePresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class NewMessagePresenter : NewMessageContract.presenter,
    BasePresenter<NewMessageContract.view>() {

    override fun fetchUsersFromFirebaseDatabase(adapter: GroupAdapter<ViewHolder>) {
        val ref = FirebaseDatabase.getInstance().getReference("users")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    Log.i("New Message", "$it")
                    val user = it.getValue(User::class.java)
                    if (user != null) {
                        adapter.add(userItemModel(user))

                    }
                }
                adapter.setOnItemClickListener { item, view ->
                   getView()?.goToChatLogActivity(item)
                }

            }

        })
    }


}