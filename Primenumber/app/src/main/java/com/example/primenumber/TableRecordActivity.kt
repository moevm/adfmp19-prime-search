package com.example.primenumber

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_table_record.*

class TableRecordActivity : AppCompatActivity(),
                            tabItem_time.OnFragmentInteractionListener,
                            tabItem_speed.OnFragmentInteractionListener,
                            tabItem_endless.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_record)

//        tabLayout.setupWithViewPager(viewPager, true)
        var tabLayout = findViewById<TabLayout>(R.id.tablayout)

        tabLayout.addTab(tabLayout.newTab().setText(R.string.game_on_time))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.game_on_speed))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.endless_mode))

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)

        val viewPager = findViewById<ViewPager>(R.id.pager)
        val pagerAdapter = PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount())

        viewPager.setAdapter(pagerAdapter)

        viewPager.setOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setOnTabSelectedListener(OurOnTabSelectedListener(viewPager))

    }
}
