package com.example.shivang.mdb


import android.content.Context
import com.example.shivang.mdb.TopRated.MovieViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v7.widget.RecyclerView
import com.example.shivang.mdb.Models.Movie
import com.squareup.picasso.Picasso


/**
 * Created by shivang on 31/12/17.
 */
class MoviesAdapter(private val mContext: Context) : RecyclerView.Adapter<MovieViewHolder>() {
    private var mMovieList: ArrayList<Movie>? = null
    private var mInflater: LayoutInflater = LayoutInflater.from(mContext)

    init {
        this.mMovieList = ArrayList<Movie>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = mInflater.inflate(R.layout.row_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = mMovieList!![position]

        // This is how we use Picasso to load images from the internet.
        Picasso.with(mContext)
                .load(movie.poster)
                .placeholder(R.color.colorPrimaryLight)
                .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return mMovieList?.size ?: 0
    }

    fun setMovieList(movieList: List<Movie>) {
        this.mMovieList!!.clear()
        this.mMovieList!!.addAll(movieList)
        // The adapter needs to know that the data has changed. If we don't call this, app will crash.
        notifyDataSetChanged()
    }
}