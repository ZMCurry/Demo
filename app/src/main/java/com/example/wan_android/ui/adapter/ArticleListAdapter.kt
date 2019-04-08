package com.example.wan_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wan_android.BR
import com.example.wan_android.R
import com.example.wan_android.databinding.ItemArticleListBinding
import com.example.wan_android.ui.model.Article

class ArticleListAdapter : BaseRvPagingAdapter<Article, RecyclerView.ViewHolder>(COMPARATOR) {
    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate<ItemArticleListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_article_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeViewHolder).mBinding.setVariable(BR.articleItem, getItem(position))
        holder.mBinding.executePendingBindings()
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClickListener(position, getItem(position))
        }
    }

    class HomeViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding
    }

}