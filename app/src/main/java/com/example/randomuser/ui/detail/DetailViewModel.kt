package com.example.randomuser.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.randomuser.domain.UserModel

class DetailViewModel(user: UserModel, app: Application) : AndroidViewModel(app) {

    private val _selectedUser = MutableLiveData<UserModel>()
    val user: LiveData<UserModel>
        get() = _selectedUser

    init {
        _selectedUser.value = user
    }

    class DetailViewModelFactory(
        private val user: UserModel,
        private val app: Application
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel(user, app) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}