package com.example.user_management_app

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.Query

@Dao
interface dao {

    @Insert
    fun addUser(user: user)

    @Query("Select * FROM user")
    fun getUSer(): List<user>
}