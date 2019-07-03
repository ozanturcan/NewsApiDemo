package com.boyner.news.network.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticlesService(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "status") val status: String,
        @ColumnInfo(name = "totalResults") val totalResults: Int,
        @ColumnInfo(name = "articles") val articles: List<Article>
)