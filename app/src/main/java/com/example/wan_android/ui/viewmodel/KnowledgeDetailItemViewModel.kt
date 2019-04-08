package com.example.wan_android.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.wan_android.db.WanDb
import com.example.wan_android.ui.model.Article
import com.example.wan_android.ui.repository.KnowledgeRepository
import com.example.wan_android.ui.repository.KnowledgeRepositoryImpl

class KnowledgeDetailItemViewModel : ViewModel() {
    var cid: Int? = null

    private val repo: KnowledgeRepository by lazy {
        KnowledgeRepositoryImpl(WanDb.create())
    }

    val articleList: LiveData<PagedList<Article>>? by lazy {
        cid?.let {
            repo.fetchArticleList(it)
        }
    }

    val stateBundleData by lazy {
        MutableLiveData<Bundle>().also {
            it.value = Bundle()
        }
    }
}
