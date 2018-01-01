package com.example.shivang.mdb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.support.v7.widget.GridLayoutManager
import com.example.shivang.mdb.Models.Movie
import com.example.shivang.mdb.Models.Movie.MovieResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Trending : Fragment() {
    private lateinit var mAdapter: MoviesAdapter
    private lateinit var mRecyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.activity_trending, container, false)
        mRecyclerView = rootView.findViewById<View>(R.id.recyclerView) as RecyclerView
        mRecyclerView.layoutManager = GridLayoutManager(container!!.context, 2)
        mAdapter = MoviesAdapter(container!!.context)
        mRecyclerView.setAdapter(mAdapter)
        val movies = ArrayList<Movie>()

        for (i in 0..24) {
            movies.add(Movie())
        }
        mAdapter.setMovieList(movies)

//        val restAdapter = RestAdapter.Builder()
//                .setEndpoint("http://api.themoviedb.org/3")
//                .setRequestInterceptor(object : RequestInterceptor() {
//                    fun intercept(request: RequestFacade) {
//                        request.addEncodedQueryParam("api_key", "YOUR_API_KEY")
//                    }
//                })
//                .setLogLevel(RestAdapter.LogLevel.FULL)
//                .build()
//        val service = restAdapter.create(MoviesApiService::class.java)
//        service.getPopularMovies(object : Callback<Movie> {
//            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
//                mAdapter.setMovieList(response)
//            }
//
//            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
//                t?.printStackTrace()
//            }
//        })

        return rootView
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById<View>(R.id.imageView) as ImageView

    }
}
