package com.example.sorsorchat.features.Login

import com.example.sorsorchat.features.SignUp.BasePresenter
import com.google.firebase.auth.FirebaseAuth

class LoginActivityPresenter : LoginActivityContract.Presenter, BasePresenter<LoginActivityContract.view>(){

    private lateinit var mAuth: FirebaseAuth

    override fun signInWithEmailAndPassword(email: String, password: String) {
        mAuth = FirebaseAuth.getInstance()

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
         getView()?.goToHomeScreen()
            return@addOnCompleteListener
        }.addOnFailureListener {
            getView()?.failureToauthenticate(it.message)
            return@addOnFailureListener
        }
    }


}