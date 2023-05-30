package com.asquare.assignment.home.ui.activities

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.MarginPageTransformer
import com.asquare.assignment.R
import com.asquare.assignment.base.ui.BaseActivity
import com.asquare.assignment.databinding.ActivityHomeBinding
import com.asquare.assignment.home.ui.adapters.CatFragmentPagingAdapter
import com.asquare.assignment.home.ui.adapters.ImagesPagerAdapter
import com.asquare.assignment.home.viewmodels.CatsViewModel
import com.asquare.assignment.home.viewmodels.HomeViewModel
import com.asquare.assignment.utils.dpToPx
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    lateinit var catFragmentPagingAdapter :CatFragmentPagingAdapter
    lateinit var imagesPagerAdapter :ImagesPagerAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun setupViewBinding(inflater: LayoutInflater) = ActivityHomeBinding.inflate(inflater)

    override fun setupUI() {
        setImagesPagerAdapter()
        setListPagerAdapter()
        mBinding.etSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                homeViewModel.search(p0.toString())
            }
        })

    }

    private fun setListPagerAdapter(){
        catFragmentPagingAdapter = CatFragmentPagingAdapter(this,homeViewModel.getImagesData())
        mBinding.viewPager.adapter = catFragmentPagingAdapter

        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager) { tab, position ->

        }.attach()
    }

    private fun setImagesPagerAdapter(){
        imagesPagerAdapter = ImagesPagerAdapter(this,homeViewModel.getImagesData())
        mBinding.viewPagerImages.adapter = imagesPagerAdapter
        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPagerImages) { tab, position ->
        }.attach()
    }


}