package com.example.wan_android.ui.model

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class KnowledgeEntity(
    var errorCode: Int,
    var errorMsg: String?,
    var data: List<KnowledgeData>?
)

@Entity(tableName = "knowledge")
@TypeConverters(KnowledgeChildrenConverters::class)
data class KnowledgeData(
    @PrimaryKey
    var id: Int,
    var name: String,
    var courseId: Int,
    var parentChapterId: Int,
    var order: Int,
    var visible: Int,
    @ColumnInfo(name = "children")
    var children: List<KnowledgeChildren>?
) {
    fun getAllChildrenName(): String {
        val stringBuilder = StringBuilder()
        children?.forEach { children ->
            stringBuilder.append(children.name).append(" ")
        }
        return if (stringBuilder.isNotEmpty()) stringBuilder.substring(
            0,
            stringBuilder.length - 1
        ) else stringBuilder.toString()
    }
}

class KnowledgeChildrenConverters {

    @TypeConverter
    fun stringToObject(value: String): List<KnowledgeChildren> {
        val listType = object : TypeToken<List<KnowledgeChildren>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun objectToString(list: List<KnowledgeChildren>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

data class KnowledgeChildren(
    var id: Int,
    var name: String,
    var courseId: Int,
    var parentChapterId: Int,
    var order: Int,
    var visible: Int
)

