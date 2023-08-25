package com.example.user_management_app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class user(
    @PrimaryKey(autoGenerate = true)
    val userid: Long = 0,
    val username: String,
    val email: String,
    val phone: String
)
