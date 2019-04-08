package com.example.wan_android.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wan_android.BR
import com.example.wan_android.R
import com.example.wan_android.databinding.ItemHomeListBinding
import com.example.wan_android.ui.model.HomeItem

class HomeListAdapter(private val headerView: View) :
    BaseRvPagingAdapter<HomeItem, RecyclerView.ViewHolder>(COMPARATOR) {
    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<HomeItem>() {
            override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
                return oldItem.id == newItem.id
            }
        }

        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1

        private const val HEADER_COUNT = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }


    override fun getItemCount(): Int {
        val itemCount = super.getItemCount()
        return if (itemCount <= HEADER_COUNT) {
            0
        } else {
            itemCount + HEADER_COUNT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                BannerViewHolder(headerView)
            }
            else -> HomeViewHolder(
                DataBindingUtil.inflate<ItemHomeListBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_list,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_ITEM -> {
                (holder as HomeViewHolder).mBinding.setVariable(
                    BR.item,
                    getItem(position - HEADER_COUNT)
                )
                holder.mBinding.executePendingBindings()
                holder.itemView.setOnClickListener {
                    itemClickListener?.onItemClickListener(position, getItem(position - HEADER_COUNT))
                }
            }
        }
    }

    class HomeViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding
    }

    class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view)
}