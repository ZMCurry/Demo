package com.example.wan_android.ui.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wan_android.ui.model.*

@Dao
interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHomeItems(posts: List<HomeItem>)

    @Query("SELECT * FROM home_items ORDER BY indexInDb ASC")
    fun queryHomeItems(): DataSource.Factory<Int, HomeItem>

    @Query("SELECT MAX(indexInDb) + 1 FROM home_items ")
    fun findNextIndex(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBannerItems(items: List<BannerItem>)

    @Query("DELETE FROM banner")
    fun deleteAllBanner()

    @Query("SELECT * FROM banner")
    fun getAllBanners(): MutableList<BannerItem>

    @Query("SELECT * FROM articles WHERE chapterId =:cid ORDER BY indexInDb ASC")
    fun queryArticleList(cid: Int): DataSource.Factory<Int, Article>

    @Query("SELECT MAX(indexInDb) + 1 FROM articles WHERE chapterId = :cid")
    fun findArticleNextIndex(cid: Int): Int

    @Query("DELETE FROM knowledge")
    fun deleteAllKnowledgeData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKnowledgeItems(items: List<KnowledgeData>)

    @Query("SELECT * FROM knowledge")
    fun findAllKnowledgeItems(): List<KnowledgeData>?

    @Query("SELECT * FROM knowledge WHERE id = :id LIMIT 1")
    fun findAllKnowledgeChildren(id: Int?): KnowledgeData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(posts: List<Article>)
}