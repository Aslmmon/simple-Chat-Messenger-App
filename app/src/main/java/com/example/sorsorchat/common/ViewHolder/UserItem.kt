package com.example.sorsorchat.common.ViewHolder

import com.example.sorsorchat.R
import com.example.sorsorchat.common.Model.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.list_items.view.*

class userItemModel(val user: User) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.list_items
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.userName.text = user.username
        Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.circleImageView)
    }
}