package com.example.wan_android.ui.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.wan_android.db.WanDb
import com.example.wan_android.retrofit.RetrofitManager
import com.example.wan_android.ui.db.HomeItemBoundaryCallback
import com.example.wan_android.ui.model.BannerItem
import com.example.wan_android.ui.model.HomeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.collections.forEachWithIndex

class HomeRepositoryImpl(private val db: WanDb) : HomeRepository {
    override fun updateBannerList(liveData: MutableLiveData<MutableList<BannerItem>>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val deferred = RetrofitManager.retrofitService.getBannerAsync()
                val response = deferred.await()
                db.runInTransaction {
                    db.posts().deleteAllBanner()
                    db.posts().insertBannerItems(response.data)
                    val allBanners = db.posts().getAllBanners()
                    liveData.postValue(allBanners)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun fetchHomeList(): LiveData<PagedList<HomeItem>> {
        val callback = HomeItemBoundaryCallback(this::insertDataIntoDb)
        val factory = db.posts().queryHomeItems()
        return factory.toLiveData(pageSize = 20, boundaryCallback = callback)
    }

    private fun insertDataIntoDb(list: List<HomeItem>?) {
        list?.let {
            CoroutineScope(Dispatchers.IO).launch {
                run {
                    db.runInTransaction {
                        val start = db.posts().findNextIndex()
                        it.forEachWithIndex { i, homeItem ->
                            homeItem.indexInDb = start + i
                        }
                        db.posts().insertHomeItems(it)
                    }
                }
            }
        }
    }
}