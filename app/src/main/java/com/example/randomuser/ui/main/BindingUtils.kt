package com.example.randomuser.ui.main

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.randomuser.R
import com.example.randomuser.domain.UserModel

@BindingAdapter("userNameString")
fun TextView.setUserName(item: UserModel) {
    text = "${item.title} ${item.firstName} ${item.LastName}"
}

@BindingAdapter("userDateOfBirthString")
fun TextView.setUserDateOfBirth(item: UserModel) {
    text = item.dateOfBirth
}

@BindingAdapter("userGenderString")
fun TextView.setUserGender(item: UserModel) {
    text = item.gender
}

@BindingAdapter("userThumbnailImage")
fun ImageView.setUserThumbnail(item: UserModel) {
    //TODO: set image from thumbnail url
    setImageResource(R.drawable.ic_launcher_background)
}
