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
import android.view.View
import kotlinx.android.synthetic.main.content_movie_detail.*


class MovieDetail : AppCompatActivity() {

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
    }
}
