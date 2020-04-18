package com.example.randomuser.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.randomuser.database.UserDatabase
import com.example.randomuser.domain.UserModel
import com.example.randomuser.network.UserNetwork
import com.example.randomuser.network.asDatabaseModel
import kotlinx.coroutines.*
import java.io.IOException

class UsersRepository(private val database: UserDatabase,
                      val coroutineScope: CoroutineScope
): PagedList.BoundaryCallback<UserModel>(){

    val users: LiveData<PagedList<UserModel>> = getUsersFromDatabase()

    private var lastRequestedPage = 1

    private fun getUsersFromDatabase() : LiveData<PagedList<UserModel>> {
        val dataSourceFactory = database.userDao.getUsers()

        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(this)
            .build()

        return data

    }

    fun requestAndSaveData() {
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val userList = UserNetwork.retrofitService.getUsers(lastRequestedPage, NETWORK_PAGE_SIZE, SEED).await()
                    lastRequestedPage++
                    database.userDao.insertAll(userList.asDatabaseModel())
                }
            } catch (networkError: IOException) {
                //TODO: handle network error
            }
        }

    }

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: UserModel) {
        requestAndSaveData()
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
        private const val DATABASE_PAGE_SIZE = 5
        private const val SEED = "abc"
    }
}