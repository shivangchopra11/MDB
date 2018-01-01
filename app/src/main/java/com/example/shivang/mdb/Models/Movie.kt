package com.example.shivang.mdb.Models

/**
 * Created by shivang on 31/12/17.
 */
class Movie {
    var title: String? = null
    var poster: String? = "http://t2.gstatic.com/images?q=tbn:ANd9GcQW3LbpT94mtUG1PZIIzJNxmFX399wr_NcvoppJ82k7z99Hx6in"
    var description: String? = null
    var backdrop: String? = null

    class MovieResult {
        lateinit var results: List<Movie>
    }
}