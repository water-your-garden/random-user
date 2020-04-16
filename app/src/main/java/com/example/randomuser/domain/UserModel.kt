package com.example.randomuser.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    val uuid: String,
    val title: String,
    val firstName: String,
    val LastName: String,
    val gender: String,
    val dateOfBirth: String,
    val profilePhotoUrl: String
) : Parcelable