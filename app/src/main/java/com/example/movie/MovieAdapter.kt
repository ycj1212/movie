package com.example.movie

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MovieAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragmentList = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}
