package com.example.wan_android.ui.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRvAdapter<T, U> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemClickListener: ItemClickListener<T>? = null

    interface ItemClickListener<T> {
        fun onItemClickListener(position: Int, item: T)
    }
}