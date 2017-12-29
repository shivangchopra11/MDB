package com.example.shivang.mdb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar)
        val view = supportActionBar!!.customView
        val searchButton = view.findViewById<View>(R.id.bSearch) as Button
        val searchBar = view.findViewById<View>(R.id.tvSearch) as EditText

        searchButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                if(searchBar.visibility== LinearLayout.GONE) {
                    searchBar.visibility = LinearLayout.VISIBLE
                }
                else {
                    searchBar.visibility = LinearLayout.GONE
                }
            }
        })
    }
}
