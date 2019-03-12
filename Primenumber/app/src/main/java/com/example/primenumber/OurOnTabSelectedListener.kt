package com.example.primenumber

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log

class OurOnTabSelectedListener(private val viewPager: ViewPager) : TabLayout.OnTabSelectedListener {
    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        Log.d("QQQ", "select : " + p0!!.position.toString())
        viewPager.setCurrentItem(p0!!.position)
    }
}