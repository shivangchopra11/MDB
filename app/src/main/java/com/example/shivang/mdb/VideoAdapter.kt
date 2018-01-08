package com.example.shivang.mdb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.shivang.mdb.Models.Movie
import com.example.shivang.mdb.Models.Video
import com.google.android.youtube.player.YouTubeThumbnailView
import com.squareup.picasso.Picasso
import android.R.attr.thumb
import android.support.v4.content.ContextCompat.startActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.google.android.youtube.player.YouTubeThumbnailLoader



/**
 * Created by shivang on 07/01/18.
 */
class VideoAdapter(private val mContext: Context) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    private var mVideoList: ArrayList<Video>? = null
    private var mInflater: LayoutInflater = LayoutInflater.from(mContext)
    lateinit var view : View
    lateinit var parent : ViewGroup


    init {
        this.mVideoList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        view = mInflater.inflate(R.layout.row_movie_video, parent, false)
        this.parent = parent
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoAdapter.VideoViewHolder, position: Int) {
        val video = mVideoList!![position]
        // This is how we use Picasso to load images from the internet.
        Picasso.with(mContext)
                .load(video.TMDB_IMAGE_PATH+video.key+"/0.jpg")
                .placeholder(R.color.colorPrimaryLight)
                .into(holder.ivVideo)

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, TrailerPlayer::class.java)
            intent.putExtra("key", video.key)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            ContextCompat.startActivity(mContext.applicationContext, intent, Bundle())
//            val intent = YouTubeStandalonePlayer.createVideoIntent(MovieDetail(), "AIzaSyDEDEoaBuCJMuozOmqfDj_DWfwWidlwzbg", video.key, 0, false, false)
//            startActivity(mContext,intent,Bundle())
        }
    }

    override fun getItemCount(): Int {
        return mVideoList?.size ?: 0
    }

    fun setMovisetVideoList(videolist: List<Video>) {
        this.mVideoList!!.clear()
        this.mVideoList!!.addAll(videolist)
        // The adapter needs to know that the data has changed. If we don't call this, app will crash.
        notifyDataSetChanged()
    }
    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivVideo: YouTubeThumbnailView = itemView.findViewById<View>(R.id.ivVideo) as YouTubeThumbnailView



    }
}