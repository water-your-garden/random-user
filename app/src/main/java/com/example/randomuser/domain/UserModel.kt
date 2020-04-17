package com.example.randomuser.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    val uuid: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val city: String,
    val country: String,
    val email: String,
    val phone: String,
    val dateOfBirth: String,
    val age: Int,
    val profilePhotoUrl: String
) : Parcelable