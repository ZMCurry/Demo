package com.example.wan_android.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wan_android.db.WanDb
import com.example.wan_android.ui.model.KnowledgeData
import com.example.wan_android.ui.repository.KnowledgeRepository
import com.example.wan_android.ui.repository.KnowledgeRepositoryImpl

class KnowledgeViewModel : ViewModel() {
    private val repo: KnowledgeRepository by lazy {
        KnowledgeRepositoryImpl(WanDb.create())
    }

    val knowledgeList: MutableLiveData<List<KnowledgeData>> by lazy {
        MutableLiveData<List<KnowledgeData>>().also {
            repo.updateKnowledge(it)
        }
    }

    fun refreshData() {
        repo.updateKnowledge(knowledgeList)
    }

    val stateBundleData by lazy {
        MutableLiveData<Bundle>().also {
            it.value = Bundle()
        }
    }
}
