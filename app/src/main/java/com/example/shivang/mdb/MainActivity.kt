package com.example.shivang.mdb

import android.app.PendingIntent.getActivity
import android.content.pm.ActivityInfo
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
import android.util.DisplayMetrics
import android.widget.LinearLayout
import android.support.v4.widget.SearchViewCompat.setSearchableInfo
import android.app.SearchManager
import android.content.Context
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    lateinit var searchBar : EditText
    lateinit var searchButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar)
        val view = supportActionBar!!.customView
        searchButton = view.findViewById<View>(R.id.bSearch) as Button
        searchBar = view.findViewById<View>(R.id.tvSearch) as EditText
        val searchLayout : FrameLayout = searchLayout
        var frag1 : SearchActivity = SearchActivity()
        supportFragmentManager.beginTransaction().add(R.id.searchLayout,frag1).commit()
        searchLayout.visibility = FrameLayout.GONE
        searchBar.visibility = LinearLayout.GONE
        searchButton.setOnClickListener {
                if(searchBar.visibility== LinearLayout.GONE) {
                    searchBar.visibility = LinearLayout.VISIBLE
                    searchLayout.visibility = FrameLayout.VISIBLE
                    mainView.visibility = RelativeLayout.GONE
                }
                else {
                    searchBar.visibility = LinearLayout.GONE
                    searchLayout.visibility = FrameLayout.GONE
                    mainView.visibility = RelativeLayout.VISIBLE
                }
        }

        var tabs: PagerSlidingTabStrip = findViewById<View>(R.id.tabs) as PagerSlidingTabStrip
        var pager: ViewPager = findViewById<View>(R.id.pager) as ViewPager
        var adapter = MyPagerAdapter(supportFragmentManager)

        tabs.tabBackground = R.drawable.selector_tab
        tabs.shouldExpand = true
//        tabs.getChildAt(0).isSelected = true
        pager.adapter = adapter
        pager.setBackgroundColor(resources.getColor(R.color.colorPrimaryLight))


        tabs.setViewPager(pager)
        tabs.setOnPageChangeListener(CustomOnPageChangeListenner(tabs));



    }


    override fun onBackPressed() {
        if(mainView.visibility==RelativeLayout.VISIBLE)
            super.onBackPressed()
        else
            searchButton.performClick()
    }
}


class CustomOnPageChangeListenner
(private val tabStrip: PagerSlidingTabStrip) : ViewPager.OnPageChangeListener {
    private var previousPage = 0

    init {
        //Set the first image button in tabStrip to selected,
        (tabStrip.getChildAt(0) as LinearLayout).getChildAt(0).isSelected = true
    }

    override fun onPageScrolled(i: Int, v: Float, i2: Int) {

    }

    override fun onPageSelected(i: Int) {
        //set the previous selected page to state_selected = false
        (tabStrip.getChildAt(0) as LinearLayout).getChildAt(previousPage).isSelected = false
        //set the selected page to state_selected = true
        (tabStrip.getChildAt(0) as LinearLayout).getChildAt(i).isSelected = true
        //remember the current page
        previousPage = i
    }

    override fun onPageScrollStateChanged(i: Int) {

    }
}



class MyPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val TITLES = arrayOf( "Top Rated", "Trending", "Upcoming")


    override fun getPageTitle(position: Int): CharSequence {
        return TITLES[position]
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TopRated()
            1 -> Trending()
            else -> Upcomming()
        }
    }

    override fun getCount(): Int {
        return TITLES.size
    }

}
