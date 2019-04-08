package com.example.wan_android.ui.adapter

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRvPagingAdapter<T, U>(diffUtil: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, RecyclerView.ViewHolder>(diffUtil) {

    var itemClickListener: ItemClickListener<T>? = null

    interface ItemClickListener<T> {
        fun onItemClickListener(position: Int, item: T?)
    }
}