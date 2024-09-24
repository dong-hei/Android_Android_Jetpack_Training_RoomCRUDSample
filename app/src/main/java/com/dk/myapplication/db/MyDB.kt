package com.dk.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dk.myapplication.db.dao.NumDao
import com.dk.myapplication.db.entity.NumEntity

@Database(entities = [NumEntity::class], version = 1)
abstract class MyDB : RoomDatabase() {

    abstract fun numDao(): NumDao

    companion object{

        @Volatile
        private var INSTANCE : MyDB? = null
        fun getDB(context  : Context) : MyDB {

            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDB::class.java,
                    "num_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}