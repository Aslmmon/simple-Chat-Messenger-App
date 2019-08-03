package com.example.sorsorchat.common.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(val username: String, val uid: String, val profileImageUrl: String) : Parcelable {
    constructor() : this("", "", "")
}