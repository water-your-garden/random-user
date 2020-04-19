package com.example.randomuser.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.randomuser.domain.UserModel
import com.example.randomuser.getOrAwaitValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val mockUser = UserModel(
        "eb4390fe-9e7a-4607-a65d-c4abd849ebfe",
        "Ms",
        "Emma",
        "Robinson",
        "female",
        "Greymouth",
        "New Zealand",
        "emma.robinson@example.com",
        "(879)-197-5424",
        "1993-05-16T23:50:59.789Z",
        27,
        "https://randomuser.me/api/portraits/women/41.jpg"
        )

    @Test
    fun getUsers() {

    }

    @Test
    fun getNavigateToSelectedProperty() {

    }

    @Test
    fun displayUserDetails() {
        // given a new ViewModel
        val mainViewModel = MainViewModel(ApplicationProvider.getApplicationContext())

        // when navigation to userDetails fragment is complete
        mainViewModel.displayUserDetails(mockUser)

        // then the value of navigateToSelectedProperty is equal to mockUser
        val value = mainViewModel.navigateToSelectedProperty.getOrAwaitValue()
        assertThat(value, `is`(mockUser))
    }

    @Test
    fun displayUserDetailsComplete() {
        // given a new ViewModel
        val mainViewModel = MainViewModel(ApplicationProvider.getApplicationContext())

        // when navigation to userDetails fragment is complete
        mainViewModel.displayUserDetailsComplete()

        // then the value of navigateToSelectedProperty is null
        val value = mainViewModel.navigateToSelectedProperty.getOrAwaitValue()
        assertThat(value, nullValue())
    }
}