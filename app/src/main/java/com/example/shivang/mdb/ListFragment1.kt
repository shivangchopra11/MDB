package com.example.shivang.mdb

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

/**
 * Created by shivang on 31/12/17.
 */
class ListFragment1 : Fragment() {
    public val ARG_POSITION = "position"

    private var position: Int = 0

    companion object {
        fun newInstance(position: Int): ListFragment1 {
            val f = ListFragment1()
            val b = Bundle()
            b.putInt("position", position)
            f.arguments = b
            return f
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        position = arguments.getInt(ARG_POSITION)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)

        val fl = FrameLayout(activity)
        fl.layoutParams = params
        fl.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))

//        val margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources
//                .displayMetrics).toInt()

        val v = TextView(activity)
//        params.setMargins(margin, margin, margin, margin)
        v.layoutParams = params
        v.layoutParams = params
        v.gravity = Gravity.CENTER
        v.text = "CARD " + (position + 1)
        v.setTextColor(resources.getColor(R.color.colorSecondary))

        fl.addView(v)
        return fl
    }
}