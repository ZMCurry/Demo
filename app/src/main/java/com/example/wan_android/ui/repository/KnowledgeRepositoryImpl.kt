package com.example.wan_android.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.wan_android.db.WanDb
import com.example.wan_android.retrofit.RetrofitManager
import com.example.wan_android.ui.db.ArticleBoundaryCallback
import com.example.wan_android.ui.model.Article
import com.example.wan_android.ui.model.KnowledgeChildren
import com.example.wan_android.ui.model.KnowledgeData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.collections.forEachWithIndex

class KnowledgeRepositoryImpl(private val db: WanDb) : KnowledgeRepository {
    override fun updateKnowledgeData(id: Int?, data: MutableLiveData<List<KnowledgeChildren>>) {
        CoroutineScope(Dispatchers.IO).launch {
            db.runInTransaction {
                db.posts().findAllKnowledgeChildren(id)?.apply {
                    data.postValue(children)
                }
            }
        }
    }

    override fun fetchArticleList(cid: Int): LiveData<PagedList<Article>> {
        val callback = ArticleBoundaryCallback(cid, this::insertDataIntoDb)
        val factory = db.posts().queryArticleList(cid)
        return factory.toLiveData(pageSize = 20, boundaryCallback = callback)
    }

    override fun updateKnowledge(data: MutableLiveData<List<KnowledgeData>>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                db.posts().findAllKnowledgeItems()?.also {
                    data.postValue(it)
                }
                val deferred = RetrofitManager.retrofitService.getKnowledgeAsync()
                val entity = deferred.await()
                insertKnowledgeIntoDb(entity.data)
                entity.data?.apply {
                    data.postValue(this)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun insertKnowledgeIntoDb(data: List<KnowledgeData>?) {
        data?.also {
            CoroutineScope((Dispatchers.IO)).launch {
                db.runInTransaction {
                    db.posts().deleteAllKnowledgeData()
                    db.posts().insertKnowledgeItems(it)
                }
            }
        }
    }

    private fun insertDataIntoDb(list: List<Article>?, cid: Int) {
        list?.let {
            CoroutineScope(Dispatchers.IO).launch {
                db.runInTransaction {
                    val start = db.posts().findArticleNextIndex(cid)
                    it.forEachWithIndex { i, homeItem ->
                        homeItem.indexInDb = start + i
                    }
                    db.posts().insertArticles(it)
                }
            }
        }
    }
}