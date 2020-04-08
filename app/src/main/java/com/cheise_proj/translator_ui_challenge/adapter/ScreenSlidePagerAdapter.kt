package com.cheise_proj.translator_ui_challenge.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cheise_proj.translator_ui_challenge.ui.about.AboutFragment
import com.cheise_proj.translator_ui_challenge.ui.home.HomeFragment
import com.cheise_proj.translator_ui_challenge.ui.language.SavedLanguageFragment

class ScreenSlidePagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    companion object {
        private const val NUM_COUNT = 3
    }

    override fun getItemCount(): Int = NUM_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment.newInstance()
            1 -> SavedLanguageFragment.newInstance()
            2 -> AboutFragment.newInstance()
            else -> throw IllegalArgumentException("Invalid fragment $position")
        }
    }
}
