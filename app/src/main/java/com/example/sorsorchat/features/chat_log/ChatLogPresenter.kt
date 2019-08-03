package com.example.sorsorchat.features.chat_log

import android.util.Log
import com.example.sorsorchat.common.Model.ChatMessage
import com.example.sorsorchat.features.SignUp.BasePresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class ChatLogPresenter :ChatLogContract.Presenter, BasePresenter<ChatLogContract.View>() {
    override fun listenToMessages() {
       // val ref = FirebaseDatabase.getInstance().getReference("/messages")
        val fromId = FirebaseAuth.getInstance().uid
        val toId = ChatLogActivity.toUser?.uid
        val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")
        ref.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                val chatMessage = p0.getValue(ChatMessage::class.java)
                if (chatMessage != null) {

                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        getView()?.passTextToChatFrom(chatMessage.text)
                    } else {
                        getView()?.passTextToChatTo(chatMessage.text)
                    }
                    Log.i("chat", " u send ${chatMessage?.text}")

                }


            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }

        })
    }
}