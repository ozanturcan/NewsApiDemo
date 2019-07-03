package com.boyner.news.viewmodel

import com.boyner.news.network.ArticlesRepository
import com.boyner.news.view.articlelist.ArticleList
import io.reactivex.Observable
import timber.log.Timber

class ArticleListViewModel(private val articlesRepository: ArticlesRepository) {

    fun getArticleList(page: Int, source: String): Observable<ArticleList> {
        //Create the data for your UI, the newsList lists and maybe some additional data needed as well
        return articlesRepository.getArticleService(page, source)
                .map {
                    Timber.d("Mapping newsList to UIData...")
                    ArticleList(it, "Top ${it.size} Article")
                }
                .onErrorReturn {
                    Timber.d("An error occurred $it")
                    ArticleList(emptyList(), "An error occurred", it)
                }
    }
}