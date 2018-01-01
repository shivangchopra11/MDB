package com.example.shivang.mdb.Network

import com.example.shivang.mdb.Models.Movie
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query


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

}