package com.example.shivang.mdb


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.SearchManager
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.shivang.mdb.Models.Movie
import com.example.shivang.mdb.Network.APIInterface
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response
import com.google.android.youtube.player.internal.v
import com.google.android.youtube.player.internal.s






class SearchActivity : Fragment() {
    private lateinit var mAdapter: MoviesAdapter
    private lateinit var mRecyclerView: RecyclerView
//    private lateinit var mSwipeRefreshLayout : SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater!!.inflate(R.layout.activity_search, container, false)
        mRecyclerView = rootView.findViewById<View>(R.id.searchView) as RecyclerView
//        if (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            mRecyclerView.layoutManager = GridLayoutManager(container!!.context, 2)
//        } else {
//            mRecyclerView.layoutManager = GridLayoutManager(container!!.context, 3)
//        }
        mRecyclerView.layoutManager = GridLayoutManager(container!!.context, 2)
        mAdapter = MoviesAdapter(container!!.context,1)
        mRecyclerView.adapter = mAdapter
//        val movies = ArrayList<Movie>()
//        for (i in 0..24) {
//            movies.add(Movie())
//        }
//        mAdapter.setMovieList(movies)




        val searchLayout : EditText = (activity as MainActivity).searchBar
        searchLayout.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                mAdapter.setMovieList(object : ArrayList<Movie>(){})
            }

            override fun afterTextChanged(s: Editable?) {


                val restAdapter = RestAdapter.Builder()
                        .setEndpoint("http://api.themoviedb.org/3")
                        .setRequestInterceptor { request -> request.addEncodedQueryParam("api_key", "35306f818103e6fc4e08a236da4b4a5c") }
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .build()
                val service = restAdapter.create(APIInterface::class.java)
                service.getSearchMovies(s.toString(),object : Callback<Movie.MovieResult> {
                    override fun success(movieResult: Movie.MovieResult, response: Response) {
                        mAdapter.setMovieList(movieResult.results)
                    }

                    override fun failure(error: RetrofitError) {
                        error.printStackTrace()
                    }
                })

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val restAdapter = RestAdapter.Builder()
                        .setEndpoint("http://api.themoviedb.org/3")
                        .setRequestInterceptor { request -> request.addEncodedQueryParam("api_key", "35306f818103e6fc4e08a236da4b4a5c") }
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .build()
                val service = restAdapter.create(APIInterface::class.java)
                service.getSearchMovies(s.toString(),object : Callback<Movie.MovieResult> {
                    override fun success(movieResult: Movie.MovieResult, response: Response) {
                        mAdapter.setMovieList(movieResult.results)
                    }

                    override fun failure(error: RetrofitError) {
                        error.printStackTrace()
                    }
                })

            }

        })



        return rootView
    }



}
