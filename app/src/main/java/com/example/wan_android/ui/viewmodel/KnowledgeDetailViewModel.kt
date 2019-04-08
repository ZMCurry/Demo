package com.example.wan_android.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wan_android.db.WanDb
import com.example.wan_android.ui.model.KnowledgeChildren
import com.example.wan_android.ui.repository.KnowledgeRepository
import com.example.wan_android.ui.repository.KnowledgeRepositoryImpl

class KnowledgeDetailViewModel : ViewModel() {
    var id: Int? = null

    private val repo: KnowledgeRepository by lazy {
        KnowledgeRepositoryImpl(WanDb.create())
    }

    val dataSource by lazy {
        MutableLiveData<List<KnowledgeChildren>>().also {
            repo.updateKnowledgeData(id, it)
        }
    }

}
