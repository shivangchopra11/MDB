package com.example.shivang.mdb.Models

import com.google.gson.annotations.SerializedName

/**
 * Created by shivang on 31/12/17.
 */
class Movie {
    val TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500"
    var title: String? = null
    @SerializedName("poster_path")
    var poster: String? = null
    @SerializedName("overview")
    var description: String? = null
    @SerializedName("backdrop_path")
    var backdrop: String? = null
    @SerializedName("release_date")
    var date: String? = null
    var adult: String? = null
    @SerializedName("vote_average")
    var rating: Double? = null
    class MovieResult {
        lateinit var results: List<Movie>
    }
}