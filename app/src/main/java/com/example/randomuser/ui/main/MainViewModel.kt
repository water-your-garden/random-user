package com.example.randomuser.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.example.randomuser.domain.UserModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val users = MutableLiveData<List<UserModel>>()

    init {
        users.value = listOf(
            UserModel(
                "82b52e2b-0cce-46f8-b468-5aed37f4b209",
                "Ms",
                "Kay",
                "Chavez",
                "female",
                "1992-09-13T22:48:10.250Z",
                "https://randomuser.me/api/portraits/thumb/women/20.jpg",
                "https://randomuser.me/api/portraits/women/20.jpg"

            ),
            UserModel(
                "d3abb88e-a9b5-43ce-93f4-467f6464e558",
                "Mr",
                "Hans-Günter",
                "Kunkel",
                "male",
                "1980-07-26T05:18:24.905Z",
                "https://randomuser.me/api/portraits/thumb/men/45.jpg",
                "https://randomuser.me/api/portraits/men/45.jpg"
            )
        )
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
