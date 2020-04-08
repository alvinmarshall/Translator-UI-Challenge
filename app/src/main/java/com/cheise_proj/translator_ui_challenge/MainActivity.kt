package com.cheise_proj.translator_ui_challenge

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentActivity
import com.cheise_proj.translator_ui_challenge.adapter.ScreenSlidePagerAdapter
import com.cheise_proj.translator_ui_challenge.ui.add_language.AddLanguageActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.content_main.*

class MainActivity : FragmentActivity() {
    private lateinit var pagerAdapter: ScreenSlidePagerAdapter
    private val tabTitles = arrayListOf("HOME", "A-Z", "ABOUT")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pagerAdapter = ScreenSlidePagerAdapter(this)
        initViewPager()
        fab_add.setOnClickListener {
            startActivity(AddLanguageActivity.newInstance(this))
        }
    }

    private fun initViewPager() {
        view_pager.apply {
            this.adapter = pagerAdapter
        }
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = tabTitles[position]
            view_pager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (view_pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            view_pager.currentItem = view_pager.currentItem - 1
        }
    }
}
