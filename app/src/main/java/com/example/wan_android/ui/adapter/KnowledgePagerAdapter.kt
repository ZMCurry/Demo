package com.example.wan_android.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class KnowledgePagerAdapter(
    fm: FragmentManager,
    private var fragments: List<Fragment>?,
    private var titles: List<String>?
) :
    FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return fragments?.get(position) ?: throw NullPointerException("fragment should not be null")
    }

    fun updateUI(fragments: List<Fragment>?, titles: List<String>?) {
        this.fragments = fragments
        this.titles = titles
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles?.get(position)
    }

    override fun getCount(): Int {
        return fragments?.size ?: 0
    }
}