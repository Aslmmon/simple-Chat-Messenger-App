package com.example.sorsorchat.common.Navigation

import android.content.Context
import android.content.Intent
import com.example.sorsorchat.common.Constants
import com.example.sorsorchat.common.ViewHolder.userItemModel
import com.example.sorsorchat.features.Login.LoginActivity
import com.example.sorsorchat.features.SignUp.SignUpActivity
import com.example.sorsorchat.features.chat_log.ChatLogActivity
import com.example.sorsorchat.features.home_latest_message_activity.LatestMessageActivity
import com.example.sorsorchat.features.new_message_activity.NewMessageActivity
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder


object NavigationToScreens {
    fun navigateToSignUpActivity(context: Context) {
        val intent = Intent(context, SignUpActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToHomeActivity(context: Context) {
        val intent = Intent(context, LatestMessageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    fun navigateToLoginActivity(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    fun navigateToNewMessageActivity(context: Context) {
        val intent = Intent(context, NewMessageActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToChatLogActivity(
        context: Context,
        item: Item<ViewHolder>
    ) {
        val userItem = item as userItemModel
        val intent = Intent(context, ChatLogActivity::class.java)
        intent.putExtra(Constants.USER_MODEL, userItem.user)
        context.startActivity(intent)
    }


}
