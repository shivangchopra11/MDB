package com.example.shivang.mdb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.shivang.mdb.Models.Cast
import com.example.shivang.mdb.Models.Movie
import com.squareup.picasso.Picasso

/**
 * Created by shivang on 05/01/18.
 */

class CastAdapter(private val mContext: Context) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {
    private var mCastList: ArrayList<Cast>? = null
    private var mInflater: LayoutInflater = LayoutInflater.from(mContext)
    lateinit var view : View
    private val TAG = "TAG1"


    init {
        this.mCastList = ArrayList<Cast>()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {

        view = mInflater.inflate(R.layout.row_movie_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = mCastList!![position]

        // This is how we use Picasso to load images from the internet.
        Picasso.with(mContext)
                .load(cast.TMDB_IMAGE_PATH+cast.profilePath)
                .placeholder(R.color.colorPrimaryLight)
                .into(holder.ivCast)
//        Log.v(TAG, cast.character)
        holder.tvCast.text = cast.character + "\n" + cast.name
    }

    override fun getItemCount(): Int {
        return mCastList?.size ?: 0
    }

    fun setCastList(castList: List<Cast>?) {
        Log.v(TAG, castList!!.size.toString())
        this.mCastList!!.clear()
        this.mCastList!!.addAll(castList!!)
//        Log.v(TAG, mCastList!!.size.toString())
        // The adapter needs to know that the data has changed. If we don't call this, app will crash.
        notifyDataSetChanged()
    }
    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivCast: ImageView = itemView.findViewById<View>(R.id.ivCast) as ImageView
        var tvCast: TextView = itemView.findViewById<View>(R.id.tvCast) as TextView

    }
}