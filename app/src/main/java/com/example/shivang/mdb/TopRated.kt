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


class TopRated : Fragment() {
    private lateinit var mAdapter: MoviesAdapter
    private lateinit var mRecyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.activity_top_rated, container, false)
        mRecyclerView = rootView.findViewById<View>(R.id.recyclerView) as RecyclerView
        mRecyclerView.layoutManager = GridLayoutManager(container!!.context, 2)
        mAdapter = MoviesAdapter(container!!.context)
        mRecyclerView.setAdapter(mAdapter)
        val movies = ArrayList<Movie>()

        for (i in 0..24) {
            movies.add(Movie())
        }
        mAdapter.setMovieList(movies)

        return rootView
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById<View>(R.id.imageView) as ImageView

    }
}
