package com.example.wan_android.ui.view.knowledge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wan_android.R
import com.example.wan_android.base.BaseFragment
import com.example.wan_android.databinding.KnowledgeFragmentBinding
import com.example.wan_android.ui.adapter.KnowledgeListAdapter
import com.example.wan_android.ui.model.KnowledgeData
import com.example.wan_android.ui.viewmodel.KnowledgeViewModel

class KnowledgeFragment : BaseFragment() {
    companion object {
        const val KEY_KNOWLEDGE_ITEM_ID = "key_knowledge_item_id"
        const val KEY_RV_STATE = "key_rv_state"
    }

    private lateinit var viewModel: KnowledgeViewModel
    private lateinit var mBinding: KnowledgeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.knowledge_fragment, container, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.stateBundleData.value?.putParcelable(
            KEY_RV_STATE,
            mBinding.rvKnowledge.layoutManager?.onSaveInstanceState()
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(mActivity).get(KnowledgeViewModel::class.java)

        mBinding.knowledgeViewModel = viewModel
        mBinding.rvKnowledge.adapter = KnowledgeListAdapter().also { adapter ->
            adapter.listener = object : KnowledgeListAdapter.KnowledgeItemClickListener {
                override fun onItemClick(position: Int, data: KnowledgeData) {
                    val also = Bundle().also {
                        it.putInt(KEY_KNOWLEDGE_ITEM_ID, data.id)
                    }
                    navigate(R.id.action_global_knowledgeDetailFragment, also)
                }
            }
        }
        mBinding.rvKnowledge.layoutManager = LinearLayoutManager(context).also {
            it.onRestoreInstanceState(
                viewModel.stateBundleData.value?.getParcelable(
                    KEY_RV_STATE
                )
            )
        }

        mBinding.srlKnowledge.setOnRefreshListener {
            viewModel.refreshData()
        }

        viewModel.knowledgeList.observe(this, Observer {
            mBinding.srlKnowledge.isRefreshing = false
        })
    }

}
