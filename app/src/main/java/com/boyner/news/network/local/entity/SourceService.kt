package com.boyner.news.network.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sourceList")
data class SourceService(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "status") val status: String,
        @ColumnInfo(name = "sources") val sources: List<Source>
)