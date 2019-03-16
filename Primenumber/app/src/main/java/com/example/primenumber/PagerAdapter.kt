package com.example.primenumber

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log

class PagerAdapter(fragmentManager: FragmentManager, private val numberOfTabs : Int) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> tabItem_time()
            1 -> tabItem_speed()
            2 -> tabItem_endless()
            else -> tabItem_time()
        }
    }

    override fun getCount(): Int {
        return numberOfTabs
    }
}