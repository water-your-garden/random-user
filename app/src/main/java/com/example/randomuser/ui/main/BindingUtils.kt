package com.example.randomuser.ui.main

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.randomuser.R
import com.example.randomuser.domain.UserModel

@BindingAdapter("userData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<UserModel>?) {
    val adapter = recyclerView.adapter as UserListAdapter
    adapter.submitList(data)
}

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
fun ImageView.setUserThumbnail(thumbUrl: String) {
    thumbUrl.let {
        val imgUri = thumbUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.loading_animation))
            .into(this)
    }
}
