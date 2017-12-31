package com.example.shivang.mdb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.util.TypedValue
import android.view.View
import android.widget.*
import com.astuetz.PagerSlidingTabStrip
import android.graphics.Color.parseColor




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
//                if(searchBar.visibility== LinearLayout.GONE) {
//                    searchBar.visibility = LinearLayout.VISIBLE
//                }
//                else {
//                    searchBar.visibility = LinearLayout.GONE
//                }
            }
        })

        var tabs: PagerSlidingTabStrip = findViewById<View>(R.id.tabs) as PagerSlidingTabStrip
        var pager: ViewPager = findViewById<View>(R.id.pager) as ViewPager
        var adapter = MyPagerAdapter(supportFragmentManager)

        tabs.tabBackground = R.drawable.selector_tab
        tabs.shouldExpand = true

        pager.adapter = adapter

        val pageMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources
                .displayMetrics).toInt()
        pager.pageMargin = pageMargin

        tabs.setViewPager(pager)
    }
}

class MyPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val TITLES = arrayOf( "Top Rated", "Trending", "Favourites")


    override fun getPageTitle(position: Int): CharSequence {
        return TITLES[position]
    }

    override fun getItem(position: Int): Fragment {
        return ListFragment1.newInstance(position)
    }

    override fun getCount(): Int {
        return TITLES.size
    }

}
