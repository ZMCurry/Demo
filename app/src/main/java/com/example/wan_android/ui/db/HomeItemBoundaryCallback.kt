package com.example.wan_android.ui.db

import androidx.paging.PagedList
import com.example.wan_android.retrofit.RetrofitManager
import com.example.wan_android.ui.model.HomeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeItemBoundaryCallback(
    val saveDataToDb: (List<HomeItem>?) -> Unit
) : PagedList.BoundaryCallback<HomeItem>() {
    override fun onZeroItemsLoaded() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val deferred = RetrofitManager.retrofitService.getHomeListAsync(0)
                val response = deferred.await()
                response.data.entity?.let {
                    it.forEach { item ->
                        item.pageIndex = 0
                    }
                }
                saveDataToDb(response.data.entity)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: HomeItem) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val deferred = RetrofitManager.retrofitService.getHomeListAsync(itemAtEnd.pageIndex + 1)
                val response = deferred.await()
                response.data.entity?.let {
                    it.forEach { item ->
                        item.pageIndex = itemAtEnd.pageIndex + 1
                    }
                }
                saveDataToDb(response.data.entity)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}