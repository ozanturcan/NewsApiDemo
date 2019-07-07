package com.boyner.news.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.boyner.news.network.local.dao.ArticlesDao
import com.boyner.news.network.local.dao.SourceDao
import com.boyner.news.network.local.entity.Article
import com.boyner.news.network.local.entity.ArticlesService
import com.boyner.news.network.local.entity.Source
import com.boyner.news.network.local.entity.SourceService


@Database(entities = [SourceService::class, Source::class, ArticlesService::class, Article::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sourceDao(): SourceDao
    abstract fun articleDao(): ArticlesDao
}