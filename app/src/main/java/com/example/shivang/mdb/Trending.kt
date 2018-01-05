package com.example.shivang.mdb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.support.v7.widget.GridLayoutManager
import android.widget.TextView
import com.example.shivang.mdb.Models.Movie
import retrofit.RestAdapter
import com.example.shivang.mdb.Network.APIInterface
import retrofit.RetrofitError
import retrofit.Callback
import retrofit.client.Response


class Trending : Fragment() {
    private lateinit var mAdapter: MoviesAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSwipeRefreshLayout : SwipeRefreshLayout
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.activity_trending, container, false)
        mRecyclerView = rootView.findViewById<View>(R.id.recyclerView) as RecyclerView
        mRecyclerView.layoutManager = GridLayoutManager(container!!.context, 2)
        mAdapter = MoviesAdapter(container!!.context)
        mRecyclerView.setAdapter(mAdapter)
//        val movies = ArrayList<Movie>()
//        for (i in 0..24) {
//            movies.add(Movie())
//        }
//        mAdapter.setMovieList(movies)


        mSwipeRefreshLayout= rootView.findViewById<View>(R.id.swipeRefreshLayout) as SwipeRefreshLayout

        mSwipeRefreshLayout.setOnRefreshListener { refreshItems() }


        val restAdapter = RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor { request -> request.addEncodedQueryParam("api_key", "35306f818103e6fc4e08a236da4b4a5c") }
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
        val service = restAdapter.create(APIInterface::class.java)
        service.getPopularMovies(object : Callback<Movie.MovieResult> {
            override fun success(movieResult: Movie.MovieResult, response: Response) {
                mAdapter.setMovieList(movieResult.results)
            }

            override fun failure(error: RetrofitError) {
                error.printStackTrace()
            }
        })

        return rootView
    }


    private fun refreshItems() {
        // Load items
        // ...

        // Load complete
        val restAdapter = RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor { request -> request.addEncodedQueryParam("api_key", "35306f818103e6fc4e08a236da4b4a5c") }
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
        val service = restAdapter.create(APIInterface::class.java)
        service.getPopularMovies(object : Callback<Movie.MovieResult> {
            override fun success(movieResult: Movie.MovieResult, response: Response) {
                mAdapter.setMovieList(movieResult.results)
            }

            override fun failure(error: RetrofitError) {
                error.printStackTrace()
            }
        })
        mSwipeRefreshLayout.isRefreshing = false
    }

}
