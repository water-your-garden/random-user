package com.example.randomuser.ui.main

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.randomuser.R
import com.example.randomuser.domain.UserModel
import java.text.SimpleDateFormat

@BindingAdapter("userData")
fun bindRecyclerView(recyclerView: RecyclerView, data: PagedList<UserModel>?) {
    val adapter = recyclerView.adapter as UserListAdapter
    adapter.submitList(data)
}

@BindingAdapter("userNameString")
fun TextView.setUserName(item: UserModel) {
    text = resources.getString(R.string.name_template, item.title, item.firstName, item.lastName)
}

@BindingAdapter("userDateOfBirthString")
fun TextView.setUserDateOfBirth(dateOfBirth: String) {
    var sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val newDate = sdf.parse(dateOfBirth.removeSuffix("."))
    sdf = SimpleDateFormat("d MMM yyyy")
    text = sdf.format(newDate).toString()
}

@BindingAdapter("userThumbnailImage")
fun ImageView.setUserThumbnail(thumbUrl: String) {
    thumbUrl.let {
        val imgUri = thumbUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .dontAnimate()
                    .placeholder(R.drawable.ic_broken_image)
                    .error(R.drawable.loading_animation)
            )
            .into(this)
    }
}

@BindingAdapter("userAgeString")
fun TextView.setUserAge(age: Int) {
    text = age.toString()
}

@BindingAdapter("userLocationString")
fun TextView.setUserLocation(item: UserModel) {
    text = resources.getString(R.string.location_template, item.country, item.city)
}
