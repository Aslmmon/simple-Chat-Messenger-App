package com.example.sorsorchat.features.SignUp

import android.net.Uri
import android.text.Editable
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*


class SignUpPresenter : SignUpContract.Presenter,
    BasePresenter<SignUpContract.view>() {

    private var textUrl: String? = null
    var mAuth = FirebaseAuth.getInstance()

    override fun saveUserDataToDatabase(email: String?) {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("users/$uid")

        val user = User(uid!!, username = email.toString(), profileImageUrl = textUrl.toString())
        ref.setValue(user)
            .addOnSuccessListener {
                Log.i("SignUp", "saved To Database")
                getView()?.showDoneUploadingToDatabase()

            }

    }

    override fun uploadImageToFirebase(selectedPhotoUri: Uri?, userEmail: Editable) {
        if (selectedPhotoUri == null) return
        val filename = UUID.randomUUID().toString()
        val refrence = FirebaseStorage.getInstance().getReference("images/${filename}")
        refrence.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.i("SignUp", "successfully uploaded: ${it.metadata!!.path}")

                refrence.downloadUrl.addOnSuccessListener {
                    Log.i("SignUp", "File Location ${it}")
                    textUrl = it.toString()
                    Log.i("SignUp", "File Location in Text url ${textUrl}")
                    //   getView()?.saveUserDataToDatabase(userEmail.toString())
                    saveUserDataToDatabase(userEmail.toString())
                    // saveUserToDatabase(it.toString())
                }
            }

    }



    override fun createEmailAndPassword(userEmail: Editable, userPassowrd: Editable, selectedPhoto: Uri?) {
        mAuth.createUserWithEmailAndPassword(userEmail.toString(), userPassowrd.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    getView()?.showSuccess()
                    uploadImageToFirebase(selectedPhoto, userEmail)
                    Log.i("SignUp", "Successfully registerd with ! ${it.result!!.user.uid}")
                    getView()?.showProgressDialog()
                    return@addOnCompleteListener
                }
            }.addOnFailureListener {
                Log.i("SignUp", "Failed to create user due to ! ${it.message}")
                getView()!!.showErrorMessageEmailORPassword(it.message.toString())
                return@addOnFailureListener
            }
    }


}

class User(val uid: String, val username: String, val profileImageUrl: String)