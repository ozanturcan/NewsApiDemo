package com.boyner.news.view.articlelist

import com.boyner.news.network.local.entity.Article

data class ArticleList(val articleList: List<Article>, val message: String, val error: Throwable? = null)
