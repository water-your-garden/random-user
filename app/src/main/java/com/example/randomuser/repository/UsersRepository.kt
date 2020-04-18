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

    private var lastRequestedPage = 1

    suspend fun refreshUsers() {
        withContext(Dispatchers.IO) {
            val userList = UserNetwork.retrofitService.getUsers(lastRequestedPage, NETWORK_PAGE_SIZE, SEED).await()
            lastRequestedPage++
            database.userDao.insertAll(userList.asDatabaseModel())
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 5
        private const val SEED = "abc"
    }
}