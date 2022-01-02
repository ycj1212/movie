package com.example.movie

import android.content.res.Configuration
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MovieAdapter(
    fragment: Fragment,
    private val orientation: Int
) : FragmentStateAdapter(fragment) {
    private val fragmentList = mutableListOf<MovieInfoFragment>()

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    fun submitList(list: List<MovieInfo>) {
        fragmentList.clear()
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            for (i in list.indices) {
                fragmentList.add(MovieInfoFragment.newInstance(MovieFragmentState.POSTER, list[i]))
                fragmentList.add(MovieInfoFragment.newInstance(MovieFragmentState.DETAILS, list[i]))
            }
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            for (i in list.indices) {
                fragmentList.add(MovieInfoFragment.newInstance(MovieFragmentState.BOTH, list[i]))
            }
        }
        notifyItemRangeInserted(0, fragmentList.size)
    }
}
