package com.example.sorsorchat.features.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sorsorchat.R
import com.example.sorsorchat.common.Constants
import com.example.sorsorchat.common.Navigation.NavigationToScreens
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginActivityContract.view {

    private lateinit var loginPresenter: LoginActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialize()
        goToSignUp.setOnClickListener {
            NavigationToScreens.navigateToSignUpActivity(this@LoginActivity)
        }
        signIn.setOnClickListener {
            if (emailSignIn.text.isNotEmpty() && password.text.isNotEmpty()) {
                signIn.startAnimation()
                loginPresenter.signInWithEmailAndPassword(emailSignIn.text.toString(), password.text.toString())
            } else {
                Toast.makeText(this, Constants.ENTER_EMAIL_AND_PASSWORD, Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun goToHomeScreen() {
        signIn.revertAnimation {
            signIn.text = "Done"
        }
        NavigationToScreens.navigateToHomeActivity(this@LoginActivity)
        finish()
    }

    override fun failureToauthenticate(message: String?) {
        signIn.revertAnimation()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initialize() {
        loginPresenter = LoginActivityPresenter()
        loginPresenter.setView(this@LoginActivity)
    }

}
