package com.example.wan_android.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "banner")
data class BannerItem(
    @PrimaryKey
    val id: Int,
    val url: String,
    val imagePath: String,
    val title: String,
    val desc: String,
    val isVisible: Int,
    val order: Int,
    @SerializedName("type")
    val mType: Int
)