package com.example.moviefy.database

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity
data class film (
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Long,

    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val releaseDate: String
    )