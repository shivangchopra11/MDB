package com.example.shivang.mdb.Models

import com.google.gson.annotations.SerializedName



/**
 * Created by shivang on 05/01/18.
 */
class Credits {
    @SerializedName("cast")
    var casts: List<Cast>? = null
//    @SerializedName("crew")
//    private val crews: List<MovieCrewBrief>? = null
    class CastResult {
        lateinit var cast : List<Cast>
    }
}