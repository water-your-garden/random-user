package com.example.randomuser.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.room.*
import com.example.randomuser.domain.UserModel

@Dao
interface UserDao {
    @Query("select * from usermodel")
    fun getUsers(): DataSource.Factory<Int, UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( users: List<UserModel>)
}



@Database(entities = [UserModel::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
}

private lateinit var INSTANCE: UserDatabase

fun getDatabase(context: Context): UserDatabase {
    synchronized(UserDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                UserDatabase::class.java,
                "users").build()
        }
    }
    return INSTANCE
}