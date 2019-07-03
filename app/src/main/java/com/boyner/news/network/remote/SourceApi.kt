package com.boyner.news.network.remote

import com.boyner.news.network.local.entity.SourceService
import io.reactivex.Observable
import retrofit2.http.GET

interface SourceApi {
    @GET("sources")
    fun getSourceService(): Observable<SourceService>
}


