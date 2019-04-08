package com.example.wan_android.ui.db

import androidx.paging.PagedList
import com.example.wan_android.retrofit.RetrofitManager
import com.example.wan_android.ui.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleBoundaryCallback(
    private val cid: Int,
    val saveDataToDb: (List<Article>?, cid: Int) -> Unit
) : PagedList.BoundaryCallback<Article>() {
    override fun onZeroItemsLoaded() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val deferred = RetrofitManager.retrofitService.getArticleListAsync(0, cid)
                val response = deferred.await()
                response.data.entity?.let {
                    it.forEach { item ->
                        item.pageIndex = 0
                    }
                }
                saveDataToDb(response.data.entity, cid)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val deferred = RetrofitManager.retrofitService.getArticleListAsync(itemAtEnd.pageIndex + 1, cid)
                val response = deferred.await()
                response.data.entity?.let {
                    it.forEach { item ->
                        item.pageIndex = itemAtEnd.pageIndex + 1
                    }
                }
                saveDataToDb(response.data.entity, cid)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}