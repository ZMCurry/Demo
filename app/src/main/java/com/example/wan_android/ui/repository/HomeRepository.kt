package com.example.wan_android.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.wan_android.ui.model.BannerItem
import com.example.wan_android.ui.model.HomeItem

interface HomeRepository {
    fun fetchHomeList(): LiveData<PagedList<HomeItem>>

    fun updateBannerList(liveData: MutableLiveData<MutableList<BannerItem>>)
}