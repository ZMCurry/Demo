package com.example.wan_android.ui.view.knowledge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.wan_android.R
import com.example.wan_android.base.BaseFragment
import com.example.wan_android.ui.adapter.KnowledgePagerAdapter
import com.example.wan_android.ui.viewmodel.KnowledgeDetailViewModel
import kotlinx.android.synthetic.main.knowledge_detail_fragment.*

class KnowledgeDetailFragment : BaseFragment() {

    companion object {
        const val KEY_CID = "key_cid"
    }

    private lateinit var detailViewModel: KnowledgeDetailViewModel

    private var knowledgePagerAdapter: KnowledgePagerAdapter? = null

    private var rootView: View? = null

    private var hasBeenInflated = false

    override fun onDestroyView() {
        super.onDestroyView()
        rootView?.also {
            if (it.parent != null) {
                (it.parent as ViewGroup).removeView(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return rootView?.also {
            if (it.parent != null) {
                (it.parent as ViewGroup).removeView(it)
            }
        } ?: inflater.inflate(R.layout.knowledge_detail_fragment, container, false).also {
            rootView = it
        }
    }


    private fun initData() {
        detailViewModel = ViewModelProviders.of(this).get(KnowledgeDetailViewModel::class.java)
        detailViewModel.id = arguments?.getInt(KnowledgeFragment.KEY_KNOWLEDGE_ITEM_ID, 0)

        fragmentManager?.let { fm ->
            knowledgePagerAdapter = KnowledgePagerAdapter(fm, null, null)
            vp_knowledge.adapter = knowledgePagerAdapter
            tab_knowledge.setupWithViewPager(vp_knowledge)

            detailViewModel.dataSource.observe(this@KnowledgeDetailFragment, Observer {
                knowledgePagerAdapter?.updateUI(detailViewModel.dataSource.value?.map { child ->
                    KnowledgeDetailItemFragment().also { item ->
                        item.arguments = Bundle().apply {
                            putInt(KEY_CID, child.id)
                        }
                    }
                }, detailViewModel.dataSource.value?.map {
                    it.name
                })
            })
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!hasBeenInflated) {
            initData()
            hasBeenInflated = true
        }

    }

}
