package com.example.shivang.mdb.Network

import com.example.shivang.mdb.Models.Movie
import retrofit.Callback
import retrofit.http.GET
import com.example.shivang.mdb.Models.Movie.MovieResult
import retrofit.http.Query


/**
 * Created by shivang on 31/12/17.
 */
interface APIInterface {
//    @GET("/movie/popular")
//    fun getPopularMovies(cb: Callback<Movie.MovieResult>)
//
//    @GET("/discover/movie")
//    fun getUpcomingMovies(@Query("primary_release_date.gte") date1: String, cb: Callback<Movies.MovieResult>)
    @GET("/movie/popular")
    fun getPopularMovies(cb: Callback<Movie.MovieResult>)

    @GET("/movie/top_rated")
    fun getTopRatedMovies(cb: Callback<Movie.MovieResult>)

    @GET("/discover/movie")
    fun getUpcomingMovies(@Query("primary_release_date.gte") date1: String, cb: Callback<Movie.MovieResult>)

}