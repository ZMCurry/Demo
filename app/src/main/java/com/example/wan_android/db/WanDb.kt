package com.example.wan_android.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wan_android.base.App
import com.example.wan_android.ui.db.HomeDao
import com.example.wan_android.ui.model.Article
import com.example.wan_android.ui.model.BannerItem
import com.example.wan_android.ui.model.HomeItem
import com.example.wan_android.ui.model.KnowledgeData

@Database(
    entities = [HomeItem::class, BannerItem::class, Article::class,
        KnowledgeData::class],
    version = 1,
    exportSchema = false
)
abstract class WanDb : RoomDatabase() {
    companion object {
        @Volatile
        private var db: WanDb? = null

        @Synchronized
        fun create(): WanDb {
            if (db == null) {
                val databaseBuilder = Room.databaseBuilder(App.mContext, WanDb::class.java, "wan_android.db")
                db = databaseBuilder
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return db!!
        }
    }

    abstract fun posts(): HomeDao
}