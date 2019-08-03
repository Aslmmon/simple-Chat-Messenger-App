package com.example.sorsorchat.features.home_latest_message_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.sorsorchat.R
import com.example.sorsorchat.common.Model.ChatMessage
import com.example.sorsorchat.common.Model.User
import com.example.sorsorchat.common.Navigation.NavigationToScreens
import com.example.sorsorchat.common.ViewHolder.LatestMessageRows
import com.example.sorsorchat.features.Login.LoginActivity
import com.example.sorsorchat.features.SignUp.SignUpPresenter
import com.example.sorsorchat.features.new_message_activity.NewMessagePresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_latest_message.*
import kotlinx.android.synthetic.main.latest_message_layout.view.*

class LatestMessageActivity : AppCompatActivity(), LatestMessageContract.view {


    companion object {
        var currentUser: User? = null
        val latestMessageMap = HashMap<String, ChatMessage>()
    }

    private lateinit var latestMessagePresnter: LatestMessagePresenter
    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_message)
        initializevars()
        latestMessagePresnter.verifyUserIsLoggedIn()
        latestMessagePresnter.fetchCurrentUser()
        latestMessagePresnter.listenForLatestMessages()

        recycler_latestMessage.adapter = adapter


    }

    private fun initializevars() {
        latestMessagePresnter = LatestMessagePresenter()
        latestMessagePresnter.setView(this@LatestMessageActivity)
    }

    override fun refrechRecyclerViewLatestMessages() {
        adapter.clear()
        latestMessageMap.values.forEach {
            adapter.add(LatestMessageRows(it))
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.new_message -> {
                NavigationToScreens.navigateToNewMessageActivity(this@LatestMessageActivity)
            }

            R.id.sign_out -> {
                FirebaseAuth.getInstance().signOut()
                NavigationToScreens.navigateToLoginActivity(this@LatestMessageActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun goToLoginActivity() {
        NavigationToScreens.navigateToLoginActivity(this)
    }


}
