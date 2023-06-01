package com.asquare.assignment.home.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.asquare.assignment.home.presentation.fragments.ImagesFragment

class ImagesPagerAdapter(activity: FragmentActivity, val imagesList: List<String>) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun createFragment(position: Int): Fragment {
        return ImagesFragment.newInstance(imagesList[position])
    }
}