package com.asquare.assignment.home.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.asquare.assignment.home.presentation.fragments.CatListFragment

class CatFragmentPagingAdapter(activity: FragmentActivity, val cardList: List<String>) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun createFragment(position: Int): Fragment {
        return CatListFragment.newInstance()
    }
}