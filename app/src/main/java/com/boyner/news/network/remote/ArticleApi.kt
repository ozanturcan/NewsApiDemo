package com.boyner.news.network.remote

import com.boyner.news.network.local.entity.ArticlesService
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ArticleApi {
    @GET("everything")
    fun getArticleService(
            @Query("page") page: Int,
            @Query("sources") sources: String,
            @Query("pageSize") pageSize: String = "50"
    ): Observable<ArticlesService>

}


