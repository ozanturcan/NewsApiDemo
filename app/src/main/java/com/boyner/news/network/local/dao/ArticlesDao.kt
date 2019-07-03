package com.boyner.news.network.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.boyner.news.network.local.entity.Article
import io.reactivex.Single

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM article WHERE source LIKE :source")
    fun getArticlesService(source: String): Single<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sourceService: List<Article>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sourceService: List<Article>)
}