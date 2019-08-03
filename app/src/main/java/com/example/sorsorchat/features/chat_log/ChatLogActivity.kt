package com.example.sorsorchat.features.chat_log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sorsorchat.R
import com.example.sorsorchat.common.Model.ChatMessage
import com.example.sorsorchat.common.ViewHolder.ChatFromItems
import com.example.sorsorchat.common.ViewHolder.ChatToItems
import com.example.sorsorchat.common.Constants
import com.example.sorsorchat.common.Model.User
import com.example.sorsorchat.features.home_latest_message_activity.LatestMessageActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_dialog.*

class ChatLogActivity : AppCompatActivity(), ChatLogContract.View {

    val adapter = GroupAdapter<ViewHolder>()
    private lateinit var chatLogPresenter: ChatLogPresenter

    companion object {
        var toUser: User? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_dialog)
        initialize()

        toUser = intent.getParcelableExtra<User>(Constants.USER_MODEL)
        supportActionBar?.title = " ${toUser?.username} "


        chatLogPresenter.listenToMessages()

        send_btn.setOnClickListener {
            perfromSendMessage()
        }
        recycler_chat.adapter = adapter

    }

    override fun passTextToChatFrom(text: String) {
        val currentUser = LatestMessageActivity.currentUser ?: return
        adapter.add(ChatFromItems(text, currentUser!!))
    }

    override fun passTextToChatTo(text: String) {
        adapter.add(ChatToItems(text, toUser!!))
    }


    private fun initialize() {
        chatLogPresenter = ChatLogPresenter()
        chatLogPresenter.setView(this@ChatLogActivity)
    }


    private fun perfromSendMessage() {
        val textEnterd = enterText.text.toString()
        val fromId = FirebaseAuth.getInstance().uid
        val toId = toUser?.uid
        val refrence = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()
        val toRefrence = FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()
        val latestMessageRefrence = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId/$toId")


        if (fromId == null) return

        val chat = ChatMessage(refrence.key!!, textEnterd, fromId, toId!!, System.currentTimeMillis() / 1000)

        refrence.setValue(chat).addOnSuccessListener {
            Log.i("chat", "saved $chat")
            enterText.text.clear()
            recycler_chat.scrollToPosition(adapter.itemCount - 1)
        }
        toRefrence.setValue(chat)
        latestMessageRefrence.setValue(chat)
    }


}


