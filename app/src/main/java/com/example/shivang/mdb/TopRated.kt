package com.example.shivang.mdb

import android.content.res.Configuration
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
import com.example.shivang.mdb.Network.APIInterface
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response


class TopRated : Fragment() {
    private lateinit var mAdapter: MoviesAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSwipeRefreshLayout : SwipeRefreshLayout
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.activity_top_rated, container, false)
        mRecyclerView = rootView.findViewById<View>(R.id.recyclerView) as RecyclerView
//        if (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            mRecyclerView.layoutManager = GridLayoutManager(container!!.context, 2)
//        } else {
//            mRecyclerView.layoutManager = GridLayoutManager(container!!.context, 3)
//        }
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
        service.getTopRatedMovies(object : Callback<Movie.MovieResult> {
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
        service.getTopRatedMovies(object : Callback<Movie.MovieResult> {
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
