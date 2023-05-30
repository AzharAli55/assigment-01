package com.asquare.assignment.home.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asquare.assignment.databinding.ItemCatsBinding
import com.asquare.assignment.models.catsbreed.CatItem

class CatsAdapter() : PagingDataAdapter<CatItem, RecyclerView.ViewHolder>(CatDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CatViewHolder(
            ItemCatsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CatViewHolder).bind(getItem(position) as CatItem)
    }




    inner class CatViewHolder(private val mBinding: ItemCatsBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind(catItem: CatItem) {
            mBinding.tvTitle.text =catItem.breed
        }
    }

    private class CatDiffCallBack : DiffUtil.ItemCallback<CatItem>() {
        override fun areItemsTheSame(oldItem: CatItem, newItem: CatItem): Boolean {
            return oldItem.breed == newItem.breed
        }

        override fun areContentsTheSame(
            oldItem: CatItem,
            newItem: CatItem,
        ): Boolean {
            return oldItem == newItem
        }
    }


}