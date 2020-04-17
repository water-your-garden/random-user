package com.example.randomuser.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUserContainer(val videos: List<NetworkUser>)

@JsonClass(generateAdapter = true)
data class NetworkUser(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dateOfBirth: DateOfBirth,
    val phone: String,
    val profilePhotoUrl: Picture
)

@JsonClass(generateAdapter = true)
data class Name(
    val title: String,
    val firstName: String,
    val lastName: String
)

@JsonClass(generateAdapter = true)
data class Location(
    val city: String,
    val country: String
)

@JsonClass(generateAdapter = true)
data class Login(
    val uuid: String
)

@JsonClass(generateAdapter = true)
data class DateOfBirth(
    val date: String,
    val age: Int
)

@JsonClass(generateAdapter = true)
data class Picture(
    val profilePhotoUrl: String
)

