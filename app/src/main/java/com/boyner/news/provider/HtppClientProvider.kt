package com.boyner.news.provider

import com.boyner.news.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request

fun clientBuilderProvider(): OkHttpClient.Builder {
    val httpClient = OkHttpClient.Builder()
    val headerAuthorizationInterceptor: Interceptor

    headerAuthorizationInterceptor = Interceptor { chain ->
        var request: Request = chain.request()
        val headers = request.headers().newBuilder()
                .add("X-Api-Key", BuildConfig.API_KEY)
                .add("Authorization", BuildConfig.API_KEY).build()
        request = request.newBuilder().headers(headers).build()
        chain.proceed(request)
    }
    httpClient.addInterceptor(headerAuthorizationInterceptor)
    return httpClient
}