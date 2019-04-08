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
import com.example.wan_android.databinding.KnowledgeDetailItemFragmentBinding
import com.example.wan_android.ui.adapter.ArticleListAdapter
import com.example.wan_android.ui.adapter.BaseRvPagingAdapter
import com.example.wan_android.ui.model.Article
import com.example.wan_android.ui.view.h5.H5Fragment
import com.example.wan_android.ui.viewmodel.KnowledgeDetailItemViewModel

class KnowledgeDetailItemFragment : BaseFragment() {

    companion object {
        private const val KEY_RV_POSITION = "key_rv_position"
    }


    private lateinit var mBinding: KnowledgeDetailItemFragmentBinding

    private lateinit var viewModel: KnowledgeDetailItemViewModel

    private var cid: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cid = arguments?.getInt(KnowledgeDetailFragment.KEY_CID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater, R.layout.knowledge_detail_item_fragment,
            container, false
        )
        mBinding.lifecycleOwner = this
        return mBinding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.stateBundleData.value?.putParcelable(
            KEY_RV_POSITION,
            mBinding.rvKnowledgeDetailItem.layoutManager?.onSaveInstanceState()
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this@KnowledgeDetailItemFragment).get(KnowledgeDetailItemViewModel::class.java)
                .also {
                    it.cid = cid
                }

        mBinding.rvKnowledgeDetailItem.apply {
            adapter = ArticleListAdapter().also {
                it.itemClickListener = object : BaseRvPagingAdapter.ItemClickListener<Article> {
                    override fun onItemClickListener(position: Int, item: Article?) {
                        navigate(R.id.action_global_h5Fragment, Bundle().apply {
                            putString(H5Fragment.URL, item?.link)
                        })
                    }
                }
            }
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.articleList?.observe(this@KnowledgeDetailItemFragment, Observer {
            (mBinding.rvKnowledgeDetailItem.adapter as ArticleListAdapter).submitList(it)
            viewModel.stateBundleData.value?.apply {
                if (!isEmpty) {
                    mBinding.rvKnowledgeDetailItem.layoutManager?.onRestoreInstanceState(
                        getParcelable(
                            KEY_RV_POSITION
                        )
                    )
                    clear()
                }
            }
        })

    }

}
