package com.example.shivang.mdb

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import com.elmargomez.typer.Font.ROBOTO_MEDIUM
import com.elmargomez.typer.Typer
import android.graphics.Typeface
import com.elmargomez.typer.Font
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.shivang.mdb.Models.Credits
import com.example.shivang.mdb.Models.Movie
import com.example.shivang.mdb.Network.APIInterface
import kotlinx.android.synthetic.main.content_movie_detail.*
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response
import retrofit2.converter.gson.GsonConverterFactory


class MovieDetail : AppCompatActivity() {
    private lateinit var mAdapter: MoviesAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mCastAdapter: CastAdapter
    private lateinit var mCastView: RecyclerView
    private val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
        val intent = intent
        var title : String = intent.getStringExtra("title")
        var backdrop : String = intent.getStringExtra("backdrop")
        var year : String = intent.getStringExtra("year")
        var desc : String = intent.getStringExtra("desc")
        var poster : String = intent.getStringExtra("poster")
        var id : Int = intent.getIntExtra("id",0)
        var rating : Double = intent.getDoubleExtra("rating",0.0)

        setTitle(" ")

        tvYear.text = year
        tvRating.text = rating.toString()
        tvTitle.text = title
        tvDesc.text = desc


        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/original"+backdrop)
                .placeholder(R.color.colorPrimaryLight)
                .into(ivBackdrop)


        val collapsingToolbarLayout = findViewById<View>(R.id.toolbar_layout) as CollapsingToolbarLayout
        val appBarLayout = findViewById<View>(R.id.app_bar) as AppBarLayout
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.title = title
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarLayout.title = " "//careful there should a space between double quote otherwise it wont work
                    isShow = false
                }
            }
        })
        mRecyclerView = findViewById<View>(R.id.similarMovies) as RecyclerView
        val horizontalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView.layoutManager = horizontalLayoutManager
        mAdapter = MoviesAdapter(this,2)
        mRecyclerView.adapter = mAdapter
        val restAdapter = RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor { request -> request.addEncodedQueryParam("api_key", "35306f818103e6fc4e08a236da4b4a5c") }
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
        val service = restAdapter.create(APIInterface::class.java)
        service.getSimilarMovies(id, object : Callback<Movie.MovieResult> {

            override fun success(movieResult: Movie.MovieResult, response: Response) {
                Log.v(TAG, movieResult.results.size.toString())
                mAdapter.setMovieList(movieResult.results)
            }

            override fun failure(error: RetrofitError) {

            }
        })

        mCastView = findViewById<View>(R.id.movieCast) as RecyclerView
        val horizontalLayoutManager1 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mCastView.layoutManager = horizontalLayoutManager1
        mCastAdapter = CastAdapter(this)
        mCastView.adapter = mCastAdapter
        service.getCredits(id, object : Callback<Credits.CastResult> {

            override fun success(castResult: Credits.CastResult, response: Response) {
                Log.v(TAG, castResult.cast.size.toString())
                mCastAdapter.setCastList(castResult.cast)
            }

            override fun failure(error: RetrofitError) {

            }
        })




    }
}
