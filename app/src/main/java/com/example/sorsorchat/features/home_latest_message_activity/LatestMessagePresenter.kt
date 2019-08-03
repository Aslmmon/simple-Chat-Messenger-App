package com.example.sorsorchat.features.home_latest_message_activity

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.sorsorchat.common.Model.ChatMessage
import com.example.sorsorchat.common.Model.User
import com.example.sorsorchat.common.Navigation.NavigationToScreens
import com.example.sorsorchat.features.Login.LoginActivity
import com.example.sorsorchat.features.SignUp.BasePresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class LatestMessagePresenter : LatestMessageContract.presenter, BasePresenter<LatestMessageContract.view>() {
    override fun listenForLatestMessages() {
        val fromId = FirebaseAuth.getInstance().uid
        val refrence = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId")
        refrence.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                var chatMessage = p0.getValue(ChatMessage::class.java) ?: return
                // Toast.makeText(this@LatestMessageActivity,"chat Message is  is ${chatMessage.fromId}",Toast.LENGTH_SHORT).show()
                LatestMessageActivity.latestMessageMap[p0.key!!] = chatMessage
                // Toast.makeText(this@LatestMessageActivity,"mssg is ${chatMessage.text}",Toast.LENGTH_SHORT).show()
                getView()?.refrechRecyclerViewLatestMessages()

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                var chatMessage = p0.getValue(ChatMessage::class.java) ?: return
                LatestMessageActivity.latestMessageMap[p0.key!!] = chatMessage
                getView()?.refrechRecyclerViewLatestMessages()

            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }

        })
    }

    override fun fetchCurrentUser() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                LatestMessageActivity.currentUser = p0.getValue(User::class.java)
                Log.i("LatestMessage", "current user is ${LatestMessageActivity.currentUser?.username}")
            }

        })
    }

    override fun verifyUserIsLoggedIn() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            getView()?.goToLoginActivity()
        }
    }


}