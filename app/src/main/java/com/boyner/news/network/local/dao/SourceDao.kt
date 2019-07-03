package com.boyner.news.network.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.boyner.news.network.local.entity.Source
import io.reactivex.Single

@Dao
interface SourceDao {

    @Query("SELECT * FROM sources")
    fun getSourceService(): Single<List<Source>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sourceService: List<Source>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sourceService: List<Source>)
}