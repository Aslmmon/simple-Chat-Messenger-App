package com.example.sorsorchat.features.home_latest_message_activity

interface LatestMessageContract {


    interface presenter {
        fun verifyUserIsLoggedIn()
        fun fetchCurrentUser()
        fun listenForLatestMessages()
    }

    interface view {
        fun goToLoginActivity()
        fun refrechRecyclerViewLatestMessages()
    }


}