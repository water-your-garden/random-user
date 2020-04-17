package com.example.randomuser.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomuser.domain.UserModel

@Entity
data class DatabaseUser constructor(
    @PrimaryKey
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
)

fun List<DatabaseUser>.asDomainModel() : List<UserModel> {
    return map {
        UserModel(
            uuid = it.uuid,
            title = it.title,
            firstName = it.firstName,
            lastName = it.lastName,
            gender = it.gender,
            city = it.city,
            country = it.country,
            email = it.email,
            phone = it.phone,
            dateOfBirth = it.dateOfBirth,
            age = it.age,
            profilePhotoUrl = it.profilePhotoUrl

        )
    }
}