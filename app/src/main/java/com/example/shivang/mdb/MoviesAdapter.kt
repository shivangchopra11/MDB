package com.example.shivang.mdb


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.shivang.mdb.Models.Movie
import com.squareup.picasso.Picasso


/**
 * Created by shivang on 31/12/17.
 */
class MoviesAdapter(private val mContext: Context,var id1:Int) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private var mMovieList: ArrayList<Movie>? = null
    private var mInflater: LayoutInflater = LayoutInflater.from(mContext)
    lateinit var view : View


    init {
        this.mMovieList = ArrayList<Movie>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        view = if(id1==2)
            mInflater.inflate(R.layout.row_movie_detail, parent, false)
        else
            mInflater.inflate(R.layout.row_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = mMovieList!![position]

        // This is how we use Picasso to load images from the internet.
        Picasso.with(mContext)
                .load(movie.TMDB_IMAGE_PATH+movie.poster)
                .placeholder(R.color.colorPrimaryLight)
                .into(holder.imageView)
        holder.tvDetails.text = movie.title + "\n" + movie.date + "\n" + if(movie.adult=="false") "U/A" else "A"
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, MovieDetail::class.java)
            intent.putExtra("backdrop", movie.backdrop)
            intent.putExtra("poster", movie.poster)
            intent.putExtra("title",movie.title)
            intent.putExtra("id",movie.id)
            var year : String="";
            for (i in 0..3) {
                year += movie.date?.get(i)
            }
            intent.putExtra("year",year)
            intent.putExtra("desc",movie.description)
            intent.putExtra("rating",movie.rating)

            startActivity(mContext,intent,Bundle())
        }
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
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById<View>(R.id.imageView) as ImageView
        var tvDetails: TextView = itemView.findViewById<View>(R.id.tvDetails) as TextView
        val pos = layoutPosition

    }
}