package com.example.sorsorchat.common.ViewHolder

import com.example.sorsorchat.R
import com.example.sorsorchat.common.Model.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*

class ChatFromItems(val text: String,val user: User) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.text_from.text = text
        val image = viewHolder.itemView.chat_from_image
        Picasso.get().load(user.profileImageUrl).into(image)
    }
}

class ChatToItems(val myText: String, val user: User) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.text_to.text = myText
        val image = viewHolder.itemView.chat_to_image

        Picasso.get().load(user.profileImageUrl).into(image)
    }
}
