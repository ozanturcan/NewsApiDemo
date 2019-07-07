package com.boyner.news.network.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

@Entity(tableName = "article")
data class Article(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "source") val source: Source,
        @ColumnInfo(name = "author") val author: String?,
        @ColumnInfo(name = "title") val title: String?,
        @ColumnInfo(name = "description") val description: String?,
        @ColumnInfo(name = "url") val url: String?,
        @ColumnInfo(name = "urlToImage") val urlToImage: String?,
        @ColumnInfo(name = "publishedAt") val publishedAt: String?,
        @ColumnInfo(name = "content") val content: String?,
        @ColumnInfo(name = "isInReadList") var isInReadList: Boolean
)