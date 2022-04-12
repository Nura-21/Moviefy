package com.example.moviefy

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "48ec4fd9c5e7061be8828c7713bdca8f",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "48ec4fd9c5e7061be8828c7713bdca8f",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "48ec4fd9c5e7061be8828c7713bdca8f",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}