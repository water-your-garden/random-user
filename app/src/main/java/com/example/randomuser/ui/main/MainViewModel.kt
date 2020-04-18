package com.example.randomuser.ui.main

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.randomuser.database.getDatabase
import com.example.randomuser.domain.UserModel
import com.example.randomuser.repository.UsersRepository
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val usersRepository = UsersRepository(getDatabase(application), viewModelScope)

    val users : LiveData<PagedList<UserModel>> = usersRepository.users
//    private val _users = MutableLiveData<List<UserModel>>()
//    val users : LiveData<List<UserModel>>
//        get () = _users

    private val _navigateToSelectedProperty = MutableLiveData<UserModel>()
    val navigateToSelectedProperty: LiveData<UserModel>
        get() = _navigateToSelectedProperty

    init {
//        _users.value = usersRepository.users.value
        refreshUsersFromRepository()
    }

    private fun refreshUsersFromRepository() {
        viewModelScope.launch {
            try {
                usersRepository.requestAndSaveData()
            } catch(networkError: IOException) {
                //TODO: handle network error
            }
        }
    }

    fun displayUserDetails(user: UserModel) {
        _navigateToSelectedProperty.value = user
    }

    fun displayUserDetailsComplete() {
        _navigateToSelectedProperty.value = null
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
