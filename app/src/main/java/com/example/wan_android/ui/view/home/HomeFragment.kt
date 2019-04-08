package com.example.wan_android.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.wan_android.R
import com.example.wan_android.base.BaseFragment
import com.example.wan_android.databinding.HomeFragmentBinding
import com.example.wan_android.databinding.ItemBannerBinding
import com.example.wan_android.ui.adapter.BannerAdapter
import com.example.wan_android.ui.adapter.BaseRvAdapter
import com.example.wan_android.ui.adapter.BaseRvPagingAdapter
import com.example.wan_android.ui.adapter.HomeListAdapter
import com.example.wan_android.ui.model.BannerItem
import com.example.wan_android.ui.model.HomeItem
import com.example.wan_android.ui.view.h5.H5Fragment
import com.example.wan_android.ui.viewmodel.HomeViewModel

class HomeFragment : BaseFragment() {
    companion object {
        private const val KEY_RV_POSITION = "key_rv_position"
    }

    private lateinit var viewModel: HomeViewModel

    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.stateBundleData.value?.putParcelable(
            KEY_RV_POSITION,
            binding.rvHomeList.layoutManager?.onSaveInstanceState()
        )
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(mActivity).get(HomeViewModel::class.java)

        val bannerBinding = DataBindingUtil.inflate<ItemBannerBinding>(
            layoutInflater,
            R.layout.item_banner,
            binding.root.parent as ViewGroup,
            false
        )
        bannerBinding.homeViewModel = viewModel
        bannerBinding.lifecycleOwner = this
        bannerBinding.banner.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        PagerSnapHelper().attachToRecyclerView(bannerBinding.banner)
        bannerBinding.banner.isNestedScrollingEnabled = false
        bannerBinding.banner.adapter = BannerAdapter().also {
            it.itemClickListener = object : BaseRvAdapter.ItemClickListener<BannerItem> {
                override fun onItemClickListener(position: Int, item: BannerItem) {
                    navigate(R.id.action_global_h5Fragment, Bundle().apply {
                        putString(H5Fragment.URL, item.url)
                    })
                }
            }
        }

        binding.rvHomeList.run {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = HomeListAdapter(bannerBinding.banner).also {
                it.itemClickListener = object : BaseRvPagingAdapter.ItemClickListener<HomeItem> {
                    override fun onItemClickListener(position: Int, item: HomeItem?) {
                        navigate(R.id.action_global_h5Fragment, Bundle().apply {
                            putString(H5Fragment.URL, item?.link)
                        })
                    }
                }
            }
        }

        viewModel.homeList.observe(this, Observer {
            (binding.rvHomeList.adapter as HomeListAdapter).submitList(it)
            viewModel.stateBundleData.value?.apply {
                if (!isEmpty) {
                    binding.rvHomeList.layoutManager?.onRestoreInstanceState(getParcelable(KEY_RV_POSITION))
                    clear()
                }
            }
        })

    }

}
