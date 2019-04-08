package com.example.wan_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.wan_android.R
import com.example.wan_android.ui.model.BannerItem

class BannerAdapter : BaseRvAdapter<BannerItem, BannerAdapter.BannerViewHolder>() {
    companion object {
        @JvmStatic
        @BindingAdapter("app:items")
        fun bindItems(recyclerView: RecyclerView, list: List<BannerItem>?) {
            val adapter = recyclerView.adapter as BannerAdapter
            adapter.updateUI(list)
        }
    }

    private var dataSource: List<BannerItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.banner_item,
                parent,
                false
            )
        )
    }

    fun updateUI(list: List<BannerItem>?) {
        dataSource = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataSource?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataSource?.let { item ->
            holder as BannerViewHolder
            holder.mBinding.setVariable(BR.bannerItem, item[position])
            holder.mBinding.executePendingBindings()
            holder.itemView.setOnClickListener {
                itemClickListener?.onItemClickListener(position, item[position])
            }
        }
    }

    class BannerViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding
    }
}