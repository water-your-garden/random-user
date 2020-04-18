package com.example.randomuser.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.randomuser.database.UserDatabase
import com.example.randomuser.database.asDomainModel
import com.example.randomuser.domain.UserModel
import com.example.randomuser.network.UserNetwork
import com.example.randomuser.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepository(private val database: UserDatabase) {

    val users: LiveData<List<UserModel>> = Transformations.map(database.userDao.getUsers()) {
        it.asDomainModel()
    }

    suspend fun refreshUsers() {
        withContext(Dispatchers.IO) {
            val userList = UserNetwork.retrofitService.getUsers(3, 5, "abc").await()
            database.userDao.insertAll(userList.asDatabaseModel())
        }
    }
}