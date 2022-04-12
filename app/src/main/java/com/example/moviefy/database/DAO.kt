package com.example.moviefy.database

import androidx.room.*
import com.example.moviefy.Movie

@Dao
interface DAO{

    @Insert
    fun insert(movie : Movie) : Long

    @Delete
    fun delete(movie : Movie) : Int

    @Query("SELECT * FROM Movie")
    fun get() : List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun check(id : Long) : Movie
}