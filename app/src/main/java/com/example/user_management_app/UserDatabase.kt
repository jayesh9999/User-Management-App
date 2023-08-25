package com.example.user_management_app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [user::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun dao(): dao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context) : UserDatabase{

            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        UserDatabase::class.java,
                        "userdb")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}