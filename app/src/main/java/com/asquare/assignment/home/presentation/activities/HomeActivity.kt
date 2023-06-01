package com.asquare.assignment.home.presentation.activities

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.asquare.assignment.base.views.BaseActivity
import com.asquare.assignment.databinding.ActivityHomeBinding
import com.asquare.assignment.home.presentation.adapters.CatFragmentPagingAdapter
import com.asquare.assignment.home.presentation.adapters.ImagesPagerAdapter
import com.asquare.assignment.home.presentation.viewmodels.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    lateinit var catFragmentPagingAdapter: CatFragmentPagingAdapter
    lateinit var imagesPagerAdapter: ImagesPagerAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun setupViewBinding(inflater: LayoutInflater) = ActivityHomeBinding.inflate(inflater)

    override fun setupUI() {
        setImagesPagerAdapter()
        setListPagerAdapter()
        mBinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                homeViewModel.search(p0.toString())
            }
        })

    }

    private fun setListPagerAdapter() {
        catFragmentPagingAdapter = CatFragmentPagingAdapter(this, homeViewModel.getImagesData())
        mBinding.viewPager.adapter = catFragmentPagingAdapter

        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager) { _, _ ->

        }.attach()
    }

    private fun setImagesPagerAdapter() {
        imagesPagerAdapter = ImagesPagerAdapter(this, homeViewModel.getImagesData())
        mBinding.viewPagerImages.adapter = imagesPagerAdapter
        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPagerImages) { _, _ ->
        }.attach()
    }


}