package com.example.sorsorchat.features.SignUp

import android.net.Uri
import android.text.Editable

interface SignUpContract {
    interface Presenter {
        fun createEmailAndPassword(userEmail: Editable, userPassowrd: Editable,selectedPhotoUri: Uri?)
        fun uploadImageToFirebase(selectedPhotoUri: Uri?, userEmail: Editable)
        fun saveUserDataToDatabase(email:String?)
    }
    interface view{
        fun showSuccess()
        fun showDoneUploadingToDatabase()
        fun showFailed()
        fun showErrorMessage()
        fun showErrorMessageEmailORPassword(message:String)
        fun showProgressDialog()
//        fun uploadImageToFirebase(selectedPhotoUri: Uri?, userEmail: Editable)
//        fun saveUserDataToDatabase(email:String?)


    }
}