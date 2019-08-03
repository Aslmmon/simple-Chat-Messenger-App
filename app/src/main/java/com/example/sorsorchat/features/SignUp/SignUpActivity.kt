package com.example.sorsorchat.features.SignUp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sorsorchat.R
import com.example.sorsorchat.common.Constants
import com.example.sorsorchat.common.Navigation.NavigationToScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.app.ProgressDialog


class SignUpActivity : AppCompatActivity(), SignUpContract.view {


    private var mAuth: FirebaseAuth? = null
    private lateinit var signUpPresenter: SignUpPresenter
    var selectedPhotoUri: Uri? = null
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initialize()
        signUp.setOnClickListener {
            val userEmail = emailSignUp.text
            val userPassowrd = passwordSignUp.text
            if (selectedPhotoUri != null) {
                if (userEmail.isNotEmpty() && userPassowrd.isNotEmpty()) {
                    signUpPresenter.createEmailAndPassword(userEmail, userPassowrd, selectedPhotoUri)
                } else {
                    Toast.makeText(this, Constants.ENTER_EMAIL_AND_PASSWORD, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, Constants.UPLOAD_PIC_PLEASE, Toast.LENGTH_SHORT).show()
            }
        }

        profileImage.setOnClickListener {
            grantPermissionForReadExternal()
        }


    }

    private fun grantPermissionForReadExternal() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        )  // Permission is not granted
        // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    Constants.READ_EXTERNAL_STORAGE
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            } else {
            Toast.makeText(this, "permission Granted", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, Constants.IMAGE_REQUEST_CODE)
        }
    }


    private fun goToLatestMessageActivity() {
        NavigationToScreens.navigateToHomeActivity(this@SignUpActivity)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Log.i("registerImage", "photo taken")
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            profileImage.setImageBitmap(bitmap)
        }
    }

    override fun showDoneUploadingToDatabase() {
        progressDialog?.dismiss()
        Toast.makeText(this@SignUpActivity, "Done Saved To Database", Toast.LENGTH_SHORT).show()
        goToLatestMessageActivity()
        finish()
    }


    private fun initialize() {
        progressDialog = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()
        signUpPresenter = SignUpPresenter()
        signUpPresenter.setView(this@SignUpActivity)
    }

    override fun showProgressDialog() {
        progressDialog?.setMessage("loading, Creating your Account")
        progressDialog?.show()
        progressDialog?.setCancelable(false)
    }

    override fun showSuccess() {
        emailSignUp.setText("")
        passwordSignUp.setText("")
        Toast.makeText(this@SignUpActivity, "Succeffuly registerd", Toast.LENGTH_SHORT).show()
    }

    override fun showFailed() {

    }

    override fun showErrorMessage() {
        Toast.makeText(this@SignUpActivity, "Please Check Your fields First", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorMessageEmailORPassword(message: String) {
        Toast.makeText(this@SignUpActivity, "${message}", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            Constants.READ_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    startActivityForResult(intent, Constants.IMAGE_REQUEST_CODE)
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

}
