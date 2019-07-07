package com.boyner.news.network.local.dao

import androidx.room.*
import com.boyner.news.network.local.entity.Article
import io.reactivex.Single

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM article WHERE source LIKE :source")
    fun getArticlesService(source: String): Single<List<Article>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(sourceService: List<Article>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sourceService: List<Article>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateArticle(article: Article)

}