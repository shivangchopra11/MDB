package com.example.shivang.mdb.Models

import com.google.gson.annotations.SerializedName



/**
 * Created by shivang on 05/01/18.
 */
class Cast {
    val TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w150"
    @SerializedName("character")
    val character: String? = null
    @SerializedName("name")
    val name: String? = null
    @SerializedName("profile_path")
    val profilePath: String? = null
}