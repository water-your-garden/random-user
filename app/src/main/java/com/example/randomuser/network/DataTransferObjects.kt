package com.example.randomuser.network

import com.example.randomuser.domain.UserModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUserContainer(val results: List<NetworkUser>)

@JsonClass(generateAdapter = true)
data class NetworkUser(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: DateOfBirth,
    val phone: String,
    val picture: Picture
)

@JsonClass(generateAdapter = true)
data class Name(
    val title: String,
    val first: String,
    val last: String
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
    val large: String
)

fun NetworkUserContainer.asDatabaseModel(): List<UserModel> {
    return results.map {
        UserModel(
            uuid = it.login.uuid,
            title = it.name.title,
            firstName = it.name.first,
            lastName = it.name.last,
            gender = it.gender,
            city = it.location.city,
            country = it.location.country,
            email = it.email,
            phone = it.phone,
            dateOfBirth = it.dob.date,
            age = it.dob.age,
            profilePhotoUrl = it.picture.large
        )
    }
}

