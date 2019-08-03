package com.example.sorsorchat.common.ViewHolder

import com.example.sorsorchat.R
import com.example.sorsorchat.common.Model.ChatMessage
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.latest_message_layout.view.*

class LatestMessageRows(val chatMessage: ChatMessage) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.latest_message_layout
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.latest_message_text.text = chatMessage.text
    }

}