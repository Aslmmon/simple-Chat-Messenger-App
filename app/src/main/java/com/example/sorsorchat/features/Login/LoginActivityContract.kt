package com.example.sorsorchat.features.Login

interface LoginActivityContract {

    interface Presenter {
        fun signInWithEmailAndPassword(email: String, password: String)

    }

    interface view {
        fun goToHomeScreen()
        fun failureToauthenticate(message: String?)
    }
}