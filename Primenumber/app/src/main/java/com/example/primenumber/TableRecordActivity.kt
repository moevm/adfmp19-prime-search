package com.example.primenumber

import android.annotation.SuppressLint
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log
import kotlinx.android.synthetic.main.activity_table_record.*
import kotlinx.android.synthetic.main.fragment_tab_item_endless.*
import kotlinx.android.synthetic.main.fragment_tab_item_speed.*
import kotlinx.android.synthetic.main.fragment_tab_item_time.*

class TableRecordActivity : AppCompatActivity(),
    tabItem_time.OnFragmentInteractionListener,
    tabItem_speed.OnFragmentInteractionListener,
    tabItem_endless.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_record)

        var tabLayout = findViewById<TabLayout>(R.id.tablayout)

        tabLayout.addTab(tabLayout.newTab().setText(R.string.game_on_time))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.game_on_speed))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.endless_mode))

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)

        val viewPager = findViewById<ViewPager>(R.id.pager)
        val pagerAdapter = PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount())

        viewPager.setAdapter(pagerAdapter)

        viewPager.setOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        if (intent != null && intent.extras != null) {
            val mode = intent.extras.get("mode")
            val level = intent.extras.get("level").toString()
            when (mode) {
                "time" -> {
                    viewPager.setCurrentItem(0)
                }
                "speed" -> {
                    viewPager.setCurrentItem(1)
                }
                else -> viewPager.setCurrentItem(2)
            }
            TabItemSpinnerState.getInstance().level = level
        }
        tabLayout.setOnTabSelectedListener(OurOnTabSelectedListener(viewPager))

    }

}
