package com.example.wan_android.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.wan_android.db.WanDb
import com.example.wan_android.ui.model.BannerItem
import com.example.wan_android.ui.model.HomeItem
import com.example.wan_android.ui.repository.HomeRepository
import com.example.wan_android.ui.repository.HomeRepositoryImpl

class HomeViewModel : ViewModel() {

    private val repo: HomeRepository by lazy {
        HomeRepositoryImpl(WanDb.create())
    }

    val stateBundleData by lazy {
        MutableLiveData<Bundle>().also {
            it.value = Bundle()
        }
    }

    val homeList: LiveData<PagedList<HomeItem>> by lazy {
        repo.fetchHomeList()
    }

    val bannerItems: LiveData<MutableList<BannerItem>> by lazy {
        MutableLiveData<MutableList<BannerItem>>().also {
            repo.updateBannerList(it)
        }
    }

}
