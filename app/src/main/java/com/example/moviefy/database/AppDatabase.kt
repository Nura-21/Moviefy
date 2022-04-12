package com.example.moviefy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviefy.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase(){

    //DAO
    abstract fun Dao():DAO

    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "movie.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as AppDatabase
        }
    }
}