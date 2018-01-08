package com.example.shivang.mdb

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import android.support.v4.content.ContextCompat.startActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import android.content.Intent



class TrailerPlayer : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer_player)
        val key : String = intent.getStringExtra("key")
        val v = Log.v("TAG", key)
        var playerView : YouTubePlayerView = findViewById<View>(R.id.videoPlayer) as YouTubePlayerView
        var listener : YouTubePlayer.OnInitializedListener
        listener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                var errorMessage : String = p1.toString()
                Log.v("TAG", errorMessage)
            }

            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
                p1?.loadVideo(key)
        }


        }
        playerView.initialize("AIzaSyDEDEoaBuCJMuozOmqfDj_DWfwWidlwzbg",listener)
//        val intent = YouTubeStandalonePlayer.createVideoIntent(this, "AIzaSyDEDEoaBuCJMuozOmqfDj_DWfwWidlwzbg", key, 0, false, false)
//        startActivity(intent)
    }
}
