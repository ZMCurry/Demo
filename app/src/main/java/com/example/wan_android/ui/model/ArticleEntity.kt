package com.example.wan_android.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ArticleEntity(
    var offset: Int,
    var size: Int,
    var total: Int,
    var pageCount: Int,
    var curPage: Int,
    var over: Boolean,
    @SerializedName("datas")
    var entity: MutableList<Article>?
)

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey
    val id: Int,
    val originId: Int,
    val title: String,
    val chapterId: Int,
    val chapterName: String?,
    val envelopePic: String,
    val link: String,
    val author: String,
    val origin: String,
    val publishTime: Long,
    val zan: String,
    val desc: String,
    val visible: Int,
    val niceDate: String,
    val courseId: Int,
    var collect: Boolean,
    var indexInDb: Int = -1,
    var pageIndex: Int
)

