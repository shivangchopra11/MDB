package com.example.shivang.mdb.Models

import com.google.gson.annotations.SerializedName

/**
 * Created by shivang on 07/01/18.
 */
class Video {
    val TMDB_IMAGE_PATH = "https://img.youtube.com/vi/"
    @SerializedName("key")
    val key: String? = null
    @SerializedName("name")
    val name: String? = null
    class VideoResult {
        lateinit var results: List<Video>
    }
}