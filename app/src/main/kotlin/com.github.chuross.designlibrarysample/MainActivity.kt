package com.github.chuross.designlibrarysample

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    val drawerLayout by Delegates.lazy { findViewById(R.id.layout_drawer) as DrawerLayout }
    val drawerToggle by Delegates.lazy { ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0) }
    val toolbar: Toolbar by Delegates.lazy { findViewById(R.id.toolbar) as Toolbar }
    val tabLayout by Delegates.lazy { findViewById(R.id.layout_tab) as TabLayout }
    val viewPager by Delegates.lazy { findViewById(R.id.viewpager) as ViewPager }

    companion object {
        val ITEMS: Array<String> = arrayOf("first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        drawerLayout.setDrawerListener(drawerToggle)
        drawerToggle.setDrawerIndicatorEnabled(true);

        val pagerAdapter = object : FragmentStatePagerAdapter(getSupportFragmentManager()) {

            override fun getPageTitle(position: Int): CharSequence = ITEMS.get(position)

            override fun getItem(position: Int): Fragment = PageFragment.create(ITEMS.get(position))

            override fun getCount(): Int = ITEMS.size()
        }
        viewPager.setAdapter(pagerAdapter)

        tabLayout.setTabsFromPagerAdapter(pagerAdapter)
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START)
        } else {
            super.onBackPressed()
        }
    }
}