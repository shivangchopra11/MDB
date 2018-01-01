package com.example.shivang.mdb

import android.app.PendingIntent.getActivity
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
//        tabs.getChildAt(0).isSelected = true
        pager.adapter = adapter
        pager.setBackgroundColor(resources.getColor(R.color.colorPrimaryLight))


        tabs.setViewPager(pager)
        tabs.setOnPageChangeListener(CustomOnPageChangeListenner(tabs));



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
