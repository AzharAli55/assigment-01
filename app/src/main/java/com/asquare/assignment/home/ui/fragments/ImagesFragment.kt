package com.asquare.assignment.home.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.asquare.assignment.base.ui.BaseFragment
import com.asquare.assignment.databinding.FragmentCatListBinding
import com.asquare.assignment.databinding.FragmentImagesBinding
import com.asquare.assignment.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment  : BaseFragment<FragmentImagesBinding>() {

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =  FragmentImagesBinding.inflate(inflater, container, false)

    override fun setupViews() {
        mBinding?.ivImage?.loadImage(getStringFromBundle(ARG_IMAGE_URL))
    }

    companion object{
        const val ARG_IMAGE_URL = "image_url"
        fun newInstance(imageUrl:String?)= ImagesFragment().apply {
            arguments  = Bundle().apply {
                putString(ARG_IMAGE_URL,imageUrl)
            }
        }
    }
}