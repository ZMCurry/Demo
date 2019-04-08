package com.example.wan_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.wan_android.BR
import com.example.wan_android.R
import com.example.wan_android.databinding.ItemHomeListBinding
import com.example.wan_android.ui.model.KnowledgeData

class KnowledgeListAdapter : BaseRvAdapter<KnowledgeData, KnowledgeListAdapter.KnowledgeListViewHolder>() {
    companion object {
        @JvmStatic
        @BindingAdapter("app:items")
        fun bindItems(recyclerView: RecyclerView, list: List<KnowledgeData>?) {
            val adapter = recyclerView.adapter as KnowledgeListAdapter
            adapter.updateUI(list)
        }
    }

    private var dataSource: List<KnowledgeData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnowledgeListViewHolder {
        return KnowledgeListViewHolder(
            DataBindingUtil.inflate<ItemHomeListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_knowledge_list,
                parent,
                false
            )
        )
    }

    interface KnowledgeItemClickListener {
        fun onItemClick(position: Int, data: KnowledgeData)
    }

    var listener: KnowledgeItemClickListener? = null

    fun updateUI(list: List<KnowledgeData>?) {
        dataSource = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataSource?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataSource?.apply {
            holder as KnowledgeListViewHolder
            holder.mBinding.setVariable(BR.knowledgeItem, this[position])
            holder.mBinding.executePendingBindings()
            holder.itemView.setOnClickListener {
                listener?.onItemClick(position, this[position])
            }
        }
    }


    class KnowledgeListViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding
    }
}