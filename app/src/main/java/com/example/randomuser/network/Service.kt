package com.example.randomuser.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface UserService {
    @GET("?page=3&results=5&exc=registered,cell,id,nat")
    fun getUsers(): Deferred<NetworkUserContainer>
}

object UserNetwork {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val users = retrofit.create(UserService::class.java)
}