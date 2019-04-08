package com.example.wan_android.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.wan_android.ui.model.Article
import com.example.wan_android.ui.model.KnowledgeChildren
import com.example.wan_android.ui.model.KnowledgeData

interface KnowledgeRepository {
    fun updateKnowledge(data: MutableLiveData<List<KnowledgeData>>)

    fun fetchArticleList(cid: Int): LiveData<PagedList<Article>>

    fun updateKnowledgeData(id: Int?, data: MutableLiveData<List<KnowledgeChildren>>)
}