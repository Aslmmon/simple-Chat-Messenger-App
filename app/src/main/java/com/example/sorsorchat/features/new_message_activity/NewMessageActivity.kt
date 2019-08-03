package com.example.sorsorchat.features.new_message_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sorsorchat.R
import com.example.sorsorchat.common.Navigation.NavigationToScreens
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_message.*

class NewMessageActivity : AppCompatActivity(), NewMessageContract.view {
    override fun goToChatLogActivity(item: Item<ViewHolder>) {
        NavigationToScreens.navigateToChatLogActivity(this,item)
    }

    private lateinit var newMessagePresenter: NewMessagePresenter
    companion object{
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        val adapter = GroupAdapter<ViewHolder>()
        supportActionBar?.title = "Select User"
        initializevars()
        newMessagePresenter.fetchUsersFromFirebaseDatabase(adapter)
        recycler.adapter = adapter

    }

    private fun initializevars() {
        newMessagePresenter = NewMessagePresenter()
        newMessagePresenter.setView(this@NewMessageActivity)
    }
}





