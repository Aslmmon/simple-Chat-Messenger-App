package com.example.sorsorchat.features.new_message_activity

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

interface NewMessageContract {

    interface presenter{
        fun fetchUsersFromFirebaseDatabase(adapter: GroupAdapter<ViewHolder>)
    }
    interface view {
        fun goToChatLogActivity(item: Item<ViewHolder>)

    }
}